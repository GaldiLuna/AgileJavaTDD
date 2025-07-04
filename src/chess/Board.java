package chess;

import chess.pieces.Piece;
import chess.pieces.NoPiece;
import util.StringUtil;
import java.util.*;

import java.io.*;

import java.io.Serializable;

public class Board implements Iterable<Piece>, Serializable {
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

    // -*-*-*- OPÇÃO PARA SALVAR COMO ARQUIVO SERIALIZADO

    /**
     * Salva o estado atual do tabuleiro em um arquivo usando serialização de objeto.
     * @param //filename O nome do arquivo para salvar.
     * @throws //IOException Se ocorrer um erro de I/O.
     */

    public void saveAsObject(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        }
    }

    /**
     * Carrega um tabuleiro de um arquivo previamente salvo com saveAsObject.
     * @param //filename O nome do arquivo para carregar.
     * @return Uma nova instância de Board com o estado carregado.
     * @throws //IOException Se ocorrer um erro de I/O.
     * @throws //ClassNotFoundException Se a classe Board não for encontrada.
     */

    public static Board loadFromObject(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Board) in.readObject();
        }
    }

    // -*-*-*- OPÇÃO PARA SALVAR COMO TEXTO COMUM

    /**
     * Salva a representação textual do tabuleiro em um arquivo.
     * @param //filename O nome do arquivo para salvar.
     * @throws //IOException Se ocorrer um erro de I/O.
     */

    public void saveAsText(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(this.printAll());
        }
    }

    /**
     * Carrega um tabuleiro a partir de sua representação textual.
     * @param //filename O nome do arquivo para carregar.
     * @return Uma nova instância de Board com o estado carregado.
     * @throws //IOException Se ocorrer um erro de I/O.
     */

    public static Board loadFromText(String filename) throws IOException {
        Board board = new Board(); // Começa com um tabuleiro vazio
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (int rank = 0; rank < 8; rank++) {
                String line = reader.readLine();
                if (line == null || line.length() < 8) {
                    throw new IOException("Formato de arquivo inválido: linha " + (rank + 1));
                }
                for (int file = 0; file < 8; file++) {
                    char representation = line.charAt(file);
                    Piece piece = Piece.createFromRepresentation(representation);
                    board.putPiece(piece, file, rank);
                }
            }
        }
        return board;
    }

}