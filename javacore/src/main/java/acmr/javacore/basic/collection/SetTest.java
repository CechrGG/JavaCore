package acmr.javacore.basic.collection;

import acmr.javacore.basic.oop.Human;
import acmr.javacore.basic.oop.HumanImpl;
import acmr.springframework.xml.entity.CatColor;

import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "rawtypes"})
public class SetTest {
    public static void main(String[] args) {
        Set set = new LinkedHashSet();
        String str1 = new String("string1");
        String str2 = new String("string1");
//        System.out.println(str1.hashCode());
//        System.out.println(str2.hashCode());
        Human gg = new HumanImpl();
        Human qq = new HumanImpl();
//        System.out.println(gg.hashCode());
//        System.out.println(qq.hashCode());
        set.add(gg);
        set.add(qq);
        set.add(str1);
        set.add(str2);
        set.add(null);
        set.add("abc");
        set.add(null);
        set.add(123);
        set.forEach((e)-> System.out.println(e));
        Set enumSet = EnumSet.noneOf(CatColor.class);
        enumSet.add(CatColor.white);
        enumSet.add(CatColor.black);
        enumSet.add(CatColor.white);
        enumSet.forEach((e)->System.out.println(e));
    }
}
