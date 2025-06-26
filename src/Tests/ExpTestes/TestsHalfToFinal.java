package Tests.ExpTestes;

import junit.framework.TestCase;

import java.math.BigDecimal;
import java.util.*;

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
        public static int generateRandomIntFrom1To50() {
            // Math.random() retorna um double entre 0.0 (inclusive) e 1.0 (exclusive)
            // Para um intervalo [1, 50]:
            // (Math.random() * (max - min + 1)) + min
            // (Math.random() * (50 - 1 + 1)) + 1
            // (Math.random() * 50) + 1
            return (int) (Math.random() * 50) + 1;
        }

    }

    private RandomNumberGenerator generator;
    private static final int NUM_ITERATIONS = 100000; //Um grande número de iterações para testes estatísticos

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        generator = new RandomNumberGenerator();
    }

    public void testRandomNumberGenerator() {
        //CONTINUAR EM CASA - OK!
    }

    public void testGeneratedNumberIsWithinRange(){
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            int randomNumber = generator.generateRandomIntFrom1To50();
            assertTrue("O número gerado deve ser  >= 1", randomNumber >= 1);
            assertTrue("O número gerado deve ser <= 50", randomNumber <= 50);
        }
    }

    public void testAllNumbersArePossiblyGenerated() {
        // Este teste verifica se todos os números no intervalo [1, 50] são gerados
        // ATENÇÃO: Mesmo com 100.000 iterações, não há 100% de garantia.
        // É um teste de probabilidade.
        Set<Integer> uniqueNumbersGenerated = new HashSet<>();
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            uniqueNumbersGenerated.add(RandomNumberGenerator.generateRandomIntFrom1To50());
        }
        assertEquals("Todos os 50 números devem ser gerados ao longo de muitas iterações",
                50, uniqueNumbersGenerated.size());
    }
    // Um teste mais avançado verificaria a distribuição, mas exigiria bibliotecas estatísticas.
    // Este exemplo é para ilustrar os conceitos.


    private static final int LIST_SIZE = 100;
    private static final int NUM_SWAPS = 100;

    //Classe para embaralhar a lista
    public static class ListSwapper {
        private Random random;

        //Construtor para permitir injetar um Random para testabilidade (mocking implícito)
        public ListSwapper(Random random) {
            this.random = random;
        }

        public List<Integer> createInitialList() {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= LIST_SIZE; i++) {
                list.add(i);
            }
            return list;
        }

        //Realiza uma única troca de elementos
        public void swapRandomElements(List<Integer> list) {
            if (list == null || list.size() < 2) {
                return; //Não é possível trocar se a lista tem menos de 2 elementos
            }
            int index1 = random.nextInt(list.size());
            int index2 = random.nextInt(list.size());

            //Garante que os índices sejam diferentes para garantir uma "troca" real,
            // a menos que o problema permita trocar um elemento consigo mesmo.
            // Para "dois números foram trocados", eles precisam ser diferentes.
            if (list.size() > 1) {
                while (index1 == index2 && list.size() > 1) {
                    index2 = random.nextInt(list.size());
                }
            } else { //Se a lista tem 0 ou 1 elemento, não há troca a ser feita.
                return;
            }

            //Troca os elementos
            Collections.swap(list, index1, index2);
        }

        //Troca elementos da lista N vezes
        public void performMultipleSwaps(List<Integer> list, int numberOfSwaps) {
            for (int i = 0; i < numberOfSwaps; i++) {
                swapRandomElements(list);
            }
        }
    }

    // --- Testes ---
    public void testListSwapper() {
        //Usamos um Random com seed para que o teste seja reproduzível
        Random seedRandom = new Random(123L);
        ListSwapper swapper = new ListSwapper(seedRandom);

        List<Integer> list = swapper.createInitialList();
        assertEquals("O tamanho inicial da lista deve ser " + LIST_SIZE, LIST_SIZE, list.size());

        //Cria uma cópia da lista original para comparação (se necessário)
        List<Integer> originalListCopy = new ArrayList<>(list);

        //Realiza as trocas
        swapper.performMultipleSwaps(list, NUM_SWAPS);

        //Verificação 1: O tamanho da lista deve permanecer o mesmo
        assertEquals("O tamanho da lista deve permanecer o mesmo após as trocas", LIST_SIZE, list.size());

        // Verificação 2: Para cada chamada ao swapper, dois números foram trocados.
        // Isso é mais fácil de verificar se fizermos o mocking do Random ou
        // se a lógica de swap garante que os índices são diferentes.
        // A implementação de 'swapRandomElements' já tenta garantir índices diferentes.
        // Uma forma de verificar isso em alto nível é garantir que a lista não é mais a original (se houver trocas).
        // A probabilidade de uma lista de 100 elementos não mudar em 100 trocas aleatórias é extremamente baixa.
        // No entanto, para provar "dois números foram trocados COM CADA CHAMADA", precisaríamos de mocking.
        // Sem mocking, podemos apenas verificar que a lista foi *alterada* da original.
        assertFalse("A lista deve ter sido alterada da original após as trocas", list.equals(originalListCopy));

        // Verificação 3: (Extra e mais robusta, se o mocking for permitido):
        // Para provar "dois números foram trocados com cada chamada",
        // precisaríamos de um MockRandom que retornasse valores previsíveis
        // e então verificar as posições exatas dos elementos.
        // Sem mocking, é difícil provar cada swap individualmente, mas
        // podemos garantir que a soma dos elementos (ou a contagem de elementos únicos)
        // permanece a mesma, o que indica que nenhum elemento foi perdido ou duplicado.
        long sumOriginal = originalListCopy.stream().mapToLong(Integer::intValue).sum();
        long sumShuffled = list.stream().mapToLong(Integer::intValue).sum();
        assertEquals("A soma dos elementos deve permanecer a mesma (nenhum elemento perdido/duplicado)",
                sumOriginal, sumShuffled);

        // Verificação 4: Garante que os elementos são os mesmos (apenas a ordem mudou)
        // Verifica se todos os elementos originais ainda estão presentes
        // (apenas em uma ordem diferente, o que é garantido se as somas são iguais e o tamanho é o mesmo).
        // Se quisermos ser mais rigorosos, podemos classificar e comparar.
        Collections.sort(list);
        Collections.sort(originalListCopy);
        assertEquals("Os elementos devem ser os mesmos, apenas em ordem diferente", originalListCopy, list);
    }

    public void testRandomSeedDifference() {
        //Gerador 1: Com uma semente fixa (reproduzível)
        Random randomComSeed = new Random(1L); //Semente fixa como 1 (long)
        double primeiroDoubleComSeed = randomComSeed.nextDouble();

        //Gerador 2: Sem semente (usa o relógio do sistema, não reproduzível)
        Random randomSemSeed = new Random(); //Usa o System.nanoTime() ou similar
        double primeiroDoubleSemSeed = randomSemSeed.nextDouble();

        // A probabilidade de randomSemSeed produzir o mesmo primeiroDouble que randomComSeed é extremamente baixa,
        // mas não impossível se o System.nanoTime() em tempo de execução for o mesmo que a semente 1L.
        // No entanto, para fins práticos e de teste, eles serão diferentes.
        assertFalse("O primeiro double de Random com semente 1 e Random sem semente devem ser diferentes",
                primeiroDoubleComSeed == primeiroDoubleSemSeed);

        // Para provar ABSOLUTAMENTE que isso é verdade (ou seja, que a semente padrão é *quase* garantidamente diferente da semente 1):
        // Não é possível fazer um teste unitário que "absolutamente prove" isso de forma determinística
        // porque Random() sem seed depende do relógio do sistema no momento da execução.
        // O teste acima faz uma asserção de desigualdade que *quase sempre* será verdadeira na prática.

        // Para uma prova mais "absoluta" em um contexto de teste, você pode:
        // 1. Injetar (mockar) o relógio do sistema para o Random sem semente, mas isso exigiria
        //    mudar o comportamento interno da classe Random, o que não é ideal.
        // 2. Aceitar que a demonstração acima é suficiente para fins práticos.
        // 3. Gerar muitos números de ambos e verificar que as sequências divergem.

        // Exemplo de como as sequências divergiriam:
        Random randomComSeed2 = new Random(1L);
        Random randomComSeed3 = new Random(1L);
        assertEquals("Geradores com a mesma semente produzem a mesma sequência",
                randomComSeed2.nextDouble(), randomComSeed3.nextDouble(), 0.0);
        assertEquals("Geradores com a mesma semente produzem a mesma sequência na segunda chamada",
                randomComSeed2.nextDouble(), randomComSeed3.nextDouble(), 0.0);

        //Imprimir para ver os valores reais
        System.out.println("Primeiro double com semente 1: " + primeiroDoubleComSeed);
        System.out.println("Primeiro double sem semente: " + primeiroDoubleSemSeed);
        //System.out.println("\n");
        System.out.println("Segundo double com semente 1: " + randomComSeed2);
        System.out.println("Terceiro double com semente 1: " + randomComSeed3);
    }

    public void testSwapWithXOR() {
        int a = 5; //Binário 0101
        int b = 10; //Binário 1010
        System.out.println("O valor de A é = " + a + " e O valor de B é = " + b);

        //Passo 1: a = a ^ b
        a = a ^ b; //a = 0101 ^ 1010 = 1111 (15 em decimal)
                   //a agora contém XOR de a e b originais

        //Passo 2: b = a ^ b
        b = a ^ b; //b = (0101 ^ 1010) ^ 1010
                   //b = 0101 ^ (1010 ^ 1010)
                   //b = 0101 ^ 0000 = 0101 (5 em decimal)
                   //b agora contém o valor original de a

        //Passo 3: a = a ^ b
        a = a ^ b; //a = (0101 ^ 1010) ^ 0101
                   //a = 1010 ^ (0101 ^ 0101)
                   //a = 1010 ^ 0000 = 1010 (10 em decimal)

        assertEquals("O valor de a deve ser 10", 10, a);
        assertEquals("O valor de b deve ser 5", 5, b);
        System.out.println("O valor de A é = " + a + " e O valor de B é = " + b);

        //Teste com números negativos
        int c = -3; //Binário: ...11111101
        int d = 7; //Binário: ...00000111
        System.out.println("O valor de C é = " + c + " e O valor de D é = " + d);

        c = c ^ d;
        d = c ^ d;
        c = c ^ d;

        assertEquals("O valor de c deve ser 7", 7, c);
        assertEquals("O valor de d deve ser -3", -3, d);
        System.out.println("O valor de C é = " + c + " e O valor de D é = " + d);

        //Teste com zero
        int e = 0;
        int f = 100;
        System.out.println("O valor de E é = " + e + " e O valor de F é = " + f);

        e = e ^ f;
        f = e ^ f;
        e = e ^ f;

        assertEquals("O valor de e deve ser 100", 100, e);
        assertEquals("O valor de f deve ser 0", 0 , f);
        System.out.println("O valor de E é = " + e + " e O valor de F é = " + f);
    }

    public void testNumberOfBitsForIntegralTypes() {
        // --- Usando constantes das classes wrapper (abordagem mais direta) ---
        assertEquals("char: " + Character.SIZE + " bits", 16, Character.SIZE);
        assertEquals("byte: " + Byte.SIZE + " bits", 8, Byte.SIZE);
        assertEquals("short: " + Short.SIZE + " bits", 16, Short.SIZE);
        assertEquals("int: " + Integer.SIZE + " bits", 32, Integer.SIZE);
        assertEquals("long: " + Long.SIZE + " bits", 64, Long.SIZE);

        System.out.println("--- Usando constantes SIZE das classes wrapper ---");
        System.out.println("char: " + Character.SIZE + " bits");
        System.out.println("byte: " + Byte.SIZE + " bits");
        System.out.println("short: " + Short.SIZE + " bits");
        System.out.println("int: " + Integer.SIZE + " bits");
        System.out.println("long: " + Long.SIZE + " bits");
        System.out.println("-------------------------------------------------");

        // --- Usando operadores de deslocamento (para tipos com sinal) ---
        // Para tipos com sinal, a ideia é encontrar a menor potência de 2 que pode representar
        // o maior valor positivo (2^n - 1) e o menor valor negativo (-2^n).
        // Isso é o mesmo que encontrar o número de bits 'n' tal que 2^(n-1) - 1 é o MAX_VALUE.
        // Ou, de forma mais simples, o número de bits 'n' é tal que 1 << (n-1) dá o MSB do maior valor negativo.

        // Para 'int' (32 bits):
        int intBits = 0;
        int testInt = 1;
        while (testInt != 0) {// Enquanto o bit mais significativo não for 0 (no caso de um número negativo, quando a representação se torna 0)
            testInt <<= 1; //Desloca para a esquerda
            intBits++;
        }
        // Isso conta os bits "úteis" para o valor máximo, mas não inclui o sinal facilmente.
        // Uma forma mais robusta e direta é usar o MAX_VALUE e log base 2, ou a constante SIZE.

        // Abordagem usando MAX_VALUE e um loop com deslocamento (para demonstrar o conceito de 'n' bits)
        // Isso é mais conceitual para "bits necessários para o valor", e não o tamanho total do tipo.
        System.out.println("--- Demonstrando com loop de deslocamento e MAX_VALUE (conceitual) ---");

        // byte (8 bits): Max value 127 (01111111)
        byte byteMaxValue = Byte.MAX_VALUE;
        int bitsForByteValue = 0;
        int tempByte = byteMaxValue;
        while (tempByte > 0) {
            tempByte >>= 1; //Desloca para a direita
            bitsForByteValue++;
        }
        //Adiciona 1 para o bit de sinal
        assertEquals("byte (por valor): " + (bitsForByteValue + 1) + " bits", 8, bitsForByteValue + 1);
        System.out.println("byte (pelo valor max, positivo): " + (bitsForByteValue + 1) + " bits (incluindo o sinal)");

        //short (16 bits): Max value 32767
        short shortMaxValue = Short.MAX_VALUE;
        int bitsForShortValue = 0;
        int tempShort = shortMaxValue;
        while (tempShort > 0) {
            tempShort >>= 1;
            bitsForShortValue++;
        }
        assertEquals("short (por valor): " + (bitsForShortValue + 1) + " bits", 16, bitsForShortValue + 1);
        System.out.println("short (pelo valor max, positivo): " + (bitsForShortValue + 1) + " bits (incluindo o sinal)");

        // int (32 bits): Max value 2147483647
        int intMaxValue = Integer.MAX_VALUE;
        int bitsForIntValue = 0;
        int tempInt = intMaxValue;
        while (tempInt > 0) {
            tempInt >>>= 1; //Usar >>> para evitar problemas com números negativos se a lógica for diferente
            bitsForIntValue++;
        }
        assertEquals("int (por valor): " + (bitsForIntValue + 1) + " bits", 32, bitsForIntValue + 1);
        System.out.println("int (pelo valor max, positivo): " + (bitsForIntValue + 1) + " bits (incluindo o sinal)");

        // long (64 bits): Max value
        long longMaxValue = Long.MAX_VALUE;
        int bitsForLongValue = 0;
        long tempLong = longMaxValue;
        while (tempLong > 0) {
            tempLong >>>= 1;
            bitsForLongValue++;
        }
        assertEquals("long (por valor): " + (bitsForLongValue + 1) + " bits", 64, bitsForLongValue + 1);
        System.out.println("long (pelo valor max, positivo): " + (bitsForLongValue + 1) + " bits (incluindo o sinal)");

        // char (sem sinal, 16 bits): Max value 65535
        char charMaxValue = Character.MAX_VALUE;
        int bitsForCharValue = 0;
        char tempChar = charMaxValue;
        while (tempChar > 0) {// char é tratado como int para operações, mas seu range é sem sinal
            tempChar >>= 1; // Usar >> pois o char é unsigned nesse contexto de bits
            bitsForCharValue++;
        }
        assertEquals("char (pelo valor max, sem sinal): " + bitsForCharValue + " bits", 16, bitsForCharValue);
        System.out.println("char (pelo valor max, sem sinal): " + bitsForCharValue + " bits");
        System.out.println("-------------------------------------------------");
    }

}
