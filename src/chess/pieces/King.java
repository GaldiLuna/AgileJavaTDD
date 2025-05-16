package chess.pieces;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(Collor colors) {
        super(colors, Type.KING);
    }

    @Override
    public List<String> getPossibleMoves(String position) {
        List<String> moves = new ArrayList<>();
        int file = position.charAt(0) - 'a';
        int rank = 8 - (position.charAt(1) - '0');

        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1}, {1, 0}, {1, 1}
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
