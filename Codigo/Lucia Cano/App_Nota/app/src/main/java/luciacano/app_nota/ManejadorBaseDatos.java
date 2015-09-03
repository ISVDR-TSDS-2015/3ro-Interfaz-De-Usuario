package luciacano.app_nota;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucia on 05/08/2015.
 */
public class ManejadorBaseDatos extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "App_MenuDB";

    // Tabla Personas
    private static final String TABLE_NOTA = "Notas";

    // Columnas de la tabla Personas
    private static final String KEY_ID = "id";
    private static final String KEY_Titulo = "Titulo";
    private static final String KEY_Nota = "Nota";
    private static final String KEY_Fecha = "Fecha";


    //Constructor de la Clase
    public ManejadorBaseDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLA_NOTAS = "CREATE TABLE " + TABLE_NOTA + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_Titulo + " TEXT,"
                + KEY_Nota + " TEXT,"
                + KEY_Fecha + " TEXT" +  ")";
        db.execSQL(CREATE_TABLA_NOTAS);
    }

    // Upgrading (salta a una version mas nueva) database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTA);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding (registrar, agregar) new persona
    void addNota(Nota Nota) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Titulo, Nota.getTitulo());
        values.put(KEY_Nota, Nota.getNota());
        values.put(KEY_Fecha, Nota.getFecha());
        // Inserting Row
        db.insert(TABLE_NOTA, null, values);
        db.close(); // Closing database connection
    }

    // Getting (consultar, leer) All Personas
    public List<Nota> getAllNotas() {
        List<Nota> listNotas = new ArrayList<Nota>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NOTA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Nota Nota = new Nota(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                // Adding persona to list
                listNotas.add(Nota);
            } while (cursor.moveToNext());
        }

        // return lista de personas
        return listNotas;
    }

    public Cursor getCursorAllNotas() {

        // Select All Query
        String selectQuery = "SELECT id _id, Titulo, Nota, Fecha FROM " + TABLE_NOTA;

        SQLiteDatabase db = this.getWritableDatabase();


        return db.rawQuery(selectQuery, null);
    }

    void BorrarNota(long id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NOTA+" WHERE ID="+id);



        db.close();






    }


}
