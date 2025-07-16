package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiHashMap<K, V> {
    private Map<K, List<V>> map = new HashMap<K, List<V>>();

    public  int size() {
        return map.size();
    }

    public void put(K key, V value) {
        List<V> values = map.get(key);
        if (values == null) {
            values = new ArrayList<V>();
            map.put(key,values);
        }
        values.add(value);
    }

    public List<V> get(K key) {
        return map.get(key);
    }
}

//ISSO NÃO É COMO O JAVA TRADUZ GENÉRICOS:
//public class MultiHashMap<Date,String> { // Notar os tipos concretos aqui
//    private Map<Date,List<String>> map =
//        new HashMap<Date,List<String>>();
//
//    public int size() {
//        return map.size();
//    }
//
//    public void put(Date key, String value) {
//        List<String> values = map.get(key);
//        if (values == null) {
//            values = new ArrayList<String>();
//            map.put(key, values);
//        }
//        values.add(value);
//    }
//
//    public List<String> get(Date key) {
//        return map.get(key);
//    }
//}

//AQUI AS INFORMAÇÕES DE VINCULAÇÃO SÃO APAGADAS E SUBSTITUÍDAS POR CASTS CONFORME APROPRIADO
//public class MultiHashMap { // Notar a ausência de parâmetros de tipo aqui
//    private Map map = new HashMap(); // Map sem tipos parametrizados
//
//    public int size() {
//        return map.size();
//    }
//
//    public void put(Object key, Object value) { // key e value são Object
//        List values = (List)map.get(key); // Cast para List
//        if (values == null) {
//            values = new ArrayList();
//            map.put(key, values);
//        }
//        values.add(value);
//    }
//
//    public List get(Object key) {
//        return (List)map.get(key); // Cast para List
//    }
//}