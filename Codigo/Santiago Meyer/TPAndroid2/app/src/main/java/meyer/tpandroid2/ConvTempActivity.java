package meyer.tpandroid2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class ConvTempActivity extends AppCompatActivity {

    private static String SOAP_ACTION;
    private static String NAMESPACE = "http://www.w3schools.com/webservices/";
    private static String METHOD_NAME;
    private static String URL = "http://www.w3schools.com/webservices/tempconvert.asmx";
    private static String gradosIngresados;
    private static String resultado;
    private static String tip;
    private static String tag;
    Button btn;
    TextView GradosCon;
    EditText grados;
    TextView av;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conv_temp);

        //Celcius Edit Control
        grados = (EditText) findViewById(R.id.grados);
        //Fahrenheit Text control
        GradosCon = (TextView) findViewById(R.id.txtGradosCon);
        //Button to trigger web service invocation
        btn = (Button) findViewById(R.id.btn1);
        av = (TextView) findViewById(R.id.textView2);
        //Button Click Listener
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Check if Celcius text control is not empty
                if (grados.getText().length() != 0 && grados.getText().toString() != "" ) {
                    //Get the text control value
                    gradosIngresados = grados.getText().toString();
                    //Create instance for AsyncCallWS
                    TareaAccesoSOAP task = new TareaAccesoSOAP();
                    //Call execute
                    task.execute();
                    //If text control is empty
                } else {
                    GradosCon.setText("ERROR");
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_conv_temp, menu);
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.Celcius:
                if (checked)
                    tip= "celsius";
                tag="Celsius";

                av.setText("° F");
                METHOD_NAME = "CelsiusToFahrenheit";
                SOAP_ACTION = "http://www.w3schools.com/webservices/CelsiusToFahrenheit";
                break;
            case R.id.Fahrenheit:
                if (checked)
                    av.setText("° C");
                    tip="fahrenheit";
                tag="Fahrenheit";
                METHOD_NAME = "FahrenheitToCelsius";
                SOAP_ACTION = "http://www.w3schools.com/webservices/FahrenheitToCelsius";
                break;
        }
    }

    //starting asynchronus task
    private class TareaAccesoSOAP extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            GradosCon.setText("Calculando...");
        }

        @Override
        protected String doInBackground(String... urls) {

            try {
                traerDatos(gradosIngresados);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            GradosCon.setText(resultado);

        }






        public void traerDatos(String tip) throws IOException, XmlPullParserException {
            //Inicializo soap request
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            PropertyInfo propertyInfo = new PropertyInfo();
            //Set Name
            propertyInfo.setName(tag);
            //Set Value
            propertyInfo.setValue(tip);
            //Set dataType
            propertyInfo.setType(double.class);

            //agrego parametros
            request.addProperty(propertyInfo);

            //Declaro la version del SOAP request
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;


            try {
                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                //Invole web service
                androidHttpTransport.call(SOAP_ACTION, envelope);
                //Get the response
                SoapPrimitive response =(SoapPrimitive)envelope.getResponse();
                //Assign it
                resultado = response.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }



}
}
