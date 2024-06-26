package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class Log {

    public static void salvar(List<String> textos, String nomeArquivo) throws Exception {

        File pasta = new File("log");
        if (!pasta.exists()) {
            pasta.mkdir();
        }

        pasta = new File("log/" + nomeArquivo + ".txt");
        if (pasta.exists()) {
            textos.addAll(ler(nomeArquivo));
        }

        try {
            FileWriter arquivo = new FileWriter(pasta);
            PrintWriter gravar = new PrintWriter(arquivo);
 
            for (String texto : textos) {
                gravar.println(texto);
            }
            gravar.close();

        } catch (IOException e) {
            throw new Exception("O arquivo não pode ser gravado " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado " + e.getMessage());
        }
    }

    public static List<String> ler(String nomeArquivo) throws Exception {
        List<String> textos = new ArrayList<>();

        String nomeArquivoCompleto = "log/" + nomeArquivo + ".txt";
        try {
            File arquivo = new File(nomeArquivoCompleto);
            java.util.Scanner scanner = new java.util.Scanner(arquivo);

            while (scanner.hasNextLine()) {
                textos.add(scanner.nextLine());
            }
            scanner.close();
            return textos;
        } catch (java.io.FileNotFoundException e) {
            throw new Exception("Arquivo não encontrado " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado " + e.getMessage());
        }
    }
}
