package stefanidiaz.manejodeactivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.apache.http.protocol.HTTP;

import java.util.List;


public class MenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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


    public void LlamarISVdR(View view){

        Uri number = Uri.parse("tel:+543573428951");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        //Intent callIntent = new Intent(Intent.ACTION_CALL, number);

        PackageManager packageManager = getPackageManager();
        List activities = packageManager.queryIntentActivities(callIntent,
                PackageManager.MATCH_DEFAULT_ONLY);

        if (activities.size() > 0){
            startActivity(callIntent);
        }
        else{
            Toast.makeText(this, "No hai activity para action_call",Toast.LENGTH_SHORT);
        }



    }

    public void WebIS(View view){

        Uri number = Uri.parse("http://www.institutodemolineria.edu.ar");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, number);
        //Intent callIntent = new Intent(Intent.ACTION_CALL, number);
        startActivity(webIntent);
    }

    public void MapaIS(View view){

        // mapa a una direccion
        //Uri ubicacion = Uri.parse("geo:0,0?q=890+Hipolito+Irigoyen,+Villa+del+Rosario,+Córdoba,+Argentina");

        //1600+Amphitheatre+Parkway,+Mountain+View,+California

        // o a unas coordenadas de latitud y longitud
        Uri ubicacion = Uri.parse("geo:-31.553932,-63.534805?z=18"); // z param is zoom level
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, ubicacion);


        startActivity(mapIntent);
    }


    public void eMailIS(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"tefi_27_06@hotmail.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto del email");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "texto del email");
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
        // You can also attach multiple items by passing an ArrayList of Uris


        startActivity(emailIntent);
    }
}
