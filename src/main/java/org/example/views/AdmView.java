package org.example.views;

import java.util.Scanner;

import static org.example.Controllers.AdmController.adicionarFilme;

public class AdmView {

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
        System.out.println("2- Listrar ingresos e total vendido");
        System.out.println("3- imprimir ingresos");
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


    public static void escolhaMenuAdm(Scanner sc, int op){
        switch (op){
            case 0 -> System.out.println("Saindo....");
            case 1 -> System.out.println("Logando....");
            default -> System.out.println("Opção errada");

//            System.out.println("0- sair");
//            System.out.println("1- Login");

        }
    }

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
            case 1 -> adicionarFilme(sc);
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







}
