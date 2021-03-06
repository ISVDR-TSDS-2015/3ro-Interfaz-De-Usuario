package diaz.stefani.appnotas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Menu_Notas extends ActionBarActivity {

    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_menu);
    }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.menu_menu, menu);
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


    public void irNuevaNota(View view) {
        //Creamos el Intent
       Intent intent = new Intent(this, NuevaNota.class);

         //Ir a Nueva Nota
        startActivity(intent);
    }

    public void VerNotas(View view) {
        //Creamos el Intent
        Intent intent = new Intent(this, Notas_VerTodas.class);

        //Ir a todas las notas
        startActivity(intent);
    }
}
