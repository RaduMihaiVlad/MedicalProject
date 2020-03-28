package UsersTypes;

import org.json.simple.JSONObject;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;

public class User implements Comparable<User> {
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

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
    public static User fromJSONObjectToUser(JSONObject jsonUser) {
        return new User(jsonUser.get("username").toString(),
                             jsonUser.get("password").toString(),
                             jsonUser.get("email").toString(),
                             jsonUser.get("firstName").toString(),
                             jsonUser.get("lastName").toString());

    }
    public static boolean isValidEmailAddress(User user) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(user.email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }


    public static void main(String[] args) {
        User user = new User("abc@yahoo.com", "abc", "parola", "Ion", "Vasile");
        System.out.println(user.isValidEmailAddress());

    }


    public int compareTo(User o) {
        if (this.username.length() > o.username.length()) return 1;
        return -1;
    }
}
