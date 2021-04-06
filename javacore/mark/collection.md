# Java Collection(集合框架)
## 1 概述
> 用于动态存放对象的容器   
> 解决数组不能满足的需求：
> * 长度可变
> * 不同类型
> > *注意：集合只能存放引用，但类型可以不同，而数组是可以存放基本数据类型和引用，但是类型必须统一*  

Collection 集合主要接口、类

![collection](image/collection/collection.png)

Map 主要接口、类

![map](image/collection/map.png)

## 2 Collection    
> 其实Collection并不是集合的顶层接口，而Iterable才是
### 2.1 Iterable接口
> 字面意思就是可迭代的，核心方法是  
> Iterator<T> iterator();   //获取迭代器，而迭代器则是为了方便迭代操作  
> 另外1.8之后还实现了两个默认方法用于Stream()
> > Iterator接口核心方法：hasNext()、next()、remove()
### 2.2 Collection接口
> Collection 接口继承了Iterable, 三个子接口如图     
> 
![list-set-queue](./image/collection/list-set-queue.png)   
*Queue虽然元素可为空，但最好不要这样做，因为poll、peek通常都有null判断*
> 包括以下自有方法：

|return|method|description|
|----|----|----|
|boolean|add(E e)|添加一个元素并确保成功返回true|
|boolean|addAll(Collection<? extends E> c)|添加指定集合中的所有元素|
|void|clear()|删除集合中所有元素|
|boolean|contains(Object o)|判断集合是否包含此元素|
|boolean|containsAll(Collection<?> c)|判断集合是否包含指定集合的所有元素|
|boolean|equals(Object o)|判断集合是否相等|
|int|hashCode()|返回此集合的hash code|
|boolean|isEmpty()|判断集合是否包含元素|
|boolean|remove(Object o)|删除集合中此元素（如果存在）|
|boolean|removeAll(Collection<?> c)|删除指定集合中包含的所有此集合的元素|
|default boolean|removeIf(Predicate<? super E> filter)|删除满足过滤条件的元素|
|boolean|retainAll(Collection<?> c)|保留指定集合中包含的此集合元素|
|int|size()|返回集合的元素数量|
|default Spliterator<E>|spliterator()|创建一个集合的分割器|
|default Stream<E>|stream()|创建一个通过上面方法创建的分割器的流|
|default Stream<E>|parallelStream()|创建一个可并行的分割器流|
|Object[]|toArray()|转换为对象数组|
|\<T\> T[]|toArray(T[] a)|转换为指定类型的对象数组|

*以上仅做简单描述，详细描述可见源码*
### 2.3 List接口
> List 接口继承了Collection，包括以下自有方法：

|return|method|description|
|----|----|----|
|void|add(int index, E element)|添加一个指定位置的元素,0<=index<=size|
|E|set(int index, E element)|替换一个指定位置的元素,返回之前的元素|
|boolean|addAll(int index, Collection<? extends E> c)|添加到指定位置的集合中的所有元素,0<=index<=size|
|E|get(int index)|获取指定位置的元素|
|int|indexOf(Object o)|包含此元素则返回第一个该元素的位置，否则为-1|
|int|lastIndexOf(Object o)|包含此元素则返回倒数第一个该元素的位置，否则为-1|
|ListIterator<E>|listIterator()|返回列表迭代器，ListIterator继承了Iterator|
|ListIterator<E>|listIterator(int index)|返回从指定位置开始的列表迭代器|
|E|remove(int index)|删除指定位置的元素|
|default void|replaceAll(UnaryOperator<E> operator)|将元素替换为满足指定操作符操作的结果|
|default void|sort(Comparator<? super E> c)|将元素按照指定比较规则进行排序|
|List\<E\>|subList(int fromIndex, int toIndex)|返回指定位置段的元素|

