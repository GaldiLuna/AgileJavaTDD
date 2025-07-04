package Tests.ExpTestes.Lesson11;

import java.io.*;

public class CloningUtil {
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T original) throws IOException, ClassNotFoundException {
        //Serializar para memória
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();

        //Desserializar de volta para um novo objeto
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (T) ois.readObject();
    }
}
