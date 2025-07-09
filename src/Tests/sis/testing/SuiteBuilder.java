package Tests.sis.testing;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.reflect.Modifier;
import java.util.*;
import junit.framework.*;
import junit.runner.*;

public class SuiteBuilder {

    public TestSuite suite() {
        TestSuite suite = new TestSuite();
        List<String> classNames = gatherTestClassNames();
        for (String className : classNames){
            try {
                // Ao criar a suite, a classe já foi pré-filtrada por isTestClassFilename,
                // mas essa camada de segurança adicional ainda é útil.
                Class<?> testClass = createClass(className);
                if (testClass != null && TestCase.class.isAssignableFrom(testClass) && isConcrete(testClass)) {
                    suite.addTest(new TestSuite(testClass));
                }
            } catch (NoClassDefFoundError e) {
                System.err.println("Classe não encontrada: " + className + " - " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Erro ao adicionar teste à suite: " + className + " - " + e.getMessage());
            }
        }
        return suite;
    }

    public List<String> gatherTestClassNames() {
        List<String> testClassNames = new ArrayList<>();
        // Obtém o classpath. Esta é uma abordagem simplificada e pode precisar de ajustes
        // para sistemas de build complexos ou ambientes específicos.
        String classpath = System.getProperty("java.class.path");
        String[] classpathEntries = classpath.split(System.getProperty("path.separator"));

        for (String entry : classpathEntries) {
            File entryFile = new File(entry);
            if (entryFile.exists()) {
                if (entryFile.isDirectory()) {
                    scanDirectory(entryFile, "", testClassNames);
                } else if (entryFile.getName().endsWith(".jar")) {
                    // Escanear JARs é mais complexo e tipicamente requer um ZipInputStream.
                    // Para simplificar neste exemplo, vamos focar principalmente em diretórios.
                    // Se você precisar escanear JARs, isso envolveria a leitura das entradas do arquivo JAR.
                    System.err.println("Aviso: A varredura de JARs para testes não está implementada neste exemplo simplificado: " + entry);
                }
            }
        }
        return testClassNames;

//        ClassPathTestCollector collector = new ClassPathTestCollector() {
//            @Override
//            public boolean isTestClass(String classFileName) {
//                if (!super.isTestClass(classFileName))
//                    return false;
//
//                String className = classNameFromFile(classFileName);
//                Class<?> klass = createClass(className);
//
//                if (klass == null) {
//                    return false;
//                }
//                return TestCase.class.isAssignableFrom(klass) && isConcrete(klass);
//            }
//        };
//        return Collections.list(collector.collectTests());
    }

    private void scanDirectory(File directory, String packageName, List<String> classNames) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                scanDirectory(file, packageName + file.getName() + ".", classNames);
            } else if (file.getName().endsWith(".class")) {
                String classFileName = file.getName();
                String fullClassName = packageName + classFileName.substring(0,classFileName.lastIndexOf(".class"));

                // Carrega a classe e aplica todas as regras de filtragem imediatamente
                Class<?> klass = createClass(fullClassName);
                if (klass != null && TestCase.class.isAssignableFrom(klass) && isConcrete(klass)) {
                    // Apenas adicionamos à lista se todas as condições forem atendidas
                    classNames.add(fullClassName);
                }
//                if (isTestClassFilename(fullClassName)) {
//                    classNames.add(fullClassName);
//                }
            }
        }
    }

    private boolean isConcrete(Class<?> klass) {
        if (klass.isInterface())
            return false;
        int modifiers = klass.getModifiers();
        return !Modifier.isAbstract(modifiers);
    }

    // Este metodo protegido é o que ClassPathTestCollector usa para pré-filtrar nomes de arquivos.
    // Embora o livro o tenha como parte de SuiteBuilder, ele é realmente uma sobrescrita
    // dentro da classe anônima de ClassPathTestCollector para o correto funcionamento do JUnit.
    // Para simplificar, o mantemos aqui como um metodo auxiliar se necessário, mas a lógica principal
    // para filtragem de testes deve estar no isTestClass do ClassPathTestCollector.
    protected String classNameFromFile(String classeFileName) {
        String className = classeFileName.replace('/', '.');
        return className.substring(0, className.lastIndexOf(".class"));
    }

    protected boolean isTestClassFilename(String className) {
        // Esta função agora é um filtro de "primeira passagem" pelo nome,
        // mas a validação completa da classe carregada é feita em scanDirectory.
        // Se você quiser um filtro mais rigoroso aqui, pode fazê-lo,
        // mas o importante é que a verificação 'isConcrete' aconteça.
        return className.contains("Test") && !className.contains("$");
//            classFileName.endsWith(".class") &&
//            classFileName.indexOf('$') < 0 &&
//            classFileName.indexOf("Test") > 0;
    }

    private Class<?> createClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            return null;
        } catch (NoClassDefFoundError e) {
            return null;
        }
    }

    public boolean contains(TestSuite suite, Class<?> testClass) {
        Enumeration<Test> tests = suite.tests();
        while (tests.hasMoreElements()) {
            Test test = tests.nextElement();
            if (test instanceof TestSuite) {
                if (contains((TestSuite) test, testClass)) {
                    return true;
                }
            } else if (test.getClass() == testClass) {
                return true;
            } else if (TestCase.class.isAssignableFrom(testClass) && testClass.isInstance(test)) {
                return true;
            }
        }
        return false;
//        List testClasses = Collections.list(suite.tests());
//        for (Object object: testClasses) {
//            if (object.getClass() == TestSuite.class)
//                if (contains((TestSuite)object, testClass))
//                    return true;
//            if (object.getClass() == testClass)
//                return true;
//        }
//        return false;
    }
}
