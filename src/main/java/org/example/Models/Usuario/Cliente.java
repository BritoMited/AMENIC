package org.example.Models.Usuario;

import java.util.UUID;

public class Cliente extends Usuario{

    private UUID id;
    private Integer idade;
    private Boolean estudante;
    private Boolean pcd;

    public Cliente(String nome, String senha, Integer idade, Boolean estudante, Boolean pcd) {
        super(nome, senha);
        this.id = UUID.randomUUID();
        this.idade = idade;
        this.estudante = estudante;
        this.pcd = pcd;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Boolean getEstudante() {
        return estudante;
    }

    public void setEstudante(Boolean estudante) {
        this.estudante = estudante;
    }

    public Boolean getPcd() {
        return pcd;
    }

    public void setPcd(Boolean pcd) {
        this.pcd = pcd;
    }
}
