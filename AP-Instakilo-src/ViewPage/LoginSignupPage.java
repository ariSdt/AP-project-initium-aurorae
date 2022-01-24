package ViewPage;

import UserManagment.User;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class LoginSignupPage {
    private static Scanner scanner = new Scanner(System.in);

    private static void showMenu() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignore) {
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("       Welcome to the  Instakilo!       ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Login here!");
        System.out.println("2. Don't have an account, make one!");
        System.out.println("3. Exit");

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

    public static void run(){
        User user;
        while (true){
            showMenu();
            int choice = optionChoosing();
            if (choice == 3){
                return;
            }
            if (choice == 1){
                System.out.println("----------------------------------------");
                System.out.println("Enter your user name: ");
                String userName = scanner.nextLine();
                System.out.println("Enter your password: ");
                String passWord = scanner.nextLine();
                user = User.logIn(userName, passWord);
                if (user == null){
                    System.out.println("User not found!");
                    run();
                }
                else{
                    System.out.println("++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("Welcome back " + user.getUserName() +"!");
                }
                UserPage.run(user);
            }
            else if (choice == 2){
                System.out.println("----------------------------------------");
                System.out.println("Enter a user name: ");
                String userName = scanner.nextLine();
                System.out.println("Enter a password: ");
                String passWord = scanner.nextLine();
                user = User.signUp(userName, passWord);
                if (user == null){
                    System.out.println("User name not available!");
                    run();
                }
                else{
                    System.out.println("++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("Welcome " + user.getUserName() +"!");
                }
                UserPage.run(user);
            }
        }
    }
}
