package com.rubino.pmdm4_broadcastreciver.llamadas;

/**
 * Created by marco on 26/01/2016.
 */
public class Llamada {

    private long id;
    private String numero,fecha;

    public Llamada() {
    }

    public Llamada( String numero, String fecha) {
        this.id = 0;
        this.numero = numero;
        this.fecha = fecha;
    }

    public Llamada(long id, String numero, String fecha) {
        this.id = id;
        this.numero = numero;
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Llamada llamada = (Llamada) o;

        if (id != llamada.id) return false;
        if (numero != null ? !numero.equals(llamada.numero) : llamada.numero != null) return false;
        return !(fecha != null ? !fecha.equals(llamada.fecha) : llamada.fecha != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Llamada{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
