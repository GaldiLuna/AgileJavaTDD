package chess;
import org.junit.Test;

public class CharacterTest extends junit.framework.TestCase {

    @Test
    public void testWhiteSpace() {
        //Testa se os caracteres de espaço, nova linha e tabulação são espaços em branco.
        assertTrue(Character.isWhitespace('\n')); //Nova linha
        assertTrue(Character.isWhitespace('\t')); //Tabulação
        assertTrue(Character.isWhitespace(' ')); //Espaço

        //Testa que outros caracteres não são espaços em branco.
        assertFalse(Character.isWhitespace('a')); //Letra
        assertFalse(Character.isWhitespace('1')); //Número
        assertFalse(Character.isWhitespace('#')); //Símbolo

        //Exemplo de outro caractere que pode ser considerado espaço (espaço em branco unicode).
        assertTrue(Character.isWhitespace('\u2003')); // EM SPACE
        assertTrue(Character.isWhitespace('\r')); //Retorno de carro
    }

    @Test
    public void testJavaIdentifierStart() {
        //Caracteres que podem iniciar um identificador Java (letra, sublinhado, cifrão).
        assertTrue(Character.isJavaIdentifierStart('a'));
        assertTrue(Character.isJavaIdentifierStart('Z'));
        assertTrue(Character.isJavaIdentifierStart('_'));
        assertTrue(Character.isJavaIdentifierStart('$'));

        //Caracteres inválidos em identificadores Java (int, símbolo, espaço).
        assertFalse(Character.isJavaIdentifierStart('1'));
        assertFalse(Character.isJavaIdentifierPart('^'));
        assertFalse(Character.isJavaIdentifierPart(' '));
    }

    @Test
    public void testJavaIdentifierPart() {
        //Dígitos que podem fazer parte (letra, int, sublinhado, cifrão).
        assertTrue(Character.isJavaIdentifierPart('a'));
        assertTrue(Character.isJavaIdentifierPart('Z'));
        assertTrue(Character.isJavaIdentifierPart('0'));
        assertTrue(Character.isJavaIdentifierPart('_'));
        assertTrue(Character.isJavaIdentifierPart('$'));

        //Dígitos inválidos para fazer parte (símbolo, espaço).
        assertFalse(Character.isJavaIdentifierPart('~'));
        assertFalse(Character.isJavaIdentifierPart(' '));
    }
}
