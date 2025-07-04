package Tests.ExpTestes.Lesson11;

import junit.framework.TestCase;
import java.io.File;
import java.io.IOException;

public class DirTest extends TestCase {

    private static final String TEST_DIR_PATH = "temp_test_dir";
    private Dir testDir;

    /**
     * Executado antes de cada metodo de teste.
     * Garante que o ambiente de teste esteja limpo.
     */

    @Override
    protected void setUp() throws Exception {
        // Cria uma instância de Dir para o nosso diretório de teste
        testDir = new Dir(TEST_DIR_PATH);
        // Garante que qualquer resquício de execuções anteriores seja limpo
        recursiveDelete(new File(TEST_DIR_PATH));
        // Cria o diretório para os testes
        testDir.ensureExists();
    }

    /**
     * Executado após cada metodo de teste.
     * Limpa todos os arquivos e diretórios criados.
     */

    @Override
    protected void tearDown() throws Exception {
        recursiveDelete(new File(TEST_DIR_PATH));
    }

    /**
     * Testa se o metodo getAttributes da classe Dir ainda funciona corretamente.
     */

    public void testGetAttributesStillWorks() {
        Dir.Attributes attributes = testDir.getAttributes();
        assertNotNull(attributes);
        assertFalse(attributes.isReadOnly());
    }

    // ===================================================================
    // TESTE PRINCIPAL PARA O EXERCÍCIO 9
    // ===================================================================

    /**
     * Demonstra que uma classe aninhada ESTÁTICA pode ser instanciada
     * diretamente, sem precisar de uma instância da classe externa.
     */

    public void testCanInstantiateStaticNestedClassDirectly() {
        File aCompletelySeparateFile = new File("."); // Usando o diretório atual como exemplo

        // Agora esta linha COMPILA e FUNCIONA, pois a classe é estática.
        // Podemos criar uma instância de 'Attributes' para qualquer 'File' que quisermos.
        Dir.Attributes attrs = new Dir.Attributes(aCompletelySeparateFile);

        assertNotNull(attrs);
        System.out.println("Diretório '.' é somente leitura? " + attrs.isReadOnly());
    }

    /**
     * Testa se o construtor falha corretamente ao receber o caminho de um ARQUIVO existente.
     */

    public void testConstructorFailsForExistingFile() throws IOException {
        // Cria um arquivo temporário
        File tempFile = new File("temp_file.txt");
        tempFile.createNewFile();

        try {
            new Dir("temp_file.txt"); // Tenta criar um Dir com o caminho do arquivo
            fail("Deveria ter lançado IOException para um caminho de arquivo existente.");
        } catch (IOException expected) {
            // Sucesso, a exceção foi lançada como esperado.
        } finally {
            // Limpeza
            tempFile.delete();
        }
    }

    /**
     * Testa o metodo ensureExists() para criar um diretório.
     */

    public void testEnsureExists() {
        File dirAsFile = new File(TEST_DIR_PATH);
        assertFalse("O diretório não deveria existir antes de ensureExists()", dirAsFile.exists());

        // Executa a ação
        testDir.ensureExists();

        // Verifica o resultado
        assertTrue("O diretório deveria existir após ensureExists()", dirAsFile.exists());
        assertTrue("O caminho deveria ser um diretório", dirAsFile.isDirectory());
    }

    // ===================================================================
    // TESTE PRINCIPAL PARA O EXERCÍCIO DA QUESTÃO 8
    // ===================================================================

    /**
     * Demonstra que uma classe interna de instância não pode ser criada diretamente.
     * A "prova" é um erro de compilação, então este teste documenta esse fato.
     */

    public void testCannotInstantiateInnerClassDirectly() {
        // A linha de código a seguir, se fosse descomentada, causaria um ERRO DE COMPILAÇÃO.
        // O erro seria algo como: "uma instância da classe externa Dir que contém Dir.Attributes é necessária".
        // Isso prova que a classe interna `Attributes` está ligada a uma instância de `Dir`.

        // Dir.Attributes attrs = new Dir.Attributes(); // <-- ISTO NÃO COMPILA!

        // O teste passa porque o objetivo é apenas documentar a falha de compilação.
        assertTrue(true);
    }

    /**
     * Demonstra a forma CORRETA de obter e usar a instância da classe interna.
     */

    public void testGetAndUseAttributesInnerClass() {
        testDir.ensureExists(); // Garante que o diretório exista

        // A única forma de obter uma instância de Attributes é através de uma instância de Dir.
        Dir.Attributes attributes = testDir.getAttributes();
        assertNotNull(attributes);

        // Podemos agora usar os métodos da classe interna
        assertFalse("O diretório de teste recém-criado não deve ser somente leitura", attributes.isReadOnly());
    }

    /**
     * Metodo utilitário para deletar diretórios recursivamente.
     * Essencial para limpar o ambiente de teste.
     */

    private void recursiveDelete(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    recursiveDelete(f);
                }
            }
        }
        file.delete();
    }
}
