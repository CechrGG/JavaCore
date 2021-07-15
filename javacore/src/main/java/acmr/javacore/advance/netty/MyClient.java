package acmr.javacore.advance.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MyClient {
    private final Logger logger = LogManager.getLogger(MyClient.class);
    private final String ip;
    private final int port;

    public MyClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new MyClientHandler());
                        }
                    });
            logger.info("MyClient 已经准备就绪");
            ChannelFuture future = bootstrap.connect(ip, port).sync();
            logger.info("MyClient 已经连接");
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            new MyClient("192.168.26.30", 8990).start();
        }
    }
}
