package acmr.javacore.basic.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

public class FilesTest {
    public static void main(String[] args) {
        try {
            List<String> strings = Files.readAllLines(Paths.get("F:/idea/JavaCore/javacore/mark/io.md"));
            strings.forEach(System.out::println);
            for(String lines : strings) {
                Files.write(Paths.get("F:/idea/JavaCore/javacore/mark/io_bak.md"),
                        Collections.singleton(lines), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
