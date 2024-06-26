package org.example.Models.Ingresso;



import org.example.Models.Cadeira;
import org.example.Models.Filme;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ingresso {

    private UUID id;
    private Integer id_sessao;
    private UUID id_cliente;
    private Filme filme;
    private Cadeira cadeira;
    private LocalDateTime horario;
    private Double valor;

    public Ingresso(Integer id_sessao, UUID id_cliente, Filme filme, Cadeira cadeira, LocalDateTime horario, Double valor) {
        this.id = UUID.randomUUID();
        this.id_sessao = id_sessao;
        this.id_cliente = id_cliente;
        this.filme = filme;
        this.cadeira = cadeira;
        this.horario = horario;
        this.valor = valor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getId_sessao() {
        return id_sessao;
    }

    public void setId_sessao(Integer id_sessao) {
        this.id_sessao = id_sessao;
    }

    public UUID getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(UUID id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Cadeira getCadeira() {
        return cadeira;
    }

    public void setCadeira(Cadeira cadeira) {
        this.cadeira = cadeira;
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
}
