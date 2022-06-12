package acmr.javacore.advance.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class MyClientHandler extends ChannelInboundHandlerAdapter {
    private final Logger logger = LoggerFactory.getLogger(MyClient.class);
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        for(int i = 0; i < 10; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer("在下宋江，宋公明，来着可是高太尉？", StandardCharsets.UTF_8));
//            TimeUnit.SECONDS.sleep(1);
//        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        logger.info("收到对方：" + ctx.channel().remoteAddress() + "来报：" + buf.toString(StandardCharsets.UTF_8));
        ctx.close();
    }
}
