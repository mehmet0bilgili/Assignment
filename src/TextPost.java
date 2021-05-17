import java.time.LocalDate;
import java.util.ArrayList;

public class TextPost implements Location, Display {

    private double latitude, longitude;
    private String textPart;
    private LocalDate addDate;
    ArrayList<Person> taggedFriends;

    public TextPost(String textPart, double latitude, double longitude, LocalDate addDate) {
        this.textPart = textPart;
        this.latitude = latitude;
        this.longitude = longitude;
        this.addDate = addDate;
        this.taggedFriends = new ArrayList<>();
    }


    public void tagFriend(Users user1, Users user2) {
        if (this.taggedFriends.contains(user2)) System.out.println(user2.getUsername() + " already tagged.");
        else if (user1.checkFriend(user2)) { this.taggedFriends.add(user2);
            System.out.println(user2.getUsername() + " tagged.");
        }
        else System.out.println(user2.getUsername() + " is not your friend, and won't be tagged!");
    }

    protected String getTextPart() { return textPart; }

    public void setTextPart(String textPart) { this.textPart = textPart; }

    protected LocalDate getAddDate() { return addDate; }

    public void setAddDate(LocalDate addDate) { this.addDate = addDate; }

    @Override
    public double getLatitude() { return this.latitude; }

    @Override
    public void setLatitude(double latitude) { this.latitude = latitude; }

    @Override
    public double getLongitude() { return longitude; }

    @Override
    public void setLongitude(double longitude) { this.longitude = longitude; }

    @Override
    public void listMe() {
        System.out.println("----------------------------");
        System.out.println("Text: " + this.textPart);
        System.out.println("Location: " + this.longitude + ", " + this.latitude);
        System.out.println("Date: " + this.addDate.getMonthValue() + "/" + this.addDate.getDayOfMonth() + "/" + this.addDate.getYear());
        StringBuilder tf = new StringBuilder();
        for (Person p : this.taggedFriends) {
            tf.append(p.getName());
            tf.append(", ");
        }
        System.out.println("Friends tagged on this post: " + tf);
    }
}
