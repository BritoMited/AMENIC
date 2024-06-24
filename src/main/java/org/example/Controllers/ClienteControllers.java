package org.example.Controllers;

import org.example.Daos.ClienteDaos;
import org.example.Models.Usuario.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteControllers {


    private static final String CLIENTE_FILE_NAME = "COLOCAR ARQUIVO";

    public static void logarUsuario(Scanner sc){

        System.out.println("1- Usuario");
        String nome = sc.nextLine();
        System.out.println("2- Senha");
        String senha = sc.nextLine();

    }
    public static void registrarUsuario(Scanner sc){

          System.out.println("1- Usuario");
          String nome = sc.nextLine();
          System.out.println("2- Senha");
          String senha = sc.nextLine();
          System.out.println("3- Idade");
          int idade = sc.nextInt();
          System.out.println("4- Estudante(true/false)");
          boolean estudante = sc.nextBoolean();
          System.out.println("5- PCD (true/flase)");
          boolean pcd = sc.nextBoolean();

          Cliente novoCliente = new Cliente(nome, senha, idade, estudante, pcd);
          List<Cliente> listaCliente = new ArrayList<>();
          listaCliente.add(novoCliente);
          ClienteDaos.registrarClienteDao(CLIENTE_FILE_NAME, listaCliente);

        System.out.println("Registro realizado!");
    }


    public static void rembolsarIngresso(){

    }
    public static void imprimirIngresso(){

    }
    public static void verificarIngressos(){

    }



}
