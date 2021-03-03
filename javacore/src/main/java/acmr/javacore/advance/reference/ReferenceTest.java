package acmr.javacore.advance.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class ReferenceTest {
    public static void main(String[] args) {
        Object object = new Object();
//        SoftReference<Object> ref = new SoftReference<>(object);
//        WeakReference<Object> ref = new WeakReference<>(object);
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> ref = new PhantomReference<>(object, queue);
        System.out.println(object);
        System.out.println(ref);
        System.out.println(ref.get());
        object = null;
        System.out.println(ref.get());
        System.gc();
        System.out.println(object);
        System.out.println(ref.get());
        System.out.println(ref);
        System.out.println(ref.isEnqueued());
        System.out.println(queue.poll());
    }
}
