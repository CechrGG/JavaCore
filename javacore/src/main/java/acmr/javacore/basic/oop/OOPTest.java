package acmr.javacore.basic.oop;

public class OOPTest {
    public static void main(String[] args) {
        Human human = new HumanImpl();
        Cat cat = new CatImpl();
        human.move();
        cat.move();
        human.useTools();
        cat.nocturnal();
        human.eat();
        cat.eat();
        human.logic();
        cat.silence();
        System.out.println(human.hashCode());
    }
}
