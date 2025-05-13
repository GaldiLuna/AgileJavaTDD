package chess.pieces;
import org.junit.Test;
import chess.pieces.Piece;

public class PieceTest extends junit.framework.TestCase {

    public void testCreates() {
        verifyCreation(
            Piece.createWhitePawn(), Piece.createBlackPawn(),
            Piece.Type.PAWN, Piece.PAWN_REPRESENTATION);
    }

    private void verifyCreation(Piece whitePiece, Piece blackPiece, Piece.Type type, char representation) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());
        assertEquals(representation, whitePiece.getRepresentation());
        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
        assertEquals(Character.toUpperCase(representation), blackPiece.getRepresentation());
    }

    public void testCreateWhitePiece(){
        Piece pawn = Piece.createWhitePawn();
        assertEquals(Piece.Collor.WHITE, pawn.getColors());
        assertEquals(Piece.Type.PAWN, pawn.getType());
        assertEquals(Piece.PAWN_REPRESENTATION, pawn.getRepresentation());
    }

    public void testCreateBlackPiece(){
        Piece pawn = Piece.createBlackPawn();
        assertEquals(Piece.Collor.BLACK, pawn.getColors());
        assertEquals(Piece.Type.PAWN, pawn.getType());
        assertEquals(Character.toUpperCase(Piece.PAWN_REPRESENTATION), pawn.getRepresentation());
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