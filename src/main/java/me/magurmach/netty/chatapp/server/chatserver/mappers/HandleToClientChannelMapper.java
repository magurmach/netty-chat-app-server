package me.magurmach.netty.chatapp.server.chatserver.mappers;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Shakib Ahmed on 12/27/17.
 */
public class HandleToClientChannelMapper {
    ConcurrentHashMap<String, Channel> handleToChannelMapper;

    private static HandleToClientChannelMapper INSTANCE = null;

    private HandleToClientChannelMapper() {
        handleToChannelMapper = new ConcurrentHashMap<>();
    }

    public static synchronized HandleToClientChannelMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HandleToClientChannelMapper();
        }

        return INSTANCE;
    }

    public void addMapping(String sourceHandle, Channel sourceChannel) {
        handleToChannelMapper.put(sourceHandle, sourceChannel);
    }

    public Channel getChannel(String handle) {
        return handleToChannelMapper.get(handle);
    }

    public void removeMapping(String sourceHandle) {
        handleToChannelMapper.remove(sourceHandle);
    }
}
