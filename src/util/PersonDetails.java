package util;

public class PersonDetails {
    private String firstName;
    private String lastName;
    private int age;

    public PersonDetails(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getAgeDescription() {
        return age + " years old";
    }

    @Dump(outputMethod = {"getFullName", "getAgeDescription"}) // Chamar múltiplos métodos
    private PersonDetails personInfo; // Campo do tipo da própria classe para demonstrar

    @Dump // Apenas o toString padrão para este
    private String email;

    public PersonDetails getPersonInfo() { return this; } // Retorna 'this' para testar
    public String getEmail() { return email; }


    // Construtor principal para o teste
    public PersonDetails(String firstName, String lastName, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.personInfo = this; // Aponta para si mesmo para que o dump seja feito
    }

    @Override
    public String toString() {
        return "PersonDetails[" + firstName + " " + lastName + ", " + age + "yrs]";
    }
}
