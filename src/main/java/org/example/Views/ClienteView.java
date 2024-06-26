package org.example.Views;

import org.example.Controllers.AdmController;
import org.example.Controllers.ClienteControllers;
import org.example.Models.Sessao;
import org.example.Models.Usuario.Administrador;
import org.example.Models.Usuario.Cliente;
import org.example.Utils.Util;

import java.util.Scanner;

import static org.example.Controllers.ClienteControllers.*;

public class ClienteView{

    public static void iniciarCliente(Scanner scanner) {
        int op;
        do {
            menuCliente();
            op = Util.lerOpcoesMenu(scanner);
            escolhaMenuCliente(scanner, op);
        } while (op != 0);
    }

    public static void iniciarClienteLogado(Cliente cliente, Scanner sc) {
        int op;
        do {
            menuCLienteLogado();
            op = Util.lerOpcoesMenu(sc);
            escolhaMenuClienteLogado(cliente, sc, op);
        } while (op != 0);
    }

    public static void iniciarClienteSessaoLogado(Cliente cliente, Scanner sc) {
        AdmController.printListaSessao();
        int op;
        do {
            menuCLienteSessaoLogado();
            op = Util.lerOpcoesMenu(sc);
            escolhaMenuClienteSessaoLogado(cliente, sc, op);
        } while (op != 0);
    }

    public static void menuCliente(){
        System.out.println("######################");
        System.out.println("#      BEM VINDO     #");
        System.out.println("#     MENU USUARIO    ");
        System.out.println("######################");
        System.out.println("0- sair");
        System.out.println("1- Login");
        System.out.println("2- Registrar");
        System.out.println("######################");

    }

    public static void menuCLienteLogado(){
        System.out.println("######################");
        System.out.println("#     MENU USUARIO    ");
        System.out.println("######################");
        System.out.println("0- sair");
        System.out.println("1- Listar sessões disponiveis");
        System.out.println("4- Rembolsar ingresso");
        System.out.println("5- Imprimir ingresso");
        System.out.println("6- Verificar ingresso");
        System.out.println("######################");

    }

    public static void menuCLienteSessaoLogado(){
        System.out.println("######################");
        System.out.println("#     MENU USUARIO    ");
        System.out.println("######################");
        System.out.println("0- sair");
        System.out.println("1- listar cadeiras disponiveis");
        System.out.println("2- Comprar ingresso");
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

        }
    }

    public static void logarUsuario(Scanner sc){

        Cliente cliente = null;
        do {
            System.out.println("Nome: ");
            String nome = sc.nextLine();
            System.out.println("Senha: ");
            String senha = sc.nextLine();

            cliente = ClienteControllers.logarUsuario(nome, senha);

        }while(cliente == null);

        iniciarClienteLogado(cliente, sc);

    }

    public static void registrarUsuario(Scanner sc){

        System.out.println("Nome de usuario: ");
        String nome = sc.nextLine();
        System.out.println("Senha: ");
        String senha = sc.nextLine();
        System.out.println("Idade: ");
        int idade = sc.nextInt();
//          System.out.println("4- Estudante(true/false)");
//          boolean estudante = sc.nextBoolean();
//          System.out.println("5- PCD (true/false)");
//          boolean pcd = sc.nextBoolean();

        Cliente novoCliente = new Cliente(nome, senha, idade, true, true);

        ClienteControllers.registrarUsuario(novoCliente);

    }


    public static void escolhaMenuClienteLogado(Cliente cliente, Scanner sc, int op){
        switch (op){
            case 0 -> System.out.println("Saindo....");
            case 1 -> iniciarClienteSessaoLogado(cliente, sc);
            case 2 -> registrarUsuario(sc);
            case 3 -> rembolsarIngresso();
            case 4 -> imprimirIngresso();
            case 5 -> verificarIngressos();
            default -> System.out.println("Opção errada");


//        System.out.println("0- sair");
//        System.out.println("1- Listar sessões disponiveis");
//        System.out.println("2- listar cadeiras disponiveis");
//        System.out.println("3- Comprar ingresso");

        }
    }

    public static void escolhaMenuClienteSessaoLogado(Cliente cliente, Scanner sc, int op){

        switch (op){
            case 0 -> System.out.println("Saindo....");
            case 1 -> System.out.println("Logando....");
            case 2 -> System.out.println("Saindo....");
            default -> System.out.println("Opção errada");


//        System.out.println("0- sair");
//        System.out.println("1- Listar sessões disponiveis");
//        System.out.println("2- listar cadeiras disponiveis");
//        System.out.println("3- Comprar ingresso");

        }
    }

}

