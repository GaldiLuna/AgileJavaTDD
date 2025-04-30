package chess.pieces;

import java.awt.*;

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
    //private final Color color;
    private final String name;

    private Piece(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public enum Color { WHITE, BLACK }

    public static Piece of(Color color, String name) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Invalid piece name: " + name);
        }
        return new Piece(color, name);
    }

    private static boolean isValidName(String name) {
        return "pawn".equalsIgnoreCase(name) ||
                "knight".equalsIgnoreCase(name) ||
                "rook".equalsIgnoreCase(name) ||
                "bishop".equalsIgnoreCase(name) ||
                "queen".equalsIgnoreCase(name) ||
                "king".equalsIgnoreCase(name);
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Piece)) return false;
        Piece other = (Piece) obj;
        return color == other.color && name.equals(other. name);
    }

    @Override
    public int hashCode() {
        return 31 * color.hashCode() + name.hashCode();
    }

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