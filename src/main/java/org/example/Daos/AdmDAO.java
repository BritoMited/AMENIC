package org.example.Daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Models.Usuario.Administrador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdmDAO {

    private static final Logger logger = LogManager.getLogger(ClienteDAO.class);

    public static List<Administrador> listarAdm(String fileName) {
     logger.info("Iniciando a leitura dos dados de tarefas");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            // Declaração de variaveis locais do metodo

            String linha = null;

            List<Administrador> adms = new ArrayList<>();
            // Laço de repetição para leitura do arquivo de tarefas

            while ((linha = br.readLine()) != null) {
                logger.info("Iniciando a leitura das linhas");
                var adm = parse(linha);

                adms.add(adm);
            }
            return adms;
        } catch (IOException e) {
         logger.error("Ocorreu um erro ao tentar ler os dados do arquivo de tarefas");
            return null;
        }
    }

    private static Administrador parse(String linha) {
        logger.info("Iniciando o parse e split");
        var fields = linha.split(";");
        // Gerando UUID from String
        var uuid = UUID.fromString(fields[0].toString());

        var adm = new Administrador(fields[1], fields[2]);
        adm.setId(uuid);

        return adm;


    }

}
