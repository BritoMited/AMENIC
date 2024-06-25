package org.example;

import org.example.Views.AdmView;
import org.example.Views.ClienteView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello world!");
        Scanner scanner = new Scanner(System.in);
        AdmView.iniciarAdm(scanner);

    }
}