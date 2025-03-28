package chess;
import chess.pieces.Pawn;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class BoardTest extends junit.framework.TestCase {
    private ChessBoard board;

    public void setUp() {
        board = new ChessBoard();
    }

    public void testAddPawnToBoard() {
        this.board = (board);
    }

    public void testBoardStartsEmpty() {
        assertEquals(0, board.getPieceCount());
    }

    public void testAddPawnsToBoard() {
        ChessBoard board = new ChessBoard();
        Pawn whitePawn = new Pawn(Pawn.WHITE);
        Pawn blackPawn = new Pawn(Pawn.BLACK);

        board.addPawn(whitePawn);
        board.addPawn(blackPawn);

        assertEquals(2, board.getPieceCount());
        assertTrue(board.getPawns().contains(whitePawn));
        assertTrue(board.getPawns(). contains(blackPawn));

    }

    public void testOnlyPawnsCanBeAdded() {
        ChessBoard board = new ChessBoard();
        //Deve gerar um erro de compilação: (deu certo apontou o erro).
        //board.addPawn(new Integer(7));
    }

}