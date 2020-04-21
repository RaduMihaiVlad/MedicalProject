import FileReaders.ClientFileReader;
import FileReaders.DoctorFileReader;
import FileWriters.ClientFileWriter;
import FileWriters.DoctorFileWriter;
import UsersTypes.Client;
import UsersTypes.Doctor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FileManipulator {

    private ClientFileReader clientFileReader;
    private DoctorFileReader doctorFileReader;
    private ClientFileWriter clientFileWriter;
    private DoctorFileWriter doctorFileWriter;

    private static FileManipulator instance = null;

    private FileManipulator(String doctorSavingPath,
                            String doctorLoadingPath,
                            String clientSavingPath,
                            String clientLoadingPath) {

        clientFileReader = ClientFileReader.getInstance(clientLoadingPath);
        doctorFileReader = DoctorFileReader.getInstance(doctorLoadingPath);
        clientFileWriter = ClientFileWriter.getInstance(clientSavingPath);
        doctorFileWriter = DoctorFileWriter.getInstance(doctorSavingPath);
    }

    public static FileManipulator getInstance() {
        if (instance == null) {
            instance = new FileManipulator("src/main/StoringFiles/doctors.csv",
                                           "src/main/StoringFiles/doctors.csv",
                                           "src/main/StoringFiles/clients.csv",
                                           "src/main/StoringFiles/clients.csv");
        }
        return instance;
    }


    public static FileManipulator getInstance(String doctorLoadingPath,
                                              String doctorSavingPath,
                                              String clientLoadingPath,
                                              String clientSavingPath) {
        if (instance == null) {
            instance = new FileManipulator(doctorSavingPath, doctorLoadingPath,
                                            clientSavingPath, clientLoadingPath);
        }
        return instance;
    }

    public void writeDoctorsToCSV(List<Doctor> doctorList) throws IOException, ParseException {
        doctorFileWriter.write(doctorList);
    }

    public void writeClientsToCSV(List<Client> clientList) throws IOException, ParseException {
        clientFileWriter.write(clientList);
    }

    public List<Client> readClients() throws IOException {
        return clientFileReader.read();
    }

    public List<Doctor> readDoctors() throws IOException {
        return doctorFileReader.read();
    }


}
