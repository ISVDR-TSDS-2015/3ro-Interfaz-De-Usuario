package com.javierpintosettlin.miprimeraapp;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class ListView2Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);

        // Obtengo la ListView
        final ListView listView = (ListView) findViewById(R.id.listView2);

        DataBaseManejador db = new DataBaseManejador(this);

        Cursor cursorAllPersonas = db.getCursorAllPersonas();

        // Columnas de la Base de Datos
        String[] columnas = new String[] { "nombre", "dni" };

        // campos del Layout
        int[] escribirEn = new int[] { R.id.list_item_nombre, R.id.list_item_dni };

        // Se genera el Adaptador del Cursor
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.persona_list_item_1,
                cursorAllPersonas,
                columnas,
                escribirEn);

        // seteamos el adapter a la listView
        listView.setAdapter(adapter);


        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Show Alert
                Toast.makeText(getApplicationContext(), "Posición:" + position + "  ID: " + id,
                        Toast.LENGTH_LONG).show();

            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view2, menu);
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
}
