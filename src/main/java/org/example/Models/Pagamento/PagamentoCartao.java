package org.example.Models.Pagamento;

import org.example.Models.Ingresso.Ingresso;
import org.example.Models.Usuario.Cliente;

import java.text.DecimalFormat;
import java.util.List;

import static org.example.Controllers.ClienteControllers.criarIngresso;

public class PagamentoCartao implements Pagamento{
    @Override
    public void gerarIngresso(List<Ingresso> listaIngresso, Cliente cliente) {
        DecimalFormat df = new DecimalFormat("#,00");

        for (Ingresso ingresso : listaIngresso) {
            if (verificarTipoIngresso(cliente.getEstudante(), cliente.getIdade())) {
                ingresso.setValor(Double.parseDouble(df.format(ingresso.getValor() * 1.05 / 2)));
            } else {
                ingresso.setValor(Double.parseDouble(df.format(ingresso.getValor() * 1.05)));
            }
            criarIngresso(ingresso);
        }
    }

    @Override
    public Boolean verificarTipoIngresso(Boolean estudante, Integer idade) {
        if(estudante) return true;
        else if (idade < 18) return true;
        return false;

    }
}
