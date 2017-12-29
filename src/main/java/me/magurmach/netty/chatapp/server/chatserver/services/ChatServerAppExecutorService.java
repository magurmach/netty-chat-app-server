package me.magurmach.netty.chatapp.server.chatserver.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Shakib Ahmed on 12/28/17.
 */
public class ChatServerAppExecutorService {
    ExecutorService executorService;

    InjectableServiceProvider injectableServiceProvider = InjectableServiceProvider.getInstance();

    public ChatServerAppExecutorService() {
        executorService = Executors.newFixedThreadPool(3);
    }

    public void submit(Runnable runnable) {
        executorService.submit(runnable);
    }

    public void startNewServer(int port) {
        executorService.submit(new ChatServerService(port));
    }

    public void startPortmanService() {
        executorService.submit(injectableServiceProvider.getPostmanService());
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
