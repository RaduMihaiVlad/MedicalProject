import javafx.scene.chart.PieChart;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class DataClientCenter extends DataUserCenter {

    public DataClientCenter(String clients_data_path) {
        super(clients_data_path);
    }

    public String registerClient(InputStream inputStream) throws IOException, ParseException {
        /*
            This method will register an user.
            If the registration was complete, it will return User successfully added.
            If the registration failed, one the above will appear:
                1. Email failure: Invalid Email
                2. It exists an user with the current email: Email already taken
                2. User already exists: User with this username already exists. Try another username.
        */
        JSONObject jsonObject = fetchUserRegisterData(inputStream);
        if (!isValidUser(jsonObject)) {
            return getUserValidationError(jsonObject);
        }
        Scanner scanner = new Scanner(inputStream);
        String phoneNumber = scanner.nextLine();
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return "Age must be an integer.";
        }
        jsonObject.put("phoneNumber", phoneNumber);
        jsonObject.put("age", age);
        return addToLocalStorage(jsonObject);
    }

    public static void main(String[] args) throws IOException, ParseException {
        DataClientCenter dataClientCenter = new DataClientCenter("src/main/config/clients.json");
        System.out.println(dataClientCenter.registerClient(System.in));
    }

}
