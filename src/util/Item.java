package util;

import java.time.LocalDate;

public class Item {
    @Dump(outputMethod = "getFormattedId") // Usará getFormattedId()
    private String id;
    @Dump
    private String description; // Usará toString() padrão
    @Dump(outputMethod = "toEpochDay") // Para uma classe de sistema como LocalDate
    private LocalDate expirationDate;

    public Item(String id, String description, LocalDate expirationDate) {
        this.id = id;
        this.description = description;
        this.expirationDate = expirationDate;
    }

    public String getFormattedId() {
        return "ID-" + id;
    }

    public String toString() { // toString padrão do Item
        return "Item:" + description;
    }
}
