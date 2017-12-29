package me.magurmach.netty.chatapp.server.chatserver.services;

import me.magurmach.netty.chatapp.messages.RegistrationResponseMessage;

/**
 * Created by Shakib Ahmed on 12/28/17.
 */
public class ClientResponseService {

    public static RegistrationResponseMessage getRegistrationResponseMessage(String handle) {
        RegistrationResponseMessage registrationResponseMessage = new RegistrationResponseMessage();
        registrationResponseMessage.setMessage("You have been registered as " + handle);
        return registrationResponseMessage;
    }
}
