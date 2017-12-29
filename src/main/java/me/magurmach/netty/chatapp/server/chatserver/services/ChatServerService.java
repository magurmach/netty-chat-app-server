package me.magurmach.netty.chatapp.server.chatserver.services;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import me.magurmach.netty.chatapp.server.chatserver.initializer.ChatServerInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Shakib Ahmed on 12/27/17.
 */
public class ChatServerService implements Runnable {
    private static Logger LOG = LogManager.getLogger(ChatServerService.class);

    private int portToListen;

    public ChatServerService(int port) {
        this.portToListen = port;
    }

    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap chatServerBootstrap = new ServerBootstrap();
            chatServerBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChatServerInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            Channel serverChannel = chatServerBootstrap.bind(portToListen).channel();
            LOG.info("Initialized Netty based chat server for [{}]", portToListen);
            serverChannel.closeFuture().sync();
        } catch(Exception e) {
            LOG.error("Server faced exception while channel sync: {}", e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
