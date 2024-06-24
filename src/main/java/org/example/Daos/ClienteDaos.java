package org.example.Daos;


import org.example.Models.Usuario.Cliente;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ClienteDaos {

    public static void registrarClienteDao(String fileName, Cliente c) {

        // logger.info("Iniciando a abertura do arquivo " + fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            // logger.info("Escrendo as informações no arquivo .txt");

                bufferedWriter.write(c.getId()+";"+c.getNome()+";"+c.getSenha());
                bufferedWriter.newLine();

        } catch (IOException ex) {
            //  logger.error("Ocorreu um erro ao tentar escrever os dados no arquivo " + fileName, ex);
        }
    }





}





