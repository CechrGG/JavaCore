package acmr.javacore.basic.io.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ServerTest {
    private final Logger logger = LoggerFactory.getLogger(ServerTest.class);
    private static final int DEFAULT_PORT = 8888;
    private final int port;
    private ServerSocket server = null;
    private static final int BACKLOG = 50;
    private final int backlog;
    private boolean wait = true;

    public ServerTest(int port, int backlog) {
        this.port = port;
        this.backlog = backlog;
    }

    public ServerTest() {
        this(DEFAULT_PORT, BACKLOG);
    }

    public int getPort() {
        return port;
    }

    public synchronized void start() {
        if(server != null) {
            logger.info("服务器已经启动，端口为：" + port);
            return;
        }
        new Thread(() -> {
            try {
                server = new ServerSocket(port, backlog);
                logger.info("服务器已经启动，端口为：" + port);
                while (wait) {
                    Socket socket = server.accept();
                    TimeUnit.SECONDS.sleep(3);
                    logger.info("客户端请求已连接，客户端IP：" + socket.getInetAddress().getHostAddress() + ",端口：" + socket.getPort() +
                            ", 服务端IP:" + socket.getLocalAddress().getHostAddress() + ", 端口：" + socket.getLocalPort());
                    new Thread(new ServerHandler(socket)).start();
                }
            } catch (IOException | InterruptedException e) {
                logger.error(e.getMessage());
            } finally {
                if(server != null) {
                    try {
                        server.close();
                    } catch (IOException e) {
                        logger.warn(e.getMessage());
                    }
                    server = null;
                }
            }
        }).start();
    }

    public synchronized void stop() {
        if(server == null) {
            logger.info("服务器已经关闭");
            return;
        }
        wait = false;
        try {
            server.close();
        } catch (IOException e) {
            logger.warn(e.getMessage());
        } finally {
            server = null;
            logger.info("服务器已经关闭");
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        ServerTest serverTest = new ServerTest();
        ServerTest serverTest1 = new ServerTest(8889, 2);
        serverTest.start();
        serverTest1.start();
        TimeUnit.SECONDS.sleep(3);
        new Thread(new ClientTest("localhost", 8888, 1)).start();
        new Thread(new ClientTest("localhost", 8889, 2)).start();
        new Thread(new ClientTest("localhost", 8889, 3)).start();
        new Thread(new ClientTest("localhost", 8889, 4)).start();
        new Thread(new ClientTest("localhost", 8889, 5)).start();
        new Thread(new ClientTest("localhost", 8889, 6)).start();
    }

}
