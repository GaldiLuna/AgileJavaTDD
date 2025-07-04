package Tests.ExpTestes.Lesson11;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dir {
    private File dir;

    public Dir(String path) throws IOException {
        this.dir = new File(path);
        if (this.dir.exists() && this.dir.isFile()) {
            throw new IOException("O caminho '" + path + "' aponta para um arquivo, não um diretório.");
        }
    }

    public void ensureExists() {
        dir.mkdirs(); //Cria o diretório e quaisquer pais necessários
    }

    public List<MyFile> getFiles() throws IOException {
        if (!dir.isDirectory()) {
            throw new IOException("O diretório '" + dir.getPath() + "' não existe.");
        }
        List<MyFile> myFiles = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    myFiles.add(new MyFile(file.getAbsolutePath()));
                }
            }
        }
        return new ArrayList<>(myFiles); // Implementação de exemplo
    }

    public void delete() {
        // ... Lógica para deletar o diretório e seu conteúdo ...
    }

    // --- Lógica para o Exercício 8 ---

    /**
     * Retorna um objeto Attributes que descreve esta instância de Dir.
     */

    public Attributes getAttributes() {
        return new Attributes(this.dir);
    }

    /**
     * CLASSE INTERNA DE INSTÂNCIA:
     * Ela está ligada a uma instância específica de Dir e pode acessar seus campos.
     */

    public static class Attributes { // NÃO é static
        private File dirFile;

        public Attributes(File dirFile) {
            this.dirFile = dirFile;
        }

        public boolean isReadOnly() {
            // Acessa o campo 'dir' da instância externa Dir
            return !this.dirFile.canWrite();
        }

        public boolean isHidden() {
            return this.dirFile.isHidden();
        }
    }
}
