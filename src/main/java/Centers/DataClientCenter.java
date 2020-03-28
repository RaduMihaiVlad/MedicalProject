package Centers;

import UsersTypes.Client;
import UsersTypes.Doctor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataClientCenter extends DataUserCenter {

    public DataClientCenter(String clients_data_path) {
        super(clients_data_path);
    }

    public String registerClient(InputStream inputStream) throws IOException, ParseException {
        /*
            This method will register a client.
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

    public String registerClientFromJSONObjectUser(JSONObject jsonUser, InputStream inputStream) throws IOException, ParseException {
        Scanner scanner = new Scanner(inputStream);
        String phoneNumber = scanner.nextLine();
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return "Age must be an integer.";
        }
        jsonUser.put("phoneNumber", phoneNumber);
        jsonUser.put("age", age);
        return addToLocalStorage(jsonUser);
    }

    private List<Client> fromJSONToClientList() throws IOException, ParseException {
        List<Client> clientList = new ArrayList<Client>();
        JSONParser parser = new JSONParser();
        JSONArray clients = (JSONArray) parser.parse(new FileReader(this.local_storage_path));
        for (Object o: clients) {
            JSONObject db_client = (JSONObject) o;
            clientList.add(Client.fromJSONObjectToClient(db_client));
        }
        return clientList;
    }

    public static void main(String[] args) throws IOException, ParseException {
        DataClientCenter dataClientCenter = new DataClientCenter("src/main/config/clients.json");
        System.out.println(dataClientCenter.registerClient(System.in));

    }

}
