package chess;
import chess.pieces.Piece;
import chess.pieces.NoPiece;
import util.StringUtil;
import java.util.*;

public class Board implements Iterable<Piece> {
    private Piece[][] board = new Piece[8][8];
    //private Map<String, Piece> board = new HashMap<>();
    private int pieceCount = 0;

    // Construtor: Inicializa o tabuleiro com NoPiece em vez de null
    public Board() {
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                board[rank][file] = Piece.createNoPiece(); //Preenche com NoPiece
            }
        }
    }

    public Piece[][] getSquares() {
        return board;
    }

    public Piece getPiece(int file, int rank) {
        return board[rank][file];
    }

    public void putPiece(Piece piece, int file, int rank) {
        // Ajusta a contagem de peças com base na substituição de NO_PIECE por peça real, ou vice-versa
        if (board[rank][file].isNoPiece() && piece != null && !piece.isNoPiece()) {
            pieceCount++;
        } else if (!board[rank][file].isNoPiece() && (piece == null || piece.isNoPiece())) {
            pieceCount--;
        }
        // Se a peça passada for null, substitua por NoPiece
        board[rank][file] = (piece == null) ? Piece.createNoPiece() : piece;
    }

    public int pieceCount() {
        return pieceCount;
    }

    public int countPieces(Piece.Collor colors, char representation) {
        int count = 0;
        for (Piece piece : this) { //Usando o iterador
            if (piece.getColors() == colors && piece.getRepresentation() == representation) {
                count++;
            }
        }
        return count;
    }

    public Piece getPieceAt(String location) {
        int file = Character.toLowerCase(location.charAt(0)) - 'a'; //a=0, b=1, etc.
        int rank = 8 - (location.charAt(1) - '0'); // "8" -> 0, "1 -> 7, etc.
        return getPiece(file, rank);
        //Teste: Para a configuração inicial, getPieceAt("a8") retorna a torre preta,
        // e getPieceAt("e1") retorna o rei branco.
    }

    public void placePiece(Piece piece, String location) {
        int file = Character.toLowerCase(location.charAt(0)) - 'a'; //a=0, b=1, etc.
        int rank = 8 - (location.charAt(1) - '0'); // "8" -> 0, "1 -> 7, etc.
        if (piece != null && !piece.isNoPiece()) { // Só atualiza a posição se for uma peça real
            piece.setPosition(location); //Atualização de posição da linha acima
        }
        putPiece(piece, file, rank);
        //Teste: Crie um tabuleiro vazio e verifique que não há peças inicialmente,
        // depois use placePiece para adicionar peças e teste com getPieceAt.
    }

    public String printAll() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                // Agora, não precisamos verificar null, apenas chamamos getRepresentation()
                sb.append(piece.getRepresentation());
            }
            sb = new StringBuilder(StringUtil.appendNewLine(sb.toString()));
        }
        return sb.toString();
    }

    public Iterator<Piece> iterator() {
        List<Piece> pieces = new ArrayList<>();
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                if (board[rank][file].isNoPiece()) { // Adiciona apenas peças que não são NoPiece
                    pieces.add(board[rank][file]);
                }
            }
        }
        return pieces.iterator();
    }
}