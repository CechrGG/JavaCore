package acmr.javacore.basic.thread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        return "我是实现Callable接口的线程";
    }
}
