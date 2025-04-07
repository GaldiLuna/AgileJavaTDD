package chess.pieces;

/**
 * Representa um peão no jogo de Xadrez.
 */
public class Pawn {
    private String color;
    private boolean isWhite;
    private char representation;
    public static final String WHITE = "White";
    public static final String BLACK = "Black";
    //public static final String BLANK = "";

    /**
     * Cria um peão com a cor específica.
     * @param color a cor do peão
     */
    public Pawn(String color) {
        this.color = color;
    }

    /**
     * Construtor com parâmetro booleano de cor e representação.
     */
    public Pawn(boolean isWhite, char representation) {
        this.isWhite = isWhite;
        this.representation = representation;
    }

    /**
     * Cria um peão branco por padrão.
     */
    public Pawn() {
        this(WHITE);
    }

    /**
     * Retorna a cor do peão.
     * @return a cor do peão
     */
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public char getPrintableRepresentation() {
        return representation;
    }

}