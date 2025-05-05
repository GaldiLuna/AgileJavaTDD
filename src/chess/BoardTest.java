package chess;
import chess.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase.*;
import util.StringUtil;

public class BoardTest extends junit.framework.TestCase {
    private ChessBoard board;

    @Before
    public void setUp() {
        board = new ChessBoard();
    }
    //protected void setUp() { board = new ChessBoard(); }

    @Test
    public void testBoardStartsEmpty() {
        ChessBoard board = new ChessBoard();
        assertEquals(0, board.getPieceCount());
    }

    @Test
    public void testCreate() {
        board.initialize();
        assertEquals(32, board.getPieceCount());
        String blankRank = StringUtil.appendNewLine("........");
        assertEquals(
            StringUtil.appendNewLine("RNBQKBNR") +
                    StringUtil.appendNewLine("PPPPPPPP") +
                    blankRank + blankRank + blankRank + blankRank +
                    StringUtil.appendNewLine("pppppppp") +
                    StringUtil.appendNewLine("rnbqkbnr"),
                    board.printBoard());

        //Verifica a segunda fileira (brancos)
        StringBuilder secondRank = new StringBuilder();
        for (Piece pawn : board.getRank(2)) {
            secondRank.append(pawn != null ? pawn.getPrintableRepresentation() : '.');
        }
        assertEquals("pppppppp", secondRank.toString());

        //Verifica a sétima fileira (pretos)
        StringBuilder seventhRank = new StringBuilder();
        for (Piece pawn : board.getRank(7)) {
            seventhRank.append(pawn != null ? pawn.getPrintableRepresentation() : '.');
        }
        assertEquals("PPPPPPPP", seventhRank.toString());

        //Verifica a representação completa do tabuleiro
        String expectedBoard =
                "RNBQKBNR" + StringUtil.NEWLINE +
                "PPPPPPPP" + StringUtil.NEWLINE + // rank 7 "pppppppp"
                "........" + StringUtil.NEWLINE +
                "........" + StringUtil.NEWLINE +
                "........" + StringUtil.NEWLINE +
                "........" + StringUtil.NEWLINE +
                "pppppppp" + StringUtil.NEWLINE + // rank 2 "PPPPPPPP"
                "rnbqkbnr";
//        System.out.println("Board representation: ");
//        System.out.println(board.toString());
        assertEquals(expectedBoard, board.toString());
    }

    @Test
    public void testInitializeRanks() {
        board.initialize();
        //Verifica se a segunda fileira contém "pppppppp"
        assertEquals("pppppppp", board.getRankPrintable(1)); // rank 2
        //Verifica se a penúltima fileira contém "PPPPPPPP"
        assertEquals("PPPPPPPP", board.getRankPrintable(6)); // rank 7
    }

    @Test
    public void testBoardPrinting() {
        board.initialize();
        String expected =
                "RNBQKBNR" + StringUtil.NEWLINE +
                "PPPPPPPP" + StringUtil.NEWLINE +
                "........" + StringUtil.NEWLINE +
                "........" + StringUtil.NEWLINE +
                "........" + StringUtil.NEWLINE +
                "........" + StringUtil.NEWLINE +
                "pppppppp" + StringUtil.NEWLINE +
                "rnbqkbnr";
        assertEquals(expected, board.printBoard());
    }

    @Test
    public void testDisplayBoard() {
        board.initialize();
        //Exibe o tabuleiro no console para verificação manual
        System.out.println(board.printBoard());
    }

}