package ViewPage;

import PostManagment.Post;
import PostManagment.PostComment;
import PostManagment.PostLike;
import UserManagment.User;

import java.util.Scanner;

public class PostPage {
    private static final Scanner scanner = new Scanner(System.in);

    public static void run(User user, Post post) {
        System.out.println("\u001B[34m"  + "----------------------------------------");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("                  Post                  ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\u001B[0m");
        System.out.println(post.getPostCaption());
        System.out.println("Likes: " + PostLike.getNumberOfPostLikes(post));
        System.out.print("Comments: ");
        PostComment.printPostComments(post);
        while (true) {
            System.out.println("\nWhat do you want to do:\n1. Like\n2. Unlike\n3. Add comment\n4. Go back");

            int choice = optionChoosing();
            if (choice == 4) {
                return;
            }
            if (choice == 1) {
                PostLike.addLike(user, post);
            } else if (choice == 2) {
                PostLike.removeLike(user, post);
            } else if (choice == 3) {
                System.out.println("Enter your comment: ");
                String comment = scanner.nextLine();
                PostComment.addComment(user, comment, post);
            }
        }
    }

    private static int optionChoosing(){
        System.out.println("Enter your choice: ");
        try{
            int option = scanner.nextInt();
            if (1 <= option && option <=4){
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

}
