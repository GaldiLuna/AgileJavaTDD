package Tests.sis.studentinfo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

abstract public class Session implements Comparable<Session>, Iterable<Student>, java.io.Serializable {
    private static int count;
    private Course course;

    public static final long serialVersionUID = 1L;
    private String name;

    //private Vector<Student> students = new Vector<Student>(); + "forma antiga de declarar Lista com Vector"
    private transient List<Student> students = new ArrayList<Student>();
    private Date startDate;
    private int numberOfCredits;
    private URL url;

    public void setUrl(String urlString) throws SessionException {
        try {
            this.url = new URL(urlString);
        }
        catch (MalformedURLException e) {
            //log(e); //Registra a exceção original
            throw new SessionException(e); //Lança uma nova SessionException
        }
    }
    public URL getUrl() {
        return url;
    }

//    private void log(Exception e) {
//        e.printStackTrace();
//    }

//    protected Session(String department, String number, Date date) {
//        this.department = department;
//        this.number = number;
//        this.startDate = date;
//    }

    protected Session(Course course, Date startDate) {
        this.course = course;
//        this.department = course.getDepartment();
//        this.number = course.getNumber();
        this.startDate = startDate;
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

    //public String getDepartment() {return department;}
    public String getDepartment() {
        return course.getDepartment();
    }

    //public String getNumber() {return number;}
    public String getNumber() {
        return course.getNumber();
    }

    public int getNumberOfStudents() {
        return students.size();
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
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

//    public static Student findByLastName(String lastName) {
//        return new Student(lastName);
//    }

    private void writeObject(ObjectOutputStream output) throws IOException {
        output.defaultWriteObject();
        output.writeInt(students.size());
        for (Student student: students)
            output.writeObject(student.getLastName());
    }

    private void readObject(ObjectInputStream input) throws Exception {
        input.defaultReadObject();
        students = new ArrayList<Student>();
        int size = input.readInt();
        for (int i = 0; i < size; i++) {
            String lastName = (String)input.readObject();
            students.add(Student.findByLastName(lastName));
        }
    }

}
