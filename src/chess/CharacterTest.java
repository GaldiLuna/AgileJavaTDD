package chess;
import static org.junit.Assert.*;
import org.junit.Test;
import junit.framework.*;

public class CharacterTest extends junit.framework.TestCase {

    public void testWhiteSpace() {
        //Testa se os caracteres de espaço, nova linha e tabulação são espaços em branco.
        assertTrue(Character.isWhitespace('\n'));
        assertTrue(Character.isWhitespace('\t'));
        assertTrue(Character.isWhitespace(' '));

        //Testa que outros caracteres não são espaços em branco.
        assertFalse(Character.isWhitespace('a'));
        assertFalse(Character.isWhitespace('1'));

        //Exemplo de outro caractere que pode ser considerado espaço (espaço em branco unicode).
        assertTrue(Character.isWhitespace('\u2003')); // EM SPACE
    }

    @Test
    public void testIdentifierCharacters() {
        //Caracteres que podem iniciar um identificador Java.
        assertTrue(Character.isJavaIdentifierStart('a'));
        assertTrue(Character.isJavaIdentifierStart('_'));

        //Dígitos que não podem iniciar mas podem fazer parte.
        assertFalse(Character.isJavaIdentifierStart('1'));
        assertTrue(Character.isJavaIdentifierPart('1'));

        //Caracteres inválidos em identificadores.
        assertFalse(Character.isJavaIdentifierPart('^'));
    }
}
