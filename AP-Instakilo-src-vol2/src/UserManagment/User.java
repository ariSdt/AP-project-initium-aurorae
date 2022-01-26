package UserManagment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;

public class User {
    private static ArrayList<User> Users = new ArrayList<>();
    private String userName;
    private String password;
    private int age;
    private String email;
    private String phoneNumber;
    private String Bio;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public User(String username, String password, String bio, String email, int age, String phoneNumber) {
    	this.userName = username;
    	this.password = password;
    	this.age = age;
    	this.Bio = bio;
    	this.phoneNumber = phoneNumber;
    	this.email = email;
 
		
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
    public static User signUp(String userName, String password) throws NoSuchAlgorithmException {
        for (User user: Users){
            if (user.getUserName().equals(userName) && user.getPassword().equals(hashPassword(password))){
                return null;
            }
        }
        User user = new User(userName, hashPassword(password));
        Users.add(user);
        
        return user;
    }

    public static User logIn(String userName, String password) throws NoSuchAlgorithmException {
    	
    	String userHelp = null;
    	String emailHelp = null;
    	String passHelp = null;
    	String bioHelp = null;
    	String phoneHelp = null;
    	int ageHelp = 18;
    	
    	
    	try {
    		
        	Class.forName("com.mysql.jdbc.Driver");             
            Connection connect = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
            String sqlOrder = "SELECT * FROM users WHERE username = ? AND password = ?";
            java.sql.PreparedStatement ps = connect.prepareStatement(sqlOrder);
            ps.setString(1, userName);
            ps.setString(2, password);
            
            
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	
            	userHelp = rs.getString("username");
            	emailHelp = rs.getString("email");
            	bioHelp = rs.getString("bio");
            	ageHelp = Integer.parseInt(rs.getString("age"));
            	phoneHelp = rs.getString("phone");
            	passHelp = rs.getString("password");
            	User user = new User(userHelp, passHelp, bioHelp, emailHelp, ageHelp, phoneHelp);
            	
                return user;
            	
            }
            
            
            
		} catch (Exception e) {
			System.out.println("error handeling" + e.toString());
		}
		return null;

//        for (User user: Users){
//            if (user.getPassword().equals(hashPassword(password)) && user.getUserName().equals(userHelp)){
//                return user;
//            }
//        }
//		return null;
    	

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
        for (User following: user.getFollowings()){
            System.out.println(counter + ". " + following.getUserName());
        }
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes());
        return new String(messageDigest.digest());
    }


    public void follow(User user) {
        ArrayList<UserFollowingFollower> UserFollowingFollowers = UserFollowingFollower.getDatabase();

        for (UserFollowingFollower userFollowingFollower : UserFollowingFollowers) {
            if (userFollowingFollower.getFollower() == this && userFollowingFollower.getFollowing() == user) {
                UserFollowingFollowers.remove(userFollowingFollower);
                System.out.println("\u001B[32m" + "User was unfollowed successfully!" + "\u001B[0m");
                return;
            }
        }

        UserFollowingFollower userFollowingFollower = new UserFollowingFollower();
        userFollowingFollower.setFollower(this);
        userFollowingFollower.setFollowing(user);
        System.out.println("\u001B[32m" + "User was followed successfully!" + "\u001B[0m");
        UserFollowingFollower.getDatabase().add(userFollowingFollower);
    }



    public ArrayList<User> getFollowers() {
        ArrayList<User> followers = new ArrayList<>();
        ArrayList<UserFollowingFollower> UserFollowingFollowers = UserFollowingFollower.getDatabase();

        for (UserFollowingFollower userFollowingFollower : UserFollowingFollowers) {
            if (userFollowingFollower.getFollowing() == this) {
                followers.add(userFollowingFollower.getFollower());
            }
        }
        return followers;
    }

    public ArrayList<User> getFollowings() {
        ArrayList<User> following = new ArrayList<>();
        ArrayList<UserFollowingFollower> UserFollowingFollowers = UserFollowingFollower.getDatabase();

        for (UserFollowingFollower userFollowingFollower : UserFollowingFollowers) {
            if (userFollowingFollower.getFollower() == this) {
                following.add(userFollowingFollower.getFollowing());
            }
        }
        return following;
    }

    public void block(User user){
        ArrayList<UserFollowingFollower> UserFollowingFollowers = UserFollowingFollower.getDatabase();

        for (UserFollowingFollower userFollowingFollower : UserFollowingFollowers) {
            if (userFollowingFollower.getBlocked() == user && userFollowingFollower.getBlocker() == this) {
                UserFollowingFollowers.remove(userFollowingFollower);
                System.out.println("\u001B[32m" + "User was unblocked successfully!" + "\u001B[0m");
                return;
            }
        }

        UserFollowingFollower userFollowingFollower = new UserFollowingFollower();
        userFollowingFollower.setBlocker(this);
        userFollowingFollower.setBlocked(user);
        UserFollowingFollower.getDatabase().add(userFollowingFollower);
        System.out.println("\u001B[32m" + "User was blocked successfully!" + "\u001B[0m");

    }

    public ArrayList<User> getBlocks(){
        ArrayList<User> blocks = new ArrayList<>();
        ArrayList<UserFollowingFollower> UserFollowingFollowers = UserFollowingFollower.getDatabase();

        for (UserFollowingFollower userFollowingFollower : UserFollowingFollowers) {
            if (userFollowingFollower.getBlocker() == this) {
                blocks.add(userFollowingFollower.getBlocked());

            }
        }
        return blocks;
    }

}

