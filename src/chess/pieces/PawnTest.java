package chess.pieces;
import org.junit.Test;

public class PawnTest extends junit.framework.TestCase {
    public void testCreatePawnWithoutColor() {
        Pawn pawn = new Pawn();
        assertEquals(Pawn.WHITE, pawn.getColor());
        assertEquals('p', pawn.getPrintableRepresentation());
    }

    public void testCreateWhitePawn() {
        Pawn pawn = new Pawn(Pawn.WHITE);
        assertEquals(Pawn.WHITE, pawn.getColor());
        assertEquals('p', pawn.getPrintableRepresentation());
    }

    public void testCreateBlackPawn() {
        Pawn pawn = new Pawn(Pawn.BLACK);
        assertEquals(Pawn.BLACK, pawn.getColor());
        assertEquals('P', pawn.getPrintableRepresentation());
    }

    @Test
    public void testPawnRepresentation() {
        Pawn whitePawn = new Pawn(true, 'p');
        Pawn blackPawn = new Pawn(false, 'P');

        assertEquals('p', whitePawn.getPrintableRepresentation());
        assertEquals('P', blackPawn.getPrintableRepresentation());
    }

}