package chess.pieces;
import org.junit.Test;

public class PawnTest extends junit.framework.TestCase {
    @Test
    public void testCreatePawnWithoutColor() {
        Pawn pawn = new Pawn();
        assertEquals(Pawn.WHITE, pawn.getColor());
        assertEquals('p', pawn.getPrintableRepresentation());
    }

    @Test
    public void testCreateWhitePawn() {
        Pawn pawn = new Pawn(Pawn.WHITE, 'p');
        assertEquals(Pawn.WHITE, pawn.getColor());
        assertEquals('p', pawn.getPrintableRepresentation());
    }

    @Test
    public void testCreateBlackPawn() {
        Pawn pawn = new Pawn(Pawn.BLACK, 'P');
        assertEquals(Pawn.BLACK, pawn.getColor());
        assertEquals('P', pawn.getPrintableRepresentation());
    }

    @Test
    public void testPawnRepresentation() {
        Pawn whitePawn = new Pawn(true, 'p');
        assertEquals('p', whitePawn.getPrintableRepresentation());

        Pawn blackPawn = new Pawn(false, 'P');
        assertEquals('P', blackPawn.getPrintableRepresentation());
    }

}