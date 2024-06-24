package org.example.Models;

import org.example.Models.Ingresso.Ingresso;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Sessao {
    private Integer id;
    private Filme filme;
    private LocalDateTime horario;
    private Double valor;
    private List<Cadeira> listaCadeiras;
    private List<Ingresso> listaIngresso = new ArrayList<>();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

    public Sessao(Filme filme, LocalDateTime horario, Double valor) {
        this.filme = filme;
        this.horario = horario;
        this.valor = valor;
    }

    public Sessao(Filme filme) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<Cadeira> getListaCadeiras() {
        return listaCadeiras;
    }

    public void setListaCadeiras(List<Cadeira> listaCadeiras) {
        this.listaCadeiras = listaCadeiras;
    }

    public List<Ingresso> getListaIngresso() {
        return listaIngresso;
    }

    public void setListaIngresso(List<Ingresso> listaIngresso) {
        this.listaIngresso = listaIngresso;
    }












}
