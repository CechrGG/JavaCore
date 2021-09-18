package acmr.javacore.basic.wtf;

public class Admin extends User{
    public Admin(long id, String name) {
        super(id, name);
    }

    @Override
    public void main() {
        super.main();
    }

    public static void staticFun() {
        System.out.println("admin static fun");
    }

    public static void main(String[] args) {
        Admin.staticFun();
        Admin admin = new Admin(1, "guo");
    }
}
