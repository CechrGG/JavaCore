package acmr.javacore.basic.thread;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class GanFanService {
    private static GanFanService instance;
    public static GanFanService getInstance() {
        if(null == instance) {
            synchronized (GanFanService.class) {
                if(null == instance) {
                    instance = new GanFanService();
                }
            }
        }
        return instance;
    }
    public static Queue<String> fanQueue = new LinkedBlockingDeque<>(100);
    public static Object lock = new Object();
    public static int count = 0;
    private final ExecutorService fanThreadPool;
    private final ExecutorService ganFanThreadPool;

    public GanFanService() {
        fanThreadPool = Executors.newFixedThreadPool(10);
        ganFanThreadPool = Executors.newCachedThreadPool();
    }

    public void startFan() {
        fanThreadPool.submit(new FanRunnable());
    }

    public void startGanFan(String name) {
        ganFanThreadPool.submit(new GanFanRunnable(name));
    }

    public synchronized int getFan() {
        return ++count;
    }

    public static void main(String[] args) throws InterruptedException {
        GanFanService.getInstance().startFan();
        Thread.sleep(1000);
        GanFanService.getInstance().startFan();
        Thread.sleep(1000);
        GanFanService.getInstance().startGanFan("guoguo1");
        Thread.sleep(1000);
        GanFanService.getInstance().startGanFan("guoguo2");
        Thread.sleep(1000);
        GanFanService.getInstance().startGanFan("guoguo3");
    }

}
