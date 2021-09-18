package acmr.javacore.basic.wtf;

public class User {
    public long id;
    public String name;

    public User() {
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void main(String[] args) {
        System.out.println(new User().toString());
    }

    public void main() {

    }

    public static void staticFun() {
        System.out.println("user static fun");
    }

    public static void staticFun(int i) {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
