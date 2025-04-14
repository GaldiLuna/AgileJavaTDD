package chess;
import chess.pieces.Pawn;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import chess.ChessBoard;

public class BoardTest extends junit.framework.TestCase {
    private ChessBoard board;

    @Before
    public void setUp() {
        board = new ChessBoard();
    }

    //public void testAddPawnToBoard() {this.board = (board);}

    @Test
    public void testBoardStartsEmpty() {
        ChessBoard board = new ChessBoard();
        assertEquals(0, board.getPieceCount());
    }

    @Test
    public void testAddPawnsToBoard() {
        ChessBoard board = new ChessBoard();

        Pawn p = new Pawn(Pawn.WHITE);
        Pawn P = new Pawn(Pawn.BLACK);

        board.addPawn(p);
        board.addPawn(P);

        assertEquals(2, board.getPieceCount());
        List<Pawn> pieces = board.getPieces();
        pieces = board.getPieces();
        assertEquals(2, pieces.size());

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
    public void testCreate() {
        assertEquals(16, board.getPieceCount());

        //Verifica a segunda fileira (brancos)
        //String secondRank = "";
        StringBuilder secondRank = new StringBuilder();
        for (Pawn pawn : board.getRank(2)) {
            //secondRank += pawn.getPrintableRepresentation();
            secondRank.append(pawn.getPrintableRepresentation());
        }
        //assertEquals("pppppppp", secondRank);
        assertEquals("pppppppp", secondRank.toString());

        //Verifica a sétima fileira (pretos)
        //String seventhRank = "";
        StringBuilder seventhRank = new StringBuilder();
        for (Pawn pawn : board.getRank(7)) {
            //seventhRank += pawn.getPrintableRepresentation();
            seventhRank.append(pawn.getPrintableRepresentation());
        }
        //assertEquals("PPPPPPPP", seventhRank);
        assertEquals("PPPPPPPP", seventhRank.toString());

        //Verifica a representação completa do tabuleiro
        String expectedBoard =
                "........" + System.lineSeparator() +
                "pppppppp" + System.lineSeparator() +
                "........" + System.lineSeparator() +
                "........" + System.lineSeparator() +
                "........" + System.lineSeparator() +
                "........" + System.lineSeparator() +
                "PPPPPPPP" + System.lineSeparator() +
                "........";
        System.out.println("Board representation: ");
        System.out.println(board.toString());
        assertEquals(expectedBoard, board.toString());
    }

//    @Test
//    public testBoardStartsWith16Pieces() {
//        assertEquals(16, board.getPieceCount());
//    }

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