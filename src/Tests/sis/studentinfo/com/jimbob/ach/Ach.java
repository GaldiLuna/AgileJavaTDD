package Tests.sis.studentinfo.com.jimbob.ach;

public interface Ach {
    public AchResponse issueDebit(AchCredentials credentials, AchTransactionData data);
    public AchResponse markTransactionAsNSF(AchCredentials credentials, AchTransactionData data, String traceCode);
    public AchResponse refundTransaction(AchCredentials credentials, AchTransactionData data, String traceCode);
    public AchResponse issueCredit(AchCredentials credentials, AchTransactionData data);
    public AchResponse voidSameDayTransaction(AchCredentials credentials, AchTransactionData data, String traceCode);
    public AchResponse queryTransactionStatus(AchCredentials credentials, AchTransactionData data, String traceCode);
}

// EXEMPLO DE BLOCO DE INICIALIZAÇÃO DE INSTÂNCIA

//Expirable t = new Expirable() {
//    private long then;
//    { // Bloco de inicialização de instância
//        long now = System.currentTimeMillis();
//        then = now + 86400000;
//    }
//
//    public boolean isExpired(Date date) {
//        return date.getTime() > then;
//    }
//};