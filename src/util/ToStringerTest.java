package util;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ToStringerTest {

    @Test
    public void testDumpBasicFields() throws IllegalAccessException {
        ExampleDumpClasse student = new ExampleDumpClasse("Alice", 123, "shhh!");
        ToStringer toStringer = new ToStringer(student);
        String dumpOutput = toStringer.dump();

        //O output deve conter os campos 'name e 'id' mas não 'secret'
        assertTrue(dumpOutput.contains("name=Alice"));
        assertTrue(dumpOutput.contains("id=123"));
        assertFalse(dumpOutput.contains("secret")); // 'secret' não tem @Dump, não deve aparecer
    }

    @Test
    public void testDumpEmptyObject() throws IllegalAccessException {
        class EmptyClass {
            @Dump public String field1 = "test";
        }
        ToStringer toStringer = new ToStringer(new EmptyClass());
        String dumpOutput = toStringer.dump();
        assertTrue(dumpOutput.contains("field1=test"));
    }

    @Test
    public void testDumpOrderedFields() throws IllegalAccessException {
        OrderedExample student = new OrderedExample("Jane", "Doe", 20, "S123");
        ToStringer toStringer = new ToStringer(student);
        String dumpOutput = toStringer.dump();

        //Verifica a ordem esperada
        int indexFirstName = dumpOutput.indexOf("firstName=Jane");
        int indexLastName = dumpOutput.indexOf("lastName=Doe");
        int indexStudentId = dumpOutput.indexOf("studentId=S123");
        //int indexAge = dumpOutput.indexOf("age=20"); // age não é @Dump, então virá por último ou não aparecerá, dependendo da lógica

        assertTrue(indexFirstName < indexLastName); // order 1 < order 2
        assertTrue(indexLastName < indexStudentId); // order 2 < order 3
        //assertTrue(indexStudentId < indexAge);      // order 3 < sem ordem (MAX_VALUE)
        // Se 'age' não é @Dump, o próximo assert não faz sentido.
        // A lógica do ToStringer corrigida apenas @Dump campos com @Dump.
        // Se 'age' não tem @Dump, ele não deve aparecer no output.
        assertFalse(dumpOutput.contains("age=20"));

        assertTrue(dumpOutput.contains("firstName=Jane"));
        assertTrue(dumpOutput.contains("lastName=Doe"));
        assertTrue(dumpOutput.contains("studentId=S123"));
        //assertTrue(dumpOutput.contains("age=20")); //Certifica-se que campos não anotados ainda são incluídos
        // Se 'age' não é @Dump, o próximo assert não faz sentido.
        // A lógica do ToStringer corrigida apenas @Dump campos com @Dump.
        // Se 'age' não tem @Dump, ele não deve aparecer no output.
        //assertFalse(dumpOutput.contains("age=20")); //Linha duplicada
    }

    @Test
    public void testDumpWithQuotes() throws IllegalAccessException {
        QuotedPerson person = new QuotedPerson("  Jhon Doe  ", "Engineer");
        ToStringer toStringer = new ToStringer(person);
        String dumpOutput = toStringer.dump();

        assertTrue(dumpOutput.contains("name=\"  Jhon Doe  \"")); // Deve ter aspas
        assertTrue(dumpOutput.contains("occupation=Engineer")); // Não deve ter aspas
    }

    @Test
    public void testDumpWithOutputMethod() throws IllegalAccessException {
        Item item = new Item("XYZ", "Awesome Gadget", LocalDate.now());
        ToStringer toStringer = new ToStringer(item);
        String dumpOutput = toStringer.dump();

        // Verifica se 'id' usou getFormattedId
        assertTrue(dumpOutput.contains("id=ID-XYZ"));
        // Verifica se 'description' usou toString padrão
        assertTrue(dumpOutput.contains("description=Awesome Gadget"));
        // Verifica se 'expirationDate' usou toEpochDay (LocalDate.of(2025,12,31) = 16799 em epoch day)
        assertTrue(dumpOutput.contains("expirationDate=20300"));
    }

    @Test
    public void testDumpWithMultipleOutputMethods() throws IllegalAccessException {
        PersonDetails person = new PersonDetails("Alice", "Wonderland", 30, "alice@example.com");
        ToStringer toStringer = new ToStringer(person);
        String dumpOutput = toStringer.dump();

        // O campo 'personInfo' deve concatenar os resultados de getFullName e getAgeDescription
        assertTrue(dumpOutput.contains("personInfo=Alice Wonderland 30 years old"));

        // O campo 'email' deve usar o toString padrão (que é o próprio valor da String)
        assertTrue(dumpOutput.contains("email=alice@example.com"));
    }
}
