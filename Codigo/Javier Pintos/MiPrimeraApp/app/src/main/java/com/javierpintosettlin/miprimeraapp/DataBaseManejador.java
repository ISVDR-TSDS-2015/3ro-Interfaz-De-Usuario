package com.javierpintosettlin.miprimeraapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javier on 25/06/2015.
 */
public class DataBaseManejador extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MiPrimerAppDB";

    // Contacts table name
    private static final String TABLE_PERSONA = "persona";

    // Contacts Table Columns names
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
        values.put(KEY_NOMBRE, persona.get_nombre()); // Nombre de persona
        values.put(KEY_DNI, persona.get_dni()); // dni de persona

        // Inserting Row
        db.insert(TABLE_PERSONA, null, values);
        db.close(); // Closing database connection
    }

    // Getting personas por ID
    Persona getPersonaPorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PERSONA, new String[] { KEY_ID,
                        KEY_NOMBRE, KEY_DNI }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Persona persona = new Persona(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return persona;
    }

    // Getting All Personas
    public List<Persona> getAllPersonas() {
        List<Persona> listPersonas = new ArrayList<Persona>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PERSONA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Persona persona = new Persona();
                persona.set_id(Integer.parseInt(cursor.getString(0)));
                persona.set_nombre(cursor.getString(1));
                persona.set_dni(cursor.getString(2));
                // Adding persona to list
                listPersonas.add(persona);
            } while (cursor.moveToNext());
        }

        // return lista de personas
        return listPersonas;
    }


    // Updating single persona
    public int updatePersona(Persona persona) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE, persona.get_nombre());
        values.put(KEY_DNI, persona.get_dni());

        // updating row
        return db.update(TABLE_PERSONA, values, KEY_ID + " = ?",
                new String[] { String.valueOf(persona.get_id()) });
    }

    // Deleting un persona
    public void deletePersona(Persona persona) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PERSONA, KEY_ID + " = ?",
                new String[] { String.valueOf(persona.get_id()) });
        db.close();
    }


    // Getting cantidad de personas
    public int getPersonasCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PERSONA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
