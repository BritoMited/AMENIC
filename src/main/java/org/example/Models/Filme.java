package org.example.Models;

public class Filme {

    private String titulo;
    private String genero;

    public Filme() {
    }

    public Filme(String titulo, String genero) {
        this.titulo = titulo;
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return titulo + ";" + genero;
    }
}
