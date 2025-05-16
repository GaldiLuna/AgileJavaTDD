package chess.pieces;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um peão no jogo de Xadrez.
 */

public abstract class Piece {
    public enum Collor {WHITE, BLACK}

    public enum Type {
        PAWN('p', 1.0), ROOK('r', 6.0), KNIGHT('n', 2.5), BISHOP('b', 4.0), QUEEN('q', 10.0), KING('k', 50.0), NO_PIECE('.', 0.0);

        private final char representation;
        private final double strength;

        Type(char representation, double strength) {
            this.representation = representation;
            this.strength = strength;
        }

        public char getRepresentation() {
            return representation;
        }

        public double getStrength() {
            return strength;
        }
    }

    private static int whitePieceCount = 0;
    private static int blackPieceCount = 0;

    protected final Collor colors;
    protected final Type type;
    protected char representation;

    protected Piece(Collor colors, Type type) {
        this.colors = colors;
        this.type = type;
        this.representation = colors == Collor.WHITE ? type.getRepresentation() : Character.toUpperCase(type.getRepresentation());
        if (colors == Collor.WHITE) {
            whitePieceCount++;
        } else {
            blackPieceCount++;
        }
    }

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

    public abstract List<String> getPossibleMoves(String position);

    //MÉTODOS DE FÁBRICA DAS PEÇAS.
    public static Piece createWhitePawn() { return new Pawn(Collor.WHITE); }
    public static Piece createBlackPawn() { return new Pawn(Collor.BLACK); }

    public static Piece createWhiteRook() { return new Rook(Collor.WHITE); }
    public static Piece createBlackRook() { return new Rook(Collor.BLACK); }

    public static Piece createWhiteKnight() { return new Knight(Collor.WHITE); }
    public static Piece createBlackKnight() { return new Knight(Collor.BLACK); }

    public static Piece createWhiteBishop() { return new Bishop(Collor.WHITE); }
    public static Piece createBlackBishop() { return new Bishop(Collor.BLACK); }

    public static Piece createWhiteQueen() { return new Queen(Collor.WHITE); }
    public static Piece createBlackQueen() { return new Queen(Collor.BLACK); }

    public static Piece createWhiteKing() { return new King(Collor.WHITE); }
    public static Piece createBlackKing() { return new King(Collor.BLACK);}

}

//Para corrigir esses erros preciso criar as classes das peças e fazer seus movimentos.