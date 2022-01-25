package ViewPage;

import PostManagment.Post;
import UserManagment.User;
import UserManagment.UserFollowingFollower;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static UserManagment.User.searchByUsername;

public class UserPage {
    private static Scanner scanner = new Scanner(System.in);

    private static void showMenu() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignore) {
        }
        System.out.println("\u001B[34m"  + "----------------------------------------");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("               Instakilo                ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\u001B[0m");
        System.out.println("1. Make a post");
        System.out.println("2. See my posts");
        System.out.println("3. Follow or unfollow a user");
        System.out.println("4. See my followings' posts. / See Home page.");
        System.out.println("5. Direct message someone");
        System.out.println("6. Search user");
        System.out.println("7. Block or unblock a user");
        System.out.println("8. See posts I've been tagged on");
        System.out.println("9. Exit");

    }

    private static int optionChoosing(){
        System.out.println("Enter your choice: ");
        try{
            int option = scanner.nextInt();
            if (1 <= option && option <=9){
                scanner.nextLine();
                return option;
            }
            System.out.println("\u001B[31m" + "Option out of range! Try again!" + "\u001B[0m");
            scanner.nextLine();
            return optionChoosing();

        }
        catch (Exception e){
            System.out.println("\u001B[31m" + "Invalid Input!" + "\u001B[0m");
            scanner.nextLine();
        }
        return optionChoosing();
    }

    private static int optionChoosing1(){
        System.out.println("Enter your choice: ");
        try{
            int option = scanner.nextInt();
            if (1 <= option && option <=2){
                scanner.nextLine();
                return option;
            }
            System.out.println("\u001B[31m" + "Option out of range! Try again!" + "\u001B[0m");
            scanner.nextLine();
            return optionChoosing();

        }
        catch (Exception e){
            System.out.println("\u001B[31m" + "Invalid Input!" + "\u001B[0m");
            scanner.nextLine();
        }
        return optionChoosing();
    }

    public static void run(User user){
        while (true){
            showMenu();
            int choice = optionChoosing();
            if (choice == 9){
                return;
            }
            //Post making//////////////////////////////////////////////////////////////////////////////////////////////
            if (choice == 1){
                System.out.println("Enter your post caption: ");
                String postCaption = scanner.nextLine();
                System.out.println("Enter your media address: (from gallery: C:\\Users\\Nik\\Desktop)");
                String mediaAddress = scanner.nextLine();
                mediaAddress = "C:\\Users\\Nik\\Desktop" + "\\" + mediaAddress;
                Post post = new Post(user, postCaption, mediaAddress);
                Post.getPosts().add(post);
                System.out.println("Do you want to tag people to your post? 1. Yes 2. No");
                int answer = optionChoosing1();
                if (answer == 1){
                    String userToTag;
                    while (true){
                        System.out.println("Enter the name of the user you want to tag to this post: ");
                        userToTag = scanner.nextLine();
                        if (searchByUsername(userToTag) != null){
                            post.tag(searchByUsername(userToTag));
                            System.out.println("Tag more users? 1. Yes 2. No");
                            answer = optionChoosing1();
                            if (answer == 2){
                                break;
                            }
                        }
                        else {
                            System.out.println("\u001B[31m" + "User not found!" + "\u001B[0m");
                            System.out.println("Tag more users? 1. Yes 2. No");
                            answer = optionChoosing1();
                            if (answer == 2){
                                break;
                            }
                        }

                    }
                }
            }
            //Seeing my posts //////////////////////////////////////////////////////////////////////////////////////////
            else if (choice == 2){
                for (Post post: Post.getAllPosts(user)){
                    Post.showPost(post);
                }
            }
            //Follow  unfollow user ////////////////////////////////////////////////////////////////////////////////////
            else if (choice == 3){
                int counter = 1;
                for (User user1: User.getUsers()){
                    if (user1 != user && !(Objects.requireNonNull(user.getFollowings()))
                            .contains(user1)){
                        System.out.println(counter + ". " + user1.getUserName());
                        ++counter;
                    }
                }
/*                if (counter == 1){
                    System.out.println("\u001B[34m" + "Sorry there are no other users available to follow!" + "\u001B[0m");
                }
                else {*/
                    System.out.println("Enter the name of the user you want to follow: (Pick from the above list.)");
                    String userName = scanner.nextLine();
                    if (searchByUsername(userName) != null){
                        user.follow(searchByUsername(userName));
                    }
                    else{
                        System.out.println("\u001B[31m" + "User not found!" + "\u001B[0m");
                    }
                //}
            }

            //Go to home page //////////////////////////////////////////////////////////////////////////////////////////
            else if (choice == 4){
                HomePage.run(user);
            }
            //Send direct message //////////////////////////////////////////////////////////////////////////////////////
            else if (choice == 5){
                DirectMessagePage.run(user);
            }
            // Search user /////////////////////////////////////////////////////////////////////////////////////////////
            else if (choice == 6){
                System.out.println("Enter user name: ");
                String userName = scanner.nextLine();
                User targetUser = User.searchByUsername(userName);
                if (targetUser == null){
                    System.out.println("\u001B[31m" + "User not found!" + "\u001B[0m");
                }
                else{
                    if ((Objects.requireNonNull(searchByUsername(userName))).getBlocks().contains(user)){
                        System.out.println("You've been blocked by this user!");
                    }
                    else{
                        System.out.println("\u001B[34m"  + "----------------------------------------" + "\u001B[0m");
                        System.out.println("User: " + targetUser.getUserName());
                        System.out.println("Follower: " + targetUser.getFollowers().size());
                        System.out.println("Following: " + targetUser.getFollowings().size());
                        ArrayList<Post> userAllPosts = Post.getAllPosts(targetUser);
                        System.out.println("Post: " + userAllPosts.size());
                        for (Post userPost: userAllPosts){
                            Post.showPost(userPost);
                        }
                        System.out.println("\u001B[34m"  + "----------------------------------------" + "\u001B[0m");
                    }
                }
            }
            //Block unblock user ///////////////////////////////////////////////////////////////////////////////////////
            else if (choice == 7) {
                System.out.println("Enter user name: ");
                String userName = scanner.nextLine();
                User targetUser = User.searchByUsername(userName);
                if (targetUser == null) {
                    System.out.println("\u001B[31m" + "User not found!" + "\u001B[0m");
                } else {
                    System.out.println("\u001B[34m" + "----------------------------------------" + "\u001B[0m");
                    System.out.println("List of my last blocked users blocked users: ");
                    ArrayList<User> blockedUsers = user.getBlocks();
                    if (blockedUsers.isEmpty()) {
                        System.out.println("\u001B[31m" + "You've no blocked users!" + "\u001B[0m");
                    } else {
                        int counter = 1;
                        for (User blockedUser : blockedUsers) {
                            System.out.println(counter + ". " + blockedUser.getUserName());
                            ++counter;
                        }
                    }
                    System.out.println("\u001B[34m" + "----------------------------------------" + "\u001B[0m");
                    user.block(searchByUsername(userName));
                }
            }
            //See posts you've been tagged to //////////////////////////////////////////////////////////////////////////
            else if (choice == 8){
                boolean flag = false;
                for (Post post: Post.getPosts()){
                    if (post.getPostTaggedUsers().contains(user)){
                        Post.showPost(post);
                        flag = true;
                    }
                }
               if (!flag){
                   System.out.println("You have never been tagged! ");
               }

            }
        }
    }
}

