package production.system;

import java.util.HashSet;
import java.util.Set;

public class Regla {

    private int id;
    private Set<String> palabrasClaves;
    private String accion;
    private int novedad;
    private int especificidad;
    private int prioridad;

    public Regla(int id, String palabrasClaves, String accion, int novedad, int especificidad,
                 int prioridad) {
        this.id = id;
        this.palabrasClaves = cargarPClaves(palabrasClaves);
        this.accion = accion;
        this.novedad = novedad;
        this.especificidad = especificidad;
        this.prioridad = prioridad;
    }

    /**
     *  Método para separar el string de palabras claves y cargar cada palabra en el
     *  atributo de clase de tipo Set
     */
    private Set<String> cargarPClaves (String palabrasClaves){
        Set<String> pClaves = new HashSet<>();
        String[] palabras = palabrasClaves.split("-");
        for(String palabra : palabras){
            if(!palabra.isEmpty())
                pClaves.add(palabra);
        }
        return pClaves;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<String> getPalabrasClaves() {
        return palabrasClaves;
    }

    public void setPalabrasClaves(Set<String> palabrasClaves) {
        this.palabrasClaves = palabrasClaves;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public int getNovedad() {
        return novedad;
    }

    public void setNovedad(int novedad) {
        this.novedad = novedad;
    }

    public int getEspecificidad() {
        return especificidad;
    }

    public void setEspecificidad(int especificidad) {
        this.especificidad = especificidad;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Regla)) {
            return false;
        }

        Regla otraRegla = (Regla) o;

        return otraRegla.id == this.id;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[" + id + "] ");
        for(String palabraClave: palabrasClaves){
            sb.append(palabraClave + " ");
        }
        sb.append(" => ");
        sb.append(accion);
        return sb.toString();
    }
}
