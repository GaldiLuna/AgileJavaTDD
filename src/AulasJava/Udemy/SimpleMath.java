/*
package AulasJava.Udemy;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SimpleMath
{

    public Double sum(Double firstNumber, Double secondNumber)
    {
        return firstNumber + secondNumber;
    }
}

@Test
void testSum_WhenSixDotTwoIsAddedByTwo_ShouldReturnEightDotTwo() {
    // Given / Arange
    SimpleMath math = new SimpleMath();

    // When / Act
    Double actual = math.sum(6.2D, 2D);

    // Then / Assert
    assertEquals(8.2D, actual, () -> "6.2 + 2 did not produce 8.2!");

    // Exemplo de Injeção de Dependências nos métodos de checkout e pagamento abaixo.
    public void checkout(Cart cart) {
        PayoutProcessor payoutProcessor = new PayoutProcessor();
        payoutProcessor.processPayment(cart);
    }

    PayoutProcessor payoutProcessor;
    public CartCheckout(PayoutProcessor payoutProcessor) {
        this.payoutProcessor = payoutProcessor;
    }

    public void Checkout(Cart cart, PayoutProcessor payoutProcessor) {
        payoutProcessor.processPayment(cart);
    }
}
*/