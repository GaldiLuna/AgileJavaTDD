package util;

public class QuotedPerson {
    @Dump(quote = true)
    private String name;
    @Dump
    private String occupation; //Sem quote

    public QuotedPerson(String name, String occupation) {
        this.name = name;
        this.occupation = occupation;
    }
}
