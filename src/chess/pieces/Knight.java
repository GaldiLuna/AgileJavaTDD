package chess.pieces;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Collor colors) {
        super(colors, Type.KNIGHT);
    }

    @Override
    public List<String> getPossibleMoves(String position) {
        List<String> moves = new ArrayList<>();
        int file = position.charAt(0) - 'a';
        int rank = 8 - (position.charAt(1) - '0');

        //Movimentos em L: 8 possibilidades de combinações
        int[][] directions = {
                {-2, -1}, {-2, 1}, //2 para cima e 1 para direita/esquerda
                {2, -1}, {2, 1}, //2 para baixo e 1 para direita/esquerda
                {-1, -2}, {1, -2}, //1 para cima e 2 para direita/esquerda
                {-1, 2}, {1, 2} //1 para baixo e 2 para direita/esquerda
        };

        for (int[] dir : directions) {
            int newFile = file + dir[0];
            int newRank = rank + dir[1];
            if (newFile >= 0 && newFile < 8 && newRank >= 0 && newRank < 8) {
                char newFileChar = (char) ('a' + newFile);
                char newRankChar = (char) ('0' + (8 - newRank));
                moves.add("" + newFileChar + newRankChar);
            }
        }
        return moves;
    }
}
