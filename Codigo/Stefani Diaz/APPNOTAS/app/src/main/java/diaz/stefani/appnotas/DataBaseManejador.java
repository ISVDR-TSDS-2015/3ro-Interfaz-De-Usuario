package diaz.stefani.appnotas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USUARIO on 07/07/2015.
 */
public class DataBaseManejador extends SQLiteOpenHelper {
    //version de base de datos
    private static final int DATABASE_VERSION = 2;

    //nombre de la base de datos
    private static final String DATABASE_NAME = "MiPrimeraAppBD";

    // tabla personas
    private static final String TABLE_NOTAS = "notas";

    //columnas de la tabla
    private static final String KEY_ID = "id";
    private static final String KEY_TITULO = "Titulo";
    private static final String KEY_NOTA = "Nota";
    private static final String KEY_FECHA = "FechaCreacion";


    //Constructor de la Clase
    public DataBaseManejador(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // creacion de tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NOTAS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITULO + " TEXT,"
                + KEY_NOTA + " TEXT,"
                + KEY_FECHA + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTAS);

// Create tables again
        onCreate(db);

    }


    void addNota(Notas notas) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITULO,notas.getTitulo() );
        values.put(KEY_NOTA, notas.getNota());
        values.put(KEY_FECHA, notas.getFechaCreacion());

// Inserting Row
        db.insert(TABLE_NOTAS, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Personas
    public List<Notas> getAllPersonas() {
        List<Notas> listNotas = new ArrayList<Notas>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NOTAS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //Persona persona = new Persona();
                //persona.setId(Integer.parseInt(cursor.getString(0)));
                //persona.setNombre(cursor.getString(1));
                //persona.setDni(cursor.getString(2));

                Notas nota = new Notas (cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2));


// Adding persona to list
                listNotas.add(nota);
            } while (cursor.moveToNext());
        }

// return lista de personas
        return listNotas;
    }

    void BorrarTodasLasNotas(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NOTAS);

        db.close();

    }

    public Cursor getCursorAllNotas(){
        String selectQuery = "SELECT id _id, Titulo, Nota, FechaCreacion FROM " + TABLE_NOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(selectQuery,null);
            }

    void BorrarNota (long id){
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NOTAS +" WHERE ID="+id);
        db.close();
    }


}

