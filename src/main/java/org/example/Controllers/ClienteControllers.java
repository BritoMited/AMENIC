package org.example.Controllers;

import org.example.Daos.AdmDAO;
import org.example.Daos.ClienteDAO;
import org.example.Daos.IngressosDAO;
import org.example.Exceptions.AdmLoginFailedException;
import org.example.Exceptions.ClienteLoginFailedException;
import org.example.Models.Ingresso.Ingresso;
import org.example.Models.Usuario.Administrador;
import org.example.Models.Usuario.Cliente;

import java.util.List;
import java.util.Scanner;

public class ClienteControllers {


    private static final String CLIENTE_FILE_NAME = "C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\AMENIC\\AM3NIC\\src\\main\\java\\org\\example\\TXT\\Cliente\\Cliente.txt";
    private static final String INGRESSO_FILE_NAME = "C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\AMENIC\\AM3NIC\\src\\main\\java\\org\\example\\TXT\\Ingressos\\Ingressos.txt";

    public static List<Cliente> listarCliente(){
        return ClienteDAO.listarCliente(CLIENTE_FILE_NAME);
    }

    public static List<Ingresso> listarIngresso(){
        return IngressosDAO.listarIngressos(INGRESSO_FILE_NAME);
    }

    public static Cliente logarUsuario(String nome, String senha){

        try{
            var listaCliente = listarCliente();

            for(Cliente cliente: listaCliente){
                if(nome.equals(cliente.getNome()) && senha.equals(cliente.getSenha())){
                    return cliente;
                }
            }

            throw new ClienteLoginFailedException("falha ao logar");

        }catch(ClienteLoginFailedException e){
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }
    public static void registrarUsuario(Cliente novoCliente){

          ClienteDAO.registrarClienteDao(CLIENTE_FILE_NAME, novoCliente);

        System.out.println("Registro realizado!");
    }


    public static void rembolsarIngresso(){

    }
    public static void imprimirIngresso(){

    }
    public static void verificarIngressos(){

    }



}
