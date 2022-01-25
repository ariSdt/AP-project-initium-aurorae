package PostManagment;

import UserManagment.User;

import java.util.ArrayList;

public class PostTagUser {
    private static ArrayList<PostTagUser> PostTagsDataBase = new ArrayList<>();
    private User taggedUser;
    private Post postWithTag;

    public static ArrayList<PostTagUser> getPostTagsDataBase() {
        return PostTagsDataBase;
    }

    public User getTaggedUser() {
        return taggedUser;
    }

    public void setTaggedUser(User taggedUser) {
        this.taggedUser = taggedUser;
    }

    public Post getPostWithTag() {
        return postWithTag;
    }

    public void setPostWithTag(Post postWithTag) {
        this.postWithTag = postWithTag;
    }
}
