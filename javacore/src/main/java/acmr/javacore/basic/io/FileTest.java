package acmr.javacore.basic.io;

import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class FileTest {
    public static void main(String[] args) {
        try {
            File file = new File("F:\\idea\\JavaCore\\javacore\\mark");
            File file1 = new File("F:\\idea\\JavaCore\\javacore", "test");
            System.out.println(file.toURI());
            File file2 = new File(new URI("file:/F:/idea/JavaCore/javacore/Mark/"));
            System.out.println(file.exists());
            System.out.println(file1.exists());
            if(!file1.exists()) {
                file1.mkdirs();
            }
            System.out.println(file2.exists());
            System.out.println(file.getName());
            System.out.println(file.getPath());
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getCanonicalPath());
            System.out.println((float)file.getTotalSpace()/(1024*1024*1024));
            System.out.println((float)file.getUsableSpace()/(1024*1024*1024));
            System.out.println(file.getParent());
            Arrays.asList(Objects.requireNonNull(file.list())).forEach(System.out::println);
            System.out.println(Arrays.toString(file.list()));
            Arrays.asList(Objects.requireNonNull(file.list((dir, name) -> name.toLowerCase(Locale.ROOT).endsWith(".md")))).forEach(System.out::println);
            Arrays.asList(File.listRoots()).forEach((i) -> System.out.println(i.getPath()));
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