*以上仅做简单描述，详细描述可见源码*
> List的常用的实现为ArrayList、Vector、LinkedList    
> ![list](./image/collection/list.png)
### 2.3.1 ArrayList
> 底层原理是数组，查询快，增删慢，线程不安全     
```java
    /**
     * Default initial capacity. 默认初始化数组长度
     */
    private static final int DEFAULT_CAPACITY = 10;
```
> 核心方法，扩容   
```java 
    /**
     * Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        //这里相当于默认扩容1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
```
### 2.3.2 Vector
> * 通过方法声明添加synchronized来实现线程安全，其他跟ArrayList没太大区别
> * 初始容量也为10，扩容默认为2倍而ArrayList是1.5倍
> * 增加了一些自己的方法，包括addElement()、elementAt()、removeElement()等
> * Stack 栈继承了Vector

### 2.3.3 LinkedList
> 底层实现原理是链表, 因此增删快，查询慢     
```java
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
    
    //查询的方法，离头部近则从头部顺序查找，离尾部近则从尾部倒序查找
    
    /**
     * Returns the (non-null) Node at the specified element index.
     */
    Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
```
> 同时也实现了Queue接口，可以当队列使用，但是线程不安全

### 2.4 Set 接口
> Set 继承了Collection，没有自己独有的方法，与Collection一样 
> 常用的实现类为HashSet、TreeSet、LinkedHashSet、EnumSet  
> EnumSet用于存储枚举对象元素，其它的实现原理都是直接用对应的Map, 因此可详见Map部分

### 2.5 Queue 接口
> Queue 继承了Collection, 独有的方法包括：

|return|method|description|
|----|----|----|
|boolean|offer(E e)|添加一个元素到队列尾部|
|E|element()|获取队列头部的元素，但不删除该元素，如果队列为空则抛出异常|
|E|peek()|获取队列头部的元素，但不删除该元素，如果队列为空则返回null|
|E|poll()|获取队列头部的元素，且删除该元素，如果队列为空则返回null|
|E|remove()|获取队列头部的元素，且删除该元素，如果队列为空则抛出异常|

*以上仅做简单描述，详细描述可见源码*

> 主要的几个接口和实现类 

![queue](./image/collection/Queue.png)

> Deque 是双端队列，基本上就是在Queue的基础上增加头尾操作，源码解释为： 
> > A linear collection that supports element insertion and removal at both ends. 
> > The name deque is short for "double ended queue" and is usually pronounced "deck"

### 2.5.1 非阻塞队列 
#### 2.5.1.1 LinkedList
```java
public class QueueTest {
    public static void main(String[] args) {
        Queue<Rat> queue = new LinkedList<>();
        new Thread(()->{
            while (true) {
                queue.offer(new Rat());
                System.out.println("洞里又出来一只耗子，屋里现在有" + queue.size() + "只");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            while (true) {
                Rat rat = queue.poll();
                if(rat == null) {
                    System.out.println("耗子在哪儿");
                } else {
                    System.out.println("抓到一只耗子");
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

//        queue.forEach((e)->System.out.println(e));
    }
}
```
#### 2.5.1.2 PriorityQueue
> 优先级队列，根据元素的优先级设定（实现Comparable或者Comparator)进行排序        
> 底层实现原理是数组，二叉小顶堆   
> 无界，线程不安全的
```java
public class PriorityQueueTest {
    public static void main(String[] args) {
        //根据耗子体重进行排序的队列
        PriorityQueue<Rat> ratQue = new PriorityQueue<>(Comparator.comparing(Rat::getWeight));
        for(int i = 0; i < 10; i++) {
            Rat rat = new Rat();
            rat.setName("耗子" + i + "号");
            rat.setWeight(BigDecimal.valueOf(10 - i + Math.random()).setScale(2, BigDecimal.ROUND_HALF_UP));
            ratQue.offer(rat);
        } 
        while (!ratQue.isEmpty()) {
            Rat rat = ratQue.poll();
            System.out.println(rat.getName() + "-" + rat.getWeight());
        }
    }

}
```
#### 2.5.1.3 ConcurrentLinkedQueue
> 并发链表队列，CAS原理来实现入列和出列的线程安全，但不能保证遍历安全   
> 不能添加null元素
### 2.5.2 阻塞队列
> BlockingQueue 主要针对并发情境下以阻塞方式实现安全高效队列，继承于Queue,独有方法包括：

