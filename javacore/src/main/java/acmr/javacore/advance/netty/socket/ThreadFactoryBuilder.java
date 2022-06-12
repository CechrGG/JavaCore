package acmr.javacore.advance.netty.socket;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guoguo
 * @version 1.0.0
 * @date 2022/6/12
 * @description
 */
public class ThreadFactoryBuilder implements ThreadFactory {
    private static final AtomicInteger POOL_NUMBER = new AtomicInteger(0);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private String nameFormat;

    public ThreadFactoryBuilder() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                String.format(nameFormat, POOL_NUMBER.get(), threadNumber.getAndIncrement()),
                0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

    public ThreadFactoryBuilder setNameFormat(String nameFormat) {
        this.nameFormat = nameFormat;
        return this;
    }

    public ThreadFactoryBuilder build() {
        POOL_NUMBER.incrementAndGet();
        return this;
    }
}
