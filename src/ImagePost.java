import java.time.LocalDate;
public class ImagePost extends TextPost {

    private String resolution, filename;

    public ImagePost(String textPart, double latitude, double longitude, LocalDate addDate, String filename, String resolution) {
        super(textPart, latitude, longitude, addDate);
        this.filename = filename;
        this.resolution = resolution;
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

    protected String getResolution() {
        return this.resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Override
    public void listMe() {
        super.listMe();
        System.out.println("Image: " + this.filename);
        System.out.println("Image resolution: " + this.resolution);
    }
}
