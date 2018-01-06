package com.fri.rso.fririders.messaging.resources;


import com.fri.rso.fririders.messaging.entities.Message;
import com.fri.rso.fririders.messaging.entities.User;
import com.fri.rso.fririders.messaging.services.MessagingBean;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("messages")
public class MessagingResource {

    private Logger logger = LogManager.getLogger( MessagingResource.class.getName() );

    @Inject
    private MessagingBean messagingBean;


    @GET
    public Response getAllMessages() {
        logger.info("REST CALL: getAllMessages");
        List<Message> messages = messagingBean.getAllMessages();
        if(messages != null && messages.size() > 0) {
            logger.info("Found "+ messages.size() + " messages.");
            return Response.ok(messages).build();
        }
        else {
            logger.error("Zero messages found");
            return Response.status(Response.Status.NOT_FOUND).entity("Zero messages found.").build();
        }
    }


    @GET
    @Path("/{messageId}")
    public Response getMessage(@PathParam("messageId") int mId) {
        logger.info("REST CALL: getMessage.");
        Message m = messagingBean.getMessage(mId);
        if(m != null) {
            return Response.ok(m).build();
        }
        else
            return Response.status(Response.Status.NOT_FOUND).entity("Message with id " + mId + " not found.").build();
    }


    @GET
    @Path("/{messageId}/sender")
    public Response getMessageSender(@PathParam("messageId") int mId) {
        logger.info("REST CALL: getMessageSender.");
        try {
            User sender = messagingBean.getMessageSender(mId);
            if (sender != null) {
                logger.info("Sender with id" + sender.getUuid() + " successfully retrieved ...");
                return Response.ok(sender).build();
            } else
                return Response.status(Response.Status.NOT_FOUND).entity("Sender of requested message not found.").build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{userId}/sent")
    public Response getSentMessages(@PathParam("userId") int uId) {
        logger.info("REST CALL: getSentMessages.");
        try {
            List<Message> messages = messagingBean.getUserMessages(uId, true);
            if (messages != null) {
                logger.info("Sent messages for user " + uId + " successfully retrieved ...");
                return Response.ok(messages).build();
            } else
                return Response.status(Response.Status.NOT_FOUND).entity("Sent messages for requested user not found.").build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{userId}/received")
    public Response getReceivedMessages(@PathParam("userId") int uId) {
        logger.info("REST CALL: getReceivedMessages.");
        try {
            List<Message> messages = messagingBean.getUserMessages(uId, false);
            if (messages != null) {
                logger.info("Received messages for user " + uId + " successfully retrieved ...");
                return Response.ok(messages).build();
            } else
                return Response.status(Response.Status.NOT_FOUND).entity("Received messages for requested user not found.").build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response sendMessage(Message m){
        logger.info("REST CALL: sendMessage.");
        try {
            messagingBean.sendMessage(m);
            return Response.ok("Message successfully sent.").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
