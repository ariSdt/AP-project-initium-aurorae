package PostManagment;

import UserManagment.User;

import java.util.ArrayList;

public class Post {
    private static ArrayList<Post> Posts = new ArrayList<>();
    private User postOwner;
    private String postCaption;

    public User getPostOwner() {
        return postOwner;
    }

    public String getPostCaption() {
        return postCaption;
    }

    public Post(User postOwner, String postCaption) {
        this.postOwner = postOwner;
        this.postCaption = postCaption;
    }

    public void addPost(User postOwner, String postCaption){
        Posts.add(new Post(postOwner, postCaption));
    }

    public Post findPostOfUser(User postOwner){
        for (Post post: Posts){
            if (postOwner.equals(post.getPostOwner())){
                return post;
            }
        }
        return null;
    }

    public static ArrayList<Post> getAllPosts(User user){
        ArrayList<Post> selectedPosts = new ArrayList<>();
        int counter = 1;
        for (Post post: Posts){
            if (post.getPostOwner().equals(user)){
                selectedPosts.add(post);
                ++counter;
            }
        }
        if (counter == 1){
            System.out.println("No posts available");
        }
        return selectedPosts;
    }

    public static void showPost(Post post){
        System.out.println("----------------------------------------");
        System.out.println("Posted by: " + post.getPostOwner().getUserName());
        System.out.println(post.getPostCaption());
        System.out.println("Like: " + PostLike.getNumberOfPostLikes(post));
        System.out.println("Comment: ");
        PostComment.printPostComments(post);
        System.out.println("----------------------------------------");
    }
}
