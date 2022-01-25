package ViewPage;

import UserManagment.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Scanner;

public class LoginSignupPage {
    private static Scanner scanner = new Scanner(System.in);

    private static void showMenu() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignore) {
        }
        System.out.println("\u001B[34m"  + "----------------------------------------");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("            Hello there!                ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\u001B[0m");
        System.out.println("1. Login here!");
        System.out.println("2. Don't have an account, make one!");
        System.out.println("3. Exit");

    }

    private static int optionChoosing1(){
        try{
            int option = scanner.nextInt();
            if (10 <= option){
                scanner.nextLine();
                return option;
            }
            System.out.println("\u001B[34m" + "Option out of range! Try again!" + "\u001B[0m");
            scanner.nextLine();
            return optionChoosing();

        }
        catch (Exception e){
            System.out.println("\u001B[34m" + "Invalid Input!" + "\u001B[0m");
            scanner.nextLine();
        }
        return optionChoosing();

    }


    private static int optionChoosing(){
        System.out.println("Enter your choice: ");
        try{
            int option = scanner.nextInt();
            if (1 <= option && option <=3){
                scanner.nextLine();
                return option;
            }
            System.out.println("\u001B[34m" + "Option out of range! Try again!" + "\u001B[0m");
            scanner.nextLine();
            return optionChoosing();

        }
        catch (Exception e){
            System.out.println("\u001B[34m" + "Invalid Input!" + "\u001B[0m");
            scanner.nextLine();
        }
        return optionChoosing();

    }

    public static void run() throws NoSuchAlgorithmException {
        User user;
        while (true){
            showMenu();
            int choice = optionChoosing();
            if (choice == 3){
                return;
            }
            if (choice == 1){
                System.out.println("\u001B[34m"  + "----------------------------------------" + "\u001B[0m");
                System.out.print("Enter your user name: ");
                String userName = scanner.nextLine();
                System.out.print("Enter your password: ");
                String passWord = scanner.nextLine();
                user = User.logIn(userName, passWord);
                if (user == null){
                    System.out.println("\u001B[31m" + "User not found!" + "\u001B[0m");
                    run();
                }
                else{
                    System.out.println("\u001B[32m" + "++++++++++++++++++++++++++++++++++++++++" + "\u001B[0m");
                    System.out.println("\u001B[32m" + "Welcome back " + user.getUserName() +"!" + "\u001B[0m");
                    System.out.println("\u001B[32m" + "++++++++++++++++++++++++++++++++++++++++" + "\u001B[0m");
                }
                UserPage.run(user);
            }
            else if (choice == 2){
                System.out.println("\u001B[34m"  + "----------------------------------------" + "\u001B[0m");
                System.out.print("Enter a user name: (This is what we call your here!) ");
                String userName = scanner.nextLine();
                System.out.print("Enter a password: ");
                String passWord = scanner.nextLine();
                user = User.signUp(userName, passWord);
                if (user == null){
                    System.out.println("\u001B[31m" + "User name not available!" + "\u001B[0m");
                    run();
                }
                else{
                    System.out.println("Spread your story through the world! Enter your bio: ");
                    String Bio = scanner.nextLine();
                    user.setBio(Bio);
                    System.out.print("Enter your age: ");
                    int age = optionChoosing1();
                    user.setAge(age);
                    System.out.print("Enter your email address: ");
                    String email = scanner.nextLine();
                    user.setEmail(email);
                    System.out.print("Enter your phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("\u001B[32m" + "++++++++++++++++++++++++++++++++++++++++" + "\u001B[0m");
                    System.out.println("\u001B[32m" + "Welcome " + user.getUserName() +"!" + "\u001B[0m");
                    System.out.println("\u001B[32m" + "++++++++++++++++++++++++++++++++++++++++" + "\u001B[0m");
                }
                UserPage.run(user);
            }
        }
    }
}
