package acmr.javacore.basic.thread;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ThreadLocalTest {
    public static ThreadLocal local = new ThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            local.set(1);
            System.out.println(Thread.currentThread().getName() + "-" + local.get());
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-" +  local.get());
            local.set("a");
            System.out.println(Thread.currentThread().getName() + "-" +  local.get());
            local.remove();
        }).start();
    }

}
