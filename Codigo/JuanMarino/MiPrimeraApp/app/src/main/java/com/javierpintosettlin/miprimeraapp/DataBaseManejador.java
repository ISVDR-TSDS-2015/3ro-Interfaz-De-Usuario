package com.javierpintosettlin.miprimeraapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan Marino on 25/06/2015.
 */
public class DataBaseManejador extends SQLiteOpenHelper {
    // All Static variables
// Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MiPrimerAppDB";

    // Tabla Personas
    private static final String TABLE_PERSONA = "persona";

    // Columna de la Tabla Personas
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
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PERSONA + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NOMBRE + " TEXT,"
                + KEY_DNI + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
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
                //Opcion1
                //Persona persona = new Persona();
                //persona.setId(Integer.parseInt(cursor.getString(0)));
                //persona.setNombre(cursor.getString(1));
                //persona.setDni(cursor.getString(2));
                //Opciion2
                Persona persona=new Persona (Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2));

// Adding persona to list
                listPersonas.add(persona);
            } while (cursor.moveToNext());
        }

// return lista de personas
        return listPersonas;
    }
}
