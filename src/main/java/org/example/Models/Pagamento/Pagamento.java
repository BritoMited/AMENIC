package org.example.Models.Pagamento;

import org.example.Models.Ingresso.Ingresso;

public interface Pagamento {

    Ingresso gerarIngresso();

    Boolean verificarTipoIngresso();

}
