package Tests.sis.studentinfo;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

abstract public class Session implements Comparable<Session>, Iterable<Student> {
    private static int count;
    private String department;
    private String number;
    //private Vector<Student> students = new Vector<Student>(); + "forma antiga de declarar Lista com Vector"
    private List<Student> students = new ArrayList<Student>();
    private Date startDate;
    private int numberOfCredits;
    private URL url;

    public void setUrl(String urlString) throws SessionException {
        try {
            this.url = new URL(urlString);
        }
        catch (MalformedURLException e) {
            log(e); //Registra a exceção original
            throw new SessionException(e); //Lança uma nova SessionException
        }
    }
    public URL getUrl() {
        return url;
    }

    private void log(Exception e) {
        //Código de logging. (por enquanto o metodo ficará vazio).
        //A segunda metade da lição contem mais informações sobre logging.
    }

    protected Session(String department, String number, Date date) {
        this.department = department;
        this.number = number;
        this.startDate = date;
    }

    public int compareTo(Session that) {
        int compare = this.getDepartment().compareTo(that.getDepartment());
        if (compare != 0)
            return compare;
        return this.getNumber().compareTo(that.getNumber());
    }

    void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public String getDepartment() {
        return department;
    }

    public String getNumber() {
        return number;
    }

    int getNumberOfStudents() {
        return students.size();
    }

    public void enroll(Student student) {
        student.addCredits(numberOfCredits);
        students.add(student);
    }

    Student get(int index) {
        return students.get(index);
    }

    protected Date getStartDate() {
        return startDate;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    abstract protected int getSessionLength();

    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(getStartDate());
        final int daysInWeek = 7;
        final int daysFromFridayToMonday = 3;
        int numberOfDays = getSessionLength() * daysInWeek - daysFromFridayToMonday;
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }

    double averageGpaForPartTimeStudents() {
        double total = 0.0;
        int count = 0;
        // FORMA ANTIGA DO FOR UTILIZANDO ENUMERATION:
        //for (Enumeration<Student> it = students.elements(); it.hasMoreElements(); {
        //      Student student = it.nextElement();
        // FORMA ANTIGA DE FAZER O LAÇO FOR-EACH (TROCAR A LINHA DO FOR ATIVA POR ESSAS DUAS COMENTADAS):
        //for (Iterator<Student> it = students.iterator(); it.hasNext(); ) {
        //      Student student = it.next();
        for (Student student : students) {
            if (student.isFullTime())
                continue;
            count++;
            total += student.getGpa();
        }
        if (count == 0) return 0.0;
        return total / count;
    }

    public Iterator<Student> iterator() {
        return students.iterator();
    }

}
