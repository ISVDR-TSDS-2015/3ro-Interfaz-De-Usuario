package luciacano.app_nota;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;


public class VerNotasActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_notas);

        VerNotas();
        //Obtengo la list view
        final ListView listView = (ListView) findViewById(R.id.ListView);



        //ListView item Click Listener (escuchador para que escuche el evento click)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //mensaje de alerta
                Toast.makeText(getApplicationContext(),
                        "Posicion:" + position + " ID:" + id, Toast.LENGTH_LONG).show();


                ManejadorBaseDatos db = new ManejadorBaseDatos(getApplicationContext());

                db.BorrarNota(id);

                VerNotas();

            }
        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ver_notas, menu);
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

    public void  VerNotas(){


        ManejadorBaseDatos db = new ManejadorBaseDatos(this);


        Log.d("Leyendo: ", "Leyendo todas las Notas..");

        //Obtengo la list view
        final ListView listView = (ListView) findViewById(R.id.ListView);


        //ManejadorBaseDatos db = new ManejadorBaseDatos(this);
        Cursor CursorAllNotas = db.getCursorAllNotas();

        //Columnas de la base de datos
        String[] columnas = new String[]{"Titulo", "Nota", "Fecha"};

        //campos del layout
        int[] escribirEn = new int[]{R.id.list_item_Titulo, R.id.list_item_Nota, R.id.list_item_Fecha};

        //Se genera el adaptador del cursor
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.nota_listview,
                CursorAllNotas,
                columnas,
                escribirEn);

        //seteamos el adapter a la listView
        listView.setAdapter(adapter);


    }



}
