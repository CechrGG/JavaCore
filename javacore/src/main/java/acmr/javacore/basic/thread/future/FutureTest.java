package acmr.javacore.basic.thread.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class FutureTest {
    private static final AtomicInteger count = new AtomicInteger(0);
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        List<Future<String>> futures = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            futures.add(executorService.submit(() -> {
                for(int j = 0; j < 100; j++) {
                    count.addAndGet(1);
                }
                return Thread.currentThread().getName() + "-" + count.get();
            }));
        }
        executorService.shutdown();
        boolean done = false;
        while (!done) {
            Thread.sleep(10);
            for (Future future : futures) {
                if (!future.isDone()) {
                    done = false;
                    break;
                } else {
                    System.out.println(future.get());
                }
                done = true;
            }
        }
        System.out.println(count.get());
    }
}
