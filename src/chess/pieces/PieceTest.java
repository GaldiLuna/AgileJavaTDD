package chess.pieces;
import chess.Board;
import org.junit.Test;
import chess.pieces.Piece;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PieceTest extends junit.framework.TestCase {

    public void testCreates() {
        verifyCreation(
            Piece.createWhitePawn(), Piece.createBlackPawn(),
            Piece.Type.PAWN, Piece.Type.PAWN.getRepresentation());
    }

    private void verifyCreation(Piece whitePiece, Piece blackPiece, Piece.Type type, char representation) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());
        assertEquals(representation, whitePiece.getRepresentation());
        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
        assertEquals(Character.toUpperCase(representation), blackPiece.getRepresentation());
    }

    public void testCreateWhitePiece(){
        Piece pawn = Piece.createWhitePawn();
        assertEquals(Piece.Collor.WHITE, pawn.getColors());
        assertEquals(Piece.Type.PAWN, pawn.getType());
        assertEquals(Piece.Type.PAWN.getRepresentation(), pawn.getRepresentation());
    }

    public void testCreateBlackPiece(){
        Piece pawn = Piece.createBlackPawn();
        assertEquals(Piece.Collor.BLACK, pawn.getColors());
        assertEquals(Piece.Type.PAWN, pawn.getType());
        assertEquals(Character.toUpperCase(Piece.Type.PAWN.getRepresentation()), pawn.getRepresentation());
    }

    public void testEquality() {
        Piece pawn1 = Piece.createWhitePawn();
        Piece pawn2 = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();
        assertEquals(pawn1, pawn2);
        assertFalse(pawn1.equals(blackPawn));
    }

    public void testPieceCount() {
        Piece.resetCounts();
        Piece.createWhitePawn();
        Piece.createBlackPawn();
        assertEquals(1, Piece.getWhitePieceCount());
        assertEquals(1, Piece.getBlackPieceCount());
    }

    public void testKingMovesFromBoard() {
        Piece king = Piece.createWhiteKing(); //Cria uma instância de Rei Branco
        List<String> moves = king.getPossibleMoves("e4"); //Obtém os movimentos possíveis a partir de E4
        List<String> expectedMoves = Arrays.asList("d3", "d4", "d5", "e3", "e5", "f3", "f4", "f5"); //Locais de movimentos esperados

        //Ordena as listas para garantir que a ordem não interfira na comparação
        Collections. sort(moves);
        Collections.sort(expectedMoves);

        assertEquals(expectedMoves, moves); //Verifica se o movimento realizado foi igual ao que era esperado
    }

    private Board board = new Board();

    public void assertContains(List<String> actual, String... expected) {
        assertTrue(actual.containsAll(Arrays.asList(expected)));
    }

    public void testKingMoveNotOnEdge() {
        Piece piece = Piece.createBlackKing();
        board.put("d3", piece);
    }

}

//Continuar da questão 4 - dando erro no PUT
//https://grok.com/share/c2hhcmQtMg%3D%3D_d80d33da-26f0-4b3f-a83e-d008805d6613