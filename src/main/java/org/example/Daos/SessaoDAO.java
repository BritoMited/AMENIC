package org.example.Daos;

import org.example.Models.Filme;
import org.example.Models.Sessao;
import org.example.Models.Usuario.Administrador;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SessaoDAO {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

    public static List<Sessao> listarSessao(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            // Declaração de variaveis locais do metodo

            String linha = null;

            List<Sessao> sessoes = new ArrayList<>();
            // Laço de repetição para leitura do arquivo de tarefas

            while ((linha = br.readLine()) != null) {
                var adm = parse(linha);

                sessoes.add(adm);
            }
            return sessoes;
        } catch (IOException e) {
            //   logger.error("Ocorreu um erro ao tentar ler os dados do arquivo de tarefas");
            return null;
        }
    }

    private static Sessao parse(String linha) {
        var fields = linha.split(";");
        // Gerando UUID from String

        Integer id = Integer.valueOf(fields[0]);
        Filme filme = new Filme(fields[1], fields[2]);
        LocalDateTime horario = LocalDateTime.parse(fields[3], dtf);
        Double valor = Double.valueOf(fields[4]);

        var sessao = new Sessao(filme, horario, valor);
        sessao.setId(id);

        return sessao;
    }

    public static void criarSessaoDAO(String fileName, Sessao sessao) {

     //   logger.info("Iniciando a abertura do arquivo " + fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
     //       logger.info("Escrendo as informações no arquivo .txt");

            bufferedWriter.write(sessao.getId()+";"+sessao.getFilme()+";"+sessao.getHorario()
                                +";"+sessao.getValor());
            bufferedWriter.newLine();

        } catch (IOException ex) {
     //       logger.error("Ocorreu um erro ao tentar escrever os dados no arquivo " + fileName, ex);
        }
    }

}
