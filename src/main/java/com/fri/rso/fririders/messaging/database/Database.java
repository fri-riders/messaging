package com.fri.rso.fririders.messaging.database;

import com.fri.rso.fririders.messaging.entities.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database {

    private static List<Message> messages = new ArrayList<>();

    public static List<Message> getMessages() {
        return messages;
    }

    // fake messages
    static {
        messages.add(new Message(1, 2, 1, "Arrival time",
                "Hi, we would like to know when can we arrive to your apartment? Best regards, Morty", false, new Date()));
        messages.add(new Message(2, 1, 2, "RE: Arrival time",
                "Hi, you can arrive any time after 14:00. Best regards, Rick", true, new Date()));
        messages.add(new Message(3, 3, 4, "Forgotten wallet",
                "Hello, when we arrived at the airport we found out that our friend has lost his wallet. " +
                        "We would like to know if he has maybe forgotten it in your apartment? Best regards, Jerry", true, new Date()));
        messages.add(new Message(4, 4, 2, "RE: Forgotten wallet",
                "Hi! Yes, we have found it under the bed! Where can we send it? Best, Summer", false, new Date()));
    }

    public static Message getMessage(int mId) {
        for (Message m: messages) {
            if (m.getId() == mId)
                return m;
        }
        return null;
    }

    public static void addMessage(Message m) throws Exception{
        try {
            messages.add(m);
        }catch (Exception e){
            throw new Exception("Error: inserting message in database failed.");
        }
    }

    public static void deleteMessage(int mId) throws Exception{
        for (Message m : messages){
            if(m.getId() == mId) {
                messages.remove(m);
                return;
            }
        }
    }

}
