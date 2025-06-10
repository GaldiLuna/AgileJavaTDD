package Tests.sis.studentinfo;

import junit.framework.*;
import java.math.BigDecimal;

public class AccountTest extends TestCase {
    public void testTransactions() {
        Account account = new Account();
        account.credit(new BigDecimal("0.10"));
        account.credit(new BigDecimal("11.00"));
        assertEquals(new BigDecimal("11.10"), account.getBalance());

        //a escala resultante da adição de um número de escala 1 a um número de escala 3 é 3
        assertEquals(new BigDecimal("5.300"), new BigDecimal("5.000").add(new BigDecimal("0.3")));

        //Você prefixa literais hexadecimais com 0x
        assertEquals(12, 0xC);

        //Você prefixa literais octais com 0
        assertEquals(10, 012);

        //Quando constrói uma expressão que usa uma mistura de valores integrais e de ponto flutuante,
        // o Java converte o resultado da expressão para um valor de ponto flutuante apropriado:
        assertEquals(600.0f, 20.0f * 30, 0.05);
        assertEquals(0.5, 15.0 / 30, 0.05);
        assertEquals(0.5, 15 / 30.0, 0.05);
    }

    public void testTransactionAverage() {
        Account account = new Account();
        account.credit(new BigDecimal("0.10"));
        account.credit(new BigDecimal("11.00"));
        account.credit(new BigDecimal("2.99"));
        assertEquals(new BigDecimal("4.70"), account.transactionAverage());

        //alguns exemplos básicos de como a precedência afeta o resultado de uma expressão
        assertEquals(7, 3 * 4 - 5);
        assertEquals(-11, 4 - 5 * 3);
        assertEquals(-3, 3 * (4 - 5));
    }
}
