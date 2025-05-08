package Tests;
import junit.framework.TestCase;
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

}
