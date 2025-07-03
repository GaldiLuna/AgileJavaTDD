package Tests.sis.studentinfo;

import java.io.*;
import java.util.*;
import Tests.sis.db.*;

public class StudentDirectory {
    //private Map<String, Student> students = new HashMap<>();

    private static final String DIR_BASENAME = "studentDir";
    private DataFile db;

    public StudentDirectory() throws IOException {
        db = DataFile.open(DIR_BASENAME);
    }

    public void add(Student student) throws IOException {
        //students.put(student.getId(), student);
        db.add(student.getId(), student);
    }

    public Student findById(String id) throws IOException {
        //return students.get(id);
        return (Student)db.findBy(id);
    }

    public void close() throws IOException {
        db.close();
    }

    public void remove() {
        db.deleteFiles();
    }
}
