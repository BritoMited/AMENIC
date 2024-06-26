package org.example;

import org.example.Controllers.AdmController;
import org.example.Views.AdmView;
import org.example.Views.ClienteView;

import java.util.Scanner;
import org.example.Utils.Util;

public class Main {

    public static void main(String[] args) {

        int op = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            exibirMenu();
            op = Util.lerOpcoesMenu(scanner);
            processaEscolhaUsuario(scanner, op);
        } while (op != 0);
    }

    private static void processaEscolhaUsuario(Scanner scanner, int op) {
        switch (op) {
            case 0 -> System.out.println("Programa encerrado");
            case 1 -> ClienteView.iniciarCliente(scanner);
            case 2 -> AdmView.iniciarMenuAdm(scanner);
        }
    }

    private static void exibirMenu() {
        System.out.println("###########################");
        System.out.println("#           MENU          #");
        System.out.println("###########################");
        System.out.println("0 - Sair");
        System.out.println("1 - Cliente");
        System.out.println("2 - Administrador");
        System.out.println("###########################");
    }

}