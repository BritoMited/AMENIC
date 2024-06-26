package org.example.Models;



import java.io.File;
import java.io.IOException;

public class FileManager {


    public static File criarPasta(String path) {
        File diretorio = new File(path);
        if (!diretorio.exists())
            diretorio.mkdir();

        return diretorio;
    }

    public File criarArquivo(File diretorio, String fileName) throws IOException {
        File file = new File(diretorio, fileName);
        if (!file.exists())
            file.createNewFile();

        return file;
    }


}