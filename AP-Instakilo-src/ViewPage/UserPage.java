package ViewPage;

import PostManagment.Post;
import UserManagment.User;
import UserManagment.UserFollowingFollower;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class UserPage {
    private static Scanner scanner = new Scanner(System.in);

    private static void showMenu() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignore) {
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("              User page                 ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
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
                System.out.println("Enter the name of the user you want to follow: (Pick from the above list.)");
                String userName = scanner.nextLine();
                UserFollowingFollower userFollowingFollower1 = UserFollowingFollower.getUserFollowingFollowerAccess(user);
                assert userFollowingFollower1 != null;
                userFollowingFollower1.addFollowing(User.searchByUsername(userName));
                UserFollowingFollower userFollowingFollower2 = UserFollowingFollower.
                        getUserFollowingFollowerAccess(User.searchByUsername(userName));
                assert userFollowingFollower2 != null;
                userFollowingFollower2.addFollower(user);
            }
            else if (choice == 4) {
                System.out.println("Enter the user name of the user you want to unfollow: ");
                String userName = scanner.nextLine();
                UserFollowingFollower userFollowingFollower1 = UserFollowingFollower.getUserFollowingFollowerAccess(user);
                assert userFollowingFollower1 != null;
                userFollowingFollower1.removeFollowing(User.searchByUsername(userName));
                UserFollowingFollower userFollowingFollower2 = UserFollowingFollower.
                        getUserFollowingFollowerAccess(User.searchByUsername(userName));
                assert userFollowingFollower2 != null;
                userFollowingFollower2.removeFollower(user);

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
