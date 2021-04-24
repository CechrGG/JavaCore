package acmr.javacore.basic.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {
    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("F:\\idea\\JavaCore\\javacore\\mark\\io.md", "rw");
            file.seek(1000);    //直接定位到要读的位置
            byte[] buffer = new byte[1024];
            file.read(buffer);
            System.out.println(new String(buffer));
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
