package Centers;

import UsersTypes.Doctor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.print.Doc;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
        int age, absolvationYear;
        try {
            age = Integer.parseInt(scanner.nextLine());
            absolvationYear = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return "Age or absolvation year must be an integer.";
        }
        String phoneNumber = scanner.nextLine();
        String city = scanner.nextLine();
        String country = scanner.nextLine();
        jsonObject.put("age", age);
        jsonObject.put("absolvation_year", absolvationYear);
        jsonObject.put("phoneNumber", phoneNumber);
        jsonObject.put("city", city);
        jsonObject.put("country", country);
        return addToLocalStorage(jsonObject);
    }


    public String registerDoctorFromJSONObjectUser(JSONObject jsonUser, InputStream inputStream) throws IOException, ParseException {
        Scanner scanner = new Scanner(inputStream);
        int age, absolvationYear;
        try {
            age = Integer.parseInt(scanner.nextLine());
            absolvationYear = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return "Age or absolvation year must be an integer.";
        }
        String phoneNumber = scanner.nextLine();
        String city = scanner.nextLine();
        String country = scanner.nextLine();
        jsonUser.put("age", age);
        jsonUser.put("absolvation_year", absolvationYear);
        jsonUser.put("phoneNumber", phoneNumber);
        jsonUser.put("city", city);
        jsonUser.put("country", country);
        return addToLocalStorage(jsonUser);
    }

    private List<Doctor> fromJSONToDoctorList() throws IOException, ParseException {
        List<Doctor> doctorList = new ArrayList<Doctor>();
        JSONParser parser = new JSONParser();
        JSONArray doctors = (JSONArray) parser.parse(new FileReader(this.local_storage_path));
        for (Object o: doctors) {
            JSONObject db_doctor = (JSONObject) o;
            doctorList.add(Doctor.fromJSONObjectToDoctor(db_doctor));
        }
        return doctorList;
    }

    public List<Doctor> getDoctors() throws IOException, ParseException {
        return fromJSONToDoctorList();
    }

    public static void main(String[] args) throws IOException, ParseException {
        DataDoctorCenter dataDoctorCenter = new DataDoctorCenter("src/main/config/doctors.json");
        System.out.println(dataDoctorCenter.registerDoctor(System.in));


    }
}
