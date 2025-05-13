package chess;
import chess.pieces.Piece;
import util.StringUtil;
import java.util.*;

public class Board {
    private Piece[][] board;
    private int pieceCount;
    private List<Piece> pieces = new ArrayList<>(Collections.nCopies(64, null)); //Tabuleiro 8x8

    public Board() {
        board = new Piece[8][8]; //8 linhas e 8 colunas
        pieceCount = 0;
    }

    public void initialize() {
        Piece.resetCounts(); //Reseta contadores antes de inicializar
        board = new Piece[8][8]; //8 linhas e 8 colunas
        pieces = new ArrayList<>(Collections.nCopies(64, null)); //Tabuleiro 8x8

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

        //Sincronizar pieces com board
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int index = row * 8 + col;
                pieces.set(index, board[row][col]);
            }
        }

        pieceCount = 32;
    }

    public int pieceCount() {
        return pieceCount;
    }

    public int countPieces(Piece.Collor colors, char representation) {
        int count = 0;
        for (Piece piece : pieces) {
            if (piece != null && piece.getColors() == colors && piece.getRepresentation() == representation) {
                count++;
            }
        }
        return count;
        //Teste: Para o tabuleiro fornecido, countPieces(Piece.Color.BLACK, 'p') deve retornar 3 (peões pretos).
    }

    public Piece getPieceAt(String location) {
        int file = Character.toLowerCase((location.charAt(0))) - 'a'; //a=0, b=1, etc.
        int rank = 8 - (location.charAt(1) - '0'); // "8" -> 0, "1 -> 7, etc.
        int index = rank * 8 + file;
        return pieces.get(index);
        //Teste: Para a configuração inicial, getPieceAt("a8") retorna a torre preta,
        // e getPieceAt("e1") retorna o rei branco.
    }

    public void placePiece(Piece piece, String location) {
        int file = Character.toLowerCase((location.charAt(0))) - 'a'; //a=0, b=1, etc.
        int rank = 8 - (location.charAt(1) - '0'); // "8" -> 0, "1 -> 7, etc.
        int index = rank * 8 + file;
        pieces.set(index, piece);
        //Teste: Crie um tabuleiro vazio e verifique que não há peças inicialmente,
        // depois use placePiece para adicionar peças e teste com getPieceAt.
    }

    public double calculateStrength(Piece.Collor colors) {
        double strength = 0.0;
        Map<Integer, Integer> pawnsPerFile = new HashMap<>();

        for (Piece piece : pieces) {
            if (piece != null && piece.getColors() == colors) {
                switch (piece.getType()) {
                    case QUEEN: strength += 9; break;
                    case ROOK: strength += 5; break;
                    case BISHOP: strength += 3; break;
                    case KNIGHT: strength += 2.5; break;
                    case PAWN:
                        int file = pieces.indexOf(piece) % 8;
                        int count = pawnsPerFile.getOrDefault(file, 0);
                        strength += (count > 0) ? 0.5 : 1;
                        pawnsPerFile.put(file, count + 1);
                        break;
                }
            }
        }
        return strength;
        //Teste: Para o tabuleiro dado, calculateStrength(Piece.Color.BLACK) retorna 20,
        // e calculateStrength(Piece.Color.WHITE) retorna 19.5.
    }

    public String printAll() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece == null) {
                    sb.append('.');
                } else {
                    sb.append(piece.getRepresentation());
                }
            }
            sb = new StringBuilder(StringUtil.appendNewLine(sb.toString()));
        }
        return sb.toString();
    }
}