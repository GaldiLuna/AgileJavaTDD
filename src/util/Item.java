package util;

import java.time.LocalDate;

public class Item {

    public static class ItemId {
        private String rawId;
        public ItemId(String rawId) { this.rawId = rawId; }
        public String getFormatted() { return "ID-" + rawId; }
        @Override public String toString() { return  rawId; } //Default toString
    }

    @Dump(outputMethods = {"getFormatted"}) // Usará getFormattedId() Chamará getFormatted() no objeto ItemId
    private ItemId id;

    @Dump
    private String description; // Usará toString() padrão da String (pois String tem toString() )

    @Dump(outputMethods = {"toEpochDay"}) // Para uma classe de sistema como LocalDate (que não tem toEpochDay() )
    private LocalDate expirationDate;

    public Item(String rawId, String description, LocalDate expirationDate) {
        this.id = new ItemId(rawId);
        this.description = description;
        this.expirationDate = LocalDate.now();
    }

    // Estes métodos abaixo NÃO são necessários para o ToStringer atual,
    // pois ele procura os métodos nos TIPOS DOS CAMPOS (ItemId, String, LocalDate).
//    public String getFormattedId() {
//        return "ID-" + id;
//    }
//
//    public String toString() { // toString padrão do Item
//        return "Item:" + description;
//    }

}
