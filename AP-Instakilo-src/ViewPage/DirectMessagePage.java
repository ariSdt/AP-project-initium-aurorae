package ViewPage;

import MessageManagment.Message;
import MessageManagment.MessageUser;
import UserManagment.User;

import java.io.IOException;
import java.util.Scanner;

public class DirectMessagePage {
    private static Scanner scanner = new Scanner(System.in);
    private static void showMenu() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignore) {
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("          Direct message page           ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Send message");
        System.out.println("2. See received message");
        System.out.println("3. Go back");
    }

    private static int optionChoosing(){

        System.out.println("Enter your choice: ");
        try{
            int option = scanner.nextInt();
            if (1 <= option && option <=3){
                scanner.nextLine();
                return option;
            }
            System.out.println("Option out of range! Try again: ");
            scanner.nextLine();
            return optionChoosing();

        }
        catch (Exception e){
            System.out.println("Invalid Input!");
            scanner.nextLine();
        }
        return optionChoosing();

    }

    public static void run(User user){
        while (true){
            showMenu();
            int choice = optionChoosing();
            if (choice == 3){
                return;
            }
            if (choice == 1){
                System.out.println("Enter the user name you want to send the message to: ");
                String userName = scanner.nextLine();
                System.out.println("Enter the messageText: ");
                String messageText = scanner.nextLine();
                MessageUser.sendIndividualMessage(user, User.searchByUsername(userName), messageText);
            }
            else if (choice == 2) {
                if (MessageUser.getMessages().isEmpty()) {
                    System.out.println("No messages yet!");
                } else {
                    for (Message message : MessageUser.getMessages()){
                        if (message.getReciever().equals(user)){
                            System.out.println(message.getSender().getUserName() + ": " + message.getMessageText());
                        }
                    }
                }
            }
        }
    }
}
