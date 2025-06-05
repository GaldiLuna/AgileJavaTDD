package Tests.sis.studentinfo;
import Tests.sis.studentinfo.Course;
import junit.framework.TestCase;

public class CourseTest extends TestCase {
    public void testCreate() {
        Course course = new Course("CMSC", "120");
        assertEquals("CMSC", course.getDepartment());
        assertEquals("120", course.getNumber());
    }

    public void testEquality() {
        Course courseA = new Course("NURS", "201");
        Course courseAPrime = new Course("NURS", "201");
        assertEquals(courseA, courseAPrime);
        if (!courseA.equals(courseAPrime))
            fail();
        Course courseB = new Course("ARTH", "330");
        assertFalse(courseA.equals(courseB));

        //Sobre o contrato de Igualdade vamos ver as 5 opções de comparação abaixo:
        //REFLEXIVIDADE
        assertEquals(courseA, courseA);

        //TRANSITIVIDADE
        Course courseAPrime2 = new Course("NURS", "201");
        assertEquals(courseAPrime, courseAPrime2);
        assertEquals(courseA, courseAPrime2);

        //SIMETRIA
        assertEquals(courseAPrime, courseA);

        //CONSISTÊNCIA
        assertEquals(courseA, courseAPrime);

        //COMPARAÇÃO COM NULO
        assertFalse(courseA.equals(null));
        assertTrue(!courseA.equals(null));

        //MAÇÃS E LARANJAS
        assertFalse(courseA.equals("CMSC-120"));

        //OUTRAS COMPARAÇÕES MAIS EXPLÍCITAS
        assertTrue(courseA.equals(courseAPrime)); // Verifica se os objetos são IGUAIS (comparando o conteúdo)
        assertFalse(courseA == courseAPrime); // Verifica se os objetos NÃO SÃO IDÊNTICOS (não são o mesmo objeto na memória)
        assertNotSame(courseA, courseAPrime); // O JUnit tem um metodo específico para verificar se NÃO são idênticos, que é mais claro
        // Para demonstrar o assertSame, você pode usá-lo para verificar a reflexividade
        // (um objeto é sempre idêntico a si mesmo)
        assertSame(courseA, courseA);
    }
}
