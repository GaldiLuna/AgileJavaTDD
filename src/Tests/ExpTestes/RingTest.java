package Tests.ExpTestes;

import junit.framework.TestCase;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.AssertionError;

public class RingTest extends TestCase {

    private Ring<String> stringRing;
    private Ring<Integer> integerRing;

    //Metodo setUp para inicializar os anéis antes de cada teste
    @Override
    protected void setUp() {
        stringRing = new Ring<>();
        integerRing = new Ring<>();
    }

    //Testa a criação de um anel vazio
    public void testCreate() {
        assertNotNull(stringRing);
        assertTrue(stringRing.isEmpty());
        assertEquals(0, stringRing.size());
    }

    //Testa adicionar um único elemento
    public void testAddSingleElement() {
        stringRing.add("Alpha");
        assertFalse(stringRing.isEmpty());
        assertEquals(1, stringRing.size());
        assertEquals("Alpha", stringRing.getCurrent());
    }

    //Testa adicionar múltiplos elementos e o ponteiro atual
    public void testAddMultipleElements() {
        stringRing.add("Alpha"); //current é "Alpha"
        stringRing.add("Beta"); //"Beta" é adicionado depois de "Alpha" que continua current
        stringRing.add("Gamma"); //"Gamma" é adicionado depois de "Alpha" que continua current

        assertEquals(3, stringRing.size());
        assertEquals("Alpha", stringRing.getCurrent()); //O ponteiro atual não muda

        //Vamos avançar para ver a ordem
        stringRing.advance(); //current agora é "Gamma"
        assertEquals("Gamma", stringRing.getCurrent());
        stringRing.advance(); //current agora é "Beta"
        assertEquals("Beta", stringRing.getCurrent());
        stringRing.advance(); //current voltou para "Alpha" (circulou o anel)
        assertEquals("Alpha", stringRing.getCurrent());
    }

    //Testa o avanço do ponteiro
    public void testAdvance() {
        stringRing.add("A");
        stringRing.add("B");
        stringRing.add("C"); //Anel: A -> C -> B -> A (current está em A)

        assertEquals("A", stringRing.getCurrent());
        stringRing.advance();
        assertEquals("C", stringRing.getCurrent());
        stringRing.advance();
        assertEquals("B", stringRing.getCurrent());
        stringRing.advance();
        assertEquals("A", stringRing.getCurrent());
    }

    //Testa o retrocesso do ponteiro
    public void testBackUp() {
        stringRing.add("A");
        stringRing.add("B");
        stringRing.add("C"); //Anel: A -> C -> B -> A (current está em A)

        assertEquals("A", stringRing.getCurrent());
        stringRing.backUp();
        assertEquals("B", stringRing.getCurrent());
        stringRing.backUp();
        assertEquals("C", stringRing.getCurrent());
        stringRing.backUp();
        assertEquals("A", stringRing.getCurrent());
    }

    //Testar remover o elemento atual
    public void testRemoveCurrent() {
        stringRing.add("X");
        stringRing.add("Y"); //Anel: X -> Y -> X

        assertEquals(2, stringRing.size());
        assertEquals("X", stringRing.getCurrent());

        String removed = stringRing.removeCurrent(); //Remove o X e o Y passa a ser o current
        assertEquals("X", removed);
        assertEquals(1, stringRing.size());
        assertEquals("Y", stringRing.getCurrent());

        removed = stringRing.removeCurrent(); //Remove o Y e o anel fica vazio
        assertEquals("Y", removed);
        assertTrue(stringRing.isEmpty());
        assertEquals(0, stringRing.size());

        //Testa remover de anel vazio
        try {
            stringRing.removeCurrent();
            fail("Should have thrown NoSuchElementException for empty ring");
        } catch (NoSuchElementException e) {
            //Sucesso
        }
    }

    //Testa remover com múltiplos elementos e verifica o ponteiro
    public void testRemoveAndAdvanceBackUp() {
        stringRing.add("1");
        stringRing.add("2");
        stringRing.add("3");

        stringRing.advance();
        assertEquals("3", stringRing.getCurrent());

        stringRing.removeCurrent();
        assertEquals(2, stringRing.size());
        assertEquals("2", stringRing.getCurrent());

        stringRing.advance();
        assertEquals("1", stringRing.getCurrent());

        stringRing.backUp();
        assertEquals("2", stringRing.getCurrent());
    }

    //Testa exceções para operações em anel vazio
    public void testEmptyRingExceptions() {
        assertTrue(stringRing.isEmpty());

        try {
            stringRing.getCurrent();
            fail("Should have thrown NoSuchElementException for getCurrent on empty ring");
        } catch (NoSuchElementException e) {
            //Sucesso
        }

        try {
            stringRing.advance();
            fail("Should have thrown NoSuchElementException for advance on empty ring");
        } catch (NoSuchElementException e) {
            //Sucesso
        }

        try {
            stringRing.backUp();
            fail("Should have thrown NoSuchElementException for backUp on empty ring");
        } catch (NoSuchElementException e) {
            //Sucesso
        }
    }

    //Testa a iteração usando for-each
    public void testForEachIteration() {
        stringRing.add("One");
        stringRing.add("Two");
        stringRing.add("Three");

        String[] expectedOrder = {"One", "Three", "Two"};
        int i = 0;
        for (String element : stringRing) {
            assertEquals(expectedOrder[i++], element);
        }
        assertEquals(expectedOrder.length, i); //Garante que todos foram iterados

        //Testar iteração com diferentes ponteiros iniciais
        stringRing.advance();
        expectedOrder = new String[]{"Three", "Two", "One"};
        i = 0;
        for (String element : stringRing) {
            assertEquals(expectedOrder[i++], element);
        }
    }

    //Testa o uso de tipos diferentes (Integer)
    public void testIntegerRing() {
        integerRing.add(10);
        integerRing.add(20);
        integerRing.add(30);

        assertEquals(3, integerRing.size());
        assertEquals(Integer.valueOf(10), integerRing.getCurrent());

        integerRing.advance();
        assertEquals(Integer.valueOf(30), integerRing.getCurrent());

        integerRing.removeCurrent();
        assertEquals(2, integerRing.size());
        assertEquals(Integer.valueOf(20), integerRing.getCurrent());
    }

    public void testAddNullThrowsAssertionError() {
        // Habilita as asserções APENAS para este metodo de teste,
        // mas é mais comum habilitar via VM Options para t0do o ambiente de teste.
        // System.setProperty("java.enable.assertions", "true"); // Não recomendado aqui.

        try {
            // Tenta adicionar um elemento nulo, o que deve disparar a asserção
            stringRing.add(null);
            // Se a linha acima não lançar AssertionError, o teste falha
            fail("Esperava que adicionar null lançasse um AssertionError, mas não lançou");
        } catch (AssertionError e) {
            // SUCCESSO: A AssertionError foi capturada como esperado.
            // Opcional: Você pode verificar a mensagem da asserção se quiser.
            assertTrue("A mensagem da asserção deveria conter 'não pode ser nulo'", e.getMessage().contains("não pode ser nulo"));
        } catch (Exception e) {
            // Captura qualquer outra exceção inesperada
            fail("Esperava AssertionError, mas lançou outro tipo de exceção: " + e.getClass().getName());
        } finally {
            // Garante que o estado das asserções seja revertido se for alterado via System.setProperty
            System.setProperty("java.enable.assertions", "false"); // Não necessário se habilitado via VM Options
        }
    }
}
