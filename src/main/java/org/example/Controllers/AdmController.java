package org.example.Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Daos.AdmDAO;
import org.example.Daos.IngressosDAO;
import org.example.Daos.SessaoDAO;
import org.example.Exceptions.AdmException;
import org.example.Exceptions.SessaoException;
import org.example.Models.FileManager;
import org.example.Models.Ingresso.Ingresso;
import org.example.Models.Sessao;
import org.example.Models.Usuario.Administrador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import static org.example.Controllers.ClienteControllers.INGRESSO_FILE_NAME;
import static org.example.Daos.IngressosDAO.buscarIngressoPorIdDao;
import static org.example.Views.AdmView.dtf;

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

    public static void imprimirSessao(Scanner sc) {
        FileManager fileManager = new FileManager();


        File diretorio = fileManager.criarPasta("C:\\Estudosjava\\Cinemjav\\AMENIC\\src\\main\\java\\org\\example\\DiretorioADM");

        System.out.println("Digite o número da sessão que deseja imprimir o ingresso: ");
        Integer id_sessao = sc.nextInt();

        List<Ingresso> ingressos = buscarIngressoPorIdDao (SESSAO_FILE_NAME , id_sessao);

        for (Ingresso ingresso : ingressos) {
            try {
                File file = fileManager.criarArquivo(diretorio, "Sessão" + ingresso.getId() + ".txt");
                FileWriter fileWriter = new FileWriter(file, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);

                printWriter.println("### Ingresso ###");
                printWriter.println("Id do Ingresso: " + ingresso.getId());
                printWriter.println("Id da sessão: " + ingresso.getId_sessao());
                printWriter.println("Titulo do filme: " + ingresso.getFilme().getTitulo());
                printWriter.println("Gênero: " + ingresso.getFilme().getGenero());
                printWriter.println("Data da sessão: " + ingresso.getHorario().format(dtf));
                printWriter.println("Cadeira: " + ingresso.getCadeira().getNumero());
                printWriter.println("### Ingresso ###");

                printWriter.close();
                System.out.println("Ingresso impresso com sucesso em: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Erro ao imprimir ingresso: ");
            }
        }
    }


}
