package com.javierpintosettlin.miprimeraapp;

/**
 * Created by USUARIO on 25/06/2015.
 */
public class Persona {
//ATRIBUTOS DE PERSONAS
    private int id;
    private  String nombre;
    private  String dni;

    public  Persona(){

    }

    public  Persona(String _nombre, String _dni){
        setNombre(_nombre);
        setDni(_dni);

    }

    public  Persona(int _id, String _nombre, String _dni){
        setId(_id);
        setNombre(_nombre);
        setDni(_dni);

    }
//PARA ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//PARA NOMBRE
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
//PARA DNI
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
