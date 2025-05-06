package chess.pieces;
import org.junit.Test;
import chess.pieces.Piece;

public class PieceTest extends junit.framework.TestCase {

    public void testCreateWhitePiece(){
        Piece pawn = Piece.createWhitePawn();
        assertEquals(Piece.Color.WHITE, pawn.getColors());
        assertEquals(Piece.Type.PAWN, pawn.getType());
        assertEquals(Piece.Symbol.p, pawn.getSymbol());
    }

    public void testCreateBlackPiece(){
        Piece pawn = Piece.createBlackPawn();
        assertEquals(Piece.Color.BLACK, pawn.getColors());
        assertEquals(Piece.Type.PAWN, pawn.getType());
        assertEquals(Piece.Symbol.P, pawn.getSymbol());
    }

    public void testEquality() {
        Piece pawn1 = Piece.createWhitePawn();
        Piece pawn2 = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();
        assertEquals(pawn1, pawn2);
        assertFalse(pawn1.equals(blackPawn));
    }

    public void testPieceCount() {
        Piece.resetCounts();
        Piece.createWhitePawn();
        Piece.createBlackPawn();
        assertEquals(1, Piece.getWhitePieceCount());
        assertEquals(1, Piece.getBlackPieceCount());
    }

}