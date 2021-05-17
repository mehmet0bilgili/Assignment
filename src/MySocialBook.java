import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.*;

public class MySocialBook {

    public static void main(String[] args) throws IOException {

        File f = new File("C:\\Users\\BILGI\\IdeaProjects\\deneme\\Assignment\\src\\users.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        String[] lineArray;

        int idC = 1; //to give id number to user.
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        ArrayList<Users> u = new ArrayList<>();

        while (line != null) {
            lineArray = line.split("\\t");
            u.add(new Users(idC, lineArray[0], lineArray[1], lineArray[2], LocalDate.parse(lineArray[3], DATEFORMATTER), lineArray[4]));
            idC++; //every new user increase the id number.
            line = br.readLine();
        }

        //command machine
        File f2 = new File("C:\\Users\\BILGI\\IdeaProjects\\deneme\\Assignment\\src\\commands.txt");
        FileReader fr2 = new FileReader(f2);
        BufferedReader br2 = new BufferedReader(fr2);
        String line2 = br2.readLine();
        String[] lineArray2;
        Users helper, active = null;
        boolean gate;

        while (line2 != null) {
            gate = true;
            lineArray2 = line2.split("\\t");
            printCommandLine(lineArray2);
            switch (lineArray2[0]) {
                case "ADDUSER":
                    helper = new Users(idC, lineArray2[1], lineArray2[2], lineArray2[3], LocalDate.parse(lineArray2[4], DATEFORMATTER), lineArray2[5]);
                    idC++; //line 23
                    u.add(helper);
                    helper.addedMessage();
                    break;
                case "REMOVEUSER":
                    for (Users user : u) {
                        if (user.getUserID() == Integer.parseInt(lineArray2[1])) {
                            u.remove(user);
                            gate = false;
                            System.out.println("User has been successfully removed.");
                            break;
                        }
                    } if (gate) System.out.println("There is no such a user.");
                    break;
                case "SIGNIN":
                    if (active == null) {
                    for (Users user : u) {
                        if (user.getUsername().equals(lineArray2[1]) && user.getPassword().equals(lineArray2[2])) {
                            System.out.println("You've successfully signed in.");
                            active = user;
                            active.setLastLogin(LocalDate.now());
                            gate = false;
                            break;
                        }
                    } if (gate) System.out.println("Wrong username or password.");
                    } else System.out.println("You already signed in by " + active.getUsername() + ". First, sign out!");
                    break;
                case "LISTUSERS":
                    for (Users d : u) {
                        d.listMe();
                    }
                    break;
                case "UPDATEPROFILE":
                    if (active != null) {
                        active.setName(lineArray2[1]);
                        active.setDateofbirth(LocalDate.parse(lineArray2[2], DATEFORMATTER));
                        active.setSchool(lineArray2[3]);
                    } else System.out.println("Error: Please sign in and try again.");
                    break;
                case "CHPASS":
                    if (active == null) System.out.println("Error: Please sign in and try again.");
                    else if (lineArray2[1].equals(active.getPassword())) {
                        active.setPassword(lineArray2[2]);
                        System.out.println("Password successfully changed.");
                    } else System.out.println("Password mismatch!");
                    break;
                case "ADDFRIEND":
                    if (active != null) {
                        helper = findByUsername(lineArray2[1], u);
                        if (helper != null) {
                            gate = false;
                            active.addFriend(helper);
                        }
                        if (gate) System.out.println("There is no such a user.");
                    } else System.out.println("Error: Please sign in and try again.");
                    break;
                case "REMOVEFRIEND":
                    if (active != null) {
                        helper = findByUsername(lineArray2[1], u);
                        if (helper != null) {
                            gate = false;
                            active.removeFriend(helper);
                        }
                        if (gate) System.out.println("There is no such user.");
                    } else System.out.println("Error: Please sign in.");
                    break;
                case "LISTFRIENDS":
                    if (active != null) active.listFriends();
                    else System.out.println("Error: Please sign in amigo.");
                    break;
                case "ADDPOST-TEXT":
                    if (active != null) {
                        TextPost tp = new TextPost(lineArray2[1], Double.parseDouble(lineArray2[2]), Double.parseDouble(lineArray2[3]), LocalDate.now());
                        addPost(u, lineArray2, active, tp);
                    } else System.out.println("Please sign in.");
                    break;
                case "ADDPOST-IMAGE":
                    if (active != null) {
                        ImagePost ip = new ImagePost(lineArray2[1], Double.parseDouble(lineArray2[2]), Double.parseDouble(lineArray2[3]), LocalDate.now(),lineArray2[5], lineArray2[6]);
                        addPost(u, lineArray2, active, ip);
                    } else System.out.println("Please sign in.");
                    break;
                case "ADDPOST-VIDEO":
                    if (active != null) {
                        VideoPost vp = new VideoPost(lineArray2[1], Double.parseDouble(lineArray2[2]), Double.parseDouble(lineArray2[3]), LocalDate.now(),lineArray2[5], Integer.parseInt(lineArray2[6]));
                        if (vp.getMinutes() != 0) {
                            addPost(u, lineArray2, active, vp);
                        }
                    } else System.out.println("Please sign in.");
                    break;
                case "REMOVELASTPOST":
                    if (active != null) {
                        active.removeLastPost();
                    } else System.out.println("Error: Please sign in.");
                    break;
                case "SHOWPOSTS":
                    helper = findByUsername(lineArray2[1], u);
                    if (helper != null) {
                        System.out.println("++++++++++++++++++\n"+helper.getUsername()+"'s post\n++++++++++++++++++");
                        helper.listPosts();
                    } else System.out.println("There is no such a user named " + lineArray2[1]);
                    break;
                case "BLOCK":
                    if (active != null) {
                        helper = findByUsername(lineArray2[1], u);
                        if (helper != null) active.block(helper);
                        else System.out.println("There is no such user.");
                    } else System.out.println("Error: Please sign in.");
                    break;
                case "SHOWBLOCKEDFRIENDS":
                    if (active != null) active.listBlockedFriends();
                    else System.out.println("Error: Please sign in.");
                    break;
                case "UNBLOCK":
                    if (active != null) {
                        helper = findByUsername(lineArray2[1], u);
                        if (helper != null) active.unblock(helper);
                        else System.out.println("There is no such user.");
                    } else System.out.println("Error: Please sign in.");
                    break;
                case "SHOWBLOCKEDUSERS":
                    if (active != null) active.listBlockedUsers();
                    else System.out.println("Error: Please sign in.");
                    break;
                case "SIGNOUT":
                    active = null;
                    System.out.println("Adios, amigo.");
                    break;
                default:
                    System.out.println("Wow wrong attempt!");
            }
            line2 = br2.readLine();
        }
    }

    private static void addPost(ArrayList<Users> u, String[] lineArray, Users active, TextPost tp) {
        String[] lineArray2;
        Users m;
        lineArray2 = lineArray[4].split(":");
        for (String s : lineArray2) {
            m = findByUsername(s, u);
            if (m != null) tp.tagFriend(active, m);
            else System.out.println("No such user named " + s + " found.");
        }
        active.addPost(tp);
    }

    static void printCommandLine(String[] lineArray) {
        System.out.println("**************************************************");
        StringBuilder w = new StringBuilder("Command: ");
        for (String s : lineArray) {
            w.append(s);
            w.append(" | ");
        }
        System.out.println(w);
    }

    static Users findByUsername(String username, ArrayList<Users> u) {
        for (Users user : u) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }
}
