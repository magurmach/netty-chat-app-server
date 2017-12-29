package me.magurmach.netty.chatapp.server.chatserver.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import me.magurmach.netty.chatapp.messages.RegistrationRequestMessage;
import me.magurmach.netty.chatapp.messages.RegistrationResponseMessage;
import me.magurmach.netty.chatapp.server.chatserver.services.ClientResponseService;
import me.magurmach.netty.chatapp.server.chatserver.services.InjectableServiceProvider;
import me.magurmach.netty.chatapp.server.chatserver.services.MailboxService;
import me.magurmach.netty.chatapp.server.chatserver.services.RegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Shakib Ahmed on 12/27/17.
 */
public class ChatServerInboundMessageHandler  extends SimpleChannelInboundHandler<Object> {
    private static Logger LOG = LogManager.getLogger(ChatServerInboundMessageHandler.class);

    private InjectableServiceProvider injectableServiceProvider = InjectableServiceProvider.getInstance();
    private RegistrationService registrationService = injectableServiceProvider.getRegistrationService();
    private MailboxService mailboxService = injectableServiceProvider.getMailboxService();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        if (msg instanceof RegistrationRequestMessage) {
            RegistrationRequestMessage registrationRequestMessage = (RegistrationRequestMessage) msg;
            registrationService.register(registrationRequestMessage, channelHandlerContext.channel());
            RegistrationResponseMessage registrationResponseMessage =
                    ClientResponseService.getRegistrationResponseMessage(registrationRequestMessage.getUserHandle());
            LOG.info("User {} registered!", registrationRequestMessage.getUserHandle());
            channelHandlerContext.channel().writeAndFlush(registrationResponseMessage);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOG.error("Error occurred for [{}]: {}", ctx.channel().remoteAddress(), cause.getMessage());
        ctx.close();
    }
}
