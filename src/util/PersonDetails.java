package util;

public class PersonDetails {
    private String firstName;
    private String lastName;
    private int age;
    //private String email;

    // Construtor principal para o teste
    public PersonDetails(String firstName, String lastName, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.personInfo = this; // Aponta para si mesmo para que o @Dump seja feito
    }

    // Estes 2 métodos são chamados no objeto 'PersonDetails' que é o 'fieldValue' para 'personInfo'
    public String getFullName() { return firstName + " " + lastName; }
    public String getAgeDescription() { return age + " years old"; }

    @Dump(outputMethods = {"getFullName", "getAgeDescription"}) // Chamará múltiplos métodos
    private PersonDetails personInfo; // Campo do tipo da própria classe para demonstrar

    @Dump // Apenas o toString padrão para este
    private String email;

    // Getters para os campos que o ToStringer pode acessar diretamente (embora use reflexão)
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public PersonDetails getPersonInfo() { return this; } // Retorna 'this' para testar
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "PersonDetails[" + firstName + " " + lastName + ", " + age + "yrs]";
    }
}
