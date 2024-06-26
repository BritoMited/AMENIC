package org.example.Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Daos.AdmDAO;
import org.example.Daos.IngressosDAO;
import org.example.Daos.SessaoDAO;
import org.example.Exceptions.AdmException;
import org.example.Exceptions.SessaoException;
import org.example.Models.Ingresso.Ingresso;
import org.example.Models.Sessao;
import org.example.Models.Usuario.Administrador;

import java.util.List;

import static org.example.Controllers.ClienteControllers.INGRESSO_FILE_NAME;

public class AdmController {

//criar uma ex disso para arquivo nao encontrado

    private static final Logger logger = LogManager.getLogger(AdmController.class);
    private static final String ADM_FILE_NAME = "C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\AMENIC\\AM3NIC\\src\\main\\java\\org\\example\\TXT\\Administrador\\Administrador.txt";
    public static final String SESSAO_FILE_NAME = "C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\AMENIC\\AM3NIC\\src\\main\\java\\org\\example\\TXT\\Sessao\\Sessao.txt";

    public static List<Administrador> listarAdm(){
        return AdmDAO.listarAdm(ADM_FILE_NAME);
    }

    public static List<Sessao> listarSessao(){
        return SessaoDAO.listarSessao(SESSAO_FILE_NAME);
    }

    public static List<Ingresso> listarIngressos(){
        return IngressosDAO.listarIngressos(INGRESSO_FILE_NAME);
    }

    public static void listarTotalIngressoPorSessao(Integer id_sessao){
        List<Ingresso> ingressoList = IngressosDAO.buscarIngressoPorIdDao(INGRESSO_FILE_NAME, id_sessao);
        Double total = 0.00;

        int j = 0;

        for (Ingresso i : ingressoList){
            j++;
            total += i.getValor();
        }
        System.out.println();
        System.out.println("TOTAL VENDIDO DA SESSÃO " + id_sessao);
        System.out.println("Total de Ingressos: " + j);
        System.out.println("Soma total: R$" + total);
        System.out.println();
        System.out.println();
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
            logger.error("Error: " + e.getMessage());
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
            logger.error("Error: " + e.getMessage());
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
            logger.error("Erro:" + e.getMessage());
        }
        return null;
    }
}
