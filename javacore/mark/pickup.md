# 一些笔记
> 此文档是一些松散的偶然想到的一些知识点

## 1 注解Annotation
> Java 注解用于为 Java 代码提供元数据。作为元数据，注解不直接影响你的代码执行，但也有一些类型的注解实际上可以用于这一目的。    
> Java 注解是从 Java5 开始引入的。声明方式：
```java
public @interface AnnotationTest {
}
```
> 那么注解如何起作用的呢，用作什么呢，先了解一下元注解    
> 元注解顾名思义我们可以理解为注解的注解，它是作用在注解中，方便我们使用注解实现想要的功能。     
> 元注解分别有@Retention、 @Target、 @Document、 @Inherited和@Repeatable（JDK1.8加入）五种

### 1.1 @Retention 
> 保留，表示注解存在阶段，可理解为生存期，包括三种类型：
```java
public enum RetentionPolicy {
    /**
     * Annotations are to be discarded by the compiler.
     * 仅存在源码中，编译器直接忽视
     */
    SOURCE,

    /**
     * Annotations are to be recorded in the class file by the compiler
     * but need not be retained by the VM at run time.  This is the default
     * behavior.
     * 默认策略，编译到class中，但运行时并不保留
     */
    CLASS,

    /**
     * Annotations are to be recorded in the class file by the compiler and
     * retained by the VM at run time, so they may be read reflectively.
     * 运行时，可通过反射获取到，我们自定义注解如果只存着源码中或者字节码文件中就无法发挥作用，
     * 而在运行期间能获取到注解才能实现我们目的，所以自定义注解中肯定大多是使用这种方式
     */
    RUNTIME
}
``` 
### 1.2 @Target
> 目标，作用范围，包括以下类型：
```java
public enum ElementType {
    /** Class, interface (including annotation type), or enum declaration */
    TYPE,

    /** Field declaration (includes enum constants) 成员变量*/
    FIELD,

    /** Method declaration */
    METHOD,

    /** Formal parameter declaration */
    PARAMETER,

    /** Constructor declaration */
    CONSTRUCTOR,

    /** Local variable declaration */
    LOCAL_VARIABLE,

    /** Annotation type declaration */
    ANNOTATION_TYPE,

    /** Package declaration */
    PACKAGE,

    /**
     * Type parameter declaration
     */
    TYPE_PARAMETER,

    /**
     * Use of a type
     */
    TYPE_USE
}
```
### 1.3 @Documented
> 将注解中的元素包含到javadoc中
### 1.4 @Inherited
> 继承，被此注解修饰的注解修饰一个父类，则派生的子类如果没有其他注解，就继承其父类注解
### 1.5 @Repeatable
> 可重复的，被修饰的注解可作用于一个Target多次

> 注解中的接口方法就相当于注解的属性，其类型包括：
> * 基本数据类型
> * String
> * 枚举类型
> * 注解类型
> * Class类型
> * 以上类型的一维数组
> 下面以简单的例子了解一下注解的使用
> 注解使用的核心就是提取注解中的属性值，主要通过AnnotatedElement接口的几个方法
```java
public interface AnnotatedElement {

    default boolean isAnnotationPresent(Class<? extends Annotation> annotationClass);

    <T extends Annotation> T getAnnotation(Class<T> annotationClass);
    
    Annotation[] getAnnotations();
    
    default <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass);

    default <T extends Annotation> T getDeclaredAnnotation(Class<T> annotationClass);

    default <T extends Annotation> T[] getDeclaredAnnotationsByType(Class<T> annotationClass);

    Annotation[] getDeclaredAnnotations();
}
```
> 