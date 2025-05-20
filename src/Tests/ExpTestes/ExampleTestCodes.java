package Tests.ExpTestes;
import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

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
        int fib = 0;
        int nextFib = 1;
        int index = 0;
        int temp;
        do {
            temp = fib + nextFib;
            fib = nextFib;
            nextFib = temp;
        } while (++index < x);
        return fib;
    }

//    private int fib(int x) {
//        if (x == 0)
//            return 0;
//        if (x == 1)
//            return 1;
//        return fib(x - 1) + fib(x - 2);
//    }

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

}
