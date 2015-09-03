package luciacano.trabajopracticointegrador;

/**
 * Created by Lucia on 07/08/2015.
 */
public class Nota {

    //Atributos de Nota
    private  int id;
    private String Titulo;
    private String Nota;
    private String Fecha;

    public Nota(){

    }

    public  Nota (String _Titulo, String _Nota, String _Fecha) {
        setTitulo(_Titulo);
        setNota(_Nota);
        setFecha(_Fecha);
    }
    public  Nota (int _id, String _Titulo, String _Nota, String _Fecha) {
        setId(_id);
        setTitulo(_Titulo);
        setNota(_Nota);
        setFecha(_Fecha);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String Nota) {
        this.Nota = Nota;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }
}
