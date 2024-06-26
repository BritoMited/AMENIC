package org.example.Daos;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Models.Usuario.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ClienteDAO {
    private static final Logger logger = LogManager.getLogger(ClienteDAO.class);
    public static List<Cliente> listarCliente(String fileName) {
        logger.info("Iniciando a leitura dos dados de tarefas");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            logger.info("Iniciando a leitura do arquivo");

            // Declaração de variaveis locais do metodo

            String linha = null;

            List<Cliente> clientes = new ArrayList<>();
            logger.info("Iniciando o array list de Cliente");
            // Laço de repetição para leitura do arquivo de tarefas

            while ((linha = br.readLine()) != null) {
                var cliente = parse(linha);
                logger.info("Adicionadno cliente ao DAO");

                clientes.add(cliente);
            }
            return clientes;
        } catch (IOException e) {
             logger.error("Ocorreu um erro ao tentar ler os dados do arquivo de tarefas");
            return null;
        }
    }

    private static Cliente parse(String linha) {
        var fields = linha.split(";");
        // Gerando UUID from String
        var uuid = UUID.fromString(fields[0].toString());

        String nome = fields[1];
        String senha = fields[2];
        Integer idade = Integer.valueOf(fields[3]);
        Boolean estudande = Boolean.valueOf(fields[4]);
        Boolean pcd = Boolean.valueOf(fields[5]);

        logger.info("salvando fields");

        var cliente = new Cliente(nome, senha, idade, estudande, pcd);
        cliente.setId(uuid);


        return cliente;
    }

    public static void registrarClienteDao(String fileName, Cliente c) {

        logger.info("Iniciando a abertura do arquivo " + fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            logger.info("Escrendo as informações no arquivo .txt");

                bufferedWriter.write(c.getId()+";"+c.getNome()+";"+c.getSenha()
                        +";"+c.getIdade()
                        +";"+c.getEstudante()
                        +";"+c.getPcd());

                bufferedWriter.newLine();

        } catch (IOException ex) {
            logger.error("Ocorreu um erro ao tentar escrever os dados no arquivo " + fileName, ex);
        }
    }





}





