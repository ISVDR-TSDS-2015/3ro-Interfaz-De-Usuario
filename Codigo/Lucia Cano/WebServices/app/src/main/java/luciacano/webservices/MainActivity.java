package luciacano.webservices;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private static String SOAP_ACTION = "http://tempuri.org/ListaKioscosPorNombre";
    private static String NAMESPACE = "http://tempuri.org/";
    private static String METHOD_NAME = "ListaKioscosPorNombre";
    private static String URL = "http://192.168.2.13/MyKiosco/WSMyKiosco.asmx?op=ListaKioscosPorNombre";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TareaAccesoSOAP task = new TareaAccesoSOAP();
//se le podria pasar los parametros de la AsyncTask
        task.execute();

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
    private class TareaAccesoSOAP extends AsyncTask<String, Void, String> {

// lo que el usuario ve antes que se ejecute
        @Override
        protected void onPreExecute() {
    //si quieren abrir un Dialog informando el progreso, va aqui
        }

//lo que se ve cuando se ejecuta el codigo
        @Override
        protected String doInBackground(String... urls) {

            //Inicializo soap request
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

//agrego parametros y el valor por el que se va a filtrar por ese parametro
            request.addProperty("nombre", "io");

//Declaro la version del SOAP request, la version que esta en el web services
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            String resultado_string = "";

            try {
                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

//Se llama al webservice, se le pasa el soap y el sobre
                androidHttpTransport.call(SOAP_ACTION, envelope);

                SoapObject resultado_object =(SoapObject)envelope.getResponse();
                Log.d("Response", resultado_object.toString());
                resultado_string = resultado_object.toString();


                List<Kiosco> listKiosco = RecibirDeSOAP(resultado_object);
                for(int i=0; i < listKiosco.size(); i++)
                {
                    Log.d("Kiosco", listKiosco.get(i).toString());
                }

//mostrarListaEnListView(listKiosco);

                Thread.sleep(4000);//la ejecucion se frena 4 segundos
                return resultado_string;

            } catch (Exception e) {
                return e.getMessage();
            }


        }

//codigo que se ejecuta despues que termina de correr el InBackground
        @Override
        protected void onPostExecute(String result) {

            TextView txtWebServices= (TextView) findViewById(R.id.txtWebServices);
            txtWebServices.setText(result);

        }
    }

    public class Ciudad {
        public int _id;
        public String _nombre;
    }

    public class Kiosco {
        public int _id;
        public String _usuario;
        public String _password;
        public String _nombre;
        public boolean _activo;
        public String _email;
        public Ciudad _ciudad;

        public String toString(){
            return _usuario + " - " + _nombre + " - " + _email + " - " + _ciudad._nombre;
        }
    }
//metodo que convierte el objeto soap en una lista de kioscos
    public List<Kiosco> RecibirDeSOAP(SoapObject soap)
    {
        List<Kiosco> listKiosco = new ArrayList<Kiosco>();

        for (int i=0; i < soap.getPropertyCount(); i++)//i++=i+1
        {
            //obtengo el objeto soap de kiosco que esta dentro del otro objeto soap
            SoapObject kioscoSOAP = (SoapObject)soap.getProperty(i);
            Kiosco kiosco = new Kiosco();

            kiosco._id=Integer.parseInt(kioscoSOAP.getProperty(0).toString());
            kiosco._usuario=kioscoSOAP.getProperty(1).toString();
            kiosco._password=kioscoSOAP.getProperty(2).toString();
            kiosco._nombre=kioscoSOAP.getProperty(3).toString();
            kiosco._activo=Boolean.parseBoolean(kioscoSOAP.getProperty(4).toString());
            kiosco._email=kioscoSOAP.getProperty(5).toString();
// la sexta propiedad del objeto soap kiosco la convertimos en otro objeto soap, ciudad soap que esta dentro de kiosco soap
            SoapObject ciudadSOAP = (SoapObject)kioscoSOAP.getProperty(6);
            Ciudad ciudad = new Ciudad();
            ciudad._id=Integer.parseInt(ciudadSOAP.getProperty(0).toString());
            ciudad._nombre=ciudadSOAP.getProperty(1).toString();

            kiosco._ciudad=ciudad;

            listKiosco.add(kiosco);
        }

        return listKiosco;
    }
}

