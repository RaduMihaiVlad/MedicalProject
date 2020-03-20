import java.io.IOException;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.awt.windows.WPrinterJob;

import javax.mail.internet.AddressException;

public class DataCenter {

    String users_data_path;

    public DataCenter(String users_data_path) {
        this.users_data_path = users_data_path;
    }

    public boolean isValidLogin(User user) {
        try {
            HashMap result =
                    new ObjectMapper().readValue(this.users_data_path, HashMap.class);
            System.out.println(result);
        } catch (IOException ioException) {
            return false;
        }
        return true;

    }


    public static void main(String[] args) {
        DataCenter dataCenter  = new DataCenter("src/main/config/users.json");
        User user = new User("abc@yahoo.com", "abc", "parola", "Ion", "Vasile");
        System.out.println(dataCenter.isValidLogin(user));
    }

}
