package org.example.Controllers;

import org.example.Daos.ClienteDAO;
import org.example.Daos.IngressosDAO;
import org.example.Daos.SessaoDAO;
import org.example.Exceptions.ClienteException;
import org.example.Models.Cadeira;
import org.example.Models.Filme;
import org.example.Models.Ingresso.Ingresso;
import org.example.Models.Pagamento.Pagamento;
import org.example.Models.Pagamento.PagamentoCartao;
import org.example.Models.Pagamento.PagamentoPayPal;
import org.example.Models.Sessao;
import org.example.Models.Usuario.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteControllers {


    private static final String CLIENTE_FILE_NAME = "C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\AMENIC\\AM3NIC\\src\\main\\java\\org\\example\\TXT\\Cliente\\Cliente.txt";
    private static final String INGRESSO_FILE_NAME = "C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\AMENIC\\AM3NIC\\src\\main\\java\\org\\example\\TXT\\Ingressos\\Ingressos.txt";
    private static final String SESSAO_FILE_NAME = "C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\AMENIC\\AM3NIC\\src\\main\\java\\org\\example\\TXT\\Sessao\\Sessao.txt";

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

            throw new ClienteException("falha ao logar");

        }catch(ClienteException e){
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

        List<Ingresso> listaTemp = new ArrayList<>();

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
            listaTemp.add(new Ingresso(
                    sessao.getId(),
                    cliente.getId(),
                    new Filme(sessao.getFilme().getTitulo(), sessao.getFilme().getGenero()),
                    new Cadeira(numeroCadeira, cliente.getPcd(), true),
                    sessao.getHorario(),
                    sessao.getValor()));

            sessao.ocupar(numeroCadeira);

            SessaoDAO.alterarSessaoDao(SESSAO_FILE_NAME, sessao);

        }

        Pagamento pagamento;

        int op;
        System.out.println("Escolha a forma de pagamento:");
        System.out.println("0. Sair");
        System.out.println("1. PayPal");
        System.out.println("2. Cartão");

        do {

            op = sc.nextInt();

            switch (op) {
                case 0 -> System.out.println("Saindo...");
                case 1 -> {
                    pagamento = new PagamentoPayPal();
                    pagamento.gerarIngresso(listaTemp, cliente);
                    System.out.println("Pagamento finalizado...");
                }
                case 2 -> {
                    pagamento = new PagamentoCartao();
                    pagamento.gerarIngresso(listaTemp, cliente);
                    System.out.println("Pagamento finalizado...");
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (op != 0);

    }


        public static void rembolsarIngresso(Cliente cliente, Scanner sc){
            Sessao sessao;
            do{
                System.out.println("Informe a sessão que deseja reembolsar o ingresso: ");
                Integer id_sessao = sc.nextInt();

                sessao = AdmController.buscarSessaoPorId(id_sessao);
            }while (sessao == null);

            IngressosDAO.removerIngressoDao(INGRESSO_FILE_NAME, cliente.getId(), sessao.getId());

        }
//    public static void imprimirIngresso(){
//
//    }
        public static void verificarIngressos(Scanner sc, Cliente cliente){
            System.out.println("Digite o numero da sessão que deseja verificar o ingresso: ");
            Integer id_sessao = sc.nextInt();

            List<Ingresso> ingressos = IngressosDAO.buscarIngressoPorIdDao(INGRESSO_FILE_NAME, cliente.getId(), id_sessao);

            for (Ingresso ingresso : ingressos){
                System.out.println("Id do ingresso: " + ingresso.getId());
                System.out.println("Id do ingresso: " + ingresso.getId_sessao());
                System.out.println("Id do ingresso: " + ingresso.getId_cliente());
                System.out.println("Id do ingresso: " + ingresso.getFilme().getTitulo());
                System.out.println("Id do ingresso: " + ingresso.getFilme().getGenero());
            }

        }
}