import java.time.LocalDate;
import java.util.ArrayList;

public class Users extends Person {

    protected int userID;
    private String  password;
    private LocalDate lastLogin;
    ArrayList<Person> friends;
    ArrayList<TextPost> posts;
    ArrayList<Person> blockList;

    public Users(int userID, String name, String username, String password, LocalDate dateofbirth, String school) {
        super(name, username, dateofbirth, school);
        this.userID = userID;
        this.password = password;
        this.friends = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.blockList = new ArrayList<>();
        setLastLogin(lastLogin);
    }

    protected int getUserID() { return this.userID; }

    public void setUserID(int userID) { this.userID = userID; }

    protected String getPassword() { return this.password; }

    public void setPassword(String password) {
        this.password = password;
    }

    protected LocalDate getLastLogin() { return lastLogin; }

    public void setLastLogin(LocalDate lastLogin) { this.lastLogin = lastLogin; }

    public void addedMessage() { System.out.println(this.name + " has been successfully added."); }

    public void addFriend(Users user) throws NullPointerException {
        if (user == null) {
            throw new NullPointerException();
        } else if (!this.friends.contains(user)) {
            this.friends.add(user);
            System.out.println(user.getUsername() + " successfully added.");
        } else System.out.println("You are already " + user.getUsername() + "'s friend");
    }

    public void removeFriend(Users user) throws NullPointerException {
        if (user == null) {
            throw new NullPointerException();
        } else if (this.friends.contains(user)) {
            this.friends.remove(user);
            System.out.println(user.getUsername() + " successfully removed.");
        } else System.out.println(user.getUsername() + " is not your friend. No need to remove.");
    }

    public boolean checkFriend(Users user) throws NullPointerException {
        if (user == null) throw new NullPointerException();
        return this.friends.contains(user);
    }

    public void addPost(TextPost tp) {
        this.posts.add(tp);
        System.out.println("Post successfully added.");
    }

    public void removeLastPost() {
        this.posts.remove(this.posts.size()-1);
        System.out.println("Last post removed.");
    }

    public void block(Users user) {
        if (!this.blockList.contains(user)) {
            this.blockList.add(user);
            System.out.println(user.getUsername() + " successfully blocked.");
        } else System.out.println(user.getUsername() + " has already blocked. No need to block again.");
    }

    public void unblock(Users user) {
        if (this.blockList.contains(user)) {
            this.blockList.remove(user);
            System.out.println(user.getUsername() + " successfully unblocked.");
        } else System.out.println(user.getUsername() + " was not blocked.");
    }

    public void listBlockedUsers() {
        for (Person p : this.blockList) {
            p.listMe();
        }
    }

    public void listBlockedFriends() {
        for (Person p: this.blockList) {
            if (this.friends.contains(p)) p.listMe();
        }
    }

    public void listFriends() {
        for (Person p : this.friends) {
            p.listMe();
        }
    }

    public void listPosts() {
        for (TextPost tp : this.posts) {
            tp.listMe();
            System.out.println("----------------------------");
        }
    }

}
