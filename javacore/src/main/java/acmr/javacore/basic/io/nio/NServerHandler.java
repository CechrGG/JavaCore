package acmr.javacore.basic.io.nio;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NServerHandler implements Runnable{
    private final Logger logger = LogManager.getLogger(NServerHandler.class);
    private Selector selector = null;
    private ServerSocketChannel channel = null;
    private final int port;
    private volatile boolean running = false;

    public NServerHandler(int port) {
        this.port = port;
        try {
            selector = Selector.open();
            channel = ServerSocketChannel.open();
            channel.configureBlocking(false);   //非阻塞
            channel.socket().bind(new InetSocketAddress(port), 1024); //绑定端口, 连接队列大小
            channel.register(selector, SelectionKey.OP_ACCEPT);
            running = true;
            logger.info("服务器已启动，端口：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        try {
            while (running) {
                selector.select(1000);  //每隔一定时间唤醒一次
                //阻塞，有注册事件时才会继续
//                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    if(key.isValid()) {
                        if(key.isAcceptable()) {    //请求处理
                            ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
                            SocketChannel socketChannel = ssChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        }
                    }
                    if(key.isReadable()) {  //读处理
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);  //开辟1K缓冲区
                        int read;
                        while ((read = socketChannel.read(buffer)) != -1) {
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);
                            String ret = new String(bytes, StandardCharsets.UTF_8);
                            ret = "接收到消息：" + ret;
                            byte[] result = ret.getBytes(StandardCharsets.UTF_8);
                            ByteBuffer retBuffer = ByteBuffer.allocate(result.length);
                            retBuffer.put(result);
                            retBuffer.flip();
                            socketChannel.write(retBuffer);
                        }
                        socketChannel.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
