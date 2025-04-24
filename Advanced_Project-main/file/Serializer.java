package file;

import java.io.*;
import java.util.List;

public class Serializer {

    // Serialize a list of objects to a binary file
    public static <T> void serializeToFile(List<T> list, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(list);
        }
    }

    // Deserialize a list of objects from a binary file
    @SuppressWarnings("unchecked")
    public static <T> List<T> deserializeFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<T>) ois.readObject();
        }
    }
}
