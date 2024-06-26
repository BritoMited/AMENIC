package org.example.Models.Pagamento;

import org.example.Models.Ingresso.Ingresso;
import org.example.Models.Usuario.Cliente;

import java.util.List;

public interface Pagamento {

    void gerarIngresso(List<Ingresso> listaIngresso, Cliente cliente);

    Boolean verificarTipoIngresso(Boolean estudante, Integer idade);

}
