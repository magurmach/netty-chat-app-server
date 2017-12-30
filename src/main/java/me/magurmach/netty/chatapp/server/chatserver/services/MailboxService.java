package me.magurmach.netty.chatapp.server.chatserver.services;

import me.magurmach.netty.chatapp.server.chatserver.models.MailboxedOutgoingServerChatMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Shakib Ahmed on 12/27/17.
 */
public class MailboxService {
    private static Logger LOG = LogManager.getLogger(MailboxService.class);

    private BlockingQueue<MailboxedOutgoingServerChatMessage> messageQueue;

    public MailboxService() {
        messageQueue = new LinkedBlockingQueue<>();
    }

    public void addMessageInMailbox(MailboxedOutgoingServerChatMessage outgoingMessage) {
        try {
            messageQueue.put(outgoingMessage);
        } catch (InterruptedException e) {
            LOG.error("Message processing error in Mailbox: {}", e.getLocalizedMessage());
        }
    }

    public MailboxedOutgoingServerChatMessage takeAMessageFromMailox() {
        try {
            return messageQueue.take();
        } catch (InterruptedException e) {
            LOG.error("Message processing error in Mailbox: {}", e.getLocalizedMessage());
        }

        return null;
    }
}
