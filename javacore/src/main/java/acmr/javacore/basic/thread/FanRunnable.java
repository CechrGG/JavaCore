package acmr.javacore.basic.thread;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FanRunnable implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(FanRunnable.class);
    private int countSelf = 0;

    @Override
    public void run() {
        System.out.println("上饭线程[" + Thread.currentThread().getName() + "]已经启动，上饭了...");
        while (true) {
//            synchronized (GanFanService.lock) {
//                int count = GanFanService.fanQueue.size() + 1;
                int count = GanFanService.getInstance().getFan();
                String fan = "第" + count + "碗";
                try {
                    if (countSelf == 20) {
                        synchronized (GanFanService.lock) {
                            System.out.println("上饭线程[" + Thread.currentThread().getName() + "]休息一会儿");
                            countSelf = 0;
                            GanFanService.lock.wait();
                        }
                    } else {
                        GanFanService.fanQueue.offer(fan);
                        countSelf++;
                        System.out.println("上饭线程[" + Thread.currentThread().getName() + "]上了" + fan);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    logger.warn(e.getMessage());
                    break;
                }
            }
//        }
    }
}
