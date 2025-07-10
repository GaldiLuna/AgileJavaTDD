package Tests.ExpTestes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class ObjectDumper {

    private static final String INDENT = " "; //Indentação para a saída hierárquica
    private static final Set<Integer> visitedHashes = new HashSet<>(); //Para evitar ciclos de referência

    public static void dump(Object obj) {
        if (obj == null) {
            System.out.println("null");
            return;
        }
        visitedHashes.clear(); //Limpa para cada nova chamada de dump
        dump(obj, 0);
    }

    private static void dump(Object obj, int indentLevel) {
        if (obj == null) {
            System.out.println(getIndent(indentLevel) + "null");
            return;
        }

        Class<?> cls = obj.getClass();
        String currentIndent = getIndent(indentLevel);

        // Evita recursão infinita para objetos com referência cíclica
        // e também evita re-despejar o mesmo objeto
        if (visitedHashes.contains(System.identityHashCode(obj))) {
            System.out.println(currentIndent + cls.getName() + "@" + Integer.toHexString(System.identityHashCode(obj)) + " (Referência já despejada)");
            return;
        }
        visitedHashes.add(System.identityHashCode(obj));

        //Evita pacotes java/javax
        if (cls.getPackage() != null && (cls.getPackage().getName().startsWith("java.") || cls.getPackage().getName().startsWith("javax."))) {
            System.out.println(currentIndent + cls.getName() + " (Pacote ignorado)");
            return;
        }

        System.out.println(currentIndent + cls.getName() + "@" + Integer.toHexString(System.identityHashCode(obj)) + " {");

        Field[] fields = cls.getDeclaredFields(); //Obtém todos os campos, incluindo privados
        for (Field field : fields) {
            // Ignorar campos de superclasses é o default de getDeclaredFields()

            String fieldIndent = getIndent(indentLevel + 1);
            System.out.print(fieldIndent + field.getName());

            if (Modifier.isStatic(field.getModifiers())) {
                System.out.print(" (static)");
            }

            System.out.print(": ");

            try {
                //Permite acesso a campos privados
                field.setAccessible(true);
                Object fieldValue = field.get(obj);

                if (fieldValue == null) {
                    System.out.println("null");
                } else if (fieldValue.getClass().isPrimitive() || fieldValue instanceof String || fieldValue instanceof Number || fieldValue instanceof Boolean || fieldValue instanceof Character) {
                    System.out.println(fieldValue);
                } else {
                    System.out.println(); //Nova linha para o despejo recursivo
                    dump(fieldValue, indentLevel + 2); //Chamada recursiva para objetos aninhados
                }
            } catch (IllegalAccessException e) {
                System.out.println("[Erro de Acesso]");
            }
        }
        System.out.println(currentIndent + "}");
    }

    private static String getIndent(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append(INDENT);
        }
        return sb.toString();
    }

    // --- Exemplo de Uso ---
    static class Person {
        private String name;
        private int age;
        private static String species = "Homo Sapiens";
        public Address address;

        public Person(String name, int age, Address address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
    }

    static class Address {
        private String street;
        private String city;
        private int zipCode;
        private Person resident; //Referência cíclica para teste

        public Address(String street, String city, int zipCode) {
            this.street = street;
            this.city = city;
            this.zipCode = zipCode;
        }

        public void setResident(Person resident) {
            this.resident = resident;
        }
    }

    public static void main(String[] args) {
        Address home = new Address("Rua da Programação", "Codelândia", 1234567);
        Person galdiluna = new Person("GaldiLuna", 42, home);
        home.setResident(galdiluna); //Cria referência cíclica

        System.out.println("--- Despejando objeto Person ---");
        ObjectDumper.dump(galdiluna);

        System.out.println("--- Despejando objeto Address ---");
        ObjectDumper.dump(home);

        System.out.println("\n--- Despejando String (ignorada) ---");
        ObjectDumper.dump("Hello World ou Olá Mundo");
    }
}
