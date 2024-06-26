package org.example.Controllers;

import org.example.Daos.AdmDAO;
import org.example.Daos.ClienteDAO;
import org.example.Daos.IngressosDAO;
import org.example.Exceptions.AdmLoginFailedException;
import org.example.Exceptions.ClienteLoginFailedException;
import org.example.Models.Cadeira;
import org.example.Models.Filme;
import org.example.Models.Ingresso.Ingresso;
import org.example.Models.Sessao;
import org.example.Models.Usuario.Administrador;
import org.example.Models.Usuario.Cliente;

import java.util.List;
import java.util.Scanner;

public class ClienteControllers {


    private static final String CLIENTE_FILE_NAME = "C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\AMENIC\\AM3NIC\\src\\main\\java\\org\\example\\TXT\\Cliente\\Cliente.txt";
    private static final String INGRESSO_FILE_NAME = "C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\AMENIC\\AM3NIC\\src\\main\\java\\org\\example\\TXT\\Ingressos\\Ingressos.txt";

    public static List<Cliente> listarCliente(){
        return ClienteDAO.listarCliente(CLIENTE_FILE_NAME);
    }

    public static List<Ingresso> listarIngresso(){
        return IngressosDAO.listarIngressos(INGRESSO_FILE_NAME);
    }

    public static void criarIngresso(Ingresso ingresso){
        IngressosDAO.criarIngressoDAO(INGRESSO_FILE_NAME, ingresso);
    }

    public static Cliente logarUsuario(String nome, String senha){

        try{
            var listaCliente = listarCliente();

            for(Cliente cliente: listaCliente){
                if(nome.equals(cliente.getNome()) && senha.equals(cliente.getSenha())){
                    return cliente;
                }
            }

            throw new ClienteLoginFailedException("falha ao logar");

        }catch(ClienteLoginFailedException e){
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }
    public static void registrarUsuario(Cliente novoCliente){

          ClienteDAO.registrarClienteDao(CLIENTE_FILE_NAME, novoCliente);

        System.out.println("Registro realizado!");
    }

    public static void comprarIngresso(Cliente cliente, Scanner sc) {
        System.out.println("Numero da sessão: ");
        Integer n = sc.nextInt();

        Sessao sessao = AdmController.buscarSessaoPorId(n);

        System.out.println("Quantidade de ingressos: ");
        int quant = sc.nextInt();
        for (int i = 0; i < quant; i++) {

            sc.nextLine();
            sessao.listarCadeiras();
            System.out.println("Digite o número da cadeira desejada: (ex: A2)");
            String numeroCadeira = sc.nextLine();
            numeroCadeira = numeroCadeira.toUpperCase();

            // Verificação da disponibilidade da cadeira
            while (sessao.isDisponivel(numeroCadeira)) {
                System.out.println("Cadeira indisponível, escolha outra:");
                numeroCadeira = sc.nextLine();
                numeroCadeira = numeroCadeira.toUpperCase();
            }

            // Adição do ingresso à sessão e marcação da cadeira como ocupada
            Ingresso ingresso = new Ingresso(
                    sessao.getId(),
                    cliente.getId(),
                    new Filme(sessao.getFilme().getTitulo(), sessao.getFilme().getGenero()),
                    new Cadeira(numeroCadeira, cliente.getPcd(), true),
                    sessao.getHorario(),
                    sessao.getValor());

            sessao.ocupar(numeroCadeira);

            criarIngresso(ingresso);

          //  sessoes[n].findIngresso(ingressoVer.getId());
    }


//    public static void rembolsarIngresso(){
//
//    }
//    public static void imprimirIngresso(){
//
//    }
//    public static void verificarIngressos(){
//
//    }



 }
}