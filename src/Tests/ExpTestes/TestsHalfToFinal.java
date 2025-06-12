package Tests.ExpTestes;

import junit.framework.TestCase;

import java.math.BigDecimal;

public class TestsHalfToFinal extends TestCase {

    public void testBigDecimalImmutability() {
        BigDecimal numeroOriginal = new BigDecimal("10.50");
        BigDecimal numeroParaAdicionar = new BigDecimal("2.00");

        //Adiciona o segundo BD ao primeiro BD
        BigDecimal resultadoAdicao = numeroOriginal.add(numeroParaAdicionar);

        //Verifica que o número original não foi alterado
        assertEquals("O número original deve permanecer 10.50", new BigDecimal("10.50"), numeroOriginal);

        //Verifica o resultado da adição
        assertEquals("O resultado da adição deve ser 12.50", new BigDecimal("12.50"), resultadoAdicao);
    }

    public void testBigDecimalEqualityAndTransformation() {
        BigDecimal dezPontoZeroZero = new BigDecimal("10.00");
        BigDecimal um = new BigDecimal("1");

        //Mostra que não são iguais (por conta da escala)
        assertFalse("10.00 e 1 não devem ser iguais com equals()", dezPontoZeroZero.equals(um));
        //Nota: se usarmos compareTo(), eles seriam iguais, pois compareTo ignora a escala para valor numérico.
        assertEquals(1, dezPontoZeroZero.compareTo(um));

        //Transforma 'um' para que seja igual a 'dezPontoZeroZero'
        BigDecimal umTransformado = um.setScale(2); // Define a escala para 2 casas decimais (1.00)
        umTransformado = umTransformado.multiply(new BigDecimal("10")); // Multiplica por 10 (10.00)

        assertEquals("O 'um' transformado deve ser igual a 'dezPontoZeroZero'", dezPontoZeroZero, umTransformado);

        //Inverte a transformação
        BigDecimal dezPontoZeroZeroTransformado = dezPontoZeroZero.divide(new BigDecimal("10")); // Divide por 10 (1.00)
        dezPontoZeroZeroTransformado = dezPontoZeroZeroTransformado.setScale(0); // Define a escala para 0 casas decimais (1)

        assertEquals("O 'dezPontoZeroZero' transformado deve ser igual a 'um'", um, dezPontoZeroZeroTransformado);
    }

    //Continuar da questão 3 em diante...

}
