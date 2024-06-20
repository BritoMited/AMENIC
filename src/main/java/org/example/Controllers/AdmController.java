package org.example.Controllers;

import org.example.Models.Filme;
import org.example.Models.Sessao;

import java.util.Scanner;

public class AdmController {

    public static void adicionarFilme(Scanner sc){
        System.out.println("1- Digite o nome do filme");
        String titulo = sc.nextLine();
        System.out.println("2- Digite o genero do filme");
        String genero = sc.nextLine();
        System.out.println("3- Digite o horario da sessao: (dd/mm/yy HH:mm)");
        String data = sc.nextLine();
        System.out.println("4- Digite o valor da sessao");
        Double valor = sc.nextDouble();
        System.out.println("5- Digite o numero da sessao");
        int n = sc.nextInt();

        Sessao algumascoisa = new Sessao(new Filme(titulo,genero) , data, valor);

    }

    public static void listarFilme(){

    }

    public static void alterarFilme(){

    }

    public static void removerFilme(){

    }
}
