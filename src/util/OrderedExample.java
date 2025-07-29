package util;

public class OrderedExample {
    @Dump(order = 2)
    private String lastName;
    @Dump(order = 1)
    private String firstName;
    private int age; //Não anotado, deve vir por último
    @Dump(order = 3)
    private String studentId;

    public OrderedExample(String firstName, String lastName, int age, String studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.studentId = studentId;
    }
}
