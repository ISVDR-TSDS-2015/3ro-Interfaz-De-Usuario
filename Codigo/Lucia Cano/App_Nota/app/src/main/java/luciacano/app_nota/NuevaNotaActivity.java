package luciacano.app_nota;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NuevaNotaActivity extends ActionBarActivity {

    public final static String MESSAGE1 = "luciacano.appnota.MENSAJE";
    public final static String MESSAGE2 = "luciacano.appnota.MENSAJE";
    public final static String MESSAGE3 = "luciacano.appnota.MENSAJE";

    //EditText Titulo, Nota; //Variables para las cajas de texto.
    //String[] info; // Para almacenar el nombre y la edad.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_nota);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nueva_nota, menu);
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
    public void Guardar(View View) {

        //Titulo=(EditText)findViewById(R.id.txt_Titulo);

        //Nota=(EditText)findViewById(R.id.txt_Nota);

        //info=new String[2];

        //info[0] = Titulo.getText().toString();
        //info[1] = Nota.getText().toString();

        EditText txt_Titulo= (EditText) findViewById(R.id.txt_Titulo);
        EditText txt_Nota= (EditText) findViewById(R.id.txt_Nota);

        String Mensaje1 = txt_Titulo.getText().toString();


        String Mensaje2 = txt_Nota.getText().toString();

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        String Fecha = sdf.format(new Date());



        ManejadorBaseDatos db = new ManejadorBaseDatos(this);

        Log.d("Insert: ", "Inserting .." + Mensaje1 + " " + Mensaje2 + " " + Fecha);

        db.addNota(new Nota(Mensaje1, Mensaje2, Fecha));


        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Informe");
        alertDialog.setMessage("Nota Guardada");
        alertDialog.show();



    }

}
