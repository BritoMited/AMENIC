package org.example.Models;

public class Cadeira {

    private String numero;
    private Boolean pcd;
    private Boolean ocupado;

    public Cadeira() {
    }

    public Cadeira(String numero, Boolean pcd, Boolean ocupado) {
        this.numero = numero;
        this.pcd = pcd;
        this.ocupado = ocupado;
    }

    public Cadeira(String field) {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Boolean getPcd() {
        return pcd;
    }

    public void setPcd(Boolean pcd) {
        this.pcd = pcd;
    }

    public Boolean getOcupado() {
        return ocupado;
    }

    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    // public void ocupar();


    @Override
    public String toString() {
        return numero + ";" +
                ";" + pcd +
                ";" + ocupado;
    }
}
