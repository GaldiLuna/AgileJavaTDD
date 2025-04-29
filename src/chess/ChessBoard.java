package chess;
import chess.pieces.Piece;
import java.util.*;

public class ChessBoard {
    static final int SQUARES_PER_SIDE = 8;
    public int numberOfSquares = ChessBoard.SQUARES_PER_SIDE * ChessBoard.SQUARES_PER_SIDE;
    //Representa as 8 fileiras do tabuleiro.
    private ArrayList<ArrayList<Piece>> ranks;
    private List<Piece> pieces = new ArrayList<>();

    public List<Piece> getPawns() {
        return pieces;
    }
    public List<Piece> getPieces() {
        return pieces;
    }

    public List<Piece> getRank(int rank) {
        return ranks.get(rank - 1);
    }

    public int getPieceCount() {
        return pieces.size();
    }

    public ChessBoard() {
        ranks = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ArrayList<Piece> row = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                row.add(null);
            }
            ranks.add(row);
        }
        // Não adiciona peças no construtor, para que o tabuleiro comece vazio.
    }

    public void initialize() {
        pieces.clear();
        for (ArrayList<Piece> row : ranks) {
            row.clear();
            for (int j = 0; j < 8; j++) {
                row.add(null);
            }
        }

        //Cria a fileira de peões brancos (na segunda fileira).
        ArrayList<Piece> whitePawns = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Piece whitePawn = new Piece(Piece.WHITE, 'p');
            whitePawns.add(whitePawn);
            pieces.add(whitePawn);
        }
        ranks.set(1, whitePawns);

        //Cria a fileira de peões pretos (na penúltima fileira).
        ArrayList<Piece> blackPawns = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Piece blackPawn = new Piece(Piece.BLACK, 'P');
            blackPawns.add(blackPawn);
            pieces.add(blackPawn);
        }
        ranks.set(6, blackPawns);
    }

    //Metodo auxiliar para obter a representação de uma fileira
    public String getRankPrintable(int rankIndex) {
        StringBuilder sb = new StringBuilder();
        for (Piece p : ranks.get(rankIndex)) {
            if (p == null) {
                sb.append('.');
            } else {
                sb.append(p.getPrintableRepresentation());
            }
        }
        return sb.toString();
    }

    //Metodo para imprimir o tabuleiro completo.
    public String printBoard() {
        StringBuilder boardString = new StringBuilder();
        //Considerando que a fileira 8 é a superior e a fileira 1 é a inferior
        for (int rank = 7; rank >= 0; rank--) {
            boardString.append(getRankPrintable(rank));
            if (rank > 0) {
                boardString.append(System.lineSeparator());
            }
        }
        return boardString.toString();
    }

    @Override
    public String toString() {
        //String result = "";
        StringBuilder sb = new StringBuilder();
        for (int rank = 8; rank >= 1; rank--) {
            List<Piece> pawns = getRank(rank);
            if (pawns.isEmpty()) {
                sb.append("........");
            } else {
                for (Piece pawn : pawns) {
                    sb.append(pawn != null ? pawn.getPrintableRepresentation() : '.');
                }
            }
            if (rank > 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}