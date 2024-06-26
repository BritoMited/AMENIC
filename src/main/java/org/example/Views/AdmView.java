package org.example.Views;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Controllers.AdmController;
import org.example.Daos.SessaoDAO;
import org.example.Models.Filme;
import org.example.Models.Sessao;
import org.example.Models.Usuario.Administrador;
import org.example.Utils.Util;

import static org.example.Controllers.AdmController.imprimirSessao;
import static org.example.Daos.SessaoDAO.alterarSessaoDao;

public class AdmView {
    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    private static final Logger logger = LogManager.getLogger(AdmView.class);

    public static void iniciarMenuAdm(Scanner sc) {
        int op;
        do {
            menuAdm();
            op = Util.lerOpcoesMenu(sc);
            escolhaMenuAdm(sc, op);
        } while (op != 0);
    }

    public static void iniciarMenuAdmLogado(Administrador adm,Scanner sc){
        int op;
        do {
            menuAdmLogado();
            op = Util.lerOpcoesMenu(sc);
            escolhaMenuAdmLogado(sc, op);
        } while (op != 0);
    }


    // =========== MENUS =============

    public static void menuAdm(){
        System.out.println("######################");
        System.out.println("#      BEM VINDO     #");
        System.out.println("#      MENU ADM      ");
        System.out.println("######################");
        System.out.println("0- sair");
        System.out.println("1- Login");
        System.out.println("######################");
    }

    public static void menuAdmLogado(){
        System.out.println("######################");
        System.out.println("#      BEM VINDO     #");
        System.out.println("#      MENU ADM      ");
        System.out.println("######################");
        System.out.println("0- sair");
        System.out.println("1- Criar sessão");
        System.out.println("2- Alterar sessão existente");
        System.out.println("3- Deletar sessão");
        System.out.println("4- Listrar ingresos e total vendido");
        System.out.println("5- Imprimir ingresos");
        System.out.println("######################");

    }


    // ====== ESCOLHAS MENU =========


    public static void escolhaMenuAdm(Scanner sc, int op) {
        switch (op){
            case 0 -> System.out.println("Saindo....");
            case 1 -> logarAdm(sc);
            default -> System.out.println("Opção errada");

        }
    }

    public static void escolhaMenuAdmLogado(Scanner sc, int op){




        switch (op){
            case 0 -> System.out.println("0- Sair");
            case 1 -> adicionarSessao(sc);
            case 2 ->{

                    System.out.println("Digite o id da sessao que quer alterar");
                    int idSessao = Integer.parseInt(sc.nextLine());

                    System.out.println("digite o titulo do filme");
                    String titulo = sc.nextLine();

                    System.out.println("Digite o genero do filme");
                    String genero = sc.nextLine();


                    LocalDateTime horarioSessao = null;
                    while (horarioSessao == null) {
                        try {
                            System.out.println("Digite o horario da sessao: (dd/MM/yy HH:mm)");
                            horarioSessao = LocalDateTime.parse(sc.nextLine(), dtf);
                        } catch (DateTimeParseException e) {
                            logger.error("Formato de data/hora inválido. Tente novamente.");
                        }
                    }

                    System.out.println("Digite o valor da sessao");
                    Double valor = Double.parseDouble(sc.nextLine());

                    Filme filme = new Filme(titulo,genero);
                    Sessao sessaoAlterada = new Sessao(filme, horarioSessao, valor);
                    sessaoAlterada.setId(idSessao);

                    SessaoDAO.alterarSessaoDao("C:\\Estudosjava\\Cinemjav\\AMENIC\\src\\main\\java\\org\\example\\TXT\\Sessao\\Sessao.txt" , sessaoAlterada);
                    break;
            }
            case 3 -> {
                System.out.println("Digite o id da sessão que quer excluir");
                int idRemover = Integer.parseInt(sc.nextLine());
                SessaoDAO.removerSessaoDao("C:\\Estudosjava\\Cinemjav\\AMENIC\\src\\main\\java\\org\\example\\TXT\\Sessao\\Sessao.txt", idRemover);

            }
            case 4 -> System.out.println("2- Listrar ingresos e total vendido");
            case 5 ->  imprimirSessao(sc);
            default -> System.out.println("Opção errada");


        }
    }
    // ====== Funcões MenuAdm =====

    public static void logarAdm(Scanner sc){
        Administrador adm = null;
        do {
            System.out.println("Nome: ");
            String nome = sc.nextLine();
            System.out.println("Senha: ");
            String senha = sc.nextLine();

            adm = AdmController.login(nome, senha);

        }while(adm == null);

        iniciarMenuAdmLogado(adm, sc);
    }

    //


    // ====== Função ADM criar sessao =====

    public static void adicionarSessao(Scanner sc){

        try {
            System.out.println("1- Digite o nome do filme");
            String titulo = sc.nextLine();
            System.out.println("2- Digite o genero do filme");
            String genero = sc.nextLine();

            LocalDateTime horarioSessao = null;
            while (horarioSessao == null) {
                try {
                    System.out.println("3- Digite o horario da sessao: (dd/MM/yy HH:mm)");
                    horarioSessao = LocalDateTime.parse(sc.nextLine(), dtf);
                } catch (DateTimeParseException e) {
                    logger.error("Formato de data/hora inválido. Tente novamente.");
                }
            }
            System.out.println("4- Digite o valor da sessao");
            Double valor = sc.nextDouble();
            System.out.println("5- Digite o numero da sessao");
            int n = sc.nextInt();

            Sessao sessao = new Sessao(new Filme(titulo, genero), horarioSessao, valor);
            sessao.setId(n);
            AdmController.criarSessao(sessao);
        } catch (Exception e) {
            logger.error("Ocorreu ao criar sessão");
        }

    }







}
