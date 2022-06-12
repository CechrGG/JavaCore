package acmr.javacore.advance.netty.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author guoguo
 * @version 1.0.0
 * @date 2022/6/12
 * @description
 */
@Slf4j
public class MyServerSocket {

    private int port = 8848;
    private ServerSocket serverSocket;

    /**
     * 服务端线程池，默认只有一个线程
     */
    ExecutorService serverThreadPool;
    /**
     * 客户端处理线程池，建一个链接添加一个线程
     */
    ExecutorService clientThreadPool;

    public MyServerSocket() {
        init();
    }

    public MyServerSocket(int port) {
        this.port = port;
        init();
    }

    private void init() {
        ThreadFactory serverThreadFactory = new ThreadFactoryBuilder().setNameFormat("Pool-%d-MyServer-Thread-%d").build();
        this.serverThreadPool = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1), serverThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        ThreadFactory clientThreadFactory = new ThreadFactoryBuilder().setNameFormat("Pool-%d-MyClient-Thread-%d").build();
        this.clientThreadPool = new ThreadPoolExecutor(5, 10, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(20), clientThreadFactory, new MyPolicy());

    }

    public void start() {
        try {
            serverSocket  = new ServerSocket(port);
            log.info("Socket 服务启动成功，端口：" + port);
            serverThreadPool.submit(() -> {
                while (true) {
                    try {
                        Socket accept = serverSocket.accept();
                        clientThreadPool.submit(new SocketThread(accept));
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.error("Socket 服务链接失败，因为：\n" + e.getMessage());
                    } catch (RejectedExecutionException e) {
                        e.printStackTrace();
                        log.warn("Socket 服务器客户处理线程池已满");
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Socket 服务启动失败，因为：\n" + e.getMessage());
        } catch (RejectedExecutionException e) {
            e.printStackTrace();
            log.warn("Socket 服务线程池已满");
        }
    }

    public void printPoolInfo() {
        log.debug(clientThreadPool.toString());
    }
}
