package Tests.ExpTestes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionCloner {

    //Metodo genérico para fazer uma cópia rasa de um objeto
    public static <T> T shallowClone(T original) throws Exception {
        if (original == null) {
            return null;
        }

        Class<?> cls = original.getClass();

        //Obter o construtor sem argumentos
        Constructor<?> constructor = null;
        try {
            constructor = cls.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("A classe '" + cls.getName() + "' deve ter um construtor sem argumentos para ser clonada.", e);
        }

        //Tornar o construtor acessível se for privado
        constructor.setAccessible(true);

        //Criar uma nova instância do objeto
        @SuppressWarnings("unchecked")
                T cloned = (T) constructor.newInstance();

        //Copiar os valores de cada campo do objeto original para o novo objeto
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            //Ignorar os campos estáticos, pois eles pertencem à classe, não à instância
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            //Tornar o campo acessível (para campos privados)
            field.setAccessible(true);

            //Copiar o valor do campo do original para o clone
            field.set(cloned, field.get(original));
        }

        return cloned;
    }

    // --- Exemplo de Uso ---
    static class MyCloneableClass {
        private String name;
        private int value;
        private AnotherObject obj; //Esse será uma cópia rasa

        //Construtor sem argumentos necessário para reflexão
        public MyCloneableClass() {
            //Pode ser público ou privado, ReflectionCloner.shallowClone irá torná-lo acessível
        }

        public MyCloneableClass(String name, int value, AnotherObject obj) {
            this.name = name;
            this.value = value;
            this.obj = obj;
        }

        @Override
        public String toString() {
            return "MyCloneableClass{name='" + name + "', value=" + value + ", obj=" + obj + "}";
        }

        public String getName() {
            return name;
        }
        public int getValue() {
            return value;
        }
        public AnotherObject getObj() {
            return obj;
        }
    }

    static class AnotherObject {
        public String data;
        public AnotherObject(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "AnotherObject{data='" + data + "'}";
        }

        public static void main(String[] args) throws Exception {
            AnotherObject originalNested = new AnotherObject("original data");
            MyCloneableClass original = new MyCloneableClass("OriginalName", 100, originalNested);

            System.out.println("Original: " + original + " (hash: " + System.identityHashCode(original) + ")");
            System.out.println("  Nested Original: " + original.getObj() + " (hash: " + System.identityHashCode(original.getObj()) + ")");

            MyCloneableClass cloned = ReflectionCloner.shallowClone(original);

            System.out.println("Cloned:  " + cloned + " (hash: " + System.identityHashCode(cloned) + ")");
            System.out.println("  Nested Cloned:  " + cloned.getObj() + " (hash: " + System.identityHashCode(cloned.getObj()) + ")");

            //Testando a cópia rasa:
            //Se mudarmos o obejto aninhado no original, o clone também será afetado
            original.getObj().data = "modified data";
            System.out.println("\nApós modificar objeto aninhado no original:");
            System.out.println("Original: " + original);
            System.out.println("Cloned:  " + cloned); //O clone reflete a mudança, provando que é uma cópia rasa!
        }
    }
}
