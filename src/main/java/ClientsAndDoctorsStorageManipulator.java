import Centers.DataClientCenter;
import Centers.DataDoctorCenter;
import UsersTypes.Client;
import UsersTypes.Doctor;
import UsersTypes.User;
import Utils.Constants;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import sun.plugin2.message.Message;

import javax.print.Doc;
import java.io.*;
import java.net.Socket;
import java.util.List;

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
        return dataDoctorCenter.registerDoctorFromJSONObjectUser(jsonUser, inputStream);
    }

    public static void main(String[] args) throws IOException, ParseException {
        ClientsAndDoctorsStorageManipulator clientsAndDoctorsStorageManipulator =
                new ClientsAndDoctorsStorageManipulator("src/main/config/clients.json",
                                                        "src/main/config/doctors.json");

        List<Doctor> doctorList = clientsAndDoctorsStorageManipulator.dataDoctorCenter.getDoctors();
        List<Client> clientList = clientsAndDoctorsStorageManipulator.dataClientCenter.getClients();
        FileManipulator fileManipulator = FileManipulator.getInstance("src/main/StoringFiles/doctors.csv",
                                                                        "src/main/StoringFiles/doctors.csv",
                                                                        "src/main/StoringFiles/clients.csv",
                                                                        "src/main/StoringFiles/clients.csv",
                                                                        "src/main/StoringFiles/actions.csv");
//        fileManipulator.writeDoctorsToCSV(doctorList);
//        fileManipulator.writeClientsToCSV(clientList);
        clientList = fileManipulator.readClients();
        doctorList = fileManipulator.readDoctors();

        clientList.add(new Client("c11", "c12", "c13@yahoo.com", "c14", "c15", "12345", 23));
        clientList.add(new Client("c21", "c22", "c23@yahoo.com", "c24", "c25", "12345", 33));
        clientList.add(new Client("c31", "c32", "c33@yahoo.com", "c34", "c35", "12345", 43));
        clientList.add(new Client("c41", "c42", "c43@yahoo.com", "c44", "c45", "12345", 53));
        clientList.add(new Client("c51", "c52", "c53@yahoo.com", "c54", "c55", "12345", 63));
        clientList.add(new Client("c61", "c62", "c63@yahoo.com", "c64", "c65", "12345", 73));
        clientList.add(new Client("c71", "c72", "c73@yahoo.com", "c74", "c75", "12345", 83));
        clientList.add(new Client("c81", "c82", "c83@yahoo.com", "c84", "c85", "12345", 93));
        clientList.add(new Client("c91", "c92", "c93@yahoo.com", "c94", "c95", "12345", 13));

        doctorList.add(new Doctor("d11", "d12", "d13@yahoo.com", "d14", "d15", 12345, 13, "1234", "city1", "country1"));
        doctorList.add(new Doctor("d21", "d22", "d23@yahoo.com", "d24", "d25", 12345, 13, "1234", "city1", "country1"));
        doctorList.add(new Doctor("d31", "d32", "d33@yahoo.com", "d34", "d35", 12345, 13, "1234", "city1", "country1"));
        doctorList.add(new Doctor("d41", "d42", "d43@yahoo.com", "d44", "d45", 12345, 13, "1234", "city1", "country1"));
        doctorList.add(new Doctor("d51", "d52", "d53@yahoo.com", "d54", "d55", 12345, 13, "1234", "city1", "country1"));
        doctorList.add(new Doctor("d61", "d62", "d63@yahoo.com", "d64", "d65", 12345, 13, "1234", "city1", "country1"));
        doctorList.add(new Doctor("d71", "d72", "d73@yahoo.com", "d74", "d75", 12345, 13, "1234", "city1", "country1"));
//
//        fileManipulator.writeClientsToCSV(clientList);
//        fileManipulator.writeDoctorsToCSV(doctorList);

        Socket socket = new Socket("localhost", 3333);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        Doctor doctor = doctorList.get(1);
        Client client = clientList.get(0);

        dataOutputStream.writeUTF(doctor.toString() + " " + Constants.REMOVE);
        dataOutputStream.flush();

    }

}
