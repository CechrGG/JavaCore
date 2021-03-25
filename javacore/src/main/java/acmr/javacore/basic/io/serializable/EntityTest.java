package acmr.javacore.basic.io.serializable;

import java.io.*;

public class EntityTest implements Serializable, Externalizable {
    private static final long serialVersionUID = 2056797420304837250L;
    private String serializable;                //普通成员，可序列化，但如果实现Externalizable的方法中没处理也不能序列化
    private transient String unSerializable;    //transient, 不可序列化
    private transient String stillSerializable; //虽然transient修饰，但如果实现Externalizable的方法中做处理也可序列化
    private static String neverSerializable;    //static 不是对象的成员变量，不能序列化，但可以手工添加序列化方法

    public static String getNeverSerializable() {
        return neverSerializable;
    }

    public static void setNeverSerializable(String neverSerializable) {
        EntityTest.neverSerializable = neverSerializable;
    }

    public String getStillSerializable() {
        return stillSerializable;
    }

    public void setStillSerializable(String stillSerializable) {
        this.stillSerializable = stillSerializable;
    }

    public String getSerializable() {
        return serializable;
    }

    public void setSerializable(String serializable) {
        this.serializable = serializable;
    }

    public String getUnSerializable() {
        return unSerializable;
    }

    public void setUnSerializable(String unSerializable) {
        this.unSerializable = unSerializable;
    }

    public void serializeStatic(ObjectOutputStream out) throws IOException {
        out.writeObject(neverSerializable);
    }

    public void deserializeStatic(ObjectInputStream in) throws IOException, ClassNotFoundException {
        neverSerializable = (String)in.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(serializable);
        out.writeObject(stillSerializable);
//        out.writeObject(neverSerializable);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        serializable = (String)in.readObject();
        stillSerializable = (String)in.readObject();
//        neverSerializable = (String)in.readObject();
    }
}
