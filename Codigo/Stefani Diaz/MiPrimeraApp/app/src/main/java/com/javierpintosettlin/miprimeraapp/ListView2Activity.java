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

        //obtengo la listview de la vista/layaut
        final ListView listView = (ListView) findViewById(R.id.listview2);

        DataBaseManejador db = new DataBaseManejador(this);

        Cursor cursorAllPersonas= db.getCursorAllPersonas();

        //columnas de la base de datos
        String [] colums = new String[] {"nombre", "dni"};

        //campos del layaut

        int[] escribiren = new int[] {R.id.list_item_nombre, R.id.list_item_dni};


        //genera un adaptador del cursor
        SimpleCursorAdapter adapter= new SimpleCursorAdapter(this,
                R.layout.persona_list_item1,
                cursorAllPersonas,
                colums,
                escribiren);


        //seteamos el adapter de la listView
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //show alert

                Toast.makeText(getApplicationContext(),
                        "Posicion: " + position + " ID: " + id, Toast.LENGTH_LONG).show();


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
