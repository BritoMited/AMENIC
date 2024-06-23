package org.example.Models.Usuario;

import java.util.UUID;

public class Administrador extends Usuario{

    private UUID id;

    public Administrador(String nome, String senha) {
        super(nome, senha);
        this.id = UUID.randomUUID();
    }



   // public void adicionarFilme();
   // public void listarFilme();
   // public void alterar Filme();
   // public void remaverFilme();
   // public void adicionar Sessao();
   // public void listar Sessao();
   // public void alterar Sessao();
   // public void removerSessao();
}
