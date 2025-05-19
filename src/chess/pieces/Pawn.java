package chess.pieces;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Collor colors) {
        super(colors, Type.PAWN);
    }

    // POR ENQUANTO IREMOS FAZER APENAS A MOVIMENTAÇÃO PARA FRENTE E
    // SERÃO IGNORADAS AS CAPTURAS DIAGONAIS FEITAS PELOS PEÕES.

    @Override
    public List<String> getPossibleMoves(String position) {
        List<String> moves = new ArrayList<>();
        int file = position.charAt(0) - 'a';
        int rank = 8 - (position.charAt(1) - '0');

        //Determina a direção do movimento com base na cor
        int direction = isWhite() ? -1 : 1; //Brancas sobem e pretas descem
        int startRank = isWhite() ? 6 : 1; //Posição inicial dos peões

        //Movimento de uma casa para frente
        int newRank = rank + direction;
        if (newRank >= 0 && newRank < 8) {
            char newFileChar = (char) ('a' + file);
            char newRankChar = (char) ('0' + (8 - newRank));
            moves.add("" + newFileChar + newRankChar);
        }

        //Movimento de duas casas a partir da posição inicial
        if (rank == startRank) {
            newRank = rank + 2 * direction;
            if (newRank >= 0 && newRank < 8) {
                char newFileChar = (char) ('a' + file);
                char newRankChar = (char) ('0' + (8 - newRank));
                moves.add("" + newFileChar + newRankChar);
            }
        }
        return moves;
    }
}