|return|method|description|
|----|----|----|
|void|put(E e)|添加一个元素到队列,如果没有足够空间则等待|
|boolean|offer(E e, long timeout, TimeUnit unit)|添加一个元素到队列,如果没有足够空间则等待给定的时间|
|E|take()|获取队列元素，且删除该元素，如果元素不可用则等待|
|E|poll(long timeout, TimeUnit unit)|获取队列的元素，且删除该元素，如果元素不可用则等待给定时间|
|int|remainingCapacity()|获取队列不阻塞情况下可接受的元素数量，如果没有限制则返回最大整数|
|int|drainTo(Collection<? super E> c)|删除队列所有元素，并添加到指定集合中|
|int|drainTo(Collection<? super E> c, int maxElements)|删除队列指定最大个数的元素，并添加到指定集合中|

> 下面通过几个常用的实现类来深入理解 
#### 2.5.2.1 ArrayBlockingQueue
> 数组实现的有界阻塞FIFO队列，主要属性
```java
    /** The queued items */
    /** 元素数组，底层数据结构 */
    final Object[] items;

    /** items index for next take, poll, peek or remove */
    /** 下一个可获取或者删除的元素索引 */
    int takeIndex;

    /** items index for next put, offer, or add */
    /** 下一个可添加元素的索引 */
    int putIndex;

    /** Number of elements in the queue */
    /** 队列中的元素个数 */
    int count;

    /*
     * Concurrency control uses the classic two-condition algorithm
     * found in any textbook.
     */

    /** Main lock guarding all access */
    /** 保证所有访问安全的独占锁 */
    final ReentrantLock lock;

    /** Condition for waiting takes */
    /** 等待获取条件，非空，即如果不为空即可获取 */
    private final Condition notEmpty;

    /** Condition for waiting puts */
    /** 等待添加条件，不满，即如果队列不满即可插入 */
    private final Condition notFull;

    /**
     * Shared state for currently active iterators, or null if there
     * are known not to be any.  Allows queue operations to update
     * iterator state. 迭代器
     */
    transient Itrs itrs = null;
```
> 构造方法 
```java
    /** capacity 即构造数组items[]的容量
     *  默认fair=false进行构造
     */
    public ArrayBlockingQueue(int capacity) {
        this(capacity, false);
    }
    /** 
     *  fair 重入锁的安全策略参数，true则保证公平（FIFO）;false则不保证
     */
    public ArrayBlockingQueue(int capacity, boolean fair) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        this.items = new Object[capacity];
        lock = new ReentrantLock(fair);
        notEmpty = lock.newCondition();
        notFull =  lock.newCondition();
    }
    /**
     *  c 初始化对象集合到队列中， 元素不能为空，数量不能超过队列容量，否则均抛出异常
     */
    public ArrayBlockingQueue(int capacity, boolean fair, Collection<? extends E> c) {
        this(capacity, fair);
        final ReentrantLock lock = this.lock;
        lock.lock(); // 保证可见性，而非互斥操作
        try {
            int i = 0;
            try {
                for (E e : c) {
                    checkNotNull(e);
                    items[i++] = e;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new IllegalArgumentException();
            }
            count = i;
            putIndex = (i == capacity) ? 0 : i;
        } finally {
            lock.unlock();
        }

    }
```
> 插入操作  
> add直接调用offer,offer是非阻塞的，如果队列满了直接返回，而put操作是阻塞的
```java
    public boolean offer(E e) {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (count == items.length)  //队列已满，直接返回
                return false;
            else {
                enqueue(e); //入列
                return true;
            }
        } finally {
            lock.unlock();
        }
    }
    
    //入列方法
    private void enqueue(E x) {
        // assert lock.getHoldCount() == 1;
        // assert items[putIndex] == null;
        final Object[] items = this.items;
        items[putIndex] = x;
        if (++putIndex == items.length) //添加之后入列索引已到最大值，则重置为0
            putIndex = 0;
        count++;    //队列元素个数
        notEmpty.signal();  //有元素入列，队列非空信号，消费线程可以继续了
    }
            
    public void put(E e) throws InterruptedException {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();   //可响应中断
        try {
            while (count == items.length)
                notFull.await();    //队列已满，等待释放锁，暂时不能插入
            enqueue(e);
        } finally {
            lock.unlock();
        }
    }
```
> 获取和删除操作   
> poll、peek均是非阻塞的，take是阻塞的
```java
    public E poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return (count == 0) ? null : dequeue(); //队列为空直接返回null不阻塞，否则出列
        } finally {
            lock.unlock();
        }
    }

    //出列操作
    private E dequeue() {
        // assert lock.getHoldCount() == 1;
        // assert items[takeIndex] != null;
        final Object[] items = this.items;
        @SuppressWarnings("unchecked")
        E x = (E) items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length)
            takeIndex = 0;  //如果出列索引到队列最大值，则重置为0
        count--;
        if (itrs != null)
            itrs.elementDequeued(); //更新迭代器中的元素
        notFull.signal();   //有元素出列，不满消息信号，生产线程可以继续了
        return x;
    }

    public E peek() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return itemAt(takeIndex); // null when queue is empty
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0)  //如果队列为空，则阻塞等待并释放锁，直到非空信号
                notEmpty.await();
            return dequeue();
        } finally {
            lock.unlock();
        }
    }
```
> 可以看出，既然用了BlockingQueue就使用其独有的方法，符合其阻塞的特性，否则只能当线程安全的普通队列使用     
> 简单的测试实例, 可以调节耗子工厂和猫的数量来观察线程等待状态       
> 可以印证，如果是公平等待的话生产者或或者消费者的等待时间会达到平衡，并按等待顺序唤醒
```java
public class ArrayBlockingQueueTest {
//    private final ArrayBlockingQueue<Rat> ratQue = new ArrayBlockingQueue<>(5);   
    private final ArrayBlockingQueue<Rat> ratQue = new ArrayBlockingQueue<>(5, true);  //公平等待，FIFO
    private boolean stop = false;
    //耗子工厂
    private class RatFactory extends Thread {
        @Override
        public void run() {
            String id = Thread.currentThread().getName();
            int count = 0;
            System.out.println("耗子生产工厂--" + id + "--开工了");
            while (!stop) {
                Rat rat = SpringAtUtil.getBean("rat");
                rat.setName("耗子[" + id + "]-" + count++ + "号");
                try {
                    System.out.println(rat.getName() + "长大了");
                    long begin = System.currentTimeMillis();
                    ratQue.put(rat);
                    long end = System.currentTimeMillis();
                    long waitTime = (end - begin) / 1000;
                    System.out.println(rat.getName() + "等了" + waitTime + "秒终于出洞了，现在屋里有" + ratQue.size() + "只耗子");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //开始猫鼠游戏
    public void startCatAndRat(int ratCount, int catCount) {
        for(int i = 0; i < ratCount; i++) {
            new RatFactory().start();
        }
        for(int i = 0; i < catCount; i++) {
            new CatGo().start();
        }
    }
    public void stop() {
        stop = true;
    }
    //喵星人还是有所作为
    private class CatGo extends Thread {
        @Override
        public void run() {
            String id = Thread.currentThread().getName();
            int count = 0;
            System.out.println("喵星人--" + id + "--出动了");
            while (!stop) {
                try {
                    System.out.println("喵星人--" + id + "在努力抓耗子");
                    long begin = System.currentTimeMillis();
                    Rat rat = ratQue.take();
                    long end = System.currentTimeMillis();
                    long timeConsuming = (end -begin) / 1000;
                    System.out.println("喵星人--" + id + "花了" + timeConsuming + "秒逮着" + rat.getName() + "，现在还有" + ratQue.size() + "只在那浪呢");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        ArrayBlockingQueueTest test = new ArrayBlockingQueueTest();
        //三只生耗子机器和两只猫
        test.startCatAndRat(3, 1);
    }
}
```
#### 2.5.2.2 DelayQueue
> DelayQueue 延时队列，底层通过PriorityQueue实现，按时间顺序排列的无界数组安全队列      
> 元素必须实现Delayed接口，而Delayed接口继承于Comparable
```java
public class DelayQueue<E extends Delayed> extends AbstractQueue<E>
    implements BlockingQueue<E> {

    private final transient ReentrantLock lock = new ReentrantLock();
    private final PriorityQueue<E> q = new PriorityQueue<E>();

    /**
     * 等待第一个元素的线程.
     */
    private Thread leader = null;

    /**
     * Condition signalled when a newer element becomes available
     * at the head of the queue or a new thread may need to
     * become leader.
     */
    private final Condition available = lock.newCondition();
}
```
> 从DelayQueue中取元素时，只能取到满足Delayed接口getDelay()方法小于0的元素
```java
public class DelayQueueTest {

    private final DelayQueue<GameAddict> gamerQue = new DelayQueue<>();
    private final boolean stop = false;

    private class GameAddict implements Delayed {
        private final String name;
        //都以毫秒为单位
        private final long gameTime;  //游戏开始时间
        private final long addictTime = 20 * 1000;    //游戏成瘾时间，到了这个时间就需要提醒游戏者了

        public GameAddict(String name, long letsPlayAGame) {
            this.name = name;
            gameTime = letsPlayAGame;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long stopTime = gameTime + addictTime - System.currentTimeMillis();
            return unit.convert(stopTime, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int)(this.gameTime / 1000 - ((GameAddict)o).gameTime / 1000);
        }
    }

    public void letsPlayAGame() {
        new Thread(()-> {
            System.out.println("适度游戏益脑，沉迷游戏伤身");
            while (!stop) {
                try {
                    GameAddict gamer = gamerQue.take();
                    System.out.println(gamer.name + ",适度游戏益脑，沉迷游戏伤身");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()-> {
            int count = 0;
            while (!stop) {
                GameAddict gameAddict = new GameAddict("大兄弟" + count++ + "号", System.currentTimeMillis());
                System.out.println(gameAddict.name + "开启了疯狂游戏时间");
                try {
                    gamerQue.put(gameAddict);
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        DelayQueueTest test = new DelayQueueTest();
        test.letsPlayAGame();
    }
}
```
#### 2.5.2.3 PriorityBlockingQueue
> 基于优先级调度的无界阻塞队列，在PriorityQueue的基础上实现线程安全阻塞
#### 2.5.2.4 LinkedBlockingQueue
> 基本特性跟ArrayBlockingQueue一样，只是底层原理为有界（默认为Integer.MAX_VALUE）单向链表，原理上不再赘述     
> LinkedBlockingDeque为"双向链表"形式
#### 2.5.2.5 SynchronousQueue
> 同步队列，是一个非常特殊的队列，它实际不存储元素，通常应用于线程池，线程池相关详解中再做剖析       
> 这里给一个简单的例子了解一下, 耗子出洞之后就等着（WAIT)，直到被抓线程才结束
```java
public class SynchronousQueueTest {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Rat> ratQue = new SynchronousQueue<>();
        new Thread(() -> {
            Rat rat = new Rat();
            rat.setName("耗子--" + Thread.currentThread().getName());
            try {
                System.out.println(rat.getName() + "出洞了");
                ratQue.put(rat);
                System.out.println(rat.getName() + "被逮着了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        TimeUnit.SECONDS.sleep(10);
        System.out.println("屋里有老鼠了，快叫黑猫警长");
        new Thread(() -> {
            try {
                Rat rat = ratQue.take();
                System.out.println("黑猫警长真厉害，一出手就把[" + rat.getName() + "]给逮着了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
```
#### 2.5.2.6 TransferQueue
> TransferQueue 传输队列，是1.7引进的继承于BlockingQueue的接口，其实看了SynchronousQueue源码会发现里面已经引入了这个原理    
> 核心就是增加了transfer方法，用于传递生产消费线程之间的元素     
> 目前唯一的实现类为LinkedTransferQueue，实际上就是ConcurrentLinkedQueue、SynchronousQueue（公平模式）和LinkedBlockingQueue的超集。    
> 而且LinkedTransferQueue更好用，因为它不仅仅综合了这几个类的功能，同时也提供了更高效的实现。底层是单向链表及各种CAS,比较复杂，暂时先了解。

