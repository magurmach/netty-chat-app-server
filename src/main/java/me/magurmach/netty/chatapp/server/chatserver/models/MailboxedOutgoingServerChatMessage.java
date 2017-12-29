package me.magurmach.netty.chatapp.server.chatserver.models;

import me.magurmach.netty.chatapp.messages.OutgoingServerChatMessage;

/**
 * Created by Shakib Ahmed on 12/27/17.
 */
public class MailboxedOutgoingServerChatMessage extends OutgoingServerChatMessage{
    private String destinationHandle;

    public MailboxedOutgoingServerChatMessage() {

    }

    public MailboxedOutgoingServerChatMessage(String destinationHandle,
                                              OutgoingServerChatMessage outgoingServerChatMessage) {
        this.destinationHandle = destinationHandle;
        this.setSourceHandle(outgoingServerChatMessage.getSourceHandle());
        this.setMessage(outgoingServerChatMessage.getMessage());
    }

    public String getDestinationHandle() {
        return destinationHandle;
    }

    public void setDestinationHandle(String destinationHandle) {
        this.destinationHandle = destinationHandle;
    }
}
