package Tests.sis.studentinfo;
import Tests.sis.studentinfo.com.jimbob.ach.*;

import junit.framework.*;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.*;

public class AccountTest extends TestCase {
    static final String ABA = "102000012";
    static final String ACCOUNT_NUMBER = "194431518811";
    private Account account;

    protected void setUp() {
        account = new Account();
        account.setBankAba(ABA);
        account.setBankAccountNumber(ACCOUNT_NUMBER);
        account.setBankAccountType(Account.BankAccountType.CHECKING);
    }

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

    public void testTRansferFromBank() {
        Ach achMock = new AchMock() {
            public AchResponse issueDebit(AchCredentials credentials, AchTransactionData data) {
                Assert.assertTrue(data.account.equals(AccountTest.ACCOUNT_NUMBER));
                Assert.assertTrue(data.aba.equals(AccountTest.ABA));
                AchResponse response = new AchResponse();
                response.timestamp = new Date();
                response.traceCode = "1";
                response.status = AchStatus.SUCCESS;
                return response;
            }

//            @Override
//            public AchResponse markTransactionAsNSF(AchCredentials credentials, AchTransactionData data, String traceCode) {
//                return null;
//            }
//
//            @Override
//            public AchResponse issueCredit(AchCredentials credentials, AchTransactionData data) {
//                return null;
//            }
//
//            @Override
//            public AchResponse voidSameDayTransaction(AchCredentials credentials, AchTransactionData data, String traceCode) {
//                return null;
//            }
//
//            @Override
//            public AchResponse queryTransactionStatus(AchCredentials credentials, AchTransactionData data, String traceCode) {
//                return null;
//            }
        };

        //account.setAch(new com.jimbob.ach.JimBobAch()); //uh-oh
        account.setAch(createMockAch(AchStatus.SUCCESS));
        final BigDecimal amount = new BigDecimal("50.00");
        account.transferFromBank(amount);
        assertEquals(amount, account.getBalance());
    }

    public void testFailedTransferFromBank() {
        account.setAch(createMockAch(AchStatus.FAILURE));
        final BigDecimal amount = new BigDecimal("50.00");
        account.transferFromBank(amount);
        assertEquals(new BigDecimal("0.00"), account.getBalance());
    }

    private Ach createMockAch(final AchStatus status) {
        return new AchMock() {
            public AchResponse issueDebit(AchCredentials credentials, AchTransactionData data) {
                Assert.assertTrue(data.account.equals(AccountTest.ACCOUNT_NUMBER));
                Assert.assertTrue(data.aba.equals(AccountTest.ABA));
                AchResponse response = new AchResponse();
                response.timestamp = new Date();
                response.traceCode = "1";
                response.status = status; //Aqui está o problema de não compilar
                return response;
            }
        };
    }

    public void testWithdraw() throws Exception {
        account.credit(new BigDecimal("100.00"));
        account.withdraw(new BigDecimal("40.00"));
        assertEquals(new BigDecimal("60.00"), account.getBalance());
    }

    public void testWithdrawInsufficientFunds() {
        account.credit(new BigDecimal("100.00"));
        account.withdraw(new BigDecimal("140,00"));
        assertEquals(new BigDecimal("100.00"), account.getBalance());
    }
}
