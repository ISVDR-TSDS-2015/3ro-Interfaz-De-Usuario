package com.javierpintosettlin.miprimeraapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USUARIO on 25/06/2015.
 */
public class DataBaseManejador extends SQLiteOpenHelper {

    //version de base de datos
    private static final int DATABASE_VERSION= 1;

    //nombre de la base de datos
    private static final String DATABASE_NAME= "MiPrimeraAppBD";

    // tabla personas
    private static final String TABLE_PERSONA="personas";

    //columnas de la tabla
    private static final String KEY_ID = "id";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_DNI = "dni";



    //Constructor de la Clase
    public DataBaseManejador(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    // creacion de tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PERSONA + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NOMBRE + " TEXT,"
                + KEY_DNI + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // salta de version de la base de datos
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

// Adding new persona
    void addPersona(Persona persona) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE, persona.getNombre()); // Nombre de persona
        values.put(KEY_DNI, persona.getDni()); // dni de persona

// Inserting Row
        db.insert(TABLE_PERSONA, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Personas
    public List<Persona> getAllPersonas() {
        List<Persona> listPersonas = new ArrayList<Persona>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PERSONA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //Persona persona = new Persona();
                //persona.setId(Integer.parseInt(cursor.getString(0)));
                //persona.setNombre(cursor.getString(1));
                //persona.setDni(cursor.getString(2));

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

    // All Personas cursor
    public Cursor getCursorAllPersonas() {

        // Select All Query
        String selectQuery = "SELECT id _id,nombre,dni FROM " + TABLE_PERSONA;

        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery(selectQuery,null);


    }


    // borra todas las personas
    void BorrarTodasLasPersonas(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_PERSONA);

        db.close();

    }
}
