package Tests.ExpTestes.Lesson11;

import junit.framework.TestCase;

public class MyFileTest extends TestCase {
    public void testReadFailsIfFileDoesNotExist() {
        MyFile myFile = new MyFile("non_existent_file.txt");
        try {
            myFile.getContentAsString();
            fail("Deveria ter lançado FileOperationException");
        } catch (FileOperationException expected) {
            //Sucesso
        }
    }
}
