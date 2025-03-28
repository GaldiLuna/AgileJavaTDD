package Tests.sis.studentinfo;
import java.util.*;

public class DateUtil {
    public static Date createDate(int year, int month, int date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set(Calendar.YEAR, year - 1900);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, date);
        return calendar.getTime();
    }
}

/**
 * Tornando o metodo Date como estático, podemos fazer sua chamada da seguinte forma:
 * Date date = DateUtil.createDate(2000, 1, 1);
 */