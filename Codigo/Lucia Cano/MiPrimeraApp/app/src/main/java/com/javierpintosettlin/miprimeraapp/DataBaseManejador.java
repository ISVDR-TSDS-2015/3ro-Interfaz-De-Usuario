package com.javierpintosettlin.miprimeraapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucia on 25/06/2015.
 */
public class DataBaseManejador extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MiPrimerAppDB";

    // Tabla Personas
    private static final String TABLE_PERSONA = "personas";

    // Columnas de la tabla Personas
    private static final String KEY_ID = "id";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_DNI = "dni";


    //Constructor de la Clase
    public DataBaseManejador(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLA_PERSONAS = "CREATE TABLE " + TABLE_PERSONA + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NOMBRE + " TEXT,"
                + KEY_DNI + " TEXT" + ")";
        db.execSQL(CREATE_TABLA_PERSONAS);
    }

    // Upgrading (salta a una version mas nueva) database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONA);

    // Create tables again
        onCreate(db);
    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding (registrar, agregar) new persona
    void addPersona(Persona persona) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE, persona.getNombre()); // Nombre de persona
        values.put(KEY_DNI, persona.getDni()); // dni de persona

    // Inserting Row
        db.insert(TABLE_PERSONA, null, values);
        db.close(); // Closing database connection
    }


    // Getting (consultar, leer) All Personas
    public List<Persona> getAllPersonas() {
        List<Persona> listPersonas = new ArrayList<Persona>();
    // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PERSONA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //Opcion 1
                //Persona persona = new Persona();
                //persona.setId(Integer.parseInt(cursor.getString(0)));
                //persona.setNombre(cursor.getString(1));
                //persona.setDni(cursor.getString(2));

                //Opcion 2
                Persona persona = new Persona(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2));
    // Adding persona to list
                listPersonas.add(persona);
            } while (cursor.moveToNext());
        }

    // return lista de personas
        return listPersonas;
    }

    //all Personas cursor
    public Cursor getCursorAllPersonas() {

        // Select All Query
        String selectQuery = "SELECT id _id, nombre, dni FROM " + TABLE_PERSONA;

        SQLiteDatabase db = this.getWritableDatabase();


        return db.rawQuery(selectQuery, null);
    }

    //Borrar Todas las Peronas
    void  BorrarTodasLasPersonas(){
        SQLiteDatabase db =this.getReadableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PERSONA);
        db.close();
    }


}
