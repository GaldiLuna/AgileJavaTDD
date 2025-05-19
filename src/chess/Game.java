package chess;
import chess.pieces.Piece;
import chess.pieces.King;
import chess.pieces.Queen;
import chess.pieces.Bishop;
import chess.pieces.Knight;
import chess.pieces.Rook;
import chess.pieces.Pawn;
import java.util.*;

public class Game {
    private Board board;

    public Game() {
        board = new Board();
        initialize();
    }

    public void initialize() {
        Piece.resetCounts(); //Reseta contadores antes de inicializar

        //Linha 8 peças pretas:
        board.putPiece(new Rook(Piece.Collor.BLACK), 0, 0);
        board.putPiece(new Knight(Piece.Collor.BLACK), 1, 0);
        board.putPiece(new Bishop(Piece.Collor.BLACK), 2, 0);
        board.putPiece(new Queen(Piece.Collor.BLACK), 3, 0);
        board.putPiece(new King(Piece.Collor.BLACK), 4, 0);
        board.putPiece(new Bishop(Piece.Collor.BLACK), 5, 0);
        board.putPiece(new Knight(Piece.Collor.BLACK), 6, 0);
        board.putPiece(new Rook(Piece.Collor.BLACK), 7, 0);
        //Linha 7 peões pretos:
        for (int i = 0; i < 8; i++) {
            board.putPiece(new Pawn(Piece.Collor.BLACK), i, 1);
        }
        //Linhas 6, 5, 4 e 3 vazias:
        for (int row = 2; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
                board.putPiece(null, col, row);
            }
        }
        //Linha 2 peões brancos:
        for (int j = 0; j < 8; j++) {
            board.putPiece(new Pawn(Piece.Collor.WHITE), j, 6);
        }
        //Linha 1 peças brancas:
        board.putPiece(new Rook(Piece.Collor.WHITE), 0, 7);
        board.putPiece(new Knight(Piece.Collor.WHITE), 1, 7);
        board.putPiece(new Bishop(Piece.Collor.WHITE), 2, 7);
        board.putPiece(new Queen(Piece.Collor.WHITE), 3, 7);
        board.putPiece(new King(Piece.Collor.WHITE), 4, 7);
        board.putPiece(new Bishop(Piece.Collor.WHITE), 5, 7);
        board.putPiece(new Knight(Piece.Collor.WHITE), 6, 7);
        board.putPiece(new Rook(Piece.Collor.WHITE), 7, 7);
    }

    public Board getBoard() {
        return board;
    }

    public double calculateStrength(Piece.Collor colors) {
        double strength = 0.0;
        Map<Integer, Integer> pawnsPerFile = new HashMap<>();

        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Piece piece = board.getPiece(file, rank);
                if (piece != null && piece.getColors() == colors) {
                    double baseStrength = piece.getType().getStrength();
                    if (piece.getType() == Piece.Type.PAWN) {
                        int count = pawnsPerFile.getOrDefault(file, 0);
                        strength += (count > 0) ? 0.5 : baseStrength;
                        pawnsPerFile.put(file, count + 1);
                    } else {
                        strength += baseStrength;
                    }
                }
            }
        }
        return strength;
    }

}
