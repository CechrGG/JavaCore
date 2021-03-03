package acmr.javacore.basic.entry;

public class StringTest {
    public static void main(String[] args) {
        String s1 = "abc";
        String s6 = "abc";
        String a = "a";
        String s7 = "a" + "bc";
        String s8 = a + "bc";
        String s2 = new String("abc");
        String s5 = new String("abc");
        String s3 = new StringBuffer("abc").toString();
        StringBuilder s4 = new StringBuilder("abc");
        System.out.println(s1 == s2);
        System.out.println(s1 == s6);
        System.out.println(s1 == s7);
        System.out.println(s1 == s8);
        System.out.println(s2 == s5);
        System.out.println(s1 == s3);
        System.out.println(s1.equals(s4));
    }
}
