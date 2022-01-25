package PostManagment;

import UserManagment.User;

import java.util.ArrayList;

public class Post {
    private static ArrayList<Post> Posts = new ArrayList<>();
    private String mediaAddress;
    private User postOwner;
    private String postCaption;


    public User getPostOwner() {
        return postOwner;
    }

    public String getPostCaption() {
        return postCaption;
    }

    public Post(User postOwner, String postCaption, String mediaAddress) {
        this.mediaAddress = mediaAddress;
        this.postOwner = postOwner;
        this.postCaption = postCaption;
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
            System.out.println("\u001B[34m" + "No post available!" + "\u001B[0m");
        }
        return selectedPosts;
    }

    public static void showPost(Post post){
        System.out.println("\u001B[34m" + "----------------------------------------" + "\u001B[0m");
        System.out.println("Posted by: " + post.getPostOwner().getUserName());
        System.out.println("Post: " + post.getMediaAddress());
        System.out.println(post.getPostCaption());
        System.out.println("Like: " + PostLike.getNumberOfPostLikes(post));
        System.out.println("Comment: ");
        PostComment.printPostComments(post);
        System.out.println("\u001B[34m" + "----------------------------------------" + "\u001B[0m");
    }

    private String getMediaAddress() {
        return mediaAddress;
    }

    public static ArrayList<Post> getPosts() {
        return Posts;
    }

    public void tag(User user){
        PostTagUser postTagUser = new PostTagUser();
        postTagUser.setPostWithTag(this);
        postTagUser.setTaggedUser(user);
        PostTagUser.getPostTagsDataBase().add(postTagUser);
    }

    public ArrayList<User> getPostTaggedUsers(){
        ArrayList<User> taggedUsers = new ArrayList<>();
        ArrayList<PostTagUser> postTagUsers = PostTagUser.getPostTagsDataBase();
        for (PostTagUser posttagUser: postTagUsers){
            if (posttagUser.getPostWithTag() == this){
                taggedUsers.add(posttagUser.getTaggedUser());
            }
        }
        return taggedUsers;

    }

}
