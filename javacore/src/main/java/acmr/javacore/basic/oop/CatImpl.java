package acmr.javacore.basic.oop;

public class CatImpl implements Cat {
    @Override
    public void move() {
        System.out.println("喵星人走起路来是如此优雅");
    }

    @Override
    public void eat() {
        System.out.println("喵星人干饭前必须先玩它一会儿");
    }

    @Override
    public void nocturnal() {
        System.out.println("喵星人昼伏夜出");
    }

    @Override
    public void silence() {
        System.out.println("喵星人总是悄无声息");
    }
}
