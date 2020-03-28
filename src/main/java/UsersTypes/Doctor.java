package UsersTypes;

public class Doctor extends User {

    private String phoneNumber;
    private int age;
    private String city;
    private String country;

    public Doctor(String username, String password, String email, String firstName,
                  String lastName, int age, String phoneNumber, String city, String country) {
        super(username, password, email, firstName, lastName);
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.country = country;
    }

    public String getCountry() { return country;}
    public void setCountry(String country) { this.country = country;}

    public String getCity() { return city;}
    public void setCity(String city) { this.city = city;}

    public int getAge() { return age;}
    public void setAge(int age) { this.age = age;}

    public String getPhoneNumber() { return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber;}
}
