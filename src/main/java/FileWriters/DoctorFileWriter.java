package FileWriters;

import FileReaders.DoctorFileReader;
import UsersTypes.Doctor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DoctorFileWriter {

    private String doctorPath;
    private static DoctorFileWriter instance;

    private DoctorFileWriter(String doctorPath) {
        this.doctorPath = doctorPath;
    }

    public static DoctorFileWriter getInstance(String doctorPath) {
        if (instance == null) {
            instance = new DoctorFileWriter(doctorPath);
        }
        return instance;
    }

    public void write(List<Doctor> doctorList) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray doctorFields = (JSONArray) parser.parse(new FileReader("src/main/config/doctor_fields.json"));

        FileWriter csvWriter;

        BufferedReader br = new BufferedReader(new FileReader(doctorPath));
        if (br.readLine() == null) {
            // File is empty, so we fill the first line with data names
            csvWriter = new FileWriter(doctorPath);
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
            csvWriter = new FileWriter(doctorPath, true);
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
}
