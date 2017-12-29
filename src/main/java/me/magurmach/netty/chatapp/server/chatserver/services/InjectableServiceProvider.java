package me.magurmach.netty.chatapp.server.chatserver.services;

/**
 * Created by Shakib Ahmed on 12/28/17.
 */
public class InjectableServiceProvider {
    private MailboxService mailboxService;
    private PostmanService postmanService;
    private RegistrationService registrationService;

    private static InjectableServiceProvider INSTANCE = new InjectableServiceProvider();

    private InjectableServiceProvider() {
        this.registrationService = new RegistrationService();
        this.mailboxService = new MailboxService();
        this.postmanService = new PostmanService(this.mailboxService);
    }

    public static synchronized InjectableServiceProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InjectableServiceProvider();
        }

        return INSTANCE;
    }

    public MailboxService getMailboxService() {
        return mailboxService;
    }

    public PostmanService getPostmanService() {
        return postmanService;
    }

    public RegistrationService getRegistrationService() {
        return registrationService;
    }
}
