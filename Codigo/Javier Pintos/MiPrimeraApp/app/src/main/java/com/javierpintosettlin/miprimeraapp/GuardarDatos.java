package com.javierpintosettlin.miprimeraapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.List;


public class GuardarDatos extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_datos);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guardar_datos, menu);
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

    public void BtnGuardarDatos(View view) {
        SharedPreferences sharedPref = this.getSharedPreferences("DatosGuardados", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("UnNumero", 123456);
        editor.putString("UnString", "Hola Mundo");
        editor.commit();
    }

    public void mostrarDatosAlmacenados(View view) {

        //Obtener el mensaje de la EditText
        TextView txtMensaje = (TextView) findViewById(R.id.lblMostrarTexto);

        // recupero los datos
        SharedPreferences sharedPref = this.getSharedPreferences("DatosGuardados", Context.MODE_PRIVATE);
        int numero = sharedPref.getInt("UnNumero", 0);
        String texto = sharedPref.getString("UnString", "");

        // escribo en la text view
        txtMensaje.setText(Integer.toString(numero) + " - " + texto);
    }

    public void guardarArchivoInterno(View view) {
        String nombreArchivo = "MiArchivo.txt";
        String string = "Hola Mundo!!!!";
        FileOutputStream outputStream;

        File archivo = new File(this.getFilesDir(), nombreArchivo);

        try {

            outputStream = openFileOutput(nombreArchivo, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarArchivoInterno(View view) {
        String filename = "MiArchivo.txt";
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            FileInputStream fis = this.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            //Muestro en la textView
            TextView lblMostrarArchivo = (TextView) findViewById(R.id.lblMostrarArchivoInterno);
            lblMostrarArchivo.setText(sb.toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void guardarSQLite(View view){

        DataBaseManejador db = new DataBaseManejador(this);

        /**
         * CRUD Operations
         * */

         // Insertando personas
        Log.d("Insert: ", "Inserting ..");

        db.addPersona(new Persona("Javier", "1234567890"));
        db.addPersona(new Persona("Heliana", "0987654321"));
        db.addPersona(new Persona("Penelope", "666"));

        // Reading all Personas
        Log.d("Reading: ", "Reading all contacts..");
        List<Persona> personaList = db.getAllPersonas();

        for (Persona per : personaList) {
            String log = "Id: " + per.get_id() + " ,Nombre: " + per.get_nombre() + " ,DNI: " + per.get_dni();

            // Writing Personas to log
            Log.d("Name: ", log);

            //Muestro en la textView
            TextView lblMostrarSQLite = (TextView) findViewById(R.id.lblMostrarSQLite);
            lblMostrarSQLite.setText(log.toString());
        }
    }
}
