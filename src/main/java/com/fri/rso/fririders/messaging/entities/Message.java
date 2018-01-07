package com.fri.rso.fririders.messaging.entities;

import java.util.Date;

public class Message {
    private long id;
    private long sender;
    private long receiver;
    private String subject;
    private String content;
    private boolean isRead;
    private Date sent;

    public Message(){
        this.id = 0;
        this.sender = 0;
        this.receiver = 0;
        this.subject = null;
        this.content = null;
        this.isRead = false;
        this.sent = null;
    }

    public Message(long id, long sender, long receiver, String subject, String content, boolean isRead, Date sent) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.isRead = isRead;
        this.sent = sent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }
}
