package me.magurmach.netty.chatapp.server.exampleserver.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Shakib Ahmed on 12/24/17.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter{
    private static Logger LOG = LogManager.getLogger(DiscardServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf inputMessage = (ByteBuf) msg;
        try {
            StringBuffer strBuf = new StringBuffer();
            while (inputMessage.isReadable()) {
                strBuf.append((char) inputMessage.readByte());
            }
            LOG.info("Received message: {}", strBuf);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOG.error(cause.getMessage());
        ctx.close();
    }


}
