package com.javierpintosettlin.miprimeraapp;

/**
 * Created by Lucia on 25/06/2015.
 */
public class Persona {
    //Atributos de Persona
    private  int id;
    private String nombre;
    private String dni;

    public Persona(){

    }

    public  Persona (String _nombre, String _dni) {
      setNombre(_nombre);
      setDni(_dni);
    }
    public  Persona (int _id, String _nombre, String _dni) {
        setId(_id);
        setNombre(_nombre);
        setDni(_dni);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
