package org.example.Models.Ingresso;

import org.example.Models.Cadeira;
import org.example.Models.Filme;
import org.example.Models.Usuario.Cliente;

import java.time.LocalDateTime;
import java.util.UUID;

public class IngressoMeia extends Ingresso{

    public IngressoMeia(UUID id, Filme filme, Cadeira cadeira, Cliente cliente, LocalDateTime horario) {
        super(id, filme, cadeira, cliente, horario);
    }
}
