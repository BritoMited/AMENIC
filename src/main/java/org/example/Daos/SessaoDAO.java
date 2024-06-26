package org.example.Daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Models.Cadeira;
import org.example.Models.Filme;
import org.example.Models.Sessao;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SessaoDAO {

    private static final Logger logger = LogManager.getLogger(SessaoDAO.class);
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

    public static List<Sessao> listarSessao(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            logger.info("Lendo arquivo");

            // Declaração de variaveis locais do metodo

            String linha = null;

            List<Sessao> sessoes = new ArrayList<>();
            // Laço de repetição para leitura do arquivo de tarefas

            while ((linha = br.readLine()) != null) {
                var sessao = parse(linha);
                logger.info("Lendo linha do arquivo");

                sessoes.add(sessao);
            }
            return sessoes;
        } catch (IOException e) {
            logger.error("Ocorreu um erro ao tentar ler os dados do arquivo de tarefas");
            return null;
        }
    }

    private static Sessao parse(String linha) {
        String[] fields = linha.split(";", 6); // Divida até o 6º campo
        logger.info("iniciando a separação informações no arquivo");
        List<Cadeira> cadeiras = new ArrayList<>();

        Integer id = Integer.valueOf(fields[0]);
        Filme filme = new Filme(fields[1], fields[2]);
        LocalDateTime horario = LocalDateTime.parse(fields[3], dtf);
        Double valor = Double.valueOf(fields[4]);

        // Removendo colchetes e espaços
        String cadeirasString = fields[5].replace("[", "").replace("]", "").trim();
        String[] cadeirasFields = cadeirasString.split(", ");
        logger.info("Separando informações no arquivo");

        for (String cadeiraField : cadeirasFields) {
            String[] cadeiraInfo = cadeiraField.split(";");
            if (cadeiraInfo.length == 3) {
                Cadeira cadeira = new Cadeira();
                cadeira.setNumero(cadeiraInfo[0]);
                cadeira.setPcd(Boolean.valueOf(cadeiraInfo[1]));
                cadeira.setOcupado(Boolean.valueOf(cadeiraInfo[2]));
                cadeiras.add(cadeira);
            }
        }

        Sessao sessao = new Sessao(filme, horario, valor);
        sessao.setId(id);
        sessao.setListaCadeiras(cadeiras);

        return sessao;
    }

    public static void criarSessaoDAO(String fileName, Sessao sessao) {

     logger.info("Iniciando a abertura do arquivo " + fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            logger.info("Escrendo as informações no arquivo .txt");

            bufferedWriter.write(sessao.getId()+";"+sessao.getFilme()
                                +";"+ sessao.getHorario().format(dtf)
                                +";"+sessao.getValor()
                                +";"+sessao.getCadeiras());
            bufferedWriter.newLine();

        } catch (IOException ex) {
            logger.error("Ocorreu um erro ao tentar escrever os dados no arquivo " + fileName, ex);
        }
    }

    public static void alterarSessaoDao(String fileName, Sessao sessaoAlterada) {
        logger.info("Iniciando a alteração do arquivo " + fileName);

        List<Sessao> listaSessoes = listarSessao(fileName);


        for (int i = 0; i < listaSessoes.size(); i++) {
            Sessao sessao = listaSessoes.get(i);
            if (sessao.getId().equals(sessaoAlterada.getId())) {
                listaSessoes.set(i, sessaoAlterada);
                logger.info("setando o id para a alteração do arquivo " + fileName);
                break;
            }
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Sessao sessao : listaSessoes) {
                bufferedWriter.write( sessao.getId() + ";" + sessao.getFilme()+ ";"
                        + sessao.getHorario().format(dtf) + ";" +
                        sessao.getValor()
                        +";"+sessao.getCadeiras());
                logger.info("Iniciando a alteração do arquivo " + fileName);
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao reescrever os dados no arquivo " + fileName);
        }
    }

    public static void removerSessaoDao(String fileName, int idRemover){
        List<Sessao> listaSessoes = listarSessao(fileName);
        logger.info("Iniciando a remoção do arquivo " + fileName);

        for (int i = 0; i < listaSessoes.size(); i++) {
            Sessao sessao = listaSessoes.get(i);
            if (sessao.getId().equals(idRemover)){
                listaSessoes.remove(i);
                logger.info("setando o id para a alteração do arquivo " + fileName);
                break;
            }

        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Sessao sessao : listaSessoes) {
                bufferedWriter.write( sessao.getId() + ";" + sessao.getFilme()+
                        ";" + sessao.getHorario().format(dtf) + ";" +
                        sessao.getValor()
                        +";"+sessao.getCadeiras());
                logger.info("Iniciando a remoção do arquivo " + fileName);
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao reescrever os dados no arquivo " + fileName);
        }

    }

}
