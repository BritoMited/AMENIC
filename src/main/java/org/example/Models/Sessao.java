package org.example.Models;

import org.example.Models.Ingresso.Ingresso;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



public class Sessao {
    private Integer id;
    private Filme filme;
    private LocalDateTime horario;
    private Double valor;
    private List<Cadeira> cadeiras;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

    public Sessao(Filme filme, LocalDateTime horario, Double valor) {
        this.filme = filme;
        this.horario = horario;
        this.valor = valor;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<Cadeira> getCadeiras() {
        return cadeiras;
    }

    public void setListaCadeiras(List<Cadeira> listaCadeiras) {
        this.cadeiras = listaCadeiras;
    }

    public void gerarCadeiras(int numLinhas, int assentosPorLinha) {
        this.cadeiras = new ArrayList<>();
        // Lista de números de cadeiras PCD
        List<String> cadeirasPcd = List.of("A1", "A2", "B1", "B2", "A7", "A8", "B7", "B8");

        for (char row = 'A'; row < 'A' + numLinhas; row++) {
            for (int num = 1; num <= assentosPorLinha; num++) {
                Cadeira cadeira = new Cadeira();
                cadeira.setNumero(String.valueOf(row) + num);
                // Verifica se a cadeira é PCD
                if (cadeirasPcd.contains(cadeira.getNumero())) {
                    cadeira.setPcd(true);
                }
                cadeira.setOcupado(false);
                cadeiras.add(cadeira);
            }
        }

        setListaCadeiras(cadeiras);
    }

    public void ocupar(String cadeira){
        for(Cadeira c: getCadeiras()) {
            if(cadeira.equals(c.getNumero())){
                c.ocupar();
            }
        }
    }

    public void desocupar(String cadeira){
        for(Cadeira c: getCadeiras()) {
            if(cadeira.equals(c.getNumero())){
                c.desocupar();
            }
        }
    }

    // Lista as cadeiras na sessão
    //mexer
    public void listarCadeiras(){
        int i = 0;
        System.out.println("--------------------------------------");
        for(Cadeira c: getCadeiras()) {

            String[] partes = c.toString().split(";");

            if (partes[1].equals("true")){
                    partes[1] = "♿";
                }
            if (partes[1].equals("false")){
                partes[1] = "  ";
            }

            if (partes[2].equals("true")){
                partes[2] = "●";
            }

            if (partes[2].equals("false")){
                partes[2] = "○";
            }

            String cadeiraString = "(" + partes[0] + " " + partes [1] + " " + partes[2] + ")";
            System.out.print(cadeiraString + " ");
            i++;
            if(i % 8 == 0) System.out.println();
            i-=8;




        }

        System.out.println("--------------------------------------");
    }

    public Boolean isDisponivel(String cadeira){
        for(Cadeira c: getCadeiras()) {
            if(cadeira.equals(c.getNumero()) && c.getOcupado()){
                return true;
            }
        }
        return false;
    }

    // mexer
    @Override
    public String toString() {
        return
                "Id da Sessão: "+ id +
                "   Titulo: " + filme.getTitulo() +
                "   Gênero: " + filme.getGenero() +
                "   Horario: " + horario.format(dtf) +
                "   Preço: R$" + valor;
    }
}
