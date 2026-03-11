package sesion01.bt6;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    public static void saveToFile(String data) throws IOException {
        if (data == null || data.isEmpty()) {
            throw new IOException("Dữ liệu rỗng, không thể ghi file!");
        }

        try (FileWriter writer = new FileWriter("user.txt")) {
            writer.write(data);
        }
    }
}
