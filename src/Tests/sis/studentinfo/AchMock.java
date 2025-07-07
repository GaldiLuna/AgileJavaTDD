package Tests.sis.studentinfo;
import Tests.sis.studentinfo.com.jimbob.ach.*;

import java.util.*;
import org.junit.Assert;

class AchMock implements Ach {
//    public AchResponse issueDebit(AchCredentials credentials, AchTransactionData data) {
//        Assert.assertTrue(data.account.equals(AccountTest.ACCOUNT_NUMBER));
//        Assert.assertTrue(data.aba.equals(AccountTest.ABA));
//        AchResponse response = new AchResponse();
//        response.timestamp = new Date();
//        response.traceCode = "1";
//        response.status = AchStatus.SUCCESS;
//        return response;
//    }

    public AchResponse issueDebit(AchCredentials credentials, AchTransactionData data) {
        return null;
    }

    @Override
    public AchResponse markTransactionAsNSF(AchCredentials credentials, AchTransactionData data, String traceCode) {
        return null;
    }

    @Override
    public AchResponse refundTransaction(AchCredentials credentials, AchTransactionData data, String traceCode) {
        return null;
    }

    @Override
    public AchResponse issueCredit(AchCredentials credentials, AchTransactionData data) {
        return null;
    }

    @Override
    public AchResponse voidSameDayTransaction(AchCredentials credentials, AchTransactionData data, String traceCode) {
        return null;
    }

    @Override
    public AchResponse queryTransactionStatus(AchCredentials credentials, AchTransactionData data, String traceCode) {
        return null;
    }
}
