package PostManagment;

import UserManagment.User;

import java.util.ArrayList;

public class PostComment {
    private Post post;
    private static ArrayList<PostComment> PostComments = new ArrayList<>();
    private static User commentOwner;
    private String commentText;

    public Post getPost() {
        return post;
    }

    public User getCommentOwner() {
        return commentOwner;
    }

    public String getCommentText() {
        return commentText;
    }

    public PostComment(User commentOwner, String commentText, Post post) {
        PostComment.commentOwner = commentOwner;
        this.commentText = commentText;
        this.post = post;
    }

    public static void addComment(User commentOwner, String commentText, Post post){
        PostComments.add(new PostComment(commentOwner, commentText, post));
    }

    public static void printPostComments(Post post){
        for (PostComment postComment: PostComments){
            if (postComment.getPost().equals(post)){
                System.out.println(postComment.getCommentOwner().getUserName() + ": " + postComment.getCommentText());
            }
        }
    }
}
