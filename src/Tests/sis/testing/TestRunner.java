package Tests.sis.testing;

import junit.framework.Test;
import junit.textui.*;

public class TestRunner {
    public static void main(String[] args) {
        // Usa o TestRunner baseado em texto em vez do swingui
        // Isso executa o metodo suite() automaticamente
        junit.textui.TestRunner.run(suite());

        //new junit.swingui.TestRunner().run(TestRunner.class); //linha sugerida pelo livro comentada pela IA
    }

    public static Test suite() {
        return new SuiteBuilder().suite();
    }
}

// Adicionar um target ao seu script de build que executa testing.TestRunner
//<target name="runAllTests" depends="build">
//    <java classname="testing.TestRunner" fork="yes">
//        <classpath refid="classpath" />
//    </java>
//</target>
