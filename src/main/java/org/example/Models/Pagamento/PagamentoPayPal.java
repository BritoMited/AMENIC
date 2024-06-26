package org.example.Models.Pagamento;

import org.example.Models.Ingresso.Ingresso;
import org.example.Models.Usuario.Cliente;

import java.util.List;

import static org.example.Controllers.ClienteControllers.criarIngresso;

public class PagamentoPayPal implements Pagamento{

    @Override
    public void gerarIngresso(List<Ingresso> listaIngresso, Cliente cliente) {
        for (Ingresso ingresso :listaIngresso){
            if(verificarTipoIngresso(cliente.getEstudante(), cliente.getIdade())){
                ingresso.setValor(ingresso.getValor()* 1.10/2);
            }else{
                ingresso.setValor(ingresso.getValor()* 1.10);
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
