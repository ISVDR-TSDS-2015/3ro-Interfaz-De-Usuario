package com.javierpintosettlin.miprimeraapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ListViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //Obtengo la ListView
        final ListView listView = (ListView)findViewById(R.id.ListView);

        //Defino un array para mostrar en la ListView
        String[] values = new String[] {"Juan", "Santiago","Lucia", "Stefani"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        //Asigno el adapter a la ListView
        listView.setAdapter(adapter);


        //ListView item Click Listener (escuchador para que escuche el evento click)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //indice del item clikeado de la listview
                int itemPosition = position;

                //
                String itemValue = (String)listView.getItemAtPosition(position);

                //mensaje de alerta
                Toast.makeText(getApplicationContext(),
                        "Posicion:" + itemPosition + " ListItem:" + itemValue, Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view, menu);
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
