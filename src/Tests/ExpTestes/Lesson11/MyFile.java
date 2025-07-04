package Tests.ExpTestes.Lesson11;

import java.io.*;

public class MyFile {
    private File file;

    public MyFile(String filename) {
        this.file = new File(filename);
    }

    public String getContentAsString() {
        if ((!file.exists())) {
            throw new FileOperationException("Arquivo não encontrado: " + file.getName());
        }
        // ... lógica de leitura com BufferedReader ...
        return ""; //Retornaria o conteúdo
    }

    public void write(String content) throws IOException {
        if (file.exists()) {
            throw new FileOperationException("Arquivo já existe: " + file.getName());
        }
        overwrite(content);
    }

    public void overwrite(String content) throws IOException {
        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
    }

    public void delete() {
        if (file.exists()) {
            file.delete();
        }
    }
}
