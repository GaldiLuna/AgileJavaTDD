package util;

public class ExampleDumpClasse {
    @Dump
    private String name;
    @Dump
    private int id;
    private String secret; //Esse campo não entra no DUMP

    public ExampleDumpClasse(String name, int id, String secret) {
        this.name = name;
        this.id = id;
        this.secret = secret;
    }

//    //Getters e Setters
//    public String getName() {
//        return name;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getSecret() {
//        return secret;
//    }
}
