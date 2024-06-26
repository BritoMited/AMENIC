package org.example.Daos;

import org.example.Models.Cadeira;
import org.example.Models.Filme;
import org.example.Models.Ingresso.Ingresso;
import org.example.Models.Sessao;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IngressosDAO {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

    public static List<Ingresso> listarIngressos(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            // Declaração de variaveis locais do metodo

            String linha = null;

            List<Ingresso> ingressos = new ArrayList<>();
            // Laço de repetição para leitura do arquivo de tarefas

            while ((linha = br.readLine()) != null) {
                var ingresso = parse(linha);

                ingressos.add(ingresso);
            }
            return ingressos;
        } catch (IOException e) {
            //   logger.error("Ocorreu um erro ao tentar ler os dados do arquivo de tarefas");
            return null;
        }
    }

    private static Ingresso parse(String linha) {
        var fields = linha.split(";");
        // Gerando UUID from String
        var uuid = UUID.fromString(fields[0].toString());
        Integer id_sessao = Integer.valueOf(fields[1]);
        UUID id_cliente = UUID.fromString(fields[2].toString());;
        Filme filme = new Filme(fields[3], fields[4]);
        Cadeira cadeira = new Cadeira(fields[5], Boolean.valueOf(fields[6]), Boolean.valueOf(fields[7]));
        LocalDateTime horario = LocalDateTime.parse(fields[7]);
        Double valor = Double.valueOf(fields[8]);

        var ingresso = new Ingresso(id_sessao, id_cliente, filme, cadeira, horario, valor);
        ingresso.setId(uuid);

        return ingresso;
    }

    public static void criarIngressoDAO(String fileName, Ingresso ingresso) {

        //   logger.info("Iniciando a abertura do arquivo " + fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            //       logger.info("Escrendo as informações no arquivo .txt");

            bufferedWriter.write(ingresso.getId()+";"+ingresso.getId_sessao()
                    +";"+ingresso.getId_cliente()
                    +";"+ingresso.getFilme()
                    +";"+ingresso.getCadeira()
                    +";"+ingresso.getHorario()
                    +";"+ingresso.getValor());
            bufferedWriter.newLine();

        } catch (IOException ex) {
            //       logger.error("Ocorreu um erro ao tentar escrever os dados no arquivo " + fileName, ex);
        }
    }

}
