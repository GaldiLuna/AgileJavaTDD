package Tests.ExpTestes.Lesson11;

import junit.framework.TestCase;
import java.io.*;

public class QuestionTen extends TestCase {
    public void testPrimitiveSizes() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        long currentSize = baos.size();
        dos.writeBoolean(true);
        System.out.println("boolean: " + (baos.size() - currentSize)); // Deve ser 1

        currentSize = baos.size();
        dos.writeByte(1);
        System.out.println("byte: " + (baos.size() - currentSize)); // Deve ser 1

        currentSize = baos.size();
        dos.writeChar('a');
        System.out.println("char: " + (baos.size() - currentSize)); // Deve ser 2

        currentSize = baos.size();
        dos.writeShort(1);
        System.out.println("short: " + (baos.size() - currentSize)); // Deve ser 2

        currentSize = baos.size();
        dos.writeInt(1);
        System.out.println("int: " + (baos.size() - currentSize)); // Deve ser 4

        currentSize = baos.size();
        dos.writeLong(1L);
        System.out.println("long: " + (baos.size() - currentSize)); // Deve ser 8

        currentSize = baos.size();
        dos.writeFloat(1.0f);
        System.out.println("float: " + (baos.size() - currentSize)); // Deve ser 4

        currentSize = baos.size();
        dos.writeDouble(1.0);
        System.out.println("double: " + (baos.size() - currentSize)); // Deve ser 8

        dos.close();
    }
}

