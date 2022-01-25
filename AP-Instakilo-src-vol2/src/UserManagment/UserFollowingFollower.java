package UserManagment;

import java.util.ArrayList;

public class UserFollowingFollower {
    private static ArrayList<UserFollowingFollower> database = new ArrayList<>();
    private User follower;
    private User following;

    public User getBlocker() {
        return blocker;
    }

    public void setBlocker(User blocker) {
        this.blocker = blocker;
    }

    private User blocker;
    private User blocked;

    public User getBlocked() {
        return blocked;
    }

    public void setBlocked(User blocked) {
        this.blocked = blocked;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    public static ArrayList<UserFollowingFollower> getDatabase() {
        return database;
    }

    public static void setDatabase(ArrayList<UserFollowingFollower> database) {
        UserFollowingFollower.database = database;
    }



}
