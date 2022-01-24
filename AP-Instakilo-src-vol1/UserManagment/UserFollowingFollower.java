package UserManagment;

import java.util.ArrayList;

public class UserFollowingFollower {
    private static ArrayList<UserFollowingFollower> UserFollowingFollowers = new ArrayList<>();
    private User user;
    private static ArrayList<User> followers = new ArrayList<>();
    private static ArrayList<User> followings = new ArrayList<>();

    public UserFollowingFollower(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<User> getFollowings() {
        return followings;
    }

    public ArrayList<User> getFollowers() {
        return followings;
    }

    public boolean addFollower(User follower){
        if (followers.contains(follower)){
            return false;
        }
        followers.add(follower);
        return true;
    }

    public boolean removeFollower(User follower){
        if (followers.contains(follower)){
            followers.remove(follower);
            return true;
        }
        return false;
    }

    public boolean addFollowing(User following){
        if (followings.contains(following)){
            return false;
        }
        followings.add(following);
        return true;
    }

    public boolean removeFollowing(User following){
        if (followings.contains(following)){
            followings.remove(following);
            return true;
        }
        return false;
    }


    public static void addNewUserToUserFollowingFollowers(User user){
        UserFollowingFollowers.add(new UserFollowingFollower((user)));
    }

    public static UserFollowingFollower getUserFollowingFollowerAccess(User user){
        for (UserFollowingFollower userFollowingFollower: UserFollowingFollowers){
            if (userFollowingFollower.getUser().equals(user)){
                return  userFollowingFollower;
            }
        }
        return null;
    }

    public static ArrayList<User> getUserAllFollowings(User user){
        for (UserFollowingFollower userFollowingFollower: UserFollowingFollowers){
            if (userFollowingFollower.getUser().equals(user)){
                return userFollowingFollower.getFollowings();
            }
        }
        return null;
    }

}
