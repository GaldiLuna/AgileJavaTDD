package Tests.ExpTestes;

public class SuperClass {
    static boolean constructorWasCalled = false;
    SuperClass(String parm) {
        constructorWasCalled = true;
    }
}
