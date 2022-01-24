package MessageManagment;

import UserManagment.User;

import java.util.ArrayList;

public class MessageUser {
    private static ArrayList<Message> Messages = new ArrayList<>();

    public static void sendIndividualMessage(User sender, User reciever, String messageText){
        if (reciever == null){
            System.out.println("Procedure failed! User not found!");

        }
        else {
        Message message = new Message(sender, reciever, messageText);
        Messages.add(message);

        }
    }

    public static ArrayList<Message> getMessages() {
        return Messages;
    }
}
