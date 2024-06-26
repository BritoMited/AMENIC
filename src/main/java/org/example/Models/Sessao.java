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
    private List<Cadeira> cadeiras;
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

    public List<Cadeira> getCadeiras() {
        return cadeiras;
    }

    public void setListaCadeiras(List<Cadeira> listaCadeiras) {
        this.cadeiras = listaCadeiras;
    }

    public void gerarCadeiras(int numLinhas, int assentosPorLinha) {
        this.cadeiras = new ArrayList<>();

        for (char row = 'A'; row < 'A' + numLinhas; row++) {
            for (int num = 1; num <= assentosPorLinha; num++) {
                Cadeira cadeira = new Cadeira();
                cadeira.setNumero(String.valueOf(row) + num);
                cadeira.setPcd(true);
                cadeira.setOcupado(false);
                cadeiras.add(cadeira);
            }
        }

        setListaCadeiras(cadeiras);
    }

    public void ocupar(String cadeira){
        for(Cadeira c: getCadeiras()) {
            if(cadeira.equals(c.getNumero())){
                c.ocupar();
            }
        }
    }

    // Lista as cadeiras na sessão
    public void listarCadeiras(){
        int i = 0;
        System.out.println("--------------------------------------");
        for(Cadeira c: getCadeiras()) {
            System.out.print(c + " ");
            i++;
            if(i % 8 == 0) System.out.println();
            i-=8;
        }
        System.out.println("--------------------------------------");
    }

    public Boolean isDisponivel(String cadeira){
        for(Cadeira c: getCadeiras()) {
            if(cadeira.equals(c.getNumero()) && c.getOcupado()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return
                "filme: " + filme +
                ", horario=" + horario +
                ", valor=" + valor;
    }
}
