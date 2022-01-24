package UserManagment;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private static ArrayList<User> Users = new ArrayList<>();
    private String userName;
    private String password;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public static ArrayList<User> getUsers() {
        return Users;
    }

    public static void setUsers(ArrayList<User> users) {
        Users = users;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static User signUp(String userName, String password){
        for (User user: Users){
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return null;
            }
        }
        User user = new User(userName, password);
        Users.add(user);
        UserFollowingFollower.addNewUserToUserFollowingFollowers(user);
        return user;
    }

    public static User logIn(String userName, String password){
        for (User user: Users){
            if (user.getPassword().equals(password) && user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }

    public static User searchByUsername(String userName){
        for(User user: Users){
            if(user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }

    public static void showFollowings(User user){
        int counter = 1;
        for (User following: Objects.requireNonNull(UserFollowingFollower.getUserFollowingFollowerAccess(user)).getFollowings()){
            System.out.println(counter + ". " + following.getUserName());
        }
    }







}
