package chess.pieces;
import static org.junit.Assert.*;
import org.junit.Test;
import junit.framework.*;
import chess.pieces.Pawn;

public class PawnTest extends junit.framework.TestCase {
    public void testCreatePawns() {
        Pawn firstPawn = new Pawn(Pawn.WHITE);
        assertEquals(Pawn.WHITE, firstPawn.getColor());

        Pawn secondPawn = new Pawn(Pawn.BLACK);
        assertEquals(Pawn.BLACK, secondPawn.getColor());
    }

    @Test
    public void testPawnRepresentation() {
        Pawn whitePawn = new Pawn(true, 'p');
        Pawn blackPawn = new Pawn(false, 'P');

        assertEquals('p', whitePawn.getPrintableRepresentation());
        assertEquals('P', blackPawn.getPrintableRepresentation());
    }

}