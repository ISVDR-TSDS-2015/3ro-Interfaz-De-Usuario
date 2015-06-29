package com.javierpintosettlin.miprimeraapp;

/**
 * Created by Javier on 25/06/2015.
 */
public class Persona {
    private int _id;
    private String _nombre;
    private String _dni;

    public Persona(){

    }

    public Persona(int id, String nombre, String dni){
        set_id(id);
        set_nombre(nombre);
        set_dni(dni);
    }

    public Persona(String nombre, String dni){
        set_nombre(nombre);
        set_dni(dni);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_dni() {
        return _dni;
    }

    public void set_dni(String _dni) {
        this._dni = _dni;
    }
}
