package acmr.javacore.basic.collection;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "rawtypes"})
public class SetTest {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add(null);
        set.add("abc");
        set.add("null");
        set.add(123);
        set.forEach((e)-> System.out.println(e));
    }
}
