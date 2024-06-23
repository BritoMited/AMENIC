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

}
