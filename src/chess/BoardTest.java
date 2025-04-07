package chess;
import chess.pieces.Pawn;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import chess.ChessBoard;

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
        Pawn p = new Pawn(Pawn.WHITE);
        Pawn P = new Pawn(Pawn.BLACK);

        board.addPawn(p);
        board.addPawn(P);

        assertEquals(2, board.getPieceCount());
        // "p" = branca e "P" = preta
        assertTrue(board.getPawns().contains(p));
        assertTrue(board.getPawns().contains(P));

    }

    public void testOnlyPawnsCanBeAdded() {
        ChessBoard board = new ChessBoard();
        //Deve gerar um erro de compilação: (deu certo apontou o erro).
        //board.addPawn(new Integer(7));
    }

    @Test
    public void testInitializeRanks() {
        ChessBoard boardTest = new ChessBoard();
        //Verifica se a segunda fileira contém "pppppppp"
        assertEquals("pppppppp", boardTest.getRankPrintable(1));
        //Verifica se a penúltima fileira contém "PPPPPPPP"
        assertEquals("PPPPPPPP", boardTest.getRankPrintable(6));
    }

    @Test
    public void testBoardPrinting() {
        ChessBoard boardTest = new ChessBoard();
        String expected =
                "........" + System.lineSeparator() +
                "PPPPPPPP" + System.lineSeparator() +
                "........" + System.lineSeparator() +
                "........" + System.lineSeparator() +
                "........" + System.lineSeparator() +
                "........" + System.lineSeparator() +
                "pppppppp" + System.lineSeparator() +
                "........";
        assertEquals(expected, boardTest.printBoard());
    }

    @Test
    public void testDisplayBoard() {
        ChessBoard boardTest = new ChessBoard();
        //Exibe o tabuleiro no console para verificação manual
        System.out.println(boardTest.printBoard());
    }

}