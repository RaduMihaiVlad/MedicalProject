import UsersTypes.Doctor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.print.Doc;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManipulator {

    private static FileManipulator instance = null;

    private FileManipulator() {}

    public static FileManipulator getInstance() {
        if (instance == null) {
            instance = new FileManipulator();
        }
        return instance;
    }

    public void writeDoctorsToCSV(String filePath, List<Doctor> doctorList) throws IOException, ParseException {
        FileWriter csvWriter = new FileWriter(filePath);

        List<String> keyList = new ArrayList<String>();
        JSONParser parser = new JSONParser();
        JSONArray doctorFields = (JSONArray) parser.parse(new FileReader("src/main/config/doctor_fields.json"));
        for (Object objectDoctorFields: doctorFields) {
            JSONObject fields =(JSONObject) objectDoctorFields;
            for (int i = 0; i < fields.keySet().toArray().length; ++i) {
                String key = (String) fields.keySet().toArray()[i];
                keyList.add(key);
                String value = (String) fields.get(key);
                csvWriter.append(key);
                if (i != fields.keySet().toArray().length - 1) {
                    csvWriter.append(",");
                } else {
                    csvWriter.append("\n");
                }
            }
        }

        for (Doctor doctor: doctorList) {
            for (int i = 0; i < keyList.size(); ++i) {
                String key = keyList.get(i);
                String item = doctor.getByField(key);
                csvWriter.append(item);
                if (i != keyList.size() - 1) {
                    csvWriter.append(",");
                } else {
                    csvWriter.append("\n");
                }
            }
        }

        csvWriter.flush();
        csvWriter.close();
    }

}
