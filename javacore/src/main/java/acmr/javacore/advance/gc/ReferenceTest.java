package acmr.javacore.advance.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class ReferenceTest {
    public static void main(String[] args) {
        //强引用
        Object object = new Object();
        //软引用，内存足够时不回收，不够时回收
//        SoftReference<Object> ref = new SoftReference<>(object);
        //弱引用，不管内存是否足够都回收
//        WeakReference<Object> ref = new WeakReference<>(object);
        //虚引用，几乎相当于没引用，主要用于跟踪GC状态
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
