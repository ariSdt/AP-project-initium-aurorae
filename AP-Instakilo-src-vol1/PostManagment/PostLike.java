package PostManagment;

import UserManagment.User;

import java.util.ArrayList;

public class PostLike {
    private static ArrayList<PostLike> PostLikes = new ArrayList<>();
    private Post post;
    private User userWhoLiked;

    public PostLike(User userWhoLiked, Post post) {
        this.userWhoLiked = userWhoLiked;
        this.post = post;
    }

    public User getUserWhoLiked() {
        return userWhoLiked;
    }

    public Post getPost() {
        return post;
    }

    public static void addLike(User user, Post post){
        for (PostLike postlike: PostLikes){
            if(user.equals(postlike.getUserWhoLiked())){
                System.out.println("\u001B[34m" + "You've already liked this post!" + "\u001B[0m");
                return;
            }
        }
        PostLikes.add(new PostLike(user, post));
        System.out.println("\u001B[32m" + "Liked!"+ "\u001B[0m");
    }

    public static void removeLike(User user, Post post){
        for (PostLike postLike: PostLikes){
            if (user.equals(postLike.getUserWhoLiked()) && postLike.getPost().equals(post)){
                PostLikes.remove(postLike);
                System.out.println("\u001B[32m" + "Like removed!" + "\u001B[0m");
                return;
            }
        }
        System.out.println("\u001B[34m" + "You've not liked this post!" + "\u001B[0m");
    }

    public static int getNumberOfPostLikes(Post post){
        int counter = 0;
        for (PostLike postLike: PostLikes){
            if(postLike.getPost().equals(post)){
                ++counter;
            }
        }
        return counter;
    }
}
