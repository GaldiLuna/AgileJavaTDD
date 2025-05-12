package chess.pieces;

/**
 * Representa um peão no jogo de Xadrez.
 */
public class Piece {
    public static final String WHITE = "White";
    public static final String BLACK = "Black";
    public static final String BLANK = "";

    public enum Collor { WHITE, BLACK }
    public enum Type { PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING, NO_PIECE }

    private static final String symbol = "z";
    public enum Symbol { p, r, n, b, q, k, z, P, R, N, B, Q, K, Z }
    //Type: NO_PIECE = Symbol: z ou Z -- VERIFICAR SE USA O DE CIMA OU DE BAIXO

    public static final char PAWN_REPRESENTATION = 'p';
    public static final char ROOK_REPRESENTATION = 'r';
    public static final char KNIGHT_REPRESENTATION = 'n';
    public static final char BISHOP_REPRESENTATION = 'b';
    public static final char QUEEN_REPRESENTATION = 'q';
    public static final char KING_REPRESENTATION = 'k';

    private static int whitePieceCount = 0;
    private static int blackPieceCount = 0;

    private final Collor colors;
    private final Type type;
    private char representation;

    //CONSTRUTOR PRIVADO:
    private Piece(Collor colors, Type type, char representation) {
        this.colors = colors;
        this.type = type;
        this.representation = representation;
        if (colors == Collor.WHITE) {
            whitePieceCount++;
        } else {
            blackPieceCount++;
        }
    }

    private static boolean isValidName(String name) {
        return "pawn".equalsIgnoreCase(name) ||
                "rook".equalsIgnoreCase(name) ||
                "knight".equalsIgnoreCase(name) ||
                "bishop".equalsIgnoreCase(name) ||
                "queen".equalsIgnoreCase(name) ||
                "king".equalsIgnoreCase(name);
    }

    private static Piece createPiece(Collor colors, Type type, char representation) {
        return new Piece(colors, type, colors == Collor.WHITE ? representation : Character.toUpperCase(representation));
    }

    //MÉTODOS DE FÁBRICA DAS PEÇAS.
    public static Piece createWhitePawn() {
        return createPiece(Collor.WHITE, Type.PAWN, PAWN_REPRESENTATION);
    }
    public static Piece createBlackPawn() {
        return createPiece(Collor.BLACK, Type.PAWN, Character.toUpperCase(PAWN_REPRESENTATION));
    }

    public static Piece createWhiteRook() {
        return createPiece(Collor.WHITE, Type.ROOK, ROOK_REPRESENTATION);
    }
    public static Piece createBlackRook() {
        return createPiece(Collor.BLACK, Type.ROOK, Character.toUpperCase(ROOK_REPRESENTATION));
    }

    public static Piece createWhiteKnight() {
        return createPiece(Collor.WHITE, Type.KNIGHT, KNIGHT_REPRESENTATION);
    }
    public static Piece createBlackKnight() {
        return createPiece(Collor.BLACK, Type.KNIGHT, Character.toUpperCase(KNIGHT_REPRESENTATION));
    }

    public static Piece createWhiteBishop() {
        return createPiece(Collor.WHITE, Type.BISHOP, BISHOP_REPRESENTATION);
    }
    public static Piece createBlackBishop() {
        return createPiece(Collor.BLACK, Type.BISHOP, Character.toUpperCase(BISHOP_REPRESENTATION));
    }

    public static Piece createWhiteQueen() {
        return createPiece(Collor.WHITE, Type.QUEEN, QUEEN_REPRESENTATION);
    }
    public static Piece createBlackQueen() {
        return createPiece(Collor.BLACK, Type.QUEEN, Character.toUpperCase(QUEEN_REPRESENTATION));
    }

    public static Piece createWhiteKing() {
        return createPiece(Collor.WHITE, Type.KING, KING_REPRESENTATION);
    }
    public static Piece createBlackKing() {
        return createPiece(Collor.BLACK, Type.KING, Character.toUpperCase(KING_REPRESENTATION));
    }
    //FIM DOS MÉTODOS DE FÁBRICA DAS PEÇAS.

    //GETTERS:
    public Collor getColors() {
        return colors;
    }
    public Type getType() {
        return type;
    }
    public char getRepresentation() {
        return representation;
    }

    public static Symbol getSymbol() {
        return symbol;
    }

    //MÉTODOS PARA VERIFICAR AS CORES:
    public boolean isWhite() {
        return colors == Collor.WHITE;
    }
    public boolean isBlack() {
        return colors == Collor.BLACK;
    }

    //CONTADORES ESTÁTICOS:
    public static int getWhitePieceCount() {
        return whitePieceCount;
    }
    public static int getBlackPieceCount() {
        return blackPieceCount;
    }
    public static void resetCounts() {
        whitePieceCount = 0;
        blackPieceCount = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Piece piece = (Piece) obj;
        return colors == piece.colors && type == piece.type;
    }

    @Override
    public int hashCode() {
        int result = colors.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

}