package acmr.javacore.advance.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {
    Logger logger = LoggerFactory.getLogger(HttpServer.class);
    private final int port;
    private final int maxContentLength;

    public HttpServer(int port, int maxContentLength) {
        this.port = port;
        this.maxContentLength = maxContentLength;
    }

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)//等待处理客户端连接队列大小
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast("decoder", new HttpRequestDecoder())
                                    .addLast("encoder", new HttpResponseEncoder())
                                    .addLast("aggregator", new HttpObjectAggregator(maxContentLength))
                                    .addLast("handler", new HttpHandler());
                        }
                    });
            logger.info("HttpServer 已经准备就绪");
            ChannelFuture future = serverBootstrap.bind(port).sync();   //绑定端口并启动
            logger.info("HttpServer 已启动, 端口为" + port);
            future.channel().closeFuture().sync();  //监听通道关闭
        } catch (Exception e) {
            logger.error("HttpServer 启动异常");
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new HttpServer(8990, 1024 * 1024).start();
    }
}