## 3 Map
> Map 是一种键值对映射（key-value）的数据结构,键不可重复，值可以重复，一个键最多映射一个值       
> Java Map是顶级接口，主要方法包括：

|return|method|description|
|----|----|----|
|int|size()|返回Map中的元素数量|
|boolean|isEmpty()|判断Map中是否有元素|
|boolean|containsKey(Object key)|判断Map中是否包含指定的key|
|boolean|containsValue(Object value)|判断Map中是否包含指定的value|
|V|get(Object key)|根据指定的key获取对应的value|
|V|put(K key, V value)|添加一个key-value键值对|
|V|remove(Object key)|删除一个指定key的元素|
|void|putAll(Map<? extends K, ? extends V> m)|将给定的Map全部添加到当前Map中|
|Set<K>|keySet()|返回所有Key的集合, key是Set集合满足不重复的特点|
|Collection<V>|values()|返回所有Value的集合，value是Collection集合满足可重复特点|
|Set<Map.Entry<K, V>>|entrySet()|返回所有键值对的集合，Entry是Map的内部接口，用于存放key-value|
|default V|getOrDefault(Object key, V defaultValue)|如果有key对应的值则返回value,否则返回defaultValue|
|default void|forEach(BiConsumer<? super K, ? super V> action)|根据某种条件遍历Map中的元素|
|default void|replaceAll(BiFunction<? super K, ? super V, ? extends V> function)|根据某种条件替换Map中的键值|
|default V|putIfAbsent(K key, V value)|当key没有对应的值或者对应的值是null时，才映射给定的值，否则返回给定的值|
|default boolean|remove(Object key, Object value)|删除指定的键值对，仅当key对应的值与value相等时|
|default boolean|replace(K key, V oldValue, V newValue)|更新键值对，仅当key对应的值与oldValue相等时|
|default boolean|replace(K key, V value)|更新键值对，仅当key有对应的值时|
|default V|computeIfAbsent(K key,Function<? super K, ? extends V> mappingFunction)|根据key计算value值并关联到key,仅当key对应的为null时|
|default V|computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)|根据key-value计算新value值并关联到key,仅当key有对应的值时，如果新值为null则删除该key|
|default V|compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)|根据key计算value值并关联到key,新值为null并且老值存在时删除该key|
|default V|merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)|根据oldValue为null则直接关联value到key,否则根据oldvalue和value计算新值并关联到key,如果新值为null则删除该key|

