package com.javierpintosettlin.miprimeraapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;


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

    public void GuardarArchivoInterno(View view) {
        String NombreArchivo = "MiArchivo.txt";
        String string = "Hola Mundo!!!";
        FileOutputStream outputStream;

        File archivo = new File(this.getFilesDir(), NombreArchivo);

        try {
            outputStream = openFileOutput(NombreArchivo, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void MostrarArchivoInterno(View view) {
        String

    }
}
