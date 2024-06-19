package org.example.Models.Usuario;

public class Cliente extends Usuario{

    private Integer idade;
    private Boolean estudante;
    private Boolean pcd;

    public Cliente(String nome, String senha, Integer idade, Boolean estudante, Boolean pcd) {
        super(nome, senha);
        this.idade = idade;
        this.estudante = estudante;
        this.pcd = pcd;
    }

    @Override
    public void login() {

    }

    @Override
    public void registrar() {

    }
}
