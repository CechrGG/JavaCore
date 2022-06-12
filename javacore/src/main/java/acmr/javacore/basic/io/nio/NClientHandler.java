package acmr.javacore.basic.io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NClientHandler implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(NClientHandler.class);
    private final String ip;
    private final int port;
    private Selector selector = null;
    private SocketChannel channel = null;
    private boolean running = false;

    public NClientHandler(String ip, int port) {
        this.ip = ip;
        this.port = port;
        try {
            selector = Selector.open();
            channel = SocketChannel.open();
            channel.configureBlocking(false);
            running = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {
            if(!channel.connect(new InetSocketAddress(ip, port))) {
                channel.register(selector, SelectionKey.OP_CONNECT);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (running) {
            try {
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    if(key.isValid()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        if(key.isConnectable()) {
                            if(!socketChannel.finishConnect()) {
                                logger.info(key.toString() + "建立连接失败");
                                return;
                            }
                        }
                        if(key.isReadable()) {
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            StringBuffer result = new StringBuffer();
                            boolean close = false;
                            while (socketChannel.read(buffer) > 0) {
                                buffer.flip();
                                byte[] bytes = new byte[buffer.remaining()];
                                buffer.get(bytes);
                                String info = new String(bytes, StandardCharsets.UTF_8);
                                if("end".equals(info)) {
                                    close = true;
                                }
                                result.append("收到服务端消息：" + info);
                            }
                            logger.info(String.valueOf(result));
                            if(close) {
                                socketChannel.close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMsg(String msg) throws IOException {
        channel.register(selector, SelectionKey.OP_READ);
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(bytes);
        buffer.flip();
        channel.write(buffer);
    }
}
