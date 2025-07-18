package util;

import java.sql.Date;
import java.util.*;

public class ListUtil {

    //Duas tentativas de criação do metodo pad() o primeiro declarando o parâmetro list como uma List que
    // pode ser vinculada a qualquer tipo e o segundo especifica um limite superior ao wildicard '?'
    //
//    public static void pad(List<?> list, Object object, int count) {
//        for (int i = 0; i < count; i++)
//            list.add(object);
//    }
//
//    public static void pad(List<? extends Date> list, Date date, int count) {
//        for (int i = 0; i < count; i++)
//            list.add(date);
//    }

    public static <T> void pad(List<T> list, T object, int count) {
        for (int i = 0; i < count; i++)
            list.add(object);
    }



}
