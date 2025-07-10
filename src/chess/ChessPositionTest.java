package chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Classe principal para resolução do exercício de posições
public class ChessPositionTest {

    public static void main(String[] args) {
        List<ChessPosition> positions = new ArrayList<>();
        positions.add(new ChessPosition("h8"));
        positions.add(new ChessPosition("a1"));
        positions.add(new ChessPosition("d5"));
        positions.add(new ChessPosition("c1"));
        positions.add(new ChessPosition("b3"));
        positions.add(new ChessPosition("a2"));

        System.out.println("Posições antes da ordenação: " + positions);

        // --- Uso da Classe InternaAnônima para Comparator ---
        Collections.sort(positions, new Comparator<ChessPosition>() {
            @Override
            public int compare(ChessPosition p1, ChessPosition p2) {
                //Prioridade 1: Ordenar pela coluna (file) de 'a' - 'h'
                int fileComparison = Character.compare(p1.getFile(), p2.getFile());
                if (fileComparison != 0) {
                    return fileComparison;
                }
                //Prioridade 2: Se as colunas forem iguais,ordenar pela linha (rank) de 1 a 8
                return Integer.compare(p1.getRank(), p2.getRank());
            }
        });
        // --- Fim da Classe Interna Anônima ---

        System.out.println("Posições depois da ordenação: " + positions);
    }
}
