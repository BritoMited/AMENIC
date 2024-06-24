package org.example.Utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Util {
    public static int lerOpcoesMenu(Scanner scanner) {
//        logger.info("Iniciando o processo de leitura do console");
        try {
            int opcao = scanner.nextInt();
            return opcao;
        } catch (InputMismatchException ex) {
//            logger.error("Ocorreu um erro ao tentar ler o console. Input diferente de INT!", ex);
            return 99;
        } finally {
            scanner.nextLine();
        }
    }
}
