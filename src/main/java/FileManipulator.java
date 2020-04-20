import UsersTypes.Client;
import UsersTypes.Doctor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.print.Doc;
import java.io.*;
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

        JSONParser parser = new JSONParser();
        JSONArray doctorFields = (JSONArray) parser.parse(new FileReader("src/main/config/doctor_fields.json"));

        FileWriter csvWriter;

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        if (br.readLine() == null) {
            // File is empty, so we fill the first line with data names
            csvWriter = new FileWriter(filePath);
            for (Object objectDoctorFields: doctorFields) {
                JSONObject fields =(JSONObject) objectDoctorFields;
                for (int i = 0; i < fields.keySet().toArray().length; ++i) {
                    String key = (String) fields.keySet().toArray()[i];
                    csvWriter.append(key);
                    if (i != fields.keySet().toArray().length - 1) {
                        csvWriter.append(",");
                    } else {
                        csvWriter.append("\n");
                    }
                }
            }

        } else {
            // File already has data inside, so we just append data here
            csvWriter = new FileWriter(filePath, true);
        }


        List<String> keyList = new ArrayList<String>();

        for (Object objectDoctorFields: doctorFields) {
            JSONObject fields =(JSONObject) objectDoctorFields;
            for (int i = 0; i < fields.keySet().toArray().length; ++i) {
                String key = (String) fields.keySet().toArray()[i];
                keyList.add(key);
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


    public void writeClientsToCSV(String filePath, List<Client> clientList) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        JSONArray clientFields = (JSONArray) parser.parse(new FileReader("src/main/config/client_fields.json"));

        FileWriter csvWriter;

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        if (br.readLine() == null) {
            // File is empty, so we fill the first line with data names
            csvWriter = new FileWriter(filePath);
            for (Object objectClientFields: clientFields) {
                JSONObject fields =(JSONObject) objectClientFields;
                for (int i = 0; i < fields.keySet().toArray().length; ++i) {
                    String key = (String) fields.keySet().toArray()[i];
                    csvWriter.append(key);
                    if (i != fields.keySet().toArray().length - 1) {
                        csvWriter.append(",");
                    } else {
                        csvWriter.append("\n");
                    }
                }
            }

        } else {
            // File already has data inside, so we just append data here
            csvWriter = new FileWriter(filePath, true);
        }


        List<String> keyList = new ArrayList<String>();

        for (Object objectClientFields: clientFields) {
            JSONObject fields =(JSONObject) objectClientFields;
            for (int i = 0; i < fields.keySet().toArray().length; ++i) {
                String key = (String) fields.keySet().toArray()[i];
                keyList.add(key);
            }
        }

        for (Client client: clientList) {
            for (int i = 0; i < keyList.size(); ++i) {
                String key = keyList.get(i);
                String item = client.getByField(key);
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
