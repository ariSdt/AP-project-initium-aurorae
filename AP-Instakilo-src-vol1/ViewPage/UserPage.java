package ViewPage;

import PostManagment.Post;
import UserManagment.User;
import UserManagment.UserFollowingFollower;

import java.io.IOException;
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
        System.out.println("3. Follow a user");
        System.out.println("4. Unfollow a user");
        System.out.println("5. See my followings' posts. / See Home page.");
        System.out.println("6. Direct message someone");
        System.out.println("7. Exit");

    }

    private static int optionChoosing(){
        System.out.println("Enter your choice: ");
        try{
            int option = scanner.nextInt();
            if (1 <= option && option <=7){
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

    public static void run(User user){
        while (true){
            showMenu();
            int choice = optionChoosing();
            if (choice == 7){
                return;
            }
            if (choice == 1){
                System.out.println("Enter your post caption: ");
                String postCaption = scanner.nextLine();
                Post post = new Post(user, postCaption);
                post.addPost(user, postCaption);
            }
            else if (choice == 2){
                for (Post post: Post.getAllPosts(user)){
                    Post.showPost(post);
                }
            }
            else if (choice == 3){
                int counter = 1;
                for (User user1: User.getUsers()){
                    if (user1 != user && !(Objects.requireNonNull(UserFollowingFollower.getUserAllFollowings(user)))
                            .contains(user1)){
                        System.out.println(counter + ". " + user1.getUserName());
                        ++counter;
                    }
                }
                if (counter == 1){
                    System.out.println("\u001B[34m" + "Sorry there are no other users available to follow!" + "\u001B[0m");
                }
                else {
                    System.out.println("Enter the name of the user you want to follow: (Pick from the above list.)");
                    String userName = scanner.nextLine();
                    UserFollowingFollower userFollowingFollower1 = UserFollowingFollower.getUserFollowingFollowerAccess(user);
                    assert userFollowingFollower1 != null;
                    userFollowingFollower1.addFollowing(searchByUsername(userName));
                    UserFollowingFollower userFollowingFollower2 = UserFollowingFollower.
                            getUserFollowingFollowerAccess(searchByUsername(userName));
                    assert userFollowingFollower2 != null;
                    userFollowingFollower2.addFollower(user);
                }
            }
            else if (choice == 4) {
                UserFollowingFollower userFollowingFollower1 = UserFollowingFollower.getUserFollowingFollowerAccess(user);
                assert userFollowingFollower1 != null;
                if (userFollowingFollower1.getFollowings().isEmpty()){
                    System.out.println("\u001B[31m" + "You have no following users to unfollow!" + "\u001B[0m");
                }
                else {
                    User.showFollowings(user);
                    System.out.println("Enter the user name of the user you want to unfollow: (Pick from the list above)");
                    String userName = scanner.nextLine();
                    if (searchByUsername(userName) != null && userFollowingFollower1.getFollowings().contains(searchByUsername(userName))) {
                        userFollowingFollower1.removeFollowing(searchByUsername(userName));
                        UserFollowingFollower userFollowingFollower2 = UserFollowingFollower.
                                getUserFollowingFollowerAccess(searchByUsername(userName));
                        assert userFollowingFollower2 != null;
                        userFollowingFollower2.removeFollower(user);

                    }
                    else{
                        System.out.println("\u001B[31m" + "User not found!" + "\u001B[0m");
                    }
                }
            }
            else if (choice == 5){
                HomePage.run(user);
            }
            else if (choice == 6){
                DirectMessagePage.run(user);
            }
        }

    }
}
