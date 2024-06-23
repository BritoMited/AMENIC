package org.example.Daos;

import org.example.Models.Usuario.Administrador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdmDaos {

    // COLOCAR CAMINHO DO ARQUIVO

    public static List<Administrador> listarAdm(String fileName) {
     //   logger.info("Iniciando a leitura dos dados de tarefas");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            // Declaração de variaveis locais do metodo

            String linha = null;

            List<Administrador> adms = new ArrayList<>();
            // Laço de repetição para leitura do arquivo de tarefas

            while ((linha = br.readLine()) != null) {
                var adm = parse(linha);

                adms.add(adm);
            }
            return adms;
        } catch (IOException e) {
         //   logger.error("Ocorreu um erro ao tentar ler os dados do arquivo de tarefas");
            return null;
        }
    }

    private static Administrador parse(String linha) {
        var fields = linha.split(";");
        // Gerando UUID from String
        var uuid = UUID.fromString(fields[0].toString());

        var adm = new Administrador(fields[1], fields[2]);
        adm.setId(uuid);

        return adm;
    }

    public static void registrarAdm(String fileName, List<Administrador> listaDados) {

       // logger.info("Iniciando a abertura do arquivo " + fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
           // logger.info("Escrendo as informações no arquivo .txt");
            for (Administrador d : listaDados) {
                bufferedWriter.write(d.getId()+";"+d.getNome()+";"+d.getSenha());
                bufferedWriter.newLine();
            }
        } catch (IOException ex) {
          //  logger.error("Ocorreu um erro ao tentar escrever os dados no arquivo " + fileName, ex);
        }
    }
}
