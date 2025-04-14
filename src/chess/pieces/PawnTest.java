package chess.pieces;
import org.junit.Test;

public class PawnTest extends junit.framework.TestCase {
    public void testCreatePawnWithoutColor() {
        Pawn pawn = new Pawn();
        assertEquals(Pawn.WHITE, pawn.getColor());
        assertEquals('p', pawn.getPrintableRepresentation());
    }

    public void testCreateColorPawns() {
        Pawn firstPawn = new Pawn(Pawn.WHITE);
        assertEquals(Pawn.WHITE, firstPawn.getColor());
        assertEquals('p', firstPawn.getPrintableRepresentation());

        Pawn secondPawn = new Pawn(Pawn.BLACK);
        assertEquals(Pawn.BLACK, secondPawn.getColor());
        assertEquals('P', secondPawn.getPrintableRepresentation());
    }

    @Test
    public void testPawnRepresentation() {
        Pawn whitePawn = new Pawn(true, 'p');
        Pawn blackPawn = new Pawn(false, 'P');

        assertEquals('p', whitePawn.getPrintableRepresentation());
        assertEquals('P', blackPawn.getPrintableRepresentation());
    }

}