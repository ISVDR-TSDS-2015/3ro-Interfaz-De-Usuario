package com.javierpintosettlin.miprimeraapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MyActivity extends ActionBarActivity {
    public final static String EXTRA_MESSAGE = "com.javierpintosettlin.miprimeraapp.MENSAJE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        /* TODO: El codigo que quieran que se cargue al
        Inicio de la Activity */


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        switch (id) {
            case R.id.action_settings:
                // El código que corresponda ....

                return true;

            case R.id.action_contacto:
                EditText txt_mensaje = (EditText) findViewById(R.id.txt_mensaje);
                txt_mensaje.setText("Tel: 03573-424242");

                return true;


        }

        return super.onOptionsItemSelected(item);
    }

    public void enviarMensaje(View view) {
        //Algun código del button

        //Creamos el Intent
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        //Obtener el mensaje de la EditText
        EditText txtMensaje = (EditText) findViewById(R.id.txt_mensaje);
        String mensaje = txtMensaje.getText().toString();

        //Enviar el mensaje a la siguiente Activity
        intent.putExtra(EXTRA_MESSAGE, mensaje);
        intent.putExtra("OTRA_COSA", "OTRA COSA");
        startActivity(intent);
    }

    public void irGuardarDatos(View view) {
        //Creamos el Intent
        Intent intent = new Intent(this, GuardarDatos.class);

        //Ir a Guardar Datos
        startActivity(intent);
    }

}
