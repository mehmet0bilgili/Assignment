import java.time.LocalDate;

public class VideoPost extends TextPost {

    private String filename;
    private int minutes;

    public VideoPost(String textPart, double latitude, double longitude, LocalDate addDate, String filename, int minutes) {
        super(textPart, latitude, longitude, addDate);
        this.filename = filename;
        setMinutes(minutes);
    }

    @Override
    public void tagFriend(Users user1, Users user2) {
        super.tagFriend(user1, user2);
    }

    protected String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    protected int getMinutes() {
        return this.minutes;
    }

    public void setMinutes(int minutes) {
        if (minutes > 10) {
            this.minutes = 0;
            System.out.println("Error: Your video exceeds maximum allowed duration of 10 minutes.");
        } else this.minutes = minutes;
    }

    @Override
    public void listMe() {
        super.listMe();
        System.out.println("Video: " + this.filename);
        System.out.println("Video Duration: " + this.minutes + " minutes.");
    }
}
