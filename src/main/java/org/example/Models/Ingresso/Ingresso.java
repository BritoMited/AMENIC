package org.example.Models.Ingresso;



import org.example.Models.Cadeira;
import org.example.Models.Filme;
import org.example.Models.Usuario.Cliente;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Ingresso {

    private UUID id;
    private Integer id_sessao;
    private Filme filme;
    private Cadeira cadeira;
    private LocalDateTime horario;
    private Cliente cliente;

    public Ingresso(UUID id, Filme filme, Cadeira cadeira, Cliente cliente, LocalDateTime horario) {
        this.id = id;
        this.filme = filme;
        this.cadeira = cadeira;
        this.cliente = cliente;
        this.horario = horario; // talvez tenha como pegar a hora .now
    }

    public Integer getId_sessao() {
        return id_sessao;
    }

    public void setId_sessao(Integer id_sessao) {
        this.id_sessao = id_sessao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