> 此外Map内部还有一个Entry接口，用于处理key-value

|return|method|description|
|----|----|----|
|K|getKey()|返回key|
|V|getValue()|返回value|
|V|setValue(V value)|赋值value|
*还有几个根据获取key、value顺序比较器的静态方法不再赘述*

> Map 常用的实现类是HashMap、TreeMap和HashTable      
> 先通过HashMap简单了解一下Map的使用
```java
public class MapTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(null, null);
        map.put(null, "null");
        map.put(-1, null);
        for(int i = 0; i < 10; i++) {
            map.put(i, i + "v");
        }
        System.out.println(map.size());
        map.compute(10, (k, v) -> k + v);
        map.computeIfAbsent(10, Object::toString);
        map.computeIfAbsent(20, (k) -> k + "v");
        map.computeIfPresent(null, (k, v) -> k + v);
        map.merge(5, "wu", (ov, nv) -> ov + nv);
        map.replaceAll((k, v) -> k + v);
        map.forEach((k,v) -> System.out.println(k + "--" + v));
    }
}
```

### 3.1 HashMap

```java
public class HashMap<K,V> extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable {

    /**
     * The default initial capacity - MUST be a power of two.
     * 默认数组大小16，必须是2的整数次幂
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     * 加载因子，默认0.75，用于判断是否扩容
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The bin count threshold for using a tree rather than list for a
     * bin.  Bins are converted to trees when adding an element to a
     * bin with at least this many nodes. The value must be greater
     * than 2 and should be at least 8 to mesh with assumptions in
     * tree removal about conversion back to plain bins upon
     * shrinkage.
     * 链表大于这个阈值后转化为红黑树
     */
    static final int TREEIFY_THRESHOLD = 8;

    /**
     * The bin count threshold for untreeifying a (split) bin during a
     * resize operation. Should be less than TREEIFY_THRESHOLD, and at
     * most 6 to mesh with shrinkage detection under removal.
     * 树节点小于这个阈值后转化为链表
     */
    static final int UNTREEIFY_THRESHOLD = 6;

    /**
     * The smallest table capacity for which bins may be treeified.
     * (Otherwise the table is resized if too many nodes in a bin.)
     * Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts
     * between resizing and treeification thresholds.
     * 链表转化为树的最小容量，如果链表增加太长，但是数组长度还没有达到这个值，则数组扩容
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    /**
     * Basic hash bin node, used for most entries.  (See below for
     * TreeNode subclass, and in LinkedHashMap for its Entry subclass.)
     */
    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    /* ---------------- Static utilities -------------- */

    /**
     * 重新计算hash,让高位也参与运算
     * 因为索引是通过(table.length-1)&hash计算的
     * 当length较小时&运算让hash的高位毫无作为，使得碰撞发生概率更大，因此引入这个计算
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * Returns a power of two size for the given target capacity.
     * 根据参数容量计算表的大小，比如13->16;20->32,位运算效率高，很巧妙
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /* ---------------- Fields -------------- */

    /**
     * The table, initialized on first use, and resized as
     * necessary. When allocated, length is always a power of two.
     * (We also tolerate length zero in some operations to allow
     * bootstrapping mechanics that are currently not needed.)
     * 存放节点的表，是一个数组
     */
    transient Node<K,V>[] table;

    /**
     * Holds cached entrySet(). Note that AbstractMap fields are used
     * for keySet() and values().
     * 所有映射的集合
     */
    transient Set<Map.Entry<K,V>> entrySet;

    /**
     * The number of key-value mappings contained in this map.
     * 映射的集合大小
     */
    transient int size;

    /**
     * The number of times this HashMap has been structurally modified
     * Structural modifications are those that change the number of mappings in
     * the HashMap or otherwise modify its internal structure (e.g.,
     * rehash).  This field is used to make iterators on Collection-views of
     * the HashMap fail-fast.  (See ConcurrentModificationException).
     * 结构变化次数
     */
    transient int modCount;

    /**
     * The next size value at which to resize (capacity * load factor).
     * 下一个扩容的容量大小
     * @serial
     */
    // (The javadoc description is true upon serialization.
    // Additionally, if the table array has not been allocated, this
    // field holds the initial array capacity, or zero signifying
    // DEFAULT_INITIAL_CAPACITY.)
    int threshold;

    /**
     * The load factor for the hash table.
     * 加载因子
     * @serial
     */
    final float loadFactor;

    /* ---------------- Public operations -------------- */

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and load factor.
     *
     * @param  initialCapacity the initial capacity
     * @param  loadFactor      the load factor
     * @throws IllegalArgumentException if the initial capacity is negative
     *         or the load factor is nonpositive
     */
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param  initialCapacity the initial capacity.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     */
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }

    /**
     * Constructs a new <tt>HashMap</tt> with the same mappings as the
     * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
     * default load factor (0.75) and an initial capacity sufficient to
     * hold the mappings in the specified <tt>Map</tt>.
     *
     * @param   m the map whose mappings are to be placed in this map
     * @throws  NullPointerException if the specified map is null
     */
    public HashMap(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }
}
```
> 几个核心方法的源码解析   
```java
    /**
     * Implements Map.put and related methods.
     *
     * @param hash hash for key
     * @param key the key
     * @param value the value to put
     * @param onlyIfAbsent if true, don't change existing value
     * @param evict if false, the table is in creation mode.
     * @return previous value, or null if none
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```
