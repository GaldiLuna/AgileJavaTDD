package chess;
import chess.pieces.Piece;
import util.StringUtil;
import javax.sql.rowset.serial.SQLOutputImpl;

public class BoardTest extends junit.framework.TestCase {
    private Board board;

    protected void setUp() { board = new Board(); }

    public void testBoardStartsEmpty() {
        Board board = new Board();
        assertEquals(0, board.pieceCount());
    }

    public void testCreate() {
        board.initialize();
        assertEquals(32, board.pieceCount());
        assertEquals(16, Piece.getWhitePieceCount());
        assertEquals(16, Piece.getBlackPieceCount());

        String blankRank = StringUtil.appendNewLine("........");
        String expectedBoard =
                StringUtil.appendNewLine("RNBQKBNR") + //Linha 8 peças pretas
                StringUtil.appendNewLine("PPPPPPPP") + //Linha 7 peões pretos
                blankRank + blankRank + blankRank + blankRank + //Linhas 6, 5, 4 e 3 vazias
                StringUtil.appendNewLine("pppppppp") + //Linha 2 peões brancos
                StringUtil.appendNewLine("rnbqkbnr"); //Linha 1 peças brancas
        assertEquals(expectedBoard, board.printAll());

        //Impressão do tabuleiro no console:
        System.out.println("Visualização do Tabuleiro:");
        System.out.println(board.printAll());
    }

}