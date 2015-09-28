package luciacano.conversortemperaturas;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.BreakIterator;
import java.util.List;

public class ConversorActivity extends ActionBarActivity {
    //CelsiusToFahrenheit
    private static String SOAP_ACTIONCF = "http://www.w3schools.com/webservices/CelsiusToFahrenheit";
    private static String NAMESPACECF = "http://www.w3schools.com/webservices/";
    private static String METHOD_NAMECF = "CelsiusToFahrenheit";
    private static String URLCF = "http://www.w3schools.com/webservices/tempconvert.asmx?op=CelsiusToFahrenheit";

    //FahrenheitToCelsius
    private static String SOAP_ACTION = "http://www.w3schools.com/webservices/FahrenheitToCelsius";
    private static String NAMESPACE = "http://www.w3schools.com/webservices/";
    private static String METHOD_NAME = "FahrenheitToCelsius";
    private static String URL = "http://www.w3schools.com/webservices/tempconvert.asmx?op=FahrenheitToCelsius";

    private EditText Valor;
    private TextView Resultado;
    private RadioButton radioC,radioF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor);

        Valor=(EditText)findViewById(R.id.Valor);
        Resultado=(TextView)findViewById(R.id.Resultado);
        radioC=(RadioButton)findViewById(R.id.radioC);
        radioF=(RadioButton)findViewById(R.id.radioF);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_conversor, menu);
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


    private class TareaAccesoSOAPCF extends AsyncTask<String, Void, String> {

        // lo que el usuario ve antes que se ejecute
        @Override
        protected void onPreExecute() {

            Resultado.setText("Calculando...");
            //si quieren abrir un Dialog informando el progreso, va aqui
            //TareaAccesoSOAP task = new TareaAccesoSOAP();
//se le podria pasar los parametros de la AsyncTask
            //task.execute();
        }

        //lo que se ve cuando se ejecuta el codigo
        @Override
        protected String doInBackground(String... urls) {

            //Inicializo soap request
            SoapObject request = new SoapObject(NAMESPACECF, METHOD_NAMECF);

//agrego parametros y el valor por el que se va a filtrar por ese parametro
            request.addProperty("Celsius", Valor.getText().toString());

//Declaro la version del SOAP request, la version que esta en el web services
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            String resultado_string = "";

            try {
                HttpTransportSE androidHttpTransport = new HttpTransportSE(URLCF);

//Se llama al webservice, se le pasa el soap y el sobre
                //androidHttpTransport.call(SOAP_ACTIONCF, envelope);

                //SoapObject resultado_object =(SoapObject)envelope.getResponse();
                //Log.d("Response", resultado_object.toString());
                //resultado_string = resultado_object.toString();

                //Invocando al web service
                androidHttpTransport.call(SOAP_ACTIONCF, envelope);
//Obtener Response
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
//Se obtiene el resultado en una variable String
                String resultado = response.toString();


                return resultado + " °F";

            } catch (Exception e) {
                return e.getMessage();
            }


        }

        //codigo que se ejecuta despues que termina de correr el InBackground
        @Override
        protected void onPostExecute(String result) {

            TextView txtWebServices= (TextView) findViewById(R.id.Resultado);
            txtWebServices.setText(result);

        }
    }

    private class TareaAccesoSOAP extends AsyncTask<String, Void, String> {

        // lo que el usuario ve antes que se ejecute
        @Override
        protected void onPreExecute() {

            Resultado.setText("Calculando");
            //si quieren abrir un Dialog informando el progreso, va aqui
            //TareaAccesoSOAP task = new TareaAccesoSOAP();
//se le podria pasar los parametros de la AsyncTask
            //task.execute();
        }

        //lo que se ve cuando se ejecuta el codigo
        @Override
        protected String doInBackground(String... urls) {

            //Inicializo soap request
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

//agrego parametros y el valor por el que se va a filtrar por ese parametro
            request.addProperty("Fahrenheit", Valor.getText().toString());

//Declaro la version del SOAP request, la version que esta en el web services
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            String resultado_string = "";

            try {
                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

//Se llama al webservice, se le pasa el soap y el sobre
                //androidHttpTransport.call(SOAP_ACTION, envelope);

                //SoapObject resultado_object =(SoapObject)envelope.getResponse();
                //Log.d("Response", resultado_object.toString());
                //resultado_string = resultado_object.toString();

                //Invocando al web service
                androidHttpTransport.call(SOAP_ACTION, envelope);
//Obtener Response
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
//Se obtiene el resultado en una variable String
                String resultado = response.toString();


                return resultado + " °C";

            } catch (Exception e) {
                return e.getMessage();
            }


        }

        //codigo que se ejecuta despues que termina de correr el InBackground
        @Override
        protected void onPostExecute(String result) {

            TextView txtWebServices= (TextView) findViewById(R.id.Resultado);
            txtWebServices.setText(result);

        }
    }


    public void Convertir(View view) {
        //String valor= Valor.getText().toString();


        if (radioC.isChecked()==true) {

            //Resultado.findViewById(R.id.Resultado);
            TareaAccesoSOAPCF task= new TareaAccesoSOAPCF();
            task.execute();



        } else
        if (radioF.isChecked()==true) {

            TareaAccesoSOAP task=new TareaAccesoSOAP();
            task.execute();


        }

        }
}
