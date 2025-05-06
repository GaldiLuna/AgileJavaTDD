package chess.pieces;

/**
 * Representa um peão no jogo de Xadrez.
 */
public class Piece {
    public static final String WHITE = "White";
    public static final String BLACK = "Black";
    public static final String BLANK = "";

    public enum Color { WHITE, BLACK }
    public enum Type { PAWN, KNIGHT, ROOK, BISHOP, QUEEN, KING }
    public enum Symbol { p, n, r, b, q, k, P, N, R, B, Q, K }

    private static int whitePieceCount = 0;
    private static int blackPieceCount = 0;

    private final Color colors;
    private final Type type;
    private final Symbol representation;

    //CONSTRUTOR PRIVADO:
    private Piece(Color colors, Type type, Symbol representation) {
        this.colors = colors;
        this.type = type;
        this.representation = representation;
        if (colors == Color.WHITE) {
            whitePieceCount++;
        } else {
            blackPieceCount++;
        }
    }

    private static boolean isValidName(String name) {
        return "pawn".equalsIgnoreCase(name) ||
                "knight".equalsIgnoreCase(name) ||
                "rook".equalsIgnoreCase(name) ||
                "bishop".equalsIgnoreCase(name) ||
                "queen".equalsIgnoreCase(name) ||
                "king".equalsIgnoreCase(name);
    }

    //MÉTODOS DE FÁBRICA DAS PEÇAS.
    public static Piece createWhitePawn() {
        return new Piece(Color.WHITE, Type.PAWN, Symbol.p);
    }
    public static Piece createBlackPawn() {
        return new Piece(Color.BLACK, Type.PAWN, Symbol.P);
    }

    public static Piece createWhiteKnight() {
        return new Piece(Color.WHITE, Type.KNIGHT, Symbol.n);
    }
    public static Piece createBlackKnight() {
        return new Piece(Color.BLACK, Type.KNIGHT, Symbol.N);
    }

    public static Piece createWhiteRook() {
        return new Piece(Color.WHITE, Type.ROOK, Symbol.r);
    }
    public static Piece createBlackRook() {
        return new Piece(Color.BLACK, Type.ROOK, Symbol.R);
    }

    public static Piece createWhiteBishop() {
        return new Piece(Color.WHITE, Type.BISHOP, Symbol.b);
    }
    public static Piece createBlackBishop() {
        return new Piece(Color.BLACK, Type.BISHOP, Symbol.B);
    }

    public static Piece createWhiteQueen() {
        return new Piece(Color.WHITE, Type.QUEEN, Symbol.q);
    }
    public static Piece createBlackQueen() {
        return new Piece(Color.BLACK, Type.QUEEN, Symbol.Q);
    }

    public static Piece createWhiteKing() {
        return new Piece(Color.WHITE, Type.KING, Symbol.k);
    }
    public static Piece createBlackKing() {
        return new Piece(Color.BLACK, Type.KING, Symbol.K);
    }
    //FIM DOS MÉTODOS DE FÁBRICA DAS PEÇAS.

    //GETTERS:
    public Color getColors() {
        return colors;
    }
    public Type getType() {
        return type;
    }
    public Symbol getSymbol() {
        return representation;
    }

    //MÉTODOS PARA VERIFICAR AS CORES:
    public boolean isWhite() {
        return colors == Color.WHITE;
    }
    public boolean isBlack() {
        return colors == Color.BLACK;
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