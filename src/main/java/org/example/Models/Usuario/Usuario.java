package org.example.Models.Usuario;

import java.util.UUID;

public abstract class Usuario {

    private UUID id;
    private String nome;
    private String senha;

    public Usuario(String nome, String senha) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.senha = senha;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public abstract void login();

    public abstract void registrar();
}
