package diaz.stefani.appnotas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class NuevaNota extends ActionBarActivity {

    public final static String MESSAGE1 = "diaz.stefani.appnotas.MENSAJE";
    public final static String MESSAGE2 = "diaz.stefani.appnotas.MENSAJE";
    public final static String MESSAGE3 = "diaz.stefani.appnotas.MENSAJE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_nota);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity__nueva_nota, menu);
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

    public void GuardarNota (View view) {


        EditText txt_titulo= (EditText) findViewById(R.id.txt_titulo);
        EditText txt_nota= (EditText) findViewById(R.id.txt_Nota);

        String mensaje1 = txt_titulo.getText().toString();


        String mensaje2 = txt_nota.getText().toString();



        //long ahora = System.currentTimeMillis();
        //Date fechaa = new Date(ahora);
        //String fecha = fechaa.toString();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaActual = new Date();
        String fecha = sdf.format(fechaActual);


        DataBaseManejador db = new DataBaseManejador(this);

        Log.d("Insert: ", "Inserting .." + mensaje1 +" " +mensaje2+" "+ fecha);

        db.addNota(new Notas(mensaje1, mensaje2, fecha));



        Toast.makeText(this, "La nota se guardo correctamente", Toast.LENGTH_LONG).show();



        //COMO MOSTRAR UN MENSAJE
        //AlertDialog alertDialog;
        //alertDialog = new AlertDialog.Builder(this).create();
        //alertDialog.setTitle("Informe");
        //alertDialog.setMessage("YA SE GUARDOO");
        //alertDialog.show();
    }

}
