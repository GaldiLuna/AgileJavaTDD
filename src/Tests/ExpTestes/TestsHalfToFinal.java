package Tests.ExpTestes;

import junit.framework.TestCase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestsHalfToFinal extends TestCase {

    /**
     *
     * Desafio: Você consegue encontrar maneiras pelas quais os wrappers, como Float,
     * agem de forma diferente do tipo primitivo correspondente?
     *
     * Tradução: Este é um desafio para identificar diferenças de comportamento entre os tipos
     * primitivos (float, int, etc.) e suas classes wrapper (Float, Integer, etc.), além da capacidade
     * de serem nulos e serem objetos.
     *
     * Exemplos de Código (algumas diferenças):
     *
     * Valor null: Um Float pode ser null, um float primitivo não. Isso é crucial em estruturas de dados
     * ou onde valores opcionais são necessários.
     *
     * Diferença 1: nullability
     * Float wrapperFloat = null; // Válido
     * float primitiveFloat = null; // ERRO DE COMPILAÇÃO
     * Comparação de objetos (== vs equals()): == para wrappers compara referências de objetos (endereço de memória),
     * enquanto para primitivos compara valores. equals() para wrappers compara valores.
     *
     * Diferença 2: Comparação de objetos
     * Float f1 = 1.0f;
     * Float f2 = 1.0f;
     * Float f3 = new Float(1.0f); // Evitar usar new Float() em código moderno
     *
     * assertFalse("Float f1 e f2 podem não ser iguais com == (otimização de cache)", f1 == f2);
     * Para valores pequenos, o Java pode otimizar e usar o cache, fazendo `f1 == f2` retornar true.
     * Para 1.0f, é provável que seja true em muitas JVMs, mas NÃO é garantido.
     * f1 == f3 será sempre false, pois f3 é uma nova instância.
     * assertFalse("Float f1 e f3 devem ser diferentes com ==", f1 == f3);
     *
     * assertTrue("Float f1 e f2 devem ser iguais com equals()", f1.equals(f2));
     * assertTrue("Float f1 e f3 devem ser iguais com equals()", f1.equals(f3));
     *
     * float p1 = 1.0f;
     * float p2 = 1.0f;
     * assertTrue("Floats primitivos p1 e p2 devem ser iguais com ==", p1 == p2);
     * Serialização: Wrappers são Serializable, primitivos não. Isso é importante para
     * persistência e comunicação em rede.
     *
     * Métodos utilitários: As classes wrapper fornecem métodos utilitários estáticos (e de instância) que não
     * existem nos primitivos. Ex: Float.parseFloat(), Float.isNaN(), Float.isInfinite(), Float.toString(), etc.
     *
     * Diferença 3: Métodos utilitários
     * String strValue = "1.23f";
     * float parsedFloat = Float.parseFloat(strValue); // Metodo da classe wrapper
     * assertEquals(1.23f, parsedFloat, 0.00001f);
     *
     * assertTrue(Float.isNaN(0.0f / 0.0f)); // Metodo da classe wrapper
     * Autoboxing/Unboxing: A conversão automática entre primitivo e wrapper (e vice-versa).
     * Embora pareça que os wrappers se comportam como primitivos, essa é uma funcionalidade do compilador.
     *
     * Diferença 4: Autoboxing/Unboxing (não é uma diferença no comportamento do tipo em si, mas na interação)
     * Float autoboxedFloat = 10.5f; // float para Float
     * float unboxedFloat = autoboxedFloat; // Float para float
     * Performance: Primitivos geralmente oferecem melhor performance porque não há sobrecarga
     * de criação de objetos ou operações de autoboxing/unboxing.
     *
     */

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

    public void testPrecisaoFlutuante() {
        float val1 = 0.9f;
        float val2 = 0.005f * 2.0f; //Isso resulta em 0.01f

        // A questão pede 0.9 e 0.005 * 2.0 (que é 0.01).
        // Há uma possível ambiguidade na pergunta aqui.
        // Se a intenção era 0.9 vs 0.9 (mas gerado por operações), então:
        float val3 = 0.3f * 3.0f; //Isso deve dar 0.9f, mas com a imprecisão dos pontos flutuantes

        //Para mostrar que 0.9f e 0.005f * 2.0f não são iguais:
        assertFalse("0.9f e 0.01 não devem ser iguais", val1 == val2);

        // Para comparar com precisão (com uma tolerância/delta)
        // A precisão em que são iguais depende do delta que você aceita.
        // Para 0.9f e 0.3f * 3.0f, a diferença é muito pequena.
        float difference = Math.abs(val3 - val1);
        System.out.println("Diferença entre 0.9f e 0.3f * 3.0f: " + difference + " (float)"); //É um número muito pequeno

        //Para doubles:
        double val4 = 0.9;
        double val5 = 0.005 * 2.0; //Isso resulta em 0.01
        double val6 = 0.3 * 3.0; //Isso deve dar 0.9, mas com a imprecisão dos pontos flutuantes

        assertFalse("0.9 e 0.01 não devem ser iguais com doubles", val4 == val5);
        assertFalse("0.9 e 0.3 * 3.0 podem não ser exatamente iguais com doubles", val4 == val6);

        // A precisão para doubles é maior que para floats.
        double differenceDouble = Math.abs(val4 - val6);
        System.out.println("Diferença entre 0.9 e 0.3 * 3.0 (double): " + differenceDouble); //É um número ainda menor

        //Ao comparar floats/doubles em testes,use um delta (tolerância)
        assertEquals("0.9f e 0.3f * 3.0f devem ser iguais dentro de uma pequena tolerância", val1, val3, 0.000001f);
        assertEquals("0.9 e 0.9 * 3.0 devem ser iguais dentro de uma pequena tolerância para doubles", val4, val6, 0.000000000000001);
    }

    static class CompileError { //FAZER O COMPLEMENTO DAS RESPOSTAS DE Y, W e Z
        //float x = 0.01;
        //float y = 1;
        //float w = 1.0;
        //float z = (int)1.0;
        //Fazer formas de corrigir as declarações das variáveis acima para que possam compilar.

        //Dessas formas abaixo a expressão da questão irá compilar sem erro.
        static float x1 = 0.01f;
        static float x2 = (float) 0.01;
       //x1 - Cast Implícito e x2 - Cast Explícito

        public static void main(String[] args) {
            //Explicação da resolução de CompileError
            System.out.println("O formato original da expressão estava sendo declarada como float, porém " +
                    "\nestava ficando armazenado na variável o tipo double e por isso estava acontecendo " +
                    "\no erro de compilação, gerando uma notificação de perda de precisão.\n");
            System.out.println("A primeira forma de corrigir a expressão inicial foi fazendo um cast implícito " +
                    "\nna variável inicial 'float x = 0.01' deixando ela da seguinte forma: 'float x1 = 0.01f'.\n");
            System.out.println("A segunda forma de corrigir a expressão inicial foi fazendo um cast explícito " +
                    "\nna variável inicial 'float x = 0.01' deixando ela da seguinte forma: 'float x2 = (float) 0.01'.");
            assertEquals(x1, x2);
        }
    }

    static class HexToDecimalSpike {
        public static void main(String[] args) {

            int hexValue = 0xDEAD; //O prefixo 0x indica que é um literal hexadecimal
            System.out.println("O valor decimal do hexadecimal 0xDEAD é: " + hexValue);
            //Saída: texto + 57005

            int octalValue = 0157255; //O prefixo 0 indica que é um literal octal
            System.out.println("O valor decimal do literal octal 0157255 é: " + octalValue);
            //Saída: texto + 57005
        }
    }

    public void testNanAndInfinityCalculations() {
        //Infinity
        double positiveInfinity = Double.POSITIVE_INFINITY;
        double negativeInfinity = Double.NEGATIVE_INFINITY;
        double zero = 0.0;
        double positiveNumber = 10.0;
        double negativeNumber = -5.0;

        //Cálculos Infinity
        assertEquals(positiveInfinity, positiveNumber / zero); //Divisão por zero resulta num infinito positivo
        assertEquals(negativeInfinity, negativeNumber / zero); //Divisão por zero resulta num infinito negativo

        assertEquals(positiveInfinity, positiveInfinity + positiveInfinity);
        assertEquals(negativeInfinity, negativeInfinity + negativeInfinity);
        assertEquals(positiveInfinity, positiveInfinity - negativeInfinity);
        assertEquals(negativeInfinity, negativeInfinity - positiveInfinity);
        assertEquals(positiveInfinity, positiveInfinity * positiveNumber);
        assertEquals(negativeInfinity, positiveInfinity * negativeNumber);

        //Operações que resultam em NaN
        assertTrue(Double.isNaN(positiveInfinity - positiveInfinity)); //Infinito menos infinito é NaN
        assertTrue(Double.isNaN(negativeInfinity - negativeInfinity)); //Infinito negativo menos infinito negativo é NaN
        assertTrue(Double.isNaN(positiveInfinity / positiveInfinity)); //Infinito dividido por infinito é NaN
        assertTrue(Double.isNaN(zero / zero)); //Zero dividido por zero é NaN
        assertTrue(Double.isNaN(positiveInfinity * zero)); //Infinito vezes zero é NaN

        //NaN tem propriedades especiais
        assertTrue(Double.isNaN(Double.NaN + positiveNumber)); //Qualquer operação com NaN resulta em NaN
        assertTrue(Double.isNaN(Double.NaN * zero));
        assertTrue(Double.isNaN(Double.NaN / positiveNumber));
        //assertTrue(Double.isNaN(Double.NaN == Double.NaN)); //NaN nunca é igual a si mesmo (nem mesmo a outro NaN)
        assertFalse(Double.NaN == Double.NaN); //True se espera False e False se espera True

        //isNaN() para verificar se é NaN
        assertTrue(Double.isNaN(0.0 / 0.0));
        assertTrue(Float.isNaN(0.0f / 0.0f));
    }

    public static class DivisibilityTest extends TestCase {

        //Implementação 1: Usando o operador de módulo (%)
        public List<Integer> filterDivisibleBy3WithModulus(int... numbers) {
            List<Integer> result = new ArrayList<>();
            for (int num : numbers) {
                if (num % 3 == 0) {
                    result.add(num);
                }
            }
            return  result;
        }

        //Implementação 2: Usando operadores de divisão (/) e multiplicação (*)
        public List<Integer> filterDivisibleBy3WithDivision(int... numbers) {
            List<Integer> result = new ArrayList<>();
            for (int num : numbers) {
                //Um número é divisível por 3 se (num / 3) * 3 é igual a num
                //Isso funciona porque a divisão inteira trunca o resultado.
                if ((num / 3) * 3 == num) {
                    result.add(num);
                }
            }
            return result;
        }

        //TESTES

        public void testFilterDivisibleBy3WithModulus() {
            //Testando a sequeência de 1 a 10
            List<Integer> expected = Arrays.asList(3, 6, 9);
            List<Integer> actual = filterDivisibleBy3WithModulus(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            assertEquals(expected, actual);

            //Testes adicionais
            assertEquals(Arrays.asList(0, 30, -3), filterDivisibleBy3WithModulus(0, 1, 2, 30, -3, 5));
            assertTrue(filterDivisibleBy3WithModulus().isEmpty()); //Lista vazia
        }

        public void testFilterDivisibleBy3WithDivision() {
            //Testando a sequência de 1 a 10
            List<Integer> expected = Arrays.asList(3, 6, 9);
            List<Integer> actual = filterDivisibleBy3WithDivision(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            assertEquals(expected, actual);

            //Testes adicionais
            assertEquals(Arrays.asList(0, 30, -3), filterDivisibleBy3WithDivision(0, 1, 2, 30, -3, 5));
            assertTrue(filterDivisibleBy3WithDivision().isEmpty());  //Lista vazia
        }
    }

    /**
     *
     * Qual é o valor de (int)1.9?
     * Tradução: Este exercício testa o comportamento do casting de um tipo de ponto flutuante para um tipo inteiro.
     *
     * Resposta: O valor de (int)1.9 é 1.
     * O casting de um double (ou float) para um int trunca a parte decimal,
     * ou seja, remove tudo após o ponto decimal. Ele não arredonda.
     *
     * Qual é o valor de Math.rint(1.9)?
     * Tradução: Este exercício testa o comportamento do metodo Math.rint(),
     * que arredonda um número de ponto flutuante para o inteiro mais próximo.
     *
     * Resposta: O valor de Math.rint(1.9) é 2.0.
     * Math.rint() retorna um double que é o valor inteiro mais próximo do argumento.
     *
     * Ao usar Math.rint, como o arredondamento é executado? 1.5 é igual a 1 ou 0? E 2.5?
     * Tradução: Este exercício aprofunda o entendimento do Math.rint() e suas regras de
     * arredondamento, especialmente para números que terminam em .5.
     *
     * Resposta:
     * Math.rint() arredonda para o inteiro mais próximo. Se o número estiver igualmente distante
     * de dois inteiros (ou seja, termina em .5), ele arredonda para o inteiro par mais próximo.
     * Esta é uma regra conhecida como "arredondamento do meio para o par" ou "arredondamento de banqueiro".
     *
     * 1.5 é igualmente distante de 1 e 2. O inteiro par mais próximo é 2.0.
     * Math.rint(1.5) resulta em 2.0.
     * 2.5 é igualmente distante de 2 e 3. O inteiro par mais próximo é 2.0.
     * Math.rint(2.5) resulta em 2.0.
     * 3.5 é igualmente distante de 3 e 4. O inteiro par mais próximo é 4.0.
     * Math.rint(3.5) resulta em 4.0.
     *
     * Quais são os valores finais das seguintes expressões, assumindo que x é 5 e y é 10 e x e y
     * são ambos ints? Marque as linhas que não compilariam também. Quais são os valores de x e y depois?
     * (Considere cada expressão como discreta, ou seja, cada expressão começa com os valores de x e y em 5 e 10.)
     * Tradução: Este exercício testa a compreensão de operadores de precedência,
     * operadores de incremento/decremento (++), e curto-circuito em operadores lógicos.
     *
     * Análise:
     * x * 5 + y++ * 7 / 4
     *
     * x = 5, y = 10
     * 5 * 5 = 25
     * y++ usa o valor original de y (10), depois y se torna 11.
     * 10 * 7 = 70
     * 70 / 4 = 17 (divisão inteira)
     * 25 + 17 = 42
     * Resultado da expressão: 42
     * Valores de x e y depois: x = 5, y = 11
     * Compila: Sim
     * ++x * 5 * y++
     *
     * x = 5, y = 10
     * ++x faz x = 6, depois usa 6.
     * 6 * 5 = 30
     * y++ usa o valor original de y (10), depois y se torna 11.
     * 30 * 10 = 300
     * Resultado da expressão: 300
     * Valores de x e y depois: x = 6, y = 11
     * Compila: Sim
     * x++ * 5 * ++y
     *
     * x = 5, y = 10
     * x++ usa o valor original de x (5), depois x se torna 6.
     * 5 * 5 = 25
     * ++y faz y = 11, depois usa 11.
     * 25 * 11 = 275
     * Resultado da expressão: 275
     * Valores de x e y depois: x = 6, y = 11
     * Compila: Sim
     * ++x + 5 * 7 + y++
     *
     * x = 5, y = 10
     * ++x faz x = 6, depois usa 6.
     * 5 * 7 = 35
     * y++ usa o valor original de y (10), depois y se torna 11.
     * 6 + 35 + 10 = 51
     * Resultado da expressão: 51
     * Valores de x e y depois: x = 6, y = 11
     * Compila: Sim
     * ++y++ % ++x++
     *
     * x = 5, y = 10
     * NÃO COMPILA. O operador ++ (pós-incremento) e ++ (pré-incremento)
     * não podem ser aplicados a uma expressão que já é o resultado de outro
     * operador de incremento. ++y++ é um erro de sintaxe. Você não pode incrementar o resultado de um incremento.
     * x * 7 == 35 || y++ == 0
     *
     * x = 5, y = 10
     * x * 7 = 5 * 7 = 35
     * 35 == 35 é true.
     * Como o lado esquerdo de || (OR lógico) é true, o lado direito (y++ == 0) não é avaliado devido ao curto-circuito.
     * Resultado da expressão: true
     * Valores de x e y depois: x = 5, y = 10 (porque y++ não foi avaliado)
     * Compila: Sim
     * ++x * ++y
     *
     * x = 5, y = 10
     * ++x faz x = 6, depois usa 6.
     * ++y faz y = 11, depois usa 11.
     * 6 * 11 = 66
     * Resultado da expressão: 66
     * Valores de x e y depois: x = 6, y = 11
     * Compila: Sim
     * x++ * y++
     *
     * x = 5, y = 10
     * x++ usa 5, depois x se torna 6.
     * y++ usa 10, depois y se torna 11.
     * 5 * 10 = 50
     * Resultado da expressão: 50
     * Valores de x e y depois: x = 6, y = 11
     * Compila: Sim
     * true && x * 7
     *
     * x = 5, y = 10
     * NÃO COMPILA. O operador && (AND lógico) espera um boolean em ambos os lados. x * 7 (35) não é um boolean.
     * Compila: Não
     * x * 2 == y || ++y == 10
     *
     * x = 5, y = 10
     * x * 2 = 5 * 2 = 10
     * 10 == y (10 == 10) é true.
     * Como o lado esquerdo de || é true, o lado direito (++y == 10) não é avaliado devido ao curto-circuito.
     * Resultado da expressão: true
     * Valores de x e y depois: x = 5, y = 10 (porque ++y não foi avaliado)
     * Compila: Sim
     * x * 2 == y || ++y == 10 (Essa linha é repetida, então o resultado é o mesmo da anterior)
     *
     * x = 5, y = 10
     * x * 2 = 5 * 2 = 10
     * 10 == y (10 == 10) é true.
     * Como o lado esquerdo de || é true, o lado direito (++y == 10) não é avaliado devido ao curto-circuito.
     * Resultado da expressão: true
     * Valores de x e y depois: x = 5, y = 10 (porque ++y não foi avaliado)
     * Compila: Sim
     *
     */

    public void testBitShiftConversion() {
        int original = 17;
        int convertido = original << 1; //Desloca 1 bit para a esquerda

        System.out.println("17 em binário: " + Integer.toBinaryString(original)); // 10001
        System.out.println("34 em binário: " + Integer.toBinaryString(convertido)); // 100010
        System.out.println("17 << 1 = " + convertido); //Saída 17 << 1 = 34

        /**
         * Explicação:
         * 17 em binário é 10001.
         * Deslocar 1 bit para a esquerda (<< 1) adiciona um 0 à direita: 100010.
         * 100010 em binário é 32 + 2 = 34 em decimal.
         */
    }

    public void testBitWiseComplement() {
        int x = 1;
        int resultado = ~x;
        System.out.println("O valor decimal de ~1 é: " + resultado); // Saída: -2

        /**
         * Qual é o valor decimal de ~1?
         * Tradução: Este exercício testa o operador de complemento de bit (~),
         * que inverte todos os bits de um número. Para números inteiros em Java,
         * isso é feito em relação à representação de complemento de dois do número.
         *
         * Resposta:
         * O operador ~ (complemento de bit) inverte todos os bits. Para um int de 32 bits, 1 é representado como:
         * 0000 0000 0000 0000 0000 0000 0000 0001
         *
         * Aplicando ~, todos os bits são invertidos:
         * 1111 1111 1111 1111 1111 1111 1111 1110
         *
         * Na representação de complemento de dois, este é o valor de -2.
         */
    }

    public void testBitShiftDifference() {
        // Para números POSITIVOS
        int positiveNum = 10; // Binário (32 bits): 0000...00001010

        // >> (deslocamento com sinal): Preenche com o bit de sinal (0 para positivos)
        int signedShiftPositive = positiveNum >> 1; // 0000...00000101 (5)
        assertEquals(5, signedShiftPositive);

        // >>> (deslocamento sem sinal): Sempre preenche com 0
        int unsignedShiftPositive = positiveNum >>> 1; // 0000...00000101 (5)
        assertEquals(5, unsignedShiftPositive);

        // Conclusão para números positivos: NÃO HÁ DIFERENÇA
        System.out.println("Para números POSITIVOS:");
        System.out.println(positiveNum + " >> 1 = " + signedShiftPositive + " (Binário: " + Integer.toBinaryString(signedShiftPositive) + ")");
        System.out.println(positiveNum + " >>> 1 = " + unsignedShiftPositive + " (Binário: " + Integer.toBinaryString(unsignedShiftPositive) + ")");
        System.out.println("Para números positivos, >> e >>> são IGUAIS.");
        System.out.println("----------------------------------------");


        // Para números NEGATIVOS
        int negativeNum = -10; // Binário (32 bits, Complemento de Dois): 1111...11110110

        // >> (deslocamento com sinal): Preenche com o bit de sinal (1 para negativos)
        int signedShiftNegative = negativeNum >> 1; // 1111...11111011 (-5)
        assertEquals(-5, signedShiftNegative);

        // >>> (deslocamento sem sinal): Sempre preenche com 0, ignorando o bit de sinal
        int unsignedShiftNegative = negativeNum >>> 1; // 0111...11111011 (2147483643)
        // Isso transforma o número negativo em um número positivo grande
        assertEquals(2147483643, unsignedShiftNegative);

        // Conclusão para números negativos: HÁ DIFERENÇA
        System.out.println("Para números NEGATIVOS:");
        System.out.println(negativeNum + " >> 1 = " + signedShiftNegative + " (Binário: " + Integer.toBinaryString(signedShiftNegative) + ")");
        System.out.println(negativeNum + " >>> 1 = " + unsignedShiftNegative + " (Binário: " + Integer.toBinaryString(unsignedShiftNegative) + ")");
        System.out.println("Para números negativos, >> e >>> são DIFERENTES.");
        System.out.println(">> mantém o sinal, >>> preenche com zero, tornando-o positivo.");

        /**
         * Demonstre a diferença entre >> e >>>. Existe uma diferença entre
         * os dois para números positivos? Para números negativos?
         * Tradução: Este exercício pede para demonstrar a diferença entre o
         * deslocamento de bits para a direita com sinal (>>) e sem sinal (>>>), e
         * analisar seus efeitos em números positivos e negativos.
         *
         * Diferenças:
         *
         * >> (Shift Right - Deslocamento com Sinal):
         * Preserva o bit de sinal. Se o número for positivo (bit de sinal 0), preenche os bits da esquerda com 0.
         * Se o número for negativo (bit de sinal 1), preenche os bits da esquerda com 1.
         * É equivalente a uma divisão inteira por potências de 2, mantendo o sinal.
         *
         * >>> (Unsigned Shift Right - Deslocamento Sem Sinal):
         * Sempre preenche os bits da esquerda com 0, independentemente do bit de sinal.
         * Sempre resulta em um número positivo (ou zero).
         * Diferença para números positivos: Não há diferença. Ambos preenchem com 0 à esquerda, e o bit de sinal já é 0.
         *
         * Diferença para números negativos: Sim, há uma diferença significativa. O >> manterá o número negativo
         * (preenchendo com 1s), enquanto o >>> o converterá em um grande número positivo (preenchendo com 0s).
         */
    }

    class RandomNumberGenerator {
        public int generateRandomIntFrom1To50() {
            // Math.random() retorna um double entre 0.0 (inclusive) e 1.0 (exclusive)
            // Para um intervalo [1, 50]:
            // (Math.random() * (max - min + 1)) + min
            // (Math.random() * (50 - 1 + 1)) + 1
            // (Math.random() * 50) + 1
            return (int) (Math.random() * 50) + 1;

            private RandomNumberGenerator generator;
            private static final int NUM_ITERATIONS = 100000; //Um grande número de iterações para testes estatísticos

            @Override
            protected void setUp() throws Exception {
                super.setUp();
                generator = new RandomNumberGenerator();
            }

            public void testRandomNumberGenerator() {
                //CONTINUAR EM CASA
            }
        }

    }

}
