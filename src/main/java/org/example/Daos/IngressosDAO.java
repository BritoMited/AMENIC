package org.example.Daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Controllers.AdmController;
import org.example.Exceptions.ClienteException;
import org.example.Models.Cadeira;
import org.example.Models.Filme;
import org.example.Models.Ingresso.Ingresso;
import org.example.Models.Sessao;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.example.Controllers.AdmController.SESSAO_FILE_NAME;

public class IngressosDAO {

    private static final Logger logger = LogManager.getLogger(IngressosDAO.class);
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

    public static List<Ingresso> listarIngressos(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            logger.info("Lendo arquivo");

            // Declaração de variaveis locais do metodo

            String linha = null;

            List<Ingresso> ingressos = new ArrayList<>();
            // Laço de repetição para leitura do arquivo de tarefas

            while ((linha = br.readLine()) != null) {
                var ingresso = parse(linha);
                logger.info("Lendo as linhas do arquivo");

                ingressos.add(ingresso);
            }
            return ingressos;
        } catch (IOException e) {
            logger.error("Ocorreu um erro ao tentar ler os dados do arquivo de tarefas");
            return null;
        }
    }

    private static Ingresso parse(String linha) {
        var fields = linha.split(";");
        // Gerando UUID from String
        var uuid = UUID.fromString(fields[0].toString());
        Integer id_sessao = Integer.valueOf(fields[1]);
        UUID id_cliente = UUID.fromString(fields[2].toString());
        Filme filme = new Filme(fields[3], fields[4]);
        Cadeira cadeira = new Cadeira(fields[5], Boolean.valueOf(fields[6]), Boolean.valueOf(fields[7]));
        LocalDateTime horario = LocalDateTime.parse(fields[8], dtf);
        Double valor = Double.valueOf(fields[9]);
        logger.info("Separando informações no arquivo");

        var ingresso = new Ingresso(id_sessao, id_cliente, filme, cadeira, horario, valor);
        ingresso.setId(uuid);

        return ingresso;
    }

    public static void criarIngressoDAO(String fileName, Ingresso ingresso) {

          logger.info("Iniciando a abertura do arquivo " + fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            logger.info("Escrendo as informações no arquivo .txt");

            bufferedWriter.write(ingresso.getId()+";"+ingresso.getId_sessao()
                    +";"+ingresso.getId_cliente()
                    +";"+ingresso.getFilme()
                    +";"+ingresso.getCadeira()
                    +";"+ingresso.getHorario().format(dtf)
                    +";"+ingresso.getValor());
            bufferedWriter.newLine();

        } catch (IOException ex) {
                   logger.error("Ocorreu um erro ao tentar escrever os dados no arquivo " + fileName, ex);
        }
    }

    public static  List<Ingresso> buscarIngressoPorIdClienteDao(String fileName, UUID id_cliente, Integer id_sessao) {
        try{
            List<Ingresso> listaIngresso = listarIngressos(fileName);
            List<Ingresso> listaRetorno = new ArrayList<>();

            for (Ingresso ingresso : listaIngresso) {

                if (ingresso.getId_cliente().equals(id_cliente) && ingresso.getId_sessao().equals(id_sessao)){
                    listaRetorno.add(ingresso);
                }

            }

            if(listaIngresso != null) return listaRetorno;
            throw new ClienteException("ingresso nao encontrado");
        }catch(ClienteException e){
            logger.error("Erro: " + e.getMessage());
        }
        return null;
    }

    public static  List<Ingresso> buscarIngressoPorIdDao(String fileName, Integer id_sessao) {
        try{
            List<Ingresso> listaIngresso = listarIngressos(fileName);
            List<Ingresso> listaRetorno = new ArrayList<>();

            for (Ingresso ingresso : listaIngresso) {

                if (ingresso.getId_sessao().equals(id_sessao)){
                    listaRetorno.add(ingresso);
                }

            }

            if(listaIngresso != null) return listaRetorno;
            throw new ClienteException("ingresso nao encontrado");
        }catch(ClienteException e){
            logger.error("Erro: " + e.getMessage());
        }
        return null;
    }


    public static void removerIngressoDao(String fileName, UUID id_cliente, Integer id_sessao){

        List<Ingresso> listaIngresso = listarIngressos(fileName);


        Iterator<Ingresso> iterator = listaIngresso.iterator();
        while (iterator.hasNext()) {
            Ingresso ingresso = iterator.next();
            if (ingresso.getId_cliente().equals(id_cliente) && ingresso.getId_sessao().equals(id_sessao)) {

                Sessao sessao = AdmController.buscarSessaoPorId(ingresso.getId_sessao());
                sessao.desocupar(ingresso.getCadeira().getNumero());
                SessaoDAO.alterarSessaoDao(SESSAO_FILE_NAME, sessao);

                iterator.remove();
            }
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Ingresso ingresso : listaIngresso) {
                bufferedWriter.write(ingresso.getId()+";"+ingresso.getId_sessao()
                        +";"+ingresso.getId_cliente()
                        +";"+ingresso.getFilme()
                        +";"+ingresso.getCadeira()
                        +";"+ingresso.getHorario().format(dtf)
                        +";"+ingresso.getValor());
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao reescrever os dados no arquivo " + fileName);
        }

    }

}
