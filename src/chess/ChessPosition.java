package chess;
import chess.pieces.*;

import java.util.*;

//Classe ChessPosition para representar uma posição no tabuleiro
//Esta classe é essencial para que possamos ter objetos "posição" para ordenar.
public class ChessPosition {
    private char file; //Colunas, ex: 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'.
    private int rank; //Linhas, ex: 1, 2, 3, 4, 5, 6, 7, 8.

    public ChessPosition(String position) {
        if (position == null || position.length() != 2) {
            throw new IllegalArgumentException("Formato de posição inválido: " + position);
        }
        this.file = Character.toLowerCase(position.charAt(0));
        this.rank = Character.parseInt(String.valueOf(position.charAt(1)));

        if (this.file < 'a' || this.file > 'h' || this.rank < 1 || this.rank > 8) {
            throw new IllegalArgumentException("Posição fora dos limites do tabuleiro: " + position);
        }
    }

    public char getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    //Metodo auxiliar par converter rank de 1-8 para índice de array 7-0 (se necessário)
    public int getRankIndex() {
        return 8 - rank;
    }

    //Metodo auxiliar par converter file de 'a'-'h' para índice de array 0-7 (se necessário)
    public int getFileIndex() {
        return file - 'a';
    }

    @Override
    public String toString() {
        return "" + file + rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPosition that = (ChessPosition) o;
        return file == that.file && rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
