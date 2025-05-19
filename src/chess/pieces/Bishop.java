package chess.pieces;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Collor colors) {
        super(colors, Type.BISHOP);
    }

    @Override
    public List<String> getPossibleMoves(String position) {
        List<String> moves = new ArrayList<>();
        int file = position.charAt(0) - 'a';
        int rank = 8 - (position.charAt(1) - '0');

        //Direções: diagonais
        int[][] directions = {
                {1, 1}, //diagonal inferior direita (sudeste)
                {1, -1}, //diagonal superior direita (nordeste)
                {-1, 1}, //diagonal inferior esquerda (sudoeste)
                {-1, -1} //diagonal superior esquerda (noroeste)
        };

        for (int[] dir : directions) {
            int newFile = file + dir[0];
            int newRank = rank + dir[1];
            while (newFile >= 0 && newFile < 8 && newRank >= 0 && newRank < 8) {
                char newFileChar = (char) ('a' + newFile);
                char newRankChar = (char) ('0' + (8 - newRank));
                moves.add("" + newFileChar + newRankChar);
                newFile += dir[0];
                newRank += dir[1];
            }
        }
        return moves;
    }
}
