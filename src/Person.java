import java.time.LocalDate;

public class Person implements Display {

    protected String name, username, school;
    private LocalDate dateofbirth;

    public Person(String name, String username, LocalDate dateofbirth, String school) {
        this.name = name;
        this.username = username;
        this.dateofbirth = dateofbirth;
        this.school = school;
    }

    protected String getSchool() { return this.school; }

    public void setSchool(String school) { this.school = school; }

    protected LocalDate getDateofbirth() { return this.dateofbirth; }

    public void setDateofbirth(LocalDate dateofbirth) { this.dateofbirth = dateofbirth; }

    protected String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    protected String getUsername() { return this.username; }

    public void setUsername(String username) { this.username = username; }


    @Override
    public void listMe() {
        System.out.println("----------------------------");
        System.out.println("Name: " + this.name);
        System.out.println("Username: " + this.username);
        System.out.println("Date of birth: " + this.dateofbirth.getMonthValue() + "/" + this.dateofbirth.getDayOfMonth() + "/" + this.dateofbirth.getYear());
        System.out.println("School: " + this.school);
        System.out.println("----------------------------");
    }

}
