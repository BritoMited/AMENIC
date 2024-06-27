package org.example.Controllers;

import org.example.Daos.ClienteDAO;
import org.example.Daos.IngressosDAO;
import org.example.Daos.SessaoDAO;
import org.example.Exceptions.ClienteException;
import org.example.Models.Cadeira;
import org.example.Models.FileManager;
import org.example.Models.Filme;
import org.example.Models.Ingresso.Ingresso;
import org.example.Models.Pagamento.Pagamento;
import org.example.Models.Pagamento.PagamentoCartao;
import org.example.Models.Pagamento.PagamentoPayPal;
import org.example.Models.Sessao;
import org.example.Models.Usuario.Cliente;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.example.Views.AdmView.dtf;


public class ClienteControllers {


    public static final String CLIENTE_FILE_NAME = "C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\AMENIC\\AM3NIC\\src\\main\\java\\org\\example\\TXT\\Cliente\\Cliente.txt";
    public static final String INGRESSO_FILE_NAME = "C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\AMENIC\\AM3NIC\\src\\main\\java\\org\\example\\TXT\\Ingressos\\Ingressos.txt";
    public static final String SESSAO_FILE_NAME = "C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\AMENIC\\AM3NIC\\src\\main\\java\\org\\example\\TXT\\Sessao\\Sessao.txt";

    public static List<Cliente> listarCliente(){
        return ClienteDAO.listarCliente(CLIENTE_FILE_NAME);
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
        System.out.println("1. PayPal");
        System.out.println("2. Cartão");

        do {
            System.out.println("0. Sair");
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

        try {
            Sessao sessao;
            do {
                System.out.println("Informe a sessão que deseja reembolsar o ingresso: ");

                    Integer id_sessao = sc.nextInt();

                    sessao = AdmController.buscarSessaoPorId(id_sessao);
                }
                while (sessao == null) ;

                IngressosDAO.removerIngressoDao(INGRESSO_FILE_NAME, cliente.getId(), sessao.getId());

        }catch (InputMismatchException e){
                System.out.println("Sessão não encontrada " + e.getMessage());
            }


        }


    //mexer
        public static void verificarIngressos(Scanner sc, Cliente cliente){
            System.out.println("Digite o numero da sessão que deseja verificar o ingresso: ");
            Integer id_sessao = sc.nextInt();

            List<Ingresso> ingressos = IngressosDAO.buscarIngressoPorIdClienteDao(INGRESSO_FILE_NAME, cliente.getId(), id_sessao);

            for (Ingresso ingresso : ingressos){
                System.out.println("######################");
                System.out.println("Id do Ingresso  : " + ingresso.getId());
                System.out.println("Id da sessão    : " + ingresso.getId_sessao());
                System.out.println("Id do Cliente   : " + ingresso.getId_cliente());
                System.out.println("Titulo do filme : " + ingresso.getFilme().getTitulo());
                System.out.println("Genero          : " + ingresso.getFilme().getGenero());

            }

        }


    public static void imprimirIngresso(Scanner sc, Cliente cliente) {
        FileManager fileManager = new FileManager();


        File diretorio = fileManager.criarPasta("C:\\Estudosjava\\Cinemjav\\AMENIC\\src\\main\\java\\org\\example\\DiretorioCliente");

        System.out.println("Digite o número da sessão que deseja imprimir o ingresso: ");
        Integer id_sessao = sc.nextInt();

        List<Ingresso> ingressos = IngressosDAO.buscarIngressoPorIdClienteDao(INGRESSO_FILE_NAME, cliente.getId(), id_sessao);

        for (Ingresso ingresso : ingressos) {
            try {
                File file = fileManager.criarArquivo(diretorio, "Ingresso_" + ingresso.getId() + ".txt");
                FileWriter fileWriter = new FileWriter(file);
                PrintWriter printWriter = new PrintWriter(fileWriter);

                printWriter.println("### Ingresso ###");
                printWriter.println("Id do Ingresso: " + ingresso.getId());
                printWriter.println("Id da sessão: " + ingresso.getId_sessao());
                printWriter.println("Id do Cliente: " + ingresso.getId_cliente());
                printWriter.println("Titulo do filme: " + ingresso.getFilme().getTitulo());
                printWriter.println("Gênero: " + ingresso.getFilme().getGenero());
                printWriter.println("Data da sessão: " + ingresso.getHorario().format(dtf));
                printWriter.println("Cadeira: " + ingresso.getCadeira().getNumero());
                printWriter.println("### Ingresso ###");

                printWriter.close();
                System.out.println("Ingresso impresso com sucesso em: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Erro ao imprimir ingresso: ");
            }
        }
    }
}