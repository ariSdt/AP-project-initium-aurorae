package MessageManagment;

import UserManagment.User;

import java.util.ArrayList;

public class Message {
    private User sender;
    private User reciever;

    public User getReciever() {
        return reciever;
    }

    public void setReciever(User reciever) {
        this.reciever = reciever;
    }

    private String messageText;


    public Message(User sender, User reciever, String messageText) {
        this.sender = sender;
        this.reciever = reciever;
        this.messageText = messageText;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }



    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
