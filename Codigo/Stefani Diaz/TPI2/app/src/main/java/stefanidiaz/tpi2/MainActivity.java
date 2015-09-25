package stefanidiaz.tpi2;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends ActionBarActivity {


    //�C A �F
    private static String SOAP_ACTION1 = "http://www.w3schools.com/webservices/CelsiusToFahrenheit";
    private static String NAMESPACE1 = "http://www.w3schools.com/webservices/";
    private static String METHOD_NAME1 = "CelsiusToFahrenheit";
    private static String URL1 = "http://www.w3schools.com/webservices/tempconvert.asmx?op=CelsiusToFahrenheit";

    //�F A �C
    private static String SOAP_ACTION2 = "http://www.w3schools.com/webservices/FahrenheitToCelsius";
    private static String NAMESPACE2 = "http://www.w3schools.com/webservices/";
    private static String METHOD_NAME2 = "FahrenheitToCelsius";
    private static String URL2 = "http://www.w3schools.com/webservices/tempconvert.asmx?op=FahrenheitToCelsius";

    private RadioButton RBC,RBF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // no tiene q llamar a ninguna funcion cuando se crea
        // tiene q hacerlo en el boton con un if
        RBC=(RadioButton)findViewById(R.id.RBC);
        RBF=(RadioButton)findViewById(R.id.RBF);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //starting asynchronus task
    private class ConvCaF extends AsyncTask<String, Void, String> {



        @Override
        protected void onPreExecute() {
            //si quieren abrir un Dialog informando el progreso, va aqui
            TextView txtResultado = (TextView)findViewById(R.id.TxtResultado);
            txtResultado.setText("Calculando...");

        }

        @Override
        protected String doInBackground(String... urls) {

            EditText txtConvertir= (EditText) findViewById(R.id.TxtConvertir);

            //Inicializo soap request
            SoapObject request = new SoapObject(NAMESPACE1, METHOD_NAME1);

            //agrego parametros (el nombre del parametro y el valor que vamos a filtrar)
            request.addProperty("Celsius", txtConvertir.getText().toString());

            //Declaro la version del SOAP request
            SoapSerializationEnvelope Sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            Sobre.setOutputSoapObject(request);
            Sobre.dotNet = true;

            //String resultado_string = "";


            try {
               HttpTransportSE androidHttpTransport = new HttpTransportSE(URL1);



                //Se llama al webservice

                androidHttpTransport.call(SOAP_ACTION1, Sobre);

                SoapPrimitive response = (SoapPrimitive) Sobre.getResponse();

              Log.i("Response", response.toString());

               String resultado = response.toString();




               return resultado + " °F";

            } catch (Exception e) {
                return e.getMessage();
           }
        }

        @Override
        protected void onPostExecute(String result) {
            //este es el codigo que se corre cunado se termina de ejecutar el metodo anterior
            TextView txtWebServices = (TextView)findViewById(R.id.TxtResultado);
            txtWebServices.setText(result);
        }
    }

    //starting asynchronus task
     private class ConvFaC extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            //si quieren abrir un Dialog informando el progreso, va aqui
            TextView txtResultado = (TextView)findViewById(R.id.TxtResultado);
            txtResultado.setText("Calculando...");

        }

        @Override
        protected String doInBackground(String... urls) {

            EditText txtConvertir= (EditText) findViewById(R.id.TxtConvertir);

            //Inicializo soap request
            SoapObject request = new SoapObject(NAMESPACE2, METHOD_NAME2);

            //agrego parametros (el nombre del parametro y el valor que vamos a filtrar)
            request.addProperty("Fahrenheit", txtConvertir.getText().toString());

            //Declaro la version del SOAP request
            SoapSerializationEnvelope Sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            Sobre.setOutputSoapObject(request);
            Sobre.dotNet = true;

            //String resultado_string = "";


            try {
                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL2);


                //Se llama al webservice

                androidHttpTransport.call(SOAP_ACTION2, Sobre);

                SoapPrimitive response = (SoapPrimitive) Sobre.getResponse();

                Log.i("Response", response.toString());

                String resultado = response.toString();




                return resultado+ " °C";

            } catch (Exception e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //este es el codigo que se corre cunado se termina de ejecutar el metodo anterior
            TextView txtWebServices = (TextView)findViewById(R.id.TxtResultado);
            txtWebServices.setText(result);
        }
    }



    public void LlamarFuncion (View view){
        TextView txtConvertir = (TextView)findViewById(R.id.TxtConvertir);
        if (txtConvertir.length() == 0)
        {
            Toast toast = Toast.makeText(this, "Inserte un numero para convertir", Toast.LENGTH_LONG);
            toast.show();
        }
            else {


            if (RBC.isChecked() == true) {
                //se crea el objeto
                ConvFaC task = new ConvFaC();

                //se le podria pasar los parametros de la AsyncTask
                task.execute();
            } else if (RBF.isChecked() == true) {
                //se crea el objeto
                ConvCaF task = new ConvCaF();
                //se le podria pasar los parametros de la AsyncTask
                task.execute();
            }
        }

    }

}
