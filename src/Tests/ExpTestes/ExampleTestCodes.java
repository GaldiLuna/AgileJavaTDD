package Tests.ExpTestes;
import Tests.sis.studentinfo.Student;
import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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

}
