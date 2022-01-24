package ViewPage;

import PostManagment.Post;
import UserManagment.User;
import UserManagment.UserFollowingFollower;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class HomePage {
    private static final Scanner scanner = new Scanner(System.in);

    public static void run(User user){
        System.out.println("\u001B[34m"  + "----------------------------------------");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("                  Home                  ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\u001B[0m");
        UserFollowingFollower userFollowingFollower = UserFollowingFollower.getUserFollowingFollowerAccess(user);
        ArrayList<Post> selectedPosts = new ArrayList<>();
        assert userFollowingFollower != null;
        int counter = 1;
        for (User following: userFollowingFollower.getFollowings()){
            if (Post.getAllPosts(following) != null) {
                for (Post post : Post.getAllPosts(following)) {
                    System.out.println("Post number: " + counter);
                    Post.showPost(post);
                    selectedPosts.add(post);
                    ++counter;
                }
            }
            else {
                System.out.println("\u001B[34m" + "No post available!" + "\u001B[0m");
            }
        }
        if(userFollowingFollower.getFollowings().isEmpty()){
            System.out.println("You should follow some people first!");
        }
        else {
            System.out.println("Do you want to go to the post page?    1. Yes    2. No");
            int choice = optionChoosing();
            if (choice == 1) {
                System.out.println("Enter the number of the post to go to its post page: ");
                int postNumber = optionChoosing2();
                if (postNumber >= 1 && postNumber < selectedPosts.size() + 1) {
                    PostPage.run(user, selectedPosts.get(postNumber - 1));
                }
            } else if (choice == 2) {
            }
        }
    }

    private static int optionChoosing(){
        System.out.println("Enter your choice: ");
        try{
            int option = scanner.nextInt();
            if (1 <= option && option <=2){
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

    private static int optionChoosing2(){
        System.out.println("Enter your choice: ");
        try{
            return scanner.nextInt();

        }
        catch (Exception e){
            System.out.println("\u001B[34m" + "Invalid Input!" + "\u001B[0m");
            scanner.nextLine();
        }
        return optionChoosing();

    }
}
