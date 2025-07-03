package Tests.sis.studentinfo;

import java.util.*;
import java.io.*;

public class CourseCatalog {
    private List<Session> sessions = new ArrayList<Session>();

    public void add(Session session) {
        sessions.add(session);
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void clearAll() {
        sessions.clear();
    }

    //output1 usa fluxo de objetos e output2 usa fluxo de dados
    public void store(String filename) throws IOException {
        ObjectOutputStream output1 = null;
        try {
            output1 = new ObjectOutputStream(new FileOutputStream(filename));
            output1.writeObject(sessions);
        }
        finally {
            output1.close();
        }

//        DataOutputStream output2 = null;
//        try {
//            output2 = new DataOutputStream(new FileOutputStream(filename));
//            output2.writeInt(sessions.size());
//            for (Session session : sessions) {
//                output2.writeLong(session.getStartDate().getTime());
//                output2.writeInt(session.getNumberOfCredits());
//                output2.writeUTF(session.getDepartment());
//                output2.writeUTF(session.getNumber());
//            }
//        }
//        finally {
//            output2.close();
//        }
    }

    //input1 usa fluxo de objetos e input2 usa fluxo de dados
    public void load(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream input1 = null;
        try {
            input1 = new ObjectInputStream(new FileInputStream(filename));
            sessions = (List<Session>)input1.readObject();
        }
        finally {
            input1.close();
        }

//        DataInputStream input2 = null;
//        try {
//            input2 = new DataInputStream(new FileInputStream(filename));
//            int numberOfSessions = input2.readInt();
//            for (int i = 0; i < numberOfSessions; i++) {
//                Date startDate = new Date(input2.readLong());
//                int credits = input2.readInt();
//                String department = input2.readUTF();
//                String number = input2.readUTF();
//
//                Course course = new Course(department, number);
//                Session session = CourseSession.create(course, startDate);
//                session.setNumberOfCredits(credits);
//                sessions.add(session);
//            }
//        }
//        finally {
//            input2.close();
//        }
    }
}
