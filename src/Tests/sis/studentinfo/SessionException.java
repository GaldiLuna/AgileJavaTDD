package Tests.sis.studentinfo;

public class SessionException extends Exception {
    public SessionException(Throwable cause) {
        super(cause); //Passa a causa raiz para a superclasse
    }
}
