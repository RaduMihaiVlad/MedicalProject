import FileReaders.ClientFileReader;
import FileReaders.DoctorFileReader;
import FileWriters.ClientFileWriter;
import FileWriters.DoctorFileWriter;
import UsersTypes.Client;
import UsersTypes.Doctor;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FileManipulator {

    private ClientFileReader clientFileReader;
    private DoctorFileReader doctorFileReader;
    private ClientFileWriter clientFileWriter;
    private DoctorFileWriter doctorFileWriter;
    private ActionMoves actionMoves;

    private static FileManipulator instance = null;

    private FileManipulator(String doctorSavingPath,
                            String doctorLoadingPath,
                            String clientSavingPath,
                            String clientLoadingPath,
                            String actionPath) throws IOException {

        clientFileReader = ClientFileReader.getInstance(clientLoadingPath);
        doctorFileReader = DoctorFileReader.getInstance(doctorLoadingPath);
        clientFileWriter = ClientFileWriter.getInstance(clientSavingPath);
        doctorFileWriter = DoctorFileWriter.getInstance(doctorSavingPath);
        actionMoves = ActionMoves.getInstance(actionPath);
    }

    public static FileManipulator getInstance() throws IOException {
        if (instance == null) {
            instance = new FileManipulator("src/main/StoringFiles/doctors.csv",
                                           "src/main/StoringFiles/doctors.csv",
                                           "src/main/StoringFiles/clients.csv",
                                           "src/main/StoringFiles/clients.csv",
                                            "src/main/StoringFiles/actions.csv");
        }
        return instance;
    }


    public static FileManipulator getInstance(String doctorLoadingPath,
                                              String doctorSavingPath,
                                              String clientLoadingPath,
                                              String clientSavingPath,
                                              String actionMoves) throws IOException {
        if (instance == null) {
            instance = new FileManipulator(doctorSavingPath, doctorLoadingPath,
                                            clientSavingPath, clientLoadingPath, actionMoves);
        }
        return instance;
    }

    public void writeDoctorsToCSV(List<Doctor> doctorList) throws IOException, ParseException {
        doctorFileWriter.write(doctorList);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        actionMoves.write("doctor_writing", dateFormat.format(date));
    }

    public void writeClientsToCSV(List<Client> clientList) throws IOException, ParseException {
        clientFileWriter.write(clientList);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        actionMoves.write("client_writing", dateFormat.format(date));
    }

    public List<Client> readClients() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        actionMoves.write("client_reading", dateFormat.format(date));
        return clientFileReader.read();
    }

    public List<Doctor> readDoctors() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        actionMoves.write("client_writing", dateFormat.format(date));
        return doctorFileReader.read();
    }


}
