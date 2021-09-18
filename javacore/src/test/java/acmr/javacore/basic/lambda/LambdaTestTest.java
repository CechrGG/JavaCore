package acmr.javacore.basic.lambda;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LambdaTestTest {
    interface SaySomething{
        void sayIt(String string);
    }

    @Test
    public void testLambda() {
        SaySomething test = (String string)->System.out.println("hello" + string);
        test.sayIt(" world");
    }

    @Test
    public void testCollection() {
        List<Integer> array = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            array.add(i);
        }
/*
        array.forEach(integer -> {
            int count = integer;
            if(integer > 5) {
                count = integer%5;
            }
            System.out.println(count);
        });
*/

        array.stream().filter(integer -> integer > 10).forEach(System.out::println);
    }

    @Test
    public void test() {
        double rat = 1.5;
        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
        int[] test = new int[]{1,2,3};
        "123".length();
    }

    @Test
    public void testBreak() {
        go:
        for(int i = 0; i < 100; i++) {
            System.out.println(i);
            for(int j = 0; j < 100; j++) {
                System.out.println(i + ":" + j);
                if(j == 22) {
                    break go;
                }
            }
        }
        System.out.println("go");
    }
}