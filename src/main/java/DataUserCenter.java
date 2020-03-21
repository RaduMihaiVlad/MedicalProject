import java.io.*;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataUserCenter {

    String local_storage_path;

    protected boolean userAlreadyExists(User user) throws IOException, ParseException {
        /*
            It will check if user already exists in local storage.
        */
        JSONParser parser = new JSONParser();
        JSONArray users = (JSONArray) parser.parse(new FileReader(this.local_storage_path));
        for (Object o: users) {
            JSONObject db_user = (JSONObject) o;
            if (db_user.get("username").equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    private boolean emailAlreadyTaken(User user) throws IOException, ParseException {
        /*
            It will check if it exists an user with the same email in local storage.
        */
        JSONParser parser = new JSONParser();
        JSONArray users = (JSONArray) parser.parse(new FileReader(this.local_storage_path));
        for (Object o: users) {
            JSONObject db_user = (JSONObject) o;
            if (db_user.get("email").equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public DataUserCenter(String users_data_path) {
        this.local_storage_path = users_data_path;
    }

    private String addUser(User user) throws IOException, ParseException {
        /*
            It will add the user to the local storage.
            NOTE: This function will not verify if the user is already in local storage.
        */
        JSONParser parser = new JSONParser();
        JSONArray users = (JSONArray) parser.parse(new FileReader(this.local_storage_path));

        JSONObject jsonUser = new JSONObject();
        jsonUser.put("username", user.getUsername());
        jsonUser.put("password", user.getPassword());
        jsonUser.put("email", user.getEmail());
        jsonUser.put("firstName", user.getFirstName());
        jsonUser.put("lastName", user.getLastName());

        users.add(jsonUser);

        try {
            FileWriter file = new FileWriter(this.local_storage_path);
            file.append(users.toJSONString());
            file.flush();
            file.close();
            return "User successfully added";

        } catch (IOException e) {
            return e.toString();
        }
    }

    protected String addToLocalStorage(JSONObject jsonData) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        JSONArray users = (JSONArray) parser.parse(new FileReader(this.local_storage_path));
        users.add(jsonData);
        try {
            FileWriter file = new FileWriter(this.local_storage_path);
            file.append(users.toJSONString());
            file.flush();
            file.close();
            return "User successfully added";

        } catch (IOException e) {
            return e.toString();
        }
    }

    protected String getUserValidationError(JSONObject jsonUser) throws IOException, ParseException {
        /*
            This function will return the validation error found in method isValidUser()
        */
        User current_user = User.fromJSONObjectToUser(jsonUser);
        if (!current_user.isValidEmailAddress()) {
            return "Invalid Email";
        }
        if (this.emailAlreadyTaken(current_user)) {
            return "Email already taken";
        }
        return "User with this username already exists. Try another username.";
    }

    protected boolean isValidUser(JSONObject jsonUser) throws IOException, ParseException {
        User current_user = User.fromJSONObjectToUser(jsonUser);
        if (!current_user.isValidEmailAddress()) {
            return false;
        }
        if (this.userAlreadyExists(current_user)) {
            return false;
        }
        if (this.emailAlreadyTaken(current_user)) {
            return false;
        }
        return true;
    }

    public JSONObject fetchUserRegisterData(InputStream inputStream) throws IOException, ParseException {
        /*
            fetch user registration data. It also checks if user's data already exists in local storage.
            If email is invalid it will return a JSONObject equal with { Invalid Email -> true}
            If user already is in local storage, it will return a JSONObject equal with { "User with this username already exists. Try another username." -> true}
            Else it will return a JSONObject fetching all user data.
        */
        Scanner scanner = new Scanner(inputStream);
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        String email = scanner.nextLine();
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();

        User user = new User(username, password, email, firstName, lastName);
        JSONObject jsonUser = new JSONObject();

//        if (!user.isValidEmailAddress()) {
//            jsonUser.put("Invalid Email", "Invalid Email");
//            return jsonUser;
//        }
//        if (this.userAlreadyExists(user)) {
//            jsonUser.put("User with this username already exists. Try another username.",
//                         "User with this username already exists. Try another username.");
//            return jsonUser;
//        }
//        if (this.emailAlreadyTaken(user)) {
//            jsonUser.put("Email already taken by another user.",
//                         "Email already taken by another user.");
//            return jsonUser;
//        }
        jsonUser.put("username", user.getUsername());
        jsonUser.put("password", user.getPassword());
        jsonUser.put("email", user.getEmail());
        jsonUser.put("firstName", user.getFirstName());
        jsonUser.put("lastName", user.getLastName());
        return jsonUser;
    }

    public boolean isValidLogin(User user) throws IOException, ParseException {
        /*
            This function will be used for login validation only.
            It will check if exists an user with the same username and password as the input's one.
        */
        JSONParser parser = new JSONParser();
        JSONArray users = (JSONArray) parser.parse(new FileReader(this.local_storage_path));
        for (Object o: users) {
            JSONObject db_user = (JSONObject) o;
            if (db_user.get("username").equals(user.getUsername())) {
                return db_user.get("password").equals(user.getPassword());
            }
        }
        return false;
    }

    public String registerUser(InputStream inputStream) throws IOException, ParseException {
        Scanner scanner = new Scanner(inputStream);
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        String email = scanner.nextLine();
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();
        User user = new User(username, password, email, firstName, lastName);
        if (!user.isValidEmailAddress()) {
            return "Invalid Email";
        }
        if (!this.emailAlreadyTaken(user)) {
            return "Email already taken by another user.";
        }
        if (this.userAlreadyExists(user)) {
            return "User with this username already exists. Try another username.";
        }
        if (addUser(user).equals("User successfully added")) {
            return "User successfully registered";
        }
        return "User unsuccessfully registered";
    }



    public static void main(String[] args) throws IOException, ParseException {
        DataUserCenter dataCenter  = new DataUserCenter("C:\\Users\\eu\\Desktop\\facultate\\PAO\\ProiectPao\\src\\main\\config\\users.json");
        User user = new User("abc1@yahoo.com", "abc", "parola", "Ion", "Vasile");
//        System.out.println(dataCenter.isValidLogin(user));
        System.out.println(dataCenter.registerUser(System.in));
    }

}
