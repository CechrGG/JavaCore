package acmr.javacore.basic.thread;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class GanFanRunnable implements Runnable{
    private final Logger logger = LogManager.getLogger(GanFanRunnable.class);
    private final String name;
    public GanFanRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("干饭人-" + name + "-开始干饭了...");
        while (true) {
//            synchronized (GanFanService.lock) {
                try {
                    String fan = GanFanService.fanQueue.poll();
                    if(null != fan) {
                        System.out.println("干饭人-" + name + "-干了" + fan);
                    } else {
                        synchronized (GanFanService.lock) {
                            System.out.println("干饭人-" + name + "-没有饭干");
                            GanFanService.lock.notifyAll();
                        }
                    }
//                    GanFanService.lock.notifyAll();
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                    break;
                }
//            }
        }
    }
}
