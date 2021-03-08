package acmr.javacore.basic.oop;

public class HumanImpl implements Human {
    @Override
    public void useTools() {
        System.out.println("人类的终极奥秘是创造和使用工具");
    }

    @Override
    public void logic() {
        System.out.println("人类的获得终极奥秘的方法是逻辑思维");
    }

    @Override
    public void eat() {
        System.out.println("就干饭来说人类和动物别无二致");
    }
}
