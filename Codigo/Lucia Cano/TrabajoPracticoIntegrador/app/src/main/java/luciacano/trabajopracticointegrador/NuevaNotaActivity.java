package luciacano.trabajopracticointegrador;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class NuevaNotaActivity extends ActionBarActivity {

    private EditText TxtTitulo;
    private EditText TxtNota;
    private Button BtnGuardar;

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

     public void BtnGuardar(View view) {
         //Algun código del button

         //Creamos el Intent
         Intent intent = new Intent(NuevaNotaActivity.this, VerNotasActivity.class);

         //Obtener el mensaje de la EditText
         TxtTitulo = (EditText) findViewById(R.id.TxtTitulo);
         TxtNota = (EditText) findViewById(R.id.TxtNota);
         BtnGuardar = (Button) findViewById(R.id.BtnGuardar);

         //Creamos la información a pasar entre actividades
         Bundle b = new Bundle();
         b.putString("Titulo", TxtTitulo.getText().toString());
         b.putString("Nota", TxtNota.getText().toString());

         //Añadimos la información al intent
         intent.putExtras(b);
     }

    public void GuardarNota(View view){


        ManejadorBaseDatos db = new ManejadorBaseDatos(this);

/**
 * CRUD Operations
 * */

// Reading all Notas
        Log.d("Leyendo: ", "Leyendo todas las Notas..");
        List<Nota> NotaList = db.getAllNotas();

        for (Nota Nota : NotaList ) {
            String log = "Id: " + Nota.getId() + " ,Titulo: " + Nota.getTitulo() + " ,Nota: " + Nota.getNota() +
                    " ,Fecha: " + Nota.getFecha().toString();

// Writing Personas to log
            Log.d("Name: ", log);

//Muestro en la textView
            TextView lblMostrarNota = (TextView) findViewById(R.id.lblMostrarNota);
            lblMostrarNota.setText(lblMostrarNota.getText()+log.toString()+ "\n");
        }
    }




}
