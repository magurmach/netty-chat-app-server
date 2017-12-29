package me.magurmach.netty.chatapp.server.chatserver.services;

import com.google.common.base.Preconditions;
import io.netty.channel.Channel;
import me.magurmach.netty.chatapp.server.chatserver.mappers.HandleToClientChannelMapper;
import me.magurmach.netty.chatapp.server.chatserver.models.MailboxedOutgoingServerChatMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

/**
 * Created by Shakib Ahmed on 12/27/17.
 */
public class PostmanService implements Runnable {
    private static Logger LOG = LogManager.getLogger(PostmanService.class);

    HandleToClientChannelMapper usernameToClientChannelMapper = HandleToClientChannelMapper.getInstance();

    MailboxService targetMailboxService;

    public PostmanService(@Nonnull MailboxService targetMailboxService) {
        this.targetMailboxService = targetMailboxService;
    }

    @Override
    public void run() {
        while(true) {
            MailboxedOutgoingServerChatMessage message = targetMailboxService.takeAMessageFromMailox();
            if (message == null) {
                LOG.error("Mailboxed message found null! Stopping Postman services.");
                break;
            }

            String targetHandle = Preconditions.checkNotNull(message.getDestinationHandle(),
                    "Mailbox has mail without destination!");

            Channel targetChannel = usernameToClientChannelMapper.getChannel(targetHandle);

            targetChannel.writeAndFlush(message);
        }
    }
}
