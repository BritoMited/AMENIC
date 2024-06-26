package org.example.Controllers;

import org.example.Daos.AdmDAO;
import org.example.Daos.SessaoDAO;
import org.example.Exceptions.AdmException;
import org.example.Exceptions.SessaoException;
import org.example.Models.Sessao;
import org.example.Models.Usuario.Administrador;

import java.util.List;

public class AdmController {


    private static final String ADM_FILE_NAME = "C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\AMENIC\\AM3NIC\\src\\main\\java\\org\\example\\TXT\\Administrador\\Administrador.txt";
    private static final String SESSAO_FILE_NAME = "C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\AMENIC\\AM3NIC\\src\\main\\java\\org\\example\\TXT\\Sessao\\Sessao.txt";

    public static List<Administrador> listarAdm(){
        return AdmDAO.listarAdm(ADM_FILE_NAME);
    }

    public static List<Sessao> listarSessao(){
        return SessaoDAO.listarSessao(SESSAO_FILE_NAME);
    }

    public static void printListaSessao(){
        for(Sessao s : listarSessao()){
            System.out.println(s);
        }
    }

    public static Administrador login(String nome, String senha){

        try{
            var listaAdm = listarAdm();

            for(Administrador adm: listaAdm){
                if(nome.equals(adm.getNome()) && senha.equals(adm.getSenha())){
                    return adm;
                }
            }
            throw new AdmException("falha ao logar");

        }catch(AdmException e){
            System.out.println("Error: " + e.getMessage());
        }


        return null;
    }

    public static void criarSessao(Sessao sessao){

        try{
            var listaSessoes = listarSessao();

            for(Sessao s: listaSessoes){
                if(s.getId().equals(sessao.getId())){
                    throw new SessaoException("já existe uma sessao com esse id");
                }
            }
            sessao.gerarCadeiras(8, 8);
            SessaoDAO.criarSessaoDAO(SESSAO_FILE_NAME, sessao);

        }catch(SessaoException e){
            System.out.println("Error: " + e.getMessage());
        }

    }
    public static Sessao buscarSessaoPorId(Integer id){
        try{
            for (Sessao s : listarSessao()){
                if(s.getId().equals(id)){
                    return s;
                }
            }
            throw new SessaoException("Sessao nao encotrada");

        }catch (SessaoException e){
            System.out.println("Erro:" + e.getMessage());
        }
        return null;
    }
}
