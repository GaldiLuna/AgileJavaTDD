package chess;
import chess.pieces.Piece;
import util.StringUtil;

import java.io.File;

public class BoardTest extends junit.framework.TestCase {
    private Board board;

    protected void setUp() { board = new Board(); }

    public void testBoardStartsEmpty() {
        assertEquals(0, board.pieceCount());
    }

    public void testCreate() {
        Game game = new Game();
        game.initialize();
        board = game.getBoard();
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

    public void testSaveAndloadAsObject() throws Exception {
        final String filename = "board_object.ser"; //.ser é uma extensão comum para objetos serializados

        //Criar um tabuleiro inicializado
        Game game = new Game();
        game.initialize();
        Board originalBoard = game.getBoard();
        String originalBoardLayout = originalBoard.printAll();
        assertEquals(32, originalBoard.pieceCount());

        //Salvar o tabuleiro
        originalBoard.saveAsObject(filename);

        //Carregar o tabuleiro em uma nova instância
        Board loadedBoard = Board.loadFromObject(filename);

        //Verificar
        assertNotNull(loadedBoard);
        assertNotSame(originalBoard, loadedBoard); //Garante que é um novo objeto
        assertEquals(32, loadedBoard.pieceCount());
        assertEquals(originalBoardLayout, loadedBoard.printAll()); //Compara a representação textual

        //Limpeza
        new File(filename).delete();
    }

    public void testSaveAndLoadAsText() throws Exception {
        final String filename = "board_text.txt";

        //Criar um tabuleiro inicializado
        Game game = new Game();
        game.initialize();
        Board originalBoard = game.getBoard();
        String originalBoardLayout = originalBoard.printAll();

        //Salvar o tabuleiro
        originalBoard.saveAsText(filename);

        //Carregar o tabuleiro em uma nova instância
        Board loadedBoard = Board.loadFromText(filename);

        //Verificar
        assertNotNull(loadedBoard);
        assertNotSame(originalBoard, loadedBoard);
        assertEquals(32, loadedBoard.pieceCount());
        assertEquals(originalBoardLayout, loadedBoard.printAll());

        //Limpeza
        new File(filename).delete();
    }

}