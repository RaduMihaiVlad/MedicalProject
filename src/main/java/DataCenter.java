import java.io.IOException;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import sun.awt.windows.WPrinterJob;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public boolean isValidLogin(User user) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray users = (JSONArray) parser.parse(new FileReader(users_data_path));

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
        User user = new User("abc@yahoo.com", "abc", "parola", "Ion", "Vasile");
        System.out.println(dataCenter.isValidLogin(user));
    }

}
