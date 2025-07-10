package Tests.ExpTestes;
import Tests.ExpTestes.ObjectDumper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//Vamos reutilizar a classe ObjectDumper.java

//Interface que o objeto de destino deve implementar e que o proxy irá "simular"
interface MyService {
    String doSomething(String input);
    int calculate(int a, int b);

    @Override //Importante: toString deve ser definido na interface para que o proxy o intercepte
    String toString();
}

//Implementação original do serviço
class MyServiceImpl implements MyService {
    private String name;
    private int counter = 0;

    public MyServiceImpl(String name) {
        this.name = name;
    }

    @Override
    public String doSomething(String input) {
        counter++;
        return "Service " + name + " did: " + input + " (count: " + counter + ")";
    }

    @Override
    public int calculate(int a, int b) {
        counter++;
        return a + b;
    }

    //Esta implementação de toString será ignorada pelo proxy quando invocado através dele
    @Override
    public String toString() {
        return "MyServiceImpl [name=" + name + ", counter=" + counter + "]";
    }
}

//O InvocationHandler que define o comportamento do proxy
class DumperProxyHandler implements InvocationHandler {
    private Object target; //O objeto real para o qual delegar
    private ObjectDumper dumper; //O utilitário de despejo de objetos

    public DumperProxyHandler(Object target, ObjectDumper dumper) {
        this.target = target;
        this.dumper = dumper;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("toString".equals(method.getName()) && method.getParameterCount() == 0) {
            //Interceptar toString(): delega para o dumper
            System.out.println("--- toString() interceptado. Despejando objeto alvo: ---");
            dumper.dump(target); //Chama o dumper no objeto real
            return "Proxy de " + target.getClass().getSimpleName() + " (toString via ObjectDumper)";
        } else {
            //Para todos os outros metodos: delega para o objeto alvo
            try {
                return method.invoke(target, args);
            } catch (java.lang.reflect.InvocationTargetException e) {
                //Se o metodo sbjacente lançar uma exceção, desembrulhe-a
                throw e.getTargetException();
            }
        }
    }
}

public class MyServiceSolution {
    public static void main(String[] args) {
        MyService realService = new MyServiceImpl("MeuServicoReal");
        ObjectDumper myDumper = new ObjectDumper(); //Instancia o ObjectDumper

        //Cria o proxy dinâmico
        MyService proxyService = (MyService) Proxy.newProxyInstance(
            MyService.class.getClassLoader(), //ClassLoader da interface
            new Class<?>[]{MyService.class}, //Interfaces que o proxy implementará
            new DumperProxyHandler(realService, myDumper)); //O InvocationHandler

        // --- Testando o proxy ---

        //Chamadas normais de metodo (delegam para o objeto real)
        System.out.println(proxyService.doSomething("tarefa 1"));
        System.out.println(proxyService.calculate(5, 7));
        System.out.println(proxyService.doSomething("tarefa 2"));

        //chamada de toString() (delegará para o ObjectDumper)
        System.out.println("\nChamando toString() no proxy:");
        System.out.println(proxyService.toString()); //Esta linha chamará o dumper

        //
        System.out.println("\nChamando toString() no objeto real diretamente (para comparação):");
        System.out.println(realService.toString()); //Esta linha chamará o toString original
    }
}
