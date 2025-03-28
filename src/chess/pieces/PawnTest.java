package chess.pieces;

public class PawnTest extends junit.framework.TestCase {
    public void testCreatePawns() {
        Pawn firstPawn = new Pawn(Pawn.WHITE);
        assertEquals(Pawn.WHITE, firstPawn.getColor());

        Pawn secondPawn = new Pawn(Pawn.BLACK);
        assertEquals(Pawn.BLACK, secondPawn.getColor());
    }

}