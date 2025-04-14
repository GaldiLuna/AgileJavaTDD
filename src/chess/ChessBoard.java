package chess;
import chess.pieces.Pawn;
import java.util.*;

public class ChessBoard {
    static final int SQUARES_PER_SIDE = 8;
    public int numberOfSquares = ChessBoard.SQUARES_PER_SIDE * ChessBoard.SQUARES_PER_SIDE;

    //Representa as 8 fileiras do tabuleiro.
    private ArrayList<ArrayList<Pawn>> ranks;
    private ArrayList<Pawn> secondRank = new ArrayList<>();
    private ArrayList<Pawn> seventhRank = new ArrayList<>();
    private List<Pawn> pieces = new ArrayList<>();

    //public void AddPawn(Pawn pawn) {pieces.add(pawn);}

    public List<Pawn> getPawns() {
        return pieces;
    }
    public List<Pawn> getPieces() {
        return pieces;
    }

    public List<Pawn> getRank(int rank) {
        if (rank == 2) {
            return secondRank;
        } else if (rank == 7) {
            return seventhRank;
        }
        return new ArrayList<>();
    }

    public int getPieceCount() {
        return pieces.size();
    }

    public void addPawn(Pawn pawn) {
        pieces.add(pawn);
        if (!(pawn instanceof Pawn)) {
            throw new IllegalArgumentException("Apenas peões podem ser adicionados ao tabuleiro.");
        }
        pieces.add(pawn);
    }

    public ChessBoard() {
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(Pawn.WHITE, 'p'));
            pieces.add(new Pawn(Pawn.BLACK, 'P'));
        }
    }

    public String Board() {
        //Inicializa as fileiras, cada fileira é uma lista de Pawn.
        ranks = new ArrayList<>();
        //Cria 8 fileiras para o tabuleiro
        for (int i = 0; i < 8; i++) {
            //Inicialmente, cada fileira está vazia (pode ser representada com nulls ou uma lista vaia).
            ArrayList<Pawn> row = new ArrayList<>();
            //Supondo que o tabuleiro seja de 8 colunas, preenche com nulls para representar quadrados vazios.
            for (int j = 0; j < 8; j++) {
                row.add(null);
            }
            ranks.add(row);
        }
        //Inicializa os peões.
        initialize();

        return "";
    }

    public void initialize() {
        pieces.clear();
        secondRank.clear();
        seventhRank.clear();

        //Adiciona peões brancos na segunda fileira
        for (int i = 0; i < 8; i++) {
            Pawn whitePawn = new Pawn(Pawn.WHITE, 'p');
            secondRank.add(whitePawn);
            pieces.add(whitePawn);
        }

        //Adiciona peões pretos na sétima fileira
        for (int i = 0; i < 8; i++) {
            Pawn blackPawn = new Pawn(Pawn.BLACK, 'P');
            seventhRank.add(blackPawn);
            pieces.add(blackPawn);
        }

        //Cria a fileira de peões brancos (na segunda fileira).
        ArrayList<Pawn> whitePawns = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            whitePawns.add(new Pawn(true, 'p'));
        }
        ranks.set(1, whitePawns);

        //Cria a fileira de peões pretos (na penúltima fileira).
        ArrayList<Pawn> blackPawns = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            blackPawns.add(new Pawn(false, 'P'));
        }
        ranks.set(6, blackPawns);

    }

    //Metodo auxiliar para obter a representação de uma fileira
    public String getRankPrintable(int rankIndex) {
        StringBuilder sb = new StringBuilder();
        for (Pawn p : ranks.get(rankIndex)) {
            if (p != null) {
                sb.append('.');
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
        String result = "";
        //StringBuilder sb = new StringBuilder();
        for (int rank = 8; rank >= 1; rank--) {
            List<Pawn> pawns = getRank(rank);
            if (pawns.isEmpty()) {
                sb.append("........");
            } else {
                for (Pawn pawn : pawns) {
                    rankStr += pawn.getPrintableRepresentation()
                    //sb.append(pawn.getPrintableRepresentation());
                }
                result += rankStr;
            }
            if (rank > 1) {
                result += System.lineSeparator();
                //sb.append(System.lineSeparator());
            }
        }
        return result;
        //return sb.toString();
    }

}