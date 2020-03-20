import java.io.*;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import sun.awt.windows.WPrinterJob;

import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.mail.internet.AddressException;

public class DataCenter {

    String users_data_path;

    public DataCenter(String users_data_path) {
        this.users_data_path = users_data_path;
    }

    public String addUser(User user) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray users = (JSONArray) parser.parse(new FileReader(this.users_data_path));

        JSONObject jsonUser = new JSONObject();
        jsonUser.put("username", user.getUsername());
        jsonUser.put("password", user.getPassword());
        jsonUser.put("email", user.getEmail());
        jsonUser.put("firstName", user.getFirstName());
        jsonUser.put("lastName", user.getLastName());

        users.add(jsonUser);

        try {
            FileWriter file = new FileWriter(this.users_data_path);
            file.append(users.toJSONString());
            file.flush();
            file.close();
            System.out.println(jsonUser.toString());
            return "User successfully added";

        } catch (IOException e) {
            return e.toString();
        }
    }

    public boolean isValidLogin(User user) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray users = (JSONArray) parser.parse(new FileReader(this.users_data_path));
        for (Object o : users) {
            JSONObject db_user = (JSONObject) o;
            if (db_user.get("username").equals(user.getUsername())) {
                return db_user.get("password").equals(user.getPassword());
            }
        }
        return false;
    }



    public static void main(String[] args) throws IOException, ParseException {
        DataCenter dataCenter  = new DataCenter("C:\\Users\\eu\\Desktop\\facultate\\PAO\\ProiectPao\\src\\main\\config\\users.json");
        User user = new User("abc1@yahoo.com", "abc", "parola", "Ion", "Vasile");
        System.out.println(dataCenter.isValidLogin(user));
//        System.out.println(dataCenter.addUser(user));
    }

}
