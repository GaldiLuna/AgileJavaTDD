package chess;
import chess.pieces.Pawn;
import java.util.*;

public class ChessBoard {
    static final int SQUARES_PER_SIDE = 8;
    public int numberOfSquares = ChessBoard.SQUARES_PER_SIDE * ChessBoard.SQUARES_PER_SIDE;

    private List<Pawn> pieces = new ArrayList<>();

    public void AddPawn(Pawn pawn) {
        pieces.add(pawn);
    }

    public List<Pawn> getPawns() {
        return pieces;
    }

    public int getPieceCount() {
        return pieces.size();
    }

    public void addPawn(Pawn pawn) {
        if (!(pawn instanceof Pawn)) {
            throw new IllegalArgumentException("APenas peões podem ser adicionados ao tabuleiro.");
        }
        pieces.add(pawn);
    }

}