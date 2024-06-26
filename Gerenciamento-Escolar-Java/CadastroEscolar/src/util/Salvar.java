package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Salvar {
    private static final File CAMINHO = new File("obj/log.ser");

    public static void salvar(Object obj) throws Exception{
        try {
            CAMINHO.getParentFile().mkdirs();

            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(CAMINHO));
            objOutput.writeObject(obj);
            objOutput.close();
        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o objeto : " + e.getMessage());
        }
    }

    public static Object ler() throws Exception{
        try {
            if (CAMINHO.exists() && CAMINHO.isFile()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(CAMINHO));
                return objInput.readObject();
            }
            throw new Exception("Objeto não encontrado");
        } catch (Exception e) {
            throw new Exception("Não foi possível Ler o objeto : " + e.getMessage());
        }
    }
    
}
