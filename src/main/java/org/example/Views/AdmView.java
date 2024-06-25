package org.example.Views;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Controllers.AdmController;
import org.example.Models.Filme;
import org.example.Models.Sessao;
import org.example.Models.Usuario.Administrador;
import org.example.Utils.Util;

public class AdmView {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    private static final Logger logger = LogManager.getLogger(AdmView.class);

    public static void iniciarAdm(Scanner scanner) {
        int op;
        do {
            menuAdm();
            op = Util.lerOpcoesMenu(scanner);
            escolhaMenuAdm(scanner, op);
        } while (op != 0);
    }

    public static void menuAdm(){
        System.out.println("######################");
        System.out.println("#      BEM VINDO     #");
        System.out.println("#      MENU ADM      ");
        System.out.println("######################");
        System.out.println("0- sair");
        System.out.println("1- Login");
        System.out.println("######################");
    }

    public static void menuAdmLogado(Administrador adm){
        System.out.println("######################");
        System.out.println("#      BEM VINDO     #");
        System.out.println("#      MENU ADM      ");
        System.out.println("######################");
        System.out.println("0- sair");
        System.out.println("1- Criar sessão");
        System.out.println("2- Listrar ingresos e total vendido");
        System.out.println("3- Imprimir ingresos");
        System.out.println("######################");

    }

    public static void menuAdmCriarSessao(){

        System.out.println("######################");
        System.out.println("#      BEM VINDO     #");
        System.out.println("#      MENU ADM      ");
        System.out.println("######################");
        System.out.println("0- sair");
        System.out.println("1- Criar Sessao");
        System.out.println("######################");
    }


    public static void escolhaMenuAdm(Scanner sc, int op) {
        switch (op){
            case 0 -> System.out.println("Saindo....");
            case 1 -> logarAdm(sc);
            case 2 -> adicionarSessao(sc);
            default -> System.out.println("Opção errada");

//            System.out.println("0- sair");
//            System.out.println("1- Login");

        }
    }
    // ====== Funcões MenuAdm =====

    public static void logarAdm(Scanner sc){

        System.out.println("Nome: ");
        String nome = sc.nextLine();
        System.out.println("Senha: ");
        String senha = sc.nextLine();

        //se logado ele vai passar como parametro para usar nessa função
        menuAdmLogado(AdmController.login(nome, senha));

    }

    //

    public static void escolhaMenuAdmLogado(Scanner sc, int op){
        switch (op){
            case 0 -> System.out.println("Saindo....");
            case 1 -> System.out.println("Logando....");
            case 2 -> System.out.println("Saindo....");
            case 3 -> System.out.println("Logando....");
            default -> System.out.println("Opção errada");

//            System.out.println("0- sair");
//            System.out.println("1- Criar sessão");
//            System.out.println("2- Listrar ingresos e total vendido");
//            System.out.println("3- imprimir ingresos");

        }
    }

    public static void escolhaMenuAdmCriarSessao(Scanner sc, int op){
        switch (op){
            case 0 -> System.out.println("Saindo....");
            case 1 -> adicionarSessao(sc);
            default -> System.out.println("Opção errada");

//            System.out.println("0- sair");
//            System.out.println("1- Digite o nome do filme");
//            System.out.println("2- Digite o genero do filme");
//            System.out.println("3- Digite o horario da sessao: (dd/mm/yy HH:mm)");
//            System.out.println("4- Digite o valor da sessao");
//            System.out.println("5- Digite o numero da sessao");
//            System.out.println("######################");

        }
    }

    // ====== Função ADM criar sessao =====

    public static void adicionarSessao(Scanner sc){
        System.out.println("1- Digite o nome do filme");
        String titulo = sc.nextLine();
        System.out.println("2- Digite o genero do filme");
        String genero = sc.nextLine();
        System.out.println("3- Digite o horario da sessao: (dd/mm/yy HH:mm)");
        LocalDateTime horarioSessao = LocalDateTime.parse(sc.nextLine(), dtf);
        System.out.println("4- Digite o valor da sessao");
        Double valor = sc.nextDouble();
        System.out.println("5- Digite o numero da sessao");
        int n = sc.nextInt();

        Sessao sessao = new Sessao(new Filme(titulo,genero), horarioSessao, valor);
        sessao.setId(n);

        AdmController.criarSessao(sessao);
    }







}
