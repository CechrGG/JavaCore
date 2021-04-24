package acmr.javacore.basic.io.stream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RWStreamTest {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("F:\\idea\\JavaCore\\javacore\\mark\\io.md");
            FileWriter writer = new FileWriter("F:\\idea\\JavaCore\\javacore\\mark\\io_bak.md");
            char[] buffer = new char[1024];
            int count;
            while ((count = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, count);
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
