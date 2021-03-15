package acmr.javacore.basic.io.serializable;

import java.io.*;

public class EntityTest implements Serializable, Externalizable {
    private static final long serialVersionUID = 2056797420304837250L;
    private String serializable;
    private transient String unSerializable;
    private transient String stillSerializable;

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(serializable);
        out.writeObject(stillSerializable);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        stillSerializable = (String)in.readObject();
        serializable = (String)in.readObject();
    }
}
