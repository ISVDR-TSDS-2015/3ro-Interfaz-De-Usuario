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


        //Obtengo la list view
        final ListView listView =(ListView) findViewById(R.id.ListView2);

        DataBaseManejador db = new DataBaseManejador(this);
        Cursor CursorAllPersonas = db.getCursorAllPersonas();

        //Columnas de la base de datos
        String [] columnas = new String [] { "nombre", "dni"};

        //campos del layout
        int[] escribirEn = new int[] { R.id.list_item_nombre, R.id.list_item_dni };

        //Se genera el adaptador del cursor
        SimpleCursorAdapter adapter =new SimpleCursorAdapter(this,R.layout.persona_list_item1,
                CursorAllPersonas,
                columnas,
                escribirEn);

        //seteamos el adapter a la listView
        listView.setAdapter(adapter);

        //ListView item Click Listener (escuchador para que escuche el evento click)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //mensaje de alerta
                Toast.makeText(getApplicationContext(),
                        "Posicion:" + position + " ID:" + id, Toast.LENGTH_LONG).show();

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
