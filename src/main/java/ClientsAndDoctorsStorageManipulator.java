import javafx.scene.chart.PieChart;
import org.json.simple.parser.ParseException;

import java.io.IOException;

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

    public static void main(String[] args) {

    }

}
