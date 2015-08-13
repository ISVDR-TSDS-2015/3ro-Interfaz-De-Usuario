package diaz.stefani.appnotas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;


public class Notas_VerTodas extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_notas__ver_todas);



//no se si anda... probar mostrarlo en el lugar donde se guarda


        DataBaseManejador db = new DataBaseManejador(this);
        //ver si funciona esto

        Log.d("Reading: ", "Reading all contacts..");
        List<Notas> personaList = db.getAllPersonas();

        for (Notas notas : personaList) {
            String log = "Titulo: " + notas.getTitulo() + " ,Nota: " + notas.getNota() + " ,Fecha: " + notas.getFechaCreacion();

            // Writing Personas to log
            Log.d("Name: ", log);

            //Muestro en la textView
            TextView lblMostrar = (TextView) findViewById(R.id.lblMostrar);
            lblMostrar.setText(lblMostrar.getText() + log.toString() + "\n");


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity__notas__ver_todas, menu);
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
    public void verNotas (View view){


        DataBaseManejador db = new DataBaseManejador(this);
        //ver si funciona esto

        Log.d("Reading: ", "Reading all contacts..");
        List<Notas> personaList = db.getAllPersonas();

        for (Notas notas : personaList) {
            String log = "Titulo: " + notas.getTitulo() + " ,Nota: " + notas.getNota() + " ,Fecha: " + notas.getFechaCreacion();

            // Writing Personas to log
            Log.d("Name: ", log);

            //Muestro en la textView
            TextView lblMostrar = (TextView) findViewById(R.id.lblMostrar);
            lblMostrar.setText(lblMostrar.getText() + log.toString() + "\n");


        }
    }
    public void BorrarTodasLasNotas (View view){
        DataBaseManejador db = new DataBaseManejador(this);
        db.BorrarTodasLasNotas();
    }


}
