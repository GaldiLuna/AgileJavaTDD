package chess.pieces;
import org.junit.Test;

public class PieceTest extends junit.framework.TestCase {
    @Test
    public void testCreatePawnWithoutColor() {
        Piece pawn = new Piece();
        assertEquals(Piece.WHITE, pawn.getColor());
        assertEquals('p', pawn.getPrintableRepresentation());
    }

    @Test
    public void testCreateWhitePawn() {
        Piece pawn = new Piece(Piece.WHITE, 'p');
        assertEquals(Piece.WHITE, pawn.getColor());
        assertEquals('p', pawn.getPrintableRepresentation());
    }

    @Test
    public void testCreateBlackPawn() {
        Piece pawn = new Piece(Piece.BLACK, 'P');
        assertEquals(Piece.BLACK, pawn.getColor());
        assertEquals('P', pawn.getPrintableRepresentation());
    }

    @Test
    public void testPawnRepresentation() {
        Piece whitePawn = new Piece(true, 'p');
        assertEquals('p', whitePawn.getPrintableRepresentation());

        Piece blackPawn = new Piece(false, 'P');
        assertEquals('P', blackPawn.getPrintableRepresentation());
    }

}