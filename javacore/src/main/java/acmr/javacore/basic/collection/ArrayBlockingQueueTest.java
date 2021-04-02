package acmr.javacore.basic.collection;

import acmr.springframework.annotation.entity.Rat;
import acmr.springframework.util.SpringAtUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueTest {
//    private final ArrayBlockingQueue<Rat> ratQue = new ArrayBlockingQueue<>(5);
    private final ArrayBlockingQueue<Rat> ratQue = new ArrayBlockingQueue<>(10, true);
    private boolean stop = false;
    //耗子工厂
    private class RatFactory extends Thread {
        @Override
        public void run() {
            String id = Thread.currentThread().getName();
            int count = 0;
            System.out.println("耗子生产工厂--" + id + "--开工了");
            while (!stop) {
                Rat rat = SpringAtUtil.getBean("rat");
                rat.setName("耗子[" + id + "]-" + count++ + "号");
                try {
                    System.out.println(rat.getName() + "长大了");
                    long begin = System.currentTimeMillis();
                    ratQue.put(rat);
//                    ratQue.offer(rat, 1, TimeUnit.SECONDS);
                    long end = System.currentTimeMillis();
                    long waitTime = (end - begin) / 1000;
                    System.out.println(rat.getName() + "等了" + waitTime + "秒终于出洞了，现在屋里有" + ratQue.size() + "只耗子");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //开始猫鼠游戏
    public void startCatAndRat(int ratCount, int catCount) {
        for(int i = 0; i < ratCount; i++) {
            new RatFactory().start();
        }
        for(int i = 0; i < catCount; i++) {
            new CatGo().start();
        }
    }
    public void stop() {
        stop = true;
    }
    //喵星人还是有所作为
    private class CatGo extends Thread {
        @Override
        public void run() {
            String id = Thread.currentThread().getName();
            int count = 0;
            System.out.println("喵星人--" + id + "--出动了");
            while (!stop) {
                try {
                    System.out.println("喵星人--" + id + "在努力抓耗子");
                    long begin = System.currentTimeMillis();
                    Rat rat = ratQue.take();
                    long end = System.currentTimeMillis();
                    long timeConsuming = (end -begin) / 1000;
                    System.out.println("喵星人--" + id + "花了" + timeConsuming + "秒逮着" + rat.getName() + "，现在还有" + ratQue.size() + "只在那浪呢");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        ArrayBlockingQueueTest test = new ArrayBlockingQueueTest();
        //三只生耗子机器和两只猫
        test.startCatAndRat(3, 1);
    }

}
