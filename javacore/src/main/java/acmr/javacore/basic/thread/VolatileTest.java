package acmr.javacore.basic.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author guoguo
 * @version 1.0.0
 * @date 2022/6/25
 * @description
 */
public class VolatileTest {
//    public static boolean stop = false; //JIT优化，不会结束
    public volatile static boolean stop = false; //正常结束
    public static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                stop = true;
                System.out.println("stop");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        while (!stop) {
//            System.out.println("Running..." + i++);   //会结束
            i++;//不会结束
        }
        System.out.println("main end");
    }
}
