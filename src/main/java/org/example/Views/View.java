package org.example.Views;

import java.util.Scanner;

public class View {
    public static void menuPrincipal(){
        System.out.println("######################");
        System.out.println("#      BEM VINDO     #");
        System.out.println("#   Cinema BritoFlix  ");
        System.out.println("######################");
        System.out.println("0- sair");
        System.out.println("1- Entrar menu usuario");
        System.out.println("2- Entrar menu ADM");
        System.out.println("######################");
    }

    public static void escolhaMenuPrincipal(Scanner sc, int op){
        switch (op){
            case 0 -> System.out.println("Saindo....");
            case 1 -> System.out.println("Logando....");
            case 2 -> System.out.println("Logando....");
            default -> System.out.println("Opção errada");

//            System.out.println("0- sair");
//            System.out.println("1- Entrar menu usuario");
//            System.out.println("2- Entrar menu ADM");



        }
    }






}
