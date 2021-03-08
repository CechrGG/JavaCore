# JAVA Collection(集合框架)
## 1 概述
> 用于动态存放对象的容器   
> 解决数组不能满足的需求：
> * 长度可变
> * 不同类型
> > *注意：集合只能存放引用类型，而数组是可以存放基本类型和引用类型，但是类型必须统一*  

Collection 集合主要接口、类

![collection](image/collection/collection.png)  
*图片中的顶层接口错了，应该是Iterable而非Iterator*

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
> ![list-set-queue](./image/collection/list-set-queue.png)
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


### 2.4 Set 接口
> Set 继承了Collection，没有自己独有的方法，与Collection一样
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


