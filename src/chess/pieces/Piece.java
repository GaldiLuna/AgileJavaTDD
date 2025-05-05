package chess.pieces;

import java.awt.*;

/**
 * Representa um peão no jogo de Xadrez.
 */
public class Piece {
    private String color;
    private String name;
    private boolean isWhite;
    private char printableRepresentation;
    public static final String WHITE = "White";
    public static final String BLACK = "Black";
    public static final String BLANK = "";
    public enum Color { WHITE, BLACK }
    public enum Type { PAWN, KNIGHT, ROOK, BISHOP, QUEEN, KING }
//    public enum Names { UpName, LowName }
//    public enum UpName { P, N, R, B, Q, K}
//    public enum LowName { p, n, r, b, q, k}
    private final Color colors;
    private final Type type;
//    private final Names names;
//    private final UpName upperNames;
//    private final LowName lowerNames;

    private Piece(String color, String name) {
        this.color = color;
        this.name = name;
    }

    private Piece(Color colors, Type type, Names names) {
        this.colors = colors;
        this.type = type;
        this.printableRepresentation = printableRepresentation;
    }

    public static Piece of(String color, String name) {
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

    public String getName() {
        return name;
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

    //MÉTODOS DE FÁBRICA DAS PEÇAS.
    public static Piece createWhitePawn() {
        return new Piece(Color.WHITE, Type.PAWN);
    }
    public static Piece createBlackPawn() {
        return new Piece(Color.BLACK, Type.PAWN);
    }

    public static Piece createWhiteKnight() {
        return new Piece(Color.WHITE, Type.KNIGHT);
    }
    public static Piece createBlackKnight() {
        return new Piece(Color.BLACK, Type.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return new Piece(Color.WHITE, Type.ROOK);
    }
    public static Piece createBlackRook() {
        return new Piece(Color.BLACK, Type.ROOK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(Color.WHITE, Type.BISHOP);
    }
    public static Piece createBlackBishop() {
        return new Piece(Color.BLACK, Type.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return new Piece(Color.WHITE, Type.QUEEN);
    }
    public static Piece createBlackQueen() {
        return new Piece(Color.BLACK, Type.QUEEN);
    }

    public static Piece createWhiteKing() {
        return new Piece(Color.WHITE, Type.KING);
    }
    public static Piece createBlackKing() {
        return new Piece(Color.BLACK, Type.KING);
    }
    //FIM DOS MÉTODOS DE FÁBRICA DAS PEÇAS.

    public Color getColors() {
        return colors;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Piece piece = (Piece) obj;
        return colors == piece.colors && type == piece.type;
        if (!(obj instanceof Piece)) return false;
        Piece other = (Piece) obj;
        return colors == other.colors && type.equals(other.type);
    }

    @Override
    public int hashCode() {
        int result = colors.hashCode();
        result = 31 * result + type.hashCode();
        return result;
        return 31 * colors.hashCode() + type.hashCode();
    }

}