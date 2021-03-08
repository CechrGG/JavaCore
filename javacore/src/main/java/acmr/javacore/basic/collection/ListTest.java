package acmr.javacore.basic.collection;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for(int i = 10; i >= 0; i--) {
            list.add("" + i);
        }
        list.forEach((e)->System.out.println(e));
//        list.add(null);
//        System.out.println(list.size());
//        list.add(1, "p5");
//        System.out.println(list.size());
        list.replaceAll((e) -> e + "_test");
//        list.sort(Comparator.comparingInt(e -> e.charAt(0)));
        list.sort((e1, e2) -> e2.charAt(0)-e1.charAt(0));
        list.forEach((e)->System.out.println(e));

    }
}
