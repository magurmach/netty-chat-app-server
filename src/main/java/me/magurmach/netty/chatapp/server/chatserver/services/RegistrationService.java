package me.magurmach.netty.chatapp.server.chatserver.services;

import io.netty.channel.Channel;
import me.magurmach.netty.chatapp.messages.RegistrationRequestMessage;
import me.magurmach.netty.chatapp.server.chatserver.mappers.HandleToClientChannelMapper;

/**
 * Created by Shakib Ahmed on 12/27/17.
 */
public class RegistrationService {

    private HandleToClientChannelMapper handleToClientChannelMapper = HandleToClientChannelMapper.getInstance();

    public void register(RegistrationRequestMessage registrationRequestMessage, Channel sourceChannel) {
        register(registrationRequestMessage.getUserHandle(), sourceChannel);
    }

    public void register(String sourceHandle, Channel sourceChannel) {
        handleToClientChannelMapper.addMapping(sourceHandle, sourceChannel);
    }

    public void unregister(String sourceHandle) {
        handleToClientChannelMapper.removeMapping(sourceHandle);
    }
}
