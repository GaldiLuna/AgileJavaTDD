package chess;
import chess.pieces.Piece;
import util.StringUtil;

import java.util.*;

public class Board {
    private Piece[][] board;
    private int pieceCount;

    public Board() {
        board = new Piece[8][8];
        pieceCount = 0;
    }

    public void initialize() {
        Piece.resetCounts(); //Reseta contadores antes de inicializar

        //Linha 8 peças pretas:
        board[0][0] = Piece.createBlackRook();
        board[0][1] = Piece.createBlackKnight();
        board[0][2] = Piece.createBlackBishop();
        board[0][3] = Piece.createBlackQueen();
        board[0][4] = Piece.createBlackKing();
        board[0][5] = Piece.createBlackBishop();
        board[0][6] = Piece.createBlackKnight();
        board[0][7] = Piece.createBlackRook();
        //Linha 7 peões pretos:
        for (int i = 0; i < 8; i++) {
            board[1][i] = Piece.createBlackPawn();
        }
        //Linhas 6, 5, 4 e 3 vazias:
        for (int row = 2; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = null;
            }
        }
        //Linha 2 peões brancos:
        for (int j = 0; j < 8; j++) {
            board[6][j] = Piece.createWhitePawn();
        }
        //Linha 1 peças brancas:
        board[7][0] = Piece.createWhiteRook();
        board[7][1] = Piece.createWhiteKnight();
        board[7][2] = Piece.createWhiteBishop();
        board[7][3] = Piece.createWhiteQueen();
        board[7][4] = Piece.createWhiteKing();
        board[7][5] = Piece.createWhiteBishop();
        board[7][6] = Piece.createWhiteKnight();
        board[7][7] = Piece.createWhiteRook();

        pieceCount = 32;
    }

    public int pieceCount() {
        return pieceCount;
    }

    public String printAll() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece == null) {
                    sb.append('.');
                } else {
                    sb.append(piece.getSymbol().name());
                }
            }
            sb = new StringBuilder(StringUtil.appendNewLine(sb.toString()));
        }
        return sb.toString();
    }
}