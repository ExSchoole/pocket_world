package org.exschool.pocketworld.util.builder;

import org.exschool.pocketworld.chat.model.Message;

import java.util.Date;

public class MessageBuilder {

    private String sender;
    private String recipient;
    private String message;
    private Date time;

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    public MessageBuilder message(String message){
        this.message=message;
        return this;
    }

    public MessageBuilder sender(String sender){
        this.sender = sender;
        return this;
    }

    public MessageBuilder recipient(String recipient){
        this.recipient = recipient;
        return this;
    }

    public MessageBuilder time(Date time){
        this.time = time;
        return this;
    }

    public Message build(){
        Message message = new Message();

        message.setSender(this.sender);
        message.setRecipient(this.recipient);
        message.setMessage(this.message);
        message.setTime(this.time);

        return message;
        }
}
