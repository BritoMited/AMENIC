package org.example.views;

import java.util.Scanner;

import static org.example.Controllers.ClienteControllers.*;

public class ClienteView{
    public static void MenuCliente(){
        System.out.println("######################");
        System.out.println("#      BEM VINDO     #");
        System.out.println("#     MENU USUARIO    ");
        System.out.println("######################");
        System.out.println("0- sair");
        System.out.println("1- Login");
        System.out.println("2- Registrar");
        System.out.println("######################");

    }

    public static void menuClienteLogando(){
        System.out.println("######################");
        System.out.println("#      BEM VINDO     #");
        System.out.println("#     MENU USUARIO    ");
        System.out.println("######################");
        System.out.println("0- sair");
        System.out.println("1- Usuario");
        System.out.println("2- Senha");
        System.out.println("######################");
    }

    public static void menuCLienteLogado(){
        System.out.println("######################");
        System.out.println("#     MENU USUARIO    ");
        System.out.println("######################");
        System.out.println("0- sair");
        System.out.println("1- Listar sessões disponiveis");
        System.out.println("2- listar cadeiras disponiveis");
        System.out.println("3- Comprar ingresso");
        System.out.println("4- Rembolsar ingresso");
        System.out.println("5- Imprimir ingresso");
        System.out.println("6- Verificar ingresso");
        System.out.println("######################");

    }
    public static void menuClienteRegistrar(){
        System.out.println("######################");
        System.out.println("#     MENU USUARIO    ");
        System.out.println("######################");
        System.out.println("0- Sair");
        System.out.println("1- Usuario");
        System.out.println("2- Senha");
        System.out.println("######################");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //PARTE DAS ESCOLHAS//

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void escolhaMenuCliente(Scanner sc, int op){
        switch (op){
            case 0 -> System.out.println("Saindo....");
            case 1 -> logarUsuario(sc);
            case 2 -> registrarUsuario(sc);
            default -> System.out.println("Opção errada");

//            System.out.println("0- sair");
//            System.out.println("1- Login");
//            System.out.println("2- Registrar");
        }
    }

    public static void escolhamenuClienteLogando(Scanner sc, int op){
        switch (op){
            case 0 -> System.out.println("Saindo....");
            default -> System.out.println("Opção errada");

//            System.out.println("######################");
//            System.out.println("#      BEM VINDO     #");
//            System.out.println("#     MENU USUARIO    ");
//            System.out.println("######################");
//            System.out.println("0- sair");
//            System.out.println("1- Usuario");
//            System.out.println("2- Senha");
//            System.out.println("######################");



        }
    }

    public static void escolhaMenuClienteLogado(Scanner sc, int op){
        switch (op){
            case 0 -> System.out.println("Saindo....");
            case 1 -> System.out.println("Logando....");
            case 2 -> System.out.println("Registrando....");
            case 3 -> System.out.println("Saindo....");
            case 4 -> rembolsarIngresso();
            case 5 -> imprimirIngresso();
            case 6 -> verificarIngressos();
            default -> System.out.println("Opção errada");


//        System.out.println("0- sair");
//        System.out.println("1- Listar sessões disponiveis");
//        System.out.println("2- listar cadeiras disponiveis");
//        System.out.println("3- Comprar ingresso");

    }
    }

    public static void escolhaMenuClienteRegistrar(Scanner sc, int op){
        switch (op){
            case 0 -> System.out.println("Saindo....");
            default -> System.out.println("Opção errada");

//            System.out.println("0- Sair");
//            System.out.println("1- Usuario");
//            System.out.println("2- Senha");

        }
    }


}

