package UsersTypes;

import org.json.simple.JSONObject;

import java.util.HashMap;

public class Client extends User {

    private String phoneNumber;
    private int age;

    public Client(String username, String password, String email, String firstName,
                  String lastName, String phoneNumber, int age) {
        super(username, password, email, firstName, lastName);
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return getUsername() + " " + getPassword() + " " + getEmail() + " " + getFirstName() + " " +
                getLastName() + " " + getPhoneNumber() + " " + String.valueOf(getAge());
    }

    public Client(HashMap<String, String> clientMap) {
        super(clientMap.get("username"), clientMap.get("password"),
                clientMap.get("email"), clientMap.get("firstname"), clientMap.get("lastname"));
        this.age = Integer.parseInt(clientMap.get("age"));
        this.phoneNumber = clientMap.get("phone_number");
    }

    public String getPhoneNumber() { return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber;}
    public int getAge() { return age;}
    public void setAge(int age) { this.age = age;}

    public static Client fromJSONObjectToClient(JSONObject jsonClient) {
        return new Client(jsonClient.get("username").toString(),
                jsonClient.get("password").toString(),
                jsonClient.get("email").toString(),
                jsonClient.get("firstName").toString(),
                jsonClient.get("lastName").toString(),
                jsonClient.get("phoneNumber").toString(),
                Integer.parseInt(jsonClient.get("age").toString()));

    }

    public String getByField(String field) {
        if (field.equals("username")) {
            return getUsername();
        }
        if (field.equals("password")) {
            return getPassword();
        }
        if (field.equals("email")) {
            return getEmail();
        }
        if (field.equals("firstname")) {
            return getFirstName();
        }
        if (field.equals("lastname")) {
            return getLastName();
        }
        if (field.equals("phone_number")) {
            return getPhoneNumber();
        }
        if (field.equals("age")) {
            return String.valueOf(getAge());
        }
        return "Unknown field";
    }


}
