import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class DataDoctorCenter extends DataUserCenter {

    public DataDoctorCenter(String users_data_path) {
        super(users_data_path);
    }

    public String registerDoctor(InputStream inputStream) throws IOException, ParseException {
        /*
            This method will register a doctor.
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
        int age, absolvationYear;
        try {
            age = Integer.parseInt(scanner.nextLine());
            absolvationYear = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return "Age or absolvation year must be an integer.";
        }
        String city = scanner.nextLine();
        String country = scanner.nextLine();
        jsonObject.put("phoneNumber", phoneNumber);
        jsonObject.put("age", age);
        return addToLocalStorage(jsonObject);
    }

    public static void main(String[] args) throws IOException, ParseException {
        DataDoctorCenter dataDoctorCenter = new DataDoctorCenter("src/main/config/doctors.json");
        System.out.println(dataDoctorCenter.registerDoctor(System.in));
    }
}
