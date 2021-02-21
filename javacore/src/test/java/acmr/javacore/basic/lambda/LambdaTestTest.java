package acmr.javacore.basic.lambda;

import org.junit.Test;

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

    }
}