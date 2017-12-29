package me.magurmach.netty.chatapp.server;

import me.magurmach.netty.chatapp.server.chatserver.services.ChatServerAppExecutorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Shakib Ahmed on 12/24/17.
 */
public class MainClass {
    private static Logger LOG = LogManager.getLogger(MainClass.class);
    public static void main(String[] args) {
        int port = 8080;
        LOG.info("Port set to be {}", port);
        ChatServerAppExecutorService chatServerAppExecutorService = new ChatServerAppExecutorService();
        chatServerAppExecutorService.startNewServer(port);
        chatServerAppExecutorService.startPortmanService();
        chatServerAppExecutorService.shutdown();
    }
}