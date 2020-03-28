import Centers.DataClientCenter;
import Centers.DataDoctorCenter;
import UsersTypes.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;

public class ClientsAndDoctorsStorageManipulator {
    DataClientCenter dataClientCenter;
    DataDoctorCenter dataDoctorCenter;

    public ClientsAndDoctorsStorageManipulator(String client_path, String doctor_path) {
        dataClientCenter = new DataClientCenter(client_path);
        dataDoctorCenter = new DataDoctorCenter(doctor_path);
    }

    public boolean userAlreadyExists(User user) throws IOException, ParseException {
        return dataClientCenter.userAlreadyExists(user) &&
                dataDoctorCenter.userAlreadyExists(user);
    }

    public String registerAsClient(InputStream inputStream) throws IOException, ParseException {
        JSONObject jsonUser = dataClientCenter.fetchUserRegisterData(inputStream);
        if (!dataClientCenter.isValidUser(jsonUser)) {
            return dataClientCenter.getUserValidationError(jsonUser);
        }
        if (!dataDoctorCenter.isValidUser(jsonUser)) {
            return dataDoctorCenter.getUserValidationError(jsonUser);
        }
        return dataClientCenter.registerClientFromJSONObjectUser(jsonUser, inputStream);
    }

    public String registerAsDoctor(InputStream inputStream) throws IOException, ParseException {
        JSONObject jsonUser = dataDoctorCenter.fetchUserRegisterData(inputStream);
        if (!dataClientCenter.isValidUser(jsonUser)) {
            return dataClientCenter.getUserValidationError(jsonUser);
        }
        if (!dataDoctorCenter.isValidUser(jsonUser)) {
            return dataDoctorCenter.getUserValidationError(jsonUser);
        }
        return dataDoctorCenter.registerClientFromJSONObjectUser(jsonUser, inputStream);
    }

    public static void main(String[] args) throws IOException, ParseException {
        ClientsAndDoctorsStorageManipulator clientsAndDoctorsStorageManipulator =
                new ClientsAndDoctorsStorageManipulator("src/main/config/clients.json",
                                                        "src/main/config/doctors.json");

        System.out.println(clientsAndDoctorsStorageManipulator.registerAsClient(System.in));

    }

}
