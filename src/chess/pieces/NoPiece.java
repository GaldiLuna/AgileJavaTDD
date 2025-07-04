package chess.pieces;

import java.util.Collections;
import java.util.List;

import java.io.Serializable;

public class NoPiece extends Piece {

    public NoPiece() {
        super(); //Chama o construtor sem argumentos em Piece
    }

    @Override
    public List<String> getPossibleMoves(String position) {
        //Uma peça "vazia" não tem movimentos possíveis
        return Collections.emptyList();
    }

    // Sobrescreve getRepresentation para garantir que sempre retorne '.' para NoPiece
    @Override
    public char getRepresentation() {
        return Type.NO_PIECE.getRepresentation();
    }

    // Sobrescreve isNoPiece para ser mais explícito
    @Override
    public boolean isNoPiece() {
        return true;
    }
}
