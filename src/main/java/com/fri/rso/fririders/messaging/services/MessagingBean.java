package com.fri.rso.fririders.messaging.services;


import com.fri.rso.fririders.messaging.database.Database;
import com.fri.rso.fririders.messaging.entities.Message;
import com.fri.rso.fririders.messaging.entities.User;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.discovery.enums.AccessType;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class MessagingBean {

    private Logger logger = LogManager.getLogger(MessagingBean.class.getName());

    private static Client client = ClientBuilder.newClient();

    @Inject
    @DiscoverService(value="users", version = "1.0.x", environment = "dev")
    private Optional<String> usersUrl;

    public List<Message> getAllMessages(){
        List<Message> messages = Database.getMessages();
        if(messages != null && messages.size() > 0) {
            logger.info("Found "+ messages.size() + " messages.");
            return messages;
        }
        else {
            logger.error("Zero messages found");
            return new ArrayList<>();
        }
    }

    public Message getMessage(int mId) {
        Message m = Database.getMessage(mId);
        return m;
    }

    public User getMessageSender(int mId) {
        logger.info("IS URL: " + this.usersUrl.isPresent());
        logger.info("URL: " + this.usersUrl.get());
        if (this.usersUrl.isPresent()){
            Message m = this.getMessage(mId);
            if(m != null) {
                try {
                    long senderId = m.getSender();
                    logger.info("Calling user service ...");
                    String url = this.usersUrl.get() + "/v1/users";
                    logger.info("URL: " + url);

                    //find info about user
                    List<User> users =
                            client.target(url)
                                    .request(MediaType.APPLICATION_JSON)
                                    .get((new GenericType<List<User>>() {
                                    }));

                    if (users != null || users.size() != 0) {
                        logger.info("User with id" + senderId + " successfully retrieved ...");
                        for(int i = 0; i < users.size(); i++){
                            if (i == senderId)
                                return users.get(i);
                        }
                    }
                    return null;
                }
                catch (WebApplicationException | ProcessingException e) {
                    logger.error(e);
                    throw new InternalServerErrorException(e);
                }
            }
            else
                return null;
        }
        else{
            logger.error("Users service not reachable.");
            return null;
        }
    }


    public void sendMessage(Message m) throws Exception{
        Database.addMessage(m);
    }


}
