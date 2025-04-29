package chess.pieces;

/**
 * Representa um peão no jogo de Xadrez.
 */
public class Piece {
    private String color;
    private boolean isWhite;
    private char printableRepresentation;
    public static final String WHITE = "White";
    public static final String BLACK = "Black";
    public static final String BLANK = "";

    /**
     * Cria um peão com a cor específica.
     * @param color a cor do peão
     */
    public Piece(String color, char printableRepresentation) {
        this.color = color;
        this.printableRepresentation = printableRepresentation;
    }

    /**
     * Construtor com parâmetro booleano de cor e representação.
     */
    public Piece(boolean isWhite, char printableRepresentation) {
        this.color = isWhite ? WHITE : BLACK;
        this.printableRepresentation = printableRepresentation;
    }

    /**
     * Cria um peão branco por padrão.
     */
    public Piece() {
        this.color = WHITE;
        this.printableRepresentation = 'p';
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
        return printableRepresentation;
    }

}