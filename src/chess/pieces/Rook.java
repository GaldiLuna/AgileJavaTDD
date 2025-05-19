package chess.pieces;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(Collor colors) {
        super(colors, Type.ROOK);
    }

    @Override
    public List<String> getPossibleMoves(String position) {
        List<String> moves = new ArrayList<>();
        int file = position.charAt(0) - 'a';
        int rank = 8 - (position.charAt(1) - '0');

        //Direções: cima, baixo, esquerda, direita
        int[][] directions = {
                {0, 1}, //Cima
                {0, -1}, //Baixo
                {1, 0}, //Direita
                {-1, 0} //Esquerda
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
