import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class User {
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public boolean isValidEmailAddress() {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(this.email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public User (String username,
                 String password,
                 String email,
                 String firstName,
                 String lastName) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getEmail() { return email;}
    public String getUsername() { return username;}
    public String getPassword() { return password;}
    public String getFirstName() { return firstName;}
    public String getLastName() { return lastName;}
    public void setEmail(String email) { this.email = email;}
    public void setUsername(String username) { this.username = username;}
    public void setPassword(String password) { this.password = password;}
    public void setFirstName(String firstName) { this.firstName = firstName;}
    public void setLastName(String lastName) { this.lastName = lastName;}

    public boolean isSameUser(User user) {
        return this.username.equals(user.username);
    }


    public static void main(String[] args) {
        User user = new User("abc@yahoo.com", "abc", "parola", "Ion", "Vasile");
        System.out.println(user.isValidEmailAddress());

    }


}
