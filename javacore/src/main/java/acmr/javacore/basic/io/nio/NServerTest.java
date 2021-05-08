package acmr.javacore.basic.io.nio;

import acmr.javacore.basic.io.bio.ServerHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NServerTest {
    private final Logger logger = LogManager.getLogger(acmr.javacore.basic.io.bio.ServerTest.class);
    private static final int DEFAULT_PORT = 8888;
    private final int port;
    private final NServerHandler handler = null;

    public NServerTest(int port) {
        this.port = port;
    }

    public NServerTest() {
        this(DEFAULT_PORT);
    }

    public int getPort() {
        return port;
    }

    public synchronized void start() {
        if(handler != null) {
            logger.info("服务器已经启动，端口为：" + port);
            return;
        }
    }

    public synchronized void stop() {
    }
}
