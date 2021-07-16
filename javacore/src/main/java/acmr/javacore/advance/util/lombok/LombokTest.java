package acmr.javacore.advance.util.lombok;

import lombok.NonNull;

public class LombokTest {
    public static void test(@NonNull User user) {
        System.out.println(user.getName());
    }

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setName("guoguo");
        user.setAge(30);
        System.out.println(user.toString());
        LombokTest.test(user);
//        LombokTest.test(null);
    }
}
