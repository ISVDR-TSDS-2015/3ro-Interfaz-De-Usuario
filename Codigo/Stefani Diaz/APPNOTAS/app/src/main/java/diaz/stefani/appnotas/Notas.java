package diaz.stefani.appnotas;

/**
 * Created by USUARIO on 07/07/2015.
 */
public class Notas {
    private String Titulo;
    private String Nota;
    private String FechaCreacion;

    public  Notas(){

    }

    public  Notas(String _titulo, String _nota, String _fechaCreacion){
        setTitulo(_titulo);
        setNota(_nota);
        setFecha(_fechaCreacion);

    }

   // public  Notas(int _id, String _nombre, String _dni){
    //    setId(_id);
     //   setNombre(_nombre);
     //   setDni(_dni);

   // }


    //PARA TITULO
    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        this.Titulo = titulo;
    }

    //PARA NOTA
    public String getNota() {
        return Nota;
    }

    public void setNota(String nota) {
        this.Nota = nota;
    }


    //PARA FECHA
    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFecha(String fechaCreacion) {
        this.FechaCreacion = fechaCreacion;
    }
}
