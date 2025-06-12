package Tests.ExpTestes;
import Tests.sis.studentinfo.Student;
import junit.framework.TestCase;
import static java.lang.Math.*;
import java.util.*;
import java.util.logging.Logger;

public class ExampleTestCodes extends TestCase {

    public void testSortStringsInPlace() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Heller");
        list.add("Kafka");
        list.add("Camus");
        list.add("Boyle");
        java.util.Collections.sort(list);
        assertEquals("Boyle", list.get(0));
        assertEquals("Camus", list.get(1));
        assertEquals("Heller", list.get(2));
        assertEquals("Kafka", list.get(3));
    }

    public void testSortStringInNewList() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Heller");
        list.add("Kafka");
        list.add("Camus");
        list.add("Boyle");
        ArrayList<String> sortedList = new ArrayList<String>(list);
        java.util.Collections.sort(sortedList);
        assertEquals("Boyle", sortedList.get(0));
        assertEquals("Camus", sortedList.get(1));
        assertEquals("Heller", sortedList.get(2));
        assertEquals("Kafka", sortedList.get(3));
        assertEquals("Heller", list.get(0));
        assertEquals("Kafka", list.get(1));
        assertEquals("Camus", list.get(2));
        assertEquals("Boyle", list.get(3));
    }

    public void testStringCompareTo() {
        assertTrue("A".compareTo("B") < 0);
        assertEquals(0, "A".compareTo("A"));
        assertTrue("B".compareTo("A") > 0);

        double value = 3 * 0.3; //VARIÁVEL PARA TESTE DO NÚMERO DE PONTO FLUTUANTE
        System.out.println("Impressão solicitada em: ExampleTestCodes.java");
        System.out.println("Números de Ponto Flutuante: " + value); //ANOTAÇÃO IMPRESSA ABAIXO DO TESTE
        assertEquals(0.9, value); //RETORNA ERRO, EXPLICAÇÃO ABAIXO
        System.out.println("Anotação: ao contrário do que se espera de resultado '0,9' esse código imprime o resultado \n '0.8999999999999999' sendo necessário o uso da Classe BigDecimal para arredondamento.");
        //BIG DECIMAL SERÁ VISTO NA LIÇÃO 10, ESTOU NA 5.
    }

    public void testSwitchResults() {
        enum Score { fieldGoal, touchDown, extraPoint, twoPointConversion, safety };
        int totalPoints = 0;
        Score score = Score.touchDown;
        switch (score) {
            case fieldGoal -> totalPoints += 4;
            case touchDown -> totalPoints += 5;
            case extraPoint -> totalPoints += 1;
            case twoPointConversion -> totalPoints += 2;
            case safety -> totalPoints += 3;

            /**
             * Uma versão mais antiga de se escrever o switch case no exemplo acima é a apresentada abaixo:
             * switch (score)
             *      case fieldGoal:
             *          totalPoints += 4;
             *          break;
             *      case touchDown:
             *          totalPoints += 5;
             *          break;
             *      case extraPoint:
             *          totalPoints += 1;
             *          break;
             *      case twoPointConversion:
             *          totalPoints += 2;
             *          break;
             *      case safety:
             *          totalPoints += 3;
             *          break;
             */
        }
        assertEquals(5, totalPoints);
    }

    //Metodo countChars para ajudar a demonstrar algumas nuances menos vistas da sintaxe do Java
    // ele retorna o número de ocorrências de um caractere especificado dentro de uma string
    public static int countChars(String input, char ch) {
        int count;
        int i;
        for (i = 0, count = 0; i < input.length(); i++)
            if (input.charAt(i) == ch)
                count++;
        return count;
    }

    //Metodo isPalindrome para ajudar a demonstrar algumas nuances menos vistas da sintaxe do Java
    // ele retorna verdadeiro se uma string lida de trás para frente for igual à leitura de frente para trás
    public static boolean isPalindrome(String string) {
        if (string.length() == 0)
            return true;
        int limit = string.length() / 2;
        for (int forward = 0, backward = string.length() - 1;
             forward < limit; forward++, backward--)
            if (string.charAt(forward) != string.charAt(backward))
                return false;
        return true;
    }

    public void testPalindrome() {
        assertFalse(isPalindrome("abcdef"));
        assertFalse(isPalindrome("abccda"));
        assertTrue(isPalindrome("abccba"));
        assertFalse(isPalindrome("abcxba"));
        assertTrue(isPalindrome("a"));
        assertTrue(isPalindrome("aa"));
        assertFalse(isPalindrome("ab"));
        assertTrue(isPalindrome(""));
        assertTrue(isPalindrome("aaa"));
        assertTrue(isPalindrome("aba"));
        assertTrue(isPalindrome("abbba"));
        assertTrue(isPalindrome("abba"));
        assertFalse(isPalindrome("abbaa"));
        assertFalse(isPalindrome("abcda"));
    }

    public void testForSkip() {
        StringBuilder builder = new StringBuilder();
        String string = "123456";
        for (int i = 0; i < string.length(); i += 2)
            builder.append(string.charAt(i));
        assertEquals("135", builder.toString());
    }

    public void testFibonacci() {
        assertEquals(0, fib(0));
        assertEquals(1, fib(1));
        assertEquals(1, fib(2));
        assertEquals(2, fib(3));
        assertEquals(3, fib(4));
        assertEquals(5, fib(5));
        assertEquals(8, fib(6));
        assertEquals(13, fib(7));
        assertEquals(21, fib(8));
        assertEquals(34, fib(9));
        assertEquals(55, fib(10));
    }

    private int fib(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;
        return fib(x - 1) + fib(x - 2);
    }

    public void testCommas() {
        String sequence = "1,2,3,4,5";
        assertEquals(sequence, sequenceUsingDo(1, 5));
        assertEquals(sequence, sequenceUsingFor(1, 5));
        assertEquals(sequence, sequenceUsingWhile(1, 5));
        sequence = "8";
        assertEquals(sequence, sequenceUsingDo(8, 8));
        assertEquals(sequence, sequenceUsingFor(8, 8));
        assertEquals(sequence, sequenceUsingWhile(8, 8));
    }

    String sequenceUsingDo(int start, int stop) {
        StringBuilder builder = new StringBuilder();
        int i = start;
        do {
            if (i > start)
                builder.append(',');
            builder.append(i);
        } while (++i <= stop);
        return builder.toString();
    }

    String sequenceUsingFor(int start, int stop) {
        StringBuilder builder = new StringBuilder();
        for (int i = start; i <= stop; i++) {
            if (i > start)
                builder.append(',');
            builder.append(i);
        }
        return builder.toString();
    }

    String sequenceUsingWhile(int start, int stop) {
        StringBuilder builder = new StringBuilder();
        int i = start;
        while (i <= stop) {
            if (i > start)
                builder.append(',');
            builder.append(i);
            i++;
        }
        return builder.toString();
    }

    public void testEndTrim() {
        assertEquals("", endTrim(""));
        assertEquals(" x", endTrim(" x "));
        assertEquals("y", endTrim("y"));
        assertEquals("xaxa", endTrim("xaxa"));
        assertEquals("", endTrim(" "));
        assertEquals("xxx", endTrim("xxx\n"));
    }

    // Metodo trim que remove caracteres em branco, na verdade, remove espaços em branco,
    //que incluem caracteres de espaço, tabulação, nova linha, alimentação de formulário e retorno de carro.
    public String endTrim(String source) {
        if (source.isEmpty()) {
            return "";
        }
        int i = source.length() - 1;
        while (i >= 0 && Character.isWhitespace(source.charAt(i))) {
            i--;
        }
        return source.substring(0, i + 1);
    }

    public void testLabeledBreak() {
        List<List<String>> table = new ArrayList<List<String>>();
        List<String> row1 = new ArrayList<String>();
        row1.add("5");
        row1.add("2");
        List<String> row2 = new ArrayList<String>();
        row2.add("3");
        row2.add("4");
        table.add(row1);
        table.add(row2);
        assertTrue(found(table, "3"));
        assertFalse(found(table, "8"));
    }

    private boolean found(List<List<String>> table, String target) {
        boolean found = false;
        search:
        for (List<String> row : table) {
            for (String value : row) {
                if (value.equals(target)) {
                    found = true;
                    break search;
                }
            }
        }
        return found;
    }

    //O OPERADOR TERNÁRIO COMPACTA UMA INSTRUÇÃO IF-ELSE E SUA FORMA GERAL DE SER USADO É:
    // condicional ? valor-verdadeiro : valor-falso
    //VAMOS USAR COMO EXEMPLO O CÓDIGO ABAIXO QUE ESTARÁ DA FORMA NORMAL E COMPACTADA.
    public int sessions;
    String message1 = "the course has " + getText(sessions) + " sessions";
    private String getText(int sessions) {
        if (sessions == 1)
            return "one";
        return "many";
    }
    //TODA ESSA EXPRESSÃO DE OPERADOR TERNÁRIO ACIMA PODERIA SE RESUMIR A UMA ÚNICA LINHA
    String message2 = "the course has " + (sessions == 1 ? "one" : "many") + " sessions";

    //O teste a seguir demonstra a "maneira antiga" de iterar por uma coleção,
    // sem o laço for-each e sem tipos parametrizados.
    public void testCasting() {
        List students = new ArrayList();
        students.add(new Student("a"));
        students.add(new Student("b"));
        List names = new ArrayList();
        Iterator it = students.iterator();
        while (it.hasNext()) {
            Student student = (Student)it.next();
            names.add(student.getLastName());
        }
        assertEquals("a", names.get(0));
        assertEquals("b", names.get(1));
    }

    public void testUnboxing() {
        int x = new Integer(5);
        assertEquals(5, x);
    }
    public void testUnboxingMath() {
        assertEquals(10, new Integer(2) * new Integer(5));
    }

    //0  1   2   3
    //4  5   6   7
    //8  9   10  11
    public void testTwoDimensionalArrays() {
        final int rows = 3;
        final int cols = 4;
        int count = 0;
        int[][] matrix = new int[rows][cols];
        for (int x = 0; x < rows; x++)
            for (int y = 0; y < cols; y++)
                matrix[x][y] = count++;
        assertEquals(11, matrix[2][3]);
        assertEquals(6, matrix[1][2]);
    }

    //0
    //1  2
    //3  4  5
    public void testPartialDimensions() {  //DUAS FORMAS DIFERENTES DE ESCREVER A MESMA MATRIZ
        final int rows = 3;

        int[][] matrix = new int[rows][];
        matrix[0] = new int[]{0};
        matrix[1] = new int[]{1, 2};
        matrix[2] = new int[]{3, 4, 5};
        assertEquals(1, matrix[1][0]);
        assertEquals(5, matrix[2][2]);

        int[][] matrixx = {{0}, {1, 2}, {3, 4, 5}};
        assertEquals(1, matrixx[1][0]);
        assertEquals(5, matrixx[2][2]);
    }

    //Comparar os dois arrays com == resultará em false
    public void testArrayEquality() {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        assertFalse(a == b);
    }

    //Mesmo se você alocar ambos os arrays com exatamente as mesmas dimensões e
    //preenchê-los com exatamente o mesmo conteúdo, a comparação retornará false
    public void testArrayEquals() {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        assertFalse(a.equals(b));
    }

    //Pode usar o metodo Arrays.equals para comparar o conteúdo, não o local de memória, de dois arrays
    public void testArrayContent() {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        assertTrue(Arrays.equals(a, b));
    }

    //FORMA ANTIGA DE TOKENIZAR UMA STRING
    private List<String> split(String name) {
        List<String> results = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(name, " ");
        while (tokenizer.hasMoreTokens())
            results.add(tokenizer.nextToken());
        return results;
    }

    public void testFactorialWhile() {
        FactorialWhile factorial = new FactorialWhile();
        assertEquals(1, factorial.compute(0));
        assertEquals(1, factorial.compute(1));
        assertEquals(2, factorial.compute(2));
        assertEquals(6, factorial.compute(3));
        assertEquals(24, factorial.compute(4));
        assertEquals(120, factorial.compute(5));
    }

    static class FactorialWhile {
        public long compute(int n) {
            if (n < 0) throw new IllegalArgumentException("A entrada não pode ser negativa");
            if (n == 0 || n == 1) return 1;
            long result = 1;
            int i = 1;
            while (i <= n) {
                result *= i;
                i++;
            }
            return result;
        }
    }

    public void testFactorialFor() {
        FactorialFor factorial = new FactorialFor();
        assertEquals(1, factorial.compute(0));
        assertEquals(1, factorial.compute(1));
        assertEquals(2, factorial.compute(2));
        assertEquals(6, factorial.compute(3));
        assertEquals(24, factorial.compute(4));
        assertEquals(120, factorial.compute(5));
    }

    static class FactorialFor {
        public long compute(int n) {
            if (n < 0) throw new IllegalArgumentException("A entrada não pode ser negativa");
            if (n == 0 || n == 1) return 1;
            long result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            return result;
        }
    }

    public void testFactorialDoWhile() {
        FactorialDoWhile factorial = new FactorialDoWhile();
        assertEquals(1, factorial.compute(0));
        assertEquals(1, factorial.compute(1));
        assertEquals(2, factorial.compute(2));
        assertEquals(6, factorial.compute(3));
        assertEquals(24, factorial.compute(4));
        assertEquals(120, factorial.compute(5));
    }

    static class FactorialDoWhile {
        public long compute(int n) {
            if (n < 0) throw new IllegalArgumentException("A entrada não pode ser negativa");
            if (n == 0 || n == 1) return 1;
            long result = 1;
            int i = 1;
            do {
                result *= i;
                i++;
            } while (i <= n);
            return result;
        }
    }

    public void testFactorialWhileTrueBreak() {
        FactorialWhileTrueBreak factorial = new FactorialWhileTrueBreak();
        assertEquals(1, factorial.compute(0));
        assertEquals(1, factorial.compute(1));
        assertEquals(2, factorial.compute(2));
        assertEquals(6, factorial.compute(3));
        assertEquals(24, factorial.compute(4));
        assertEquals(120, factorial.compute(5));
    }

    static class FactorialWhileTrueBreak {
        public long compute(int n) {
            if (n < 0) throw new IllegalArgumentException("A entrada não pode ser negativa");
            if (n == 0 || n == 1) return 1;
            long result = 1;
            int i = 1;
            while (true) {
                if (i > n) break;
                result *= i;
                i++;
            }
            return result;
        }
    }

    static class NumberString {
        public String generateNumbers(int n) {
            if (n < 1) return "";
            StringBuilder result = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                result.append(i);
                if (i % 5 == 0) {
                    result.append("*");
                    if (i < n) result.append(" ");
                    continue;
                }
                if (i < n) result.append(" ");
            }
            return result.toString();
        }
    }

    public void testGenerateNumbers() {
        NumberString ns = new NumberString();
        assertEquals("1 2 3 4 5* 6 7 8 9 10* 11 12", ns.generateNumbers(12));
        assertEquals("", ns.generateNumbers(0));
        assertEquals("1", ns.generateNumbers(1));
        assertEquals("1 2 3 4 5*", ns.generateNumbers(5));
    }

    static class NumberStringVector {
        public Vector<String> splitNumbers(String input) {
            Vector<String> result = new Vector<>();
            for (String part : input.split(" ")) {
                result.add(part);
            }
            return result;
        }

        public String reconstructFromVector(Vector<String> numbers) {
            StringBuilder result = new StringBuilder();
            Enumeration<String> enumeration = numbers.elements();
            while (enumeration.hasMoreElements()) {
                result.append(enumeration.nextElement());
                if (enumeration.hasMoreElements()) {
                    result.append(" ");
                }
            }
            return result.toString();
        }
    }

    public void testSplitNumbers() {
        NumberStringVector nsv = new NumberStringVector();
        Vector<String> result = nsv.splitNumbers("1 2 3 4 5* 6 7");
        Vector<String> expected = new Vector<>();
        expected.addAll(Arrays.asList("1", "2", "3", "4", "5*", "6", "7"));
        assertEquals(expected, result);
    }

    public void testReconstructFromVector() {
        NumberStringVector reconstruct = new NumberStringVector();
        Vector<String> input = new Vector<>();
        input.addAll(Arrays.asList("1", "2", "3", "4", "5*", "6", "7"));
        assertEquals("1 2 3 4 5* 6 7", reconstruct.reconstructFromVector(input));
    }

    /**
     * OS 2 BLOCOS DE CÓDIGO ABAIXO SÃO APRA EXEMPLIFICAR A APLICAÇÃO DO FINALLY QUE É UTILIZADO
     * PARA FECHAR CONEXÕES ABERTAS NO INÍCIO DE UM BLOCO DE CÓDIGO, USANDO AQUI UMA ABERTURA DE
     * CONEXÃO COM O BANCO DE DADOS SQL, NO PRIMEIRO BLOCO TEMOS O USO DO TRY-CATCH SENDO O CATCH
     * OPCIONAL PELO FATO DE TER O FINALLY INCLUÍDO, NO SEGUNDO BLOCO DE CÓDIGO TEMOS O EXEMPLO
     * DE USO DO FINALLY SEM A PRESENÇA DO CATCH.

    public static Student findByLastName(String lastName) throws RuntimeException {
        java.sql.Connection dbConnection = null;
        try {
            dbConnection = getConnection();
            return lookup(dbConnection, lastName);
        }
        catch (java.sql.SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            close(dbConnection);
        }
    }

    public static Student findByLastName(String lastName) throws java.sql.SQLException {
        java.sql.Connection dbConnection = null;
        try {
            dbConnection = getConnection();
            return lookup(dbConnection, lastName);
        }
        finally {
            close(dbConnection);
        }
    }
     */

    public void testLoggingHierarchy() {
        Logger logger = Logger.getLogger("Tests.sis.studentinfo.Student");
        assertTrue(logger == Logger.getLogger("Tests.sis.studentinfo.Student"));
        Logger parent = Logger.getLogger("Tests.sis.studentinfo");
        assertEquals(parent, logger.getParent());
        assertEquals(Logger.getLogger("Tests"), parent.getParent());
    }

    static class WordCount {
        private final String text;

        public WordCount(String text) {
            this.text = text;
        }

        /**
         * Analisa o texto, conta a frequência das palavras e retorna um Set formatado.
         * @return Um Set de Strings com cada palavra e sua frequência.
         */
        public Set<String> getWordFrequencies() {
            //Mapa para armazenar a palavra (em minúsculas) e sua frequência
            Map<String, Integer> wordFrequencies = new HashMap<>();

            //1. Converte tudi para minúsculas para ignorar o case
            //2. Usa split com a regex \W+ para dividir por qualquer caractere que não seja uma letra ou número
            String[] words = text.toLowerCase().split("\\W+");

            //Itera sobre as palavras extraídas
            for (String word : words) {
                if (!word.isEmpty()) {
                    //Pega a contagem atual (ou 0 se for a primeira vez) e incrementa
                    int count = wordFrequencies.getOrDefault(word, 0);
                    wordFrequencies.put(word, count + 1);
                }
            }

            //Cria o Set de resultado para formatar a saída
            Set<String> resultSet = new HashSet<>();
            for (Map.Entry<String, Integer> entry : wordFrequencies.entrySet()) {
                resultSet.add(entry.getKey() + ": " + entry.getValue());
            }

            return resultSet;
        }

        public static void main(String[] args) {
            //Texto extraído das duas primeiras frases do exercício
            String inputText = "Create a String literal using the first two sentences of this exercise. You will create a " +
                                "WordCount class to parse through the text and count the number of instances of each word.";

            WordCount wordCounter = new WordCount(inputText);
            Set<String> frequencies = wordCounter.getWordFrequencies();

            //Imprime o resultado
            System.out.println("Frequência de cada palavra:");
            for (String frequency : frequencies) {
                System.out.println(frequency);
            }
        }
    }

    static class Name {
        private final String name;

        public Name(String name) {
            this.name = name;
        }

        //Apenas o metodo equals foi implementado, como pedido.
        @Override
        public boolean equals(Object o) {
            //1. Verifica se é a mesma instância
            if (this == o) return true;
            //2. Verifica se o objeto é nulo ou de classe diferente
            if (o == null || getClass() != o.getClass()) return false;
            //3. Faz o cast e compara o campo relevante
            Name otherName = (Name) o;
            return Objects.equals(this.name, otherName.name);
        }

        //O metodo hashCode NÃO é implementado na primeira etapa logo abaixo.
        //A classe usará a implementação padrão de Object.

        //Na segunda etapa temos a solução implementando o hashCode
        //O hashCode é baseado no mesmo campo usado no equals
        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public static void main(String[] args) {
        System.out.println("\n--- Teste do Exercício 3 ---");

        Set<Name> nameSet1 = new HashSet<>();
        nameSet1.add(new Name("Foo"));
        nameSet1.add(new Name("Bar"));

        //Embora 'new Name("Foo)' seja .equals() ao que está no set, seus hashCodes são diferentes e o teste falha.
        boolean containsNewInstance = nameSet1.contains((new Name("Foo")));
        System.out.println("O Set contém new Name(\"Foo\")? " + containsNewInstance); //Imprime: FALSE

        //O set contém a referência EXATA ao objeto e o teste agora passa.
        Name foo = new Name("Foo");
        nameSet1.add(foo);
        boolean containsSameInstance = nameSet1.contains(foo);
        System.out.println("O Set contém a instância 'foo'? " + containsSameInstance); //Imprime: TRUE

        System.out.println("\n -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- \n");

        System.out.println("--- Teste do Exercício 4 (com a classe Name corrigida) ---");

        Set<Name> nameSet2 = new HashSet<>();
        nameSet2.add(new Name("Foo"));
        nameSet2.add(new Name("Bar"));

        //Como 'hashCode' e 'equals' estão consistentes, o HashSet encontra o objeto corretamente.
        boolean containsOtherInstance = nameSet2.contains(new Name("Foo"));
        System.out.println("O Set contém new Name(\"Foo\")? " + containsOtherInstance); //Imprime: TRUE
    }

    public void testInfinity() {
        final float tolerance = 0.5f;
        final float x = 1f;

        assertEquals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY * 100, tolerance);
        assertEquals(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY * -1, tolerance);

        assertEquals(Float.POSITIVE_INFINITY, x / 0f, tolerance);
        assertEquals(Float.NEGATIVE_INFINITY, x / -0f, tolerance);

        assertTrue(Float.isNaN(x % 0f));

        assertEquals(0f, x / Float.POSITIVE_INFINITY, tolerance);
        assertEquals(-0f, x / Float.NEGATIVE_INFINITY, tolerance);

        assertEquals(x, x % Float.POSITIVE_INFINITY, tolerance);

        assertTrue(Float.isNaN(0f / 0f));
        assertTrue(Float.isNaN(0f % 0f));

        assertEquals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY / x, tolerance);
        assertEquals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY / x, tolerance);

        assertTrue(Float.isNaN(Float.POSITIVE_INFINITY % x));
        assertTrue(Float.isNaN(Float.POSITIVE_INFINITY / Float.POSITIVE_INFINITY));
        assertTrue(Float.isNaN(Float.POSITIVE_INFINITY % Float.POSITIVE_INFINITY));
        assertTrue(Float.isNaN(Float.POSITIVE_INFINITY / Float.NEGATIVE_INFINITY));
        assertTrue(Float.isNaN(Float.POSITIVE_INFINITY % Float.NEGATIVE_INFINITY));
        assertTrue(Float.isNaN(Float.NEGATIVE_INFINITY / Float.POSITIVE_INFINITY));
        assertTrue(Float.isNaN(Float.NEGATIVE_INFINITY % Float.POSITIVE_INFINITY));
        assertTrue(Float.isNaN(Float.NEGATIVE_INFINITY / Float.NEGATIVE_INFINITY));
        assertTrue(Float.isNaN(Float.NEGATIVE_INFINITY % Float.NEGATIVE_INFINITY));
    }

    public void testMaxValues() {
        int i = Integer.MAX_VALUE;
        assertEquals(Integer.MAX_VALUE + 1, i + 1);
        System.out.println("Valor máximo de um int: " + Integer.MAX_VALUE);
        System.out.println("INT Overflow: " + (Integer.MAX_VALUE + 1)); // Resulta em Integer.MIN_VALUE

        byte b1 = Byte.MAX_VALUE;
        assertEquals(Byte.MAX_VALUE + 1, b1 + 1);
        System.out.println("Valor máximo de um byte: " + Byte.MAX_VALUE);
        byte b2 = b1 += 1;
        assertEquals(Byte.MIN_VALUE - 1, b2 - 1);
        System.out.println("Byte Overflow: " + b2); // Resulta em Byte.MIN_VALUE
    }

    public void testTabelaVerdadeOrientadaATestes() {
        //Operador and -> &
        assertEquals(0, 0 & 0);
        assertEquals(0, 0 & 1);
        assertEquals(0, 1 & 0);
        assertEquals(1, 1 & 1);

        //Operador or -> |
        assertEquals(0, 0 | 0);
        assertEquals(1, 0 | 1);
        assertEquals(1, 1 | 0);
        assertEquals(1, 1 | 1);

        //Operador xor -> ^
        assertEquals(0, 0 ^ 0);
        assertEquals(1, 0 ^ 1);
        assertEquals(1, 1 ^ 0);
        assertEquals(0, 1 ^ 1);

        //Operador de negação lógica -> ~
        int x = 0x7FFFFFF1;
        // 0111_1111_1111_1111_1111_1111_1111_0001
        assertEquals(0x8000000E, ~x);
        // 1000_0000_0000_0000_0000_0000_0000_1110

        //O operador xor tem a capacidade única de ser reversível.
        int z = 5;       // 101
        int y = 7;       // 111
        int zPrime = z ^ y; // 010
        assertEquals(2, zPrime);
        assertEquals(z, zPrime ^ y);
    }

    public void testParity() {
        //verificação de paridade - paridade par ou paridade ímpar - usando XOR
        assertEquals(0, xorAll(0, 1, 0, 1)); // paridade par
        assertEquals(1, xorAll(0, 1, 1, 1)); // paridade ímpar
    }

    private int xorAll(int first, int... rest) {
        int parity = first;
        for (int num: rest)
            parity ^= num;
        return parity;
    }

    public void testBitShifting() {
        //Deslocamento de bits para esquerda, os bits mais à esquerda são perdidos.
        // Os bits mais à direita são preenchidos com zeros.
                                                     //      101 = 5
        assertEquals(10, 5 << 1);     //     1010 = 10
        assertEquals(20, 5 << 2);     //    10100 = 20
        assertEquals(40, 5 << 3);     //   101000 = 40
        assertEquals(80, 5 << 4);     //   1010000 = 80

        assertEquals(20, 40 >> 1);    //    10100 = 20
        assertEquals(10, 40 >> 2);    //     1010 = 10
        assertEquals(10, 40 >> 3);    //      101 = 5

                                                    // ... 1111 0110 = -10
        assertEquals(-20, -10 << 1); // ... 1110 1100 = -20
        assertEquals(-5, -10 >> 1); // ... 1111 1011 = -5

    }

    public void testImprimirBinario() {
        //Metodo feito para verificar os binários do metodo logo acima ^^^
        int eA = 5 << 1;
        int eB = 5 << 2;
        int eC = 5 << 3;
        int eD = 5 << 4;

        int dA = 80 >> 1;
        int dB = 80 >> 2;
        int dC = 80 >> 3;

        int n1 = -10 << 1;
        int n2 = -10 >> 1;

        String binarioA = Integer.toBinaryString(eA);
        String binarioB = Integer.toBinaryString(eB);
        String binarioC = Integer.toBinaryString(eC);
        String binarioD = Integer.toBinaryString(eD);

        String binarioF = Integer.toBinaryString(dA);
        String binarioG = Integer.toBinaryString(dB);
        String binarioH = Integer.toBinaryString(dC);

        String binarioN1 = Integer.toBinaryString(n1);
        String binarioN2 = Integer.toBinaryString(n2);

        System.out.println("O número: " + eA + " é representado pelo binário: " + binarioA);
        System.out.println("O número: " + eB + " é representado pelo binário: " + binarioB);
        System.out.println("O número: " + eC + " é representado pelo binário: " + binarioC);
        System.out.println("O número: " + eD + " é representado pelo binário: " + binarioD);

        System.out.println("O número: " + dA + " é representado pelo binário: " + binarioF);
        System.out.println("O número: " + dB + " é representado pelo binário: " + binarioG);
        System.out.println("O número: " + dC + " é representado pelo binário: " + binarioH);

        System.out.println("O negativo: " + n1 + " é representado pelo binário: " + binarioN1);
        System.out.println("O negativo: " + n2 + " é representado pelo binário: " + binarioN2);
    }

    static class Math {
        public static double hypotenuse(double a, double b) {
            return sqrt(pow(a, 2.0) + pow(b, 2.0));
        }
    }

    public void testHipotenusa( ){
        final double TOLERANCE = 0.05;

        assertEquals(5.0, Math.hypotenuse(3.0, 4.0), TOLERANCE);
    }

    public void testConversor() {
        assertEquals("101", Integer.toBinaryString(5)); // Converte 5 para binário
        assertEquals("32", Integer.toHexString(50));    // Converte 50 para hexadecimal
        assertEquals("21", Integer.toOctalString(17));  // Converte 17 para octal
        assertEquals("1022", Integer.toString(35, 3)); // Converte 35 para base 3
        assertEquals(253, (int) Integer.decode("0xFD"));   // Hexadecimal (prefixo 0x)
        assertEquals(253, (int) Integer.decode("0XFD"));   // Hexadecimal (prefixo 0X)
        assertEquals(253, (int) Integer.decode("#FD"));    // Hexadecimal (prefixo #)
        assertEquals(15, (int) Integer.decode("017"));     // Octal (prefixo 0)
        assertEquals(10, (int) Integer.decode("10"));      // Decimal
        assertEquals(-253, (int) Integer.decode("-0xFD"));  // Hexadecimal negativo
        assertEquals(-253, (int) Integer.decode("-0XFD")); // Hexadecimal negativo
        assertEquals(-253, (int) Integer.decode("-#FD"));  // Hexadecimal negativo
        assertEquals(-15, (int) Integer.decode("-017"));   // Octal negativo
        assertEquals(-10, (int) Integer.decode("-10"));    // Decimal negativo
    }

    public void testCoinsFlips() {
        final long seed = 100L;
        final int total = 10;

        Random random1 = new Random(seed);
        List<Boolean> flips1 = new ArrayList<Boolean>();
        for (int i = 0; i < total; i++)
            flips1.add(random1.nextBoolean());

        Random random2 = new Random(seed);
        List<Boolean> flips2 = new ArrayList<Boolean>();
        for (int j = 0; j < total; j++)
            flips2.add(random2.nextBoolean());

        assertEquals(flips1, flips2);
    }

}
