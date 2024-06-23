package org.example.Controllers;

import org.example.Daos.AdmDaos;
import org.example.Exceptions.AdmLoginFailedException;
import org.example.Models.Filme;
import org.example.Models.Sessao;
import org.example.Models.Usuario.Administrador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AdmController {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    private static final String ADM_FILE_NAME = "colocar caminho do arquivo";

    public static List<Administrador> listarAdm(){
        return AdmDaos.listarAdm(ADM_FILE_NAME);
    }

    public static Administrador login(String nome, String senha) throws AdmLoginFailedException {

        try{
            var listaAdm = listarAdm();

            for(Administrador adm: listaAdm){
                if(nome.equals(adm.getNome()) && senha.equals(adm.getSenha())){
                    return adm;
                }
            }

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        throw new AdmLoginFailedException("Nome ou senha incorretos");

    }

    public void registrar() {

    }

    public static void adicionarFilme(Scanner sc){
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

        Sessao algumascoisa = new Sessao(new Filme(titulo,genero), horarioSessao, valor);

    }

    public static void listarFilme(){

    }

    public static void alterarFilme(){

    }

    public static void removerFilme(){

    }
}
