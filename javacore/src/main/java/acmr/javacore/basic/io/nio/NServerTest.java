package acmr.javacore.basic.io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Scanner;

public class NServerTest {
    private final Logger logger = LoggerFactory.getLogger(NServerTest.class);
    private static final int DEFAULT_PORT = 8888;
    private final int port;
    private NServerHandler handler = null;

    public NServerTest(int port) {
        this.port = port;
        handler = new NServerHandler(port);
    }

    public NServerTest() {
        this(DEFAULT_PORT);
    }

    public int getPort() {
        return port;
    }

    public synchronized void start() {
        new Thread(handler).start();
    }

    public static void main(String[] args) throws IOException {
        NServerTest test = new NServerTest();
        test.start();
        NClientHandler client = new NClientHandler("localhost", 8888);
        new Thread(client).start();
        while (true) {
            client.sendMsg(new Scanner(System.in).nextLine());
        }
    }
}
