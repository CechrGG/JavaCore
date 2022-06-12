package acmr.javacore.basic.io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NServerHandler implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(NServerHandler.class);
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
                        if(key.isReadable()) {  //读处理
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);  //开辟1K缓冲区
                            int read;
                            boolean close = false;
                            while (socketChannel.read(buffer) > 0) {
                                buffer.flip();
                                byte[] bytes = new byte[buffer.remaining()];
                                buffer.get(bytes);
                                String ret = new String(bytes, StandardCharsets.UTF_8);
                                if("end".equals(ret)) {
                                    close = true;
                                } else {
                                    ret = "接收到客户端消息：" + ret;
                                }
                                byte[] result = ret.getBytes(StandardCharsets.UTF_8);
                                ByteBuffer retBuffer = ByteBuffer.allocate(result.length);
                                retBuffer.put(result);
                                retBuffer.flip();
                                socketChannel.write(retBuffer);
                                if(close)
                                    break;
                            }
                            if(close) {
                                socketChannel.close();
                                logger.warn("此链接通道已关闭");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
