package org.example.Models.Pagamento;

import org.example.Models.Ingresso.Ingresso;

public class PagamentoCartao implements Pagamento{
    @Override
    public Ingresso gerarIngresso() {
        return null;
    }

    @Override
    public Boolean verificarTipoIngresso() {
        return null;
    }
}
