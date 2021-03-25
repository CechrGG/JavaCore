package acmr.javacore.basic.io.serializable;

import java.io.*;

public class SerializableTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        EntityTest test = new EntityTest();
        test.setSerializable("我是序列化属性");
        test.setUnSerializable("我就序列化不了？");
        test.setStillSerializable("看我还是序列化了");
        EntityTest.setNeverSerializable("难道我可以序列化？");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("D:\\serializable.txt")));
        out.writeObject(test);
        test.serializeStatic(out);
        out.close();
        EntityTest.setNeverSerializable("想序列化我，没门儿");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("D:\\serializable.txt")));
        EntityTest getTest = (EntityTest)in.readObject();
        getTest.deserializeStatic(in);
        System.out.println(getTest.getSerializable());
        System.out.println(getTest.getUnSerializable());
        System.out.println(getTest.getStillSerializable());
        System.out.println(EntityTest.getNeverSerializable());
    }
}
