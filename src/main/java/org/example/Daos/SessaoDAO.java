package org.example.Daos;

import org.example.Models.Cadeira;
import org.example.Models.Filme;
import org.example.Models.Sessao;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SessaoDAO {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

    public static List<Sessao> listarSessao(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            // Declaração de variaveis locais do metodo

            String linha = null;

            List<Sessao> sessoes = new ArrayList<>();
            // Laço de repetição para leitura do arquivo de tarefas

            while ((linha = br.readLine()) != null) {
                var sessao = parse(linha);

                sessoes.add(sessao);
            }
            return sessoes;
        } catch (IOException e) {
            //   logger.error("Ocorreu um erro ao tentar ler os dados do arquivo de tarefas");
            return null;
        }
    }









































































































    private static Sessao parse(String linha) {
        String[] fields = linha.split(";", 6); // Divida até o 6º campo
        // Gerando UUID from String
        List<Cadeira> cadeiras = new ArrayList<>();

        Integer id = Integer.valueOf(fields[0]);
        Filme filme = new Filme(fields[1], fields[2]);
        LocalDateTime horario = LocalDateTime.parse(fields[3]);
        Double valor = Double.valueOf(fields[4]);

        // Removendo colchetes e espaços
        String cadeirasString = fields[5].replace("[", "").replace("]", "").trim();
        String[] cadeirasFields = cadeirasString.split(", ");

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

     //   logger.info("Iniciando a abertura do arquivo " + fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
     //       logger.info("Escrendo as informações no arquivo .txt");

            bufferedWriter.write(sessao.getId()+";"+sessao.getFilme()
                                +";"+sessao.getHorario()
                                +";"+sessao.getValor()
                                +";"+sessao.getCadeiras());
            bufferedWriter.newLine();

        } catch (IOException ex) {
     //       logger.error("Ocorreu um erro ao tentar escrever os dados no arquivo " + fileName, ex);
        }
    }

}
