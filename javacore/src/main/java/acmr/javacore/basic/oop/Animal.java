package acmr.javacore.basic.oop;

public interface Animal {
    default void move() {
        System.out.println("动物生来会跑路");
    }
    void eat();
}
