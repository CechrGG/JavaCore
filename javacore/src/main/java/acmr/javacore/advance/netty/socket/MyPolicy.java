package acmr.javacore.advance.netty.socket;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author guoguo
 * @version 1.0.0
 * @date 2022/6/12
 * @description
 */
public class MyPolicy implements RejectedExecutionHandler {

    public MyPolicy() {

    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        throw new RejectedExecutionException("MyClient " + r.toString() +
                " rejected from MyServer" + executor.toString());
    }
}
