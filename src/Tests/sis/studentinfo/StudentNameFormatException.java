package Tests.sis.studentinfo;

public class StudentNameFormatException extends IllegalArgumentException {
    public StudentNameFormatException(String message) {
        super(message); //Passa a mensagem para a superclasse (classe pai)
    }

}
