package FileReaders;

import UsersTypes.Doctor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DoctorFileReader {

    private String doctorPath;
    private static DoctorFileReader instance;

    private DoctorFileReader(String doctorPath) {
        this.doctorPath = doctorPath;
    }

    public static DoctorFileReader getInstance(String doctorPath) {
        if (instance == null) {
            instance = new DoctorFileReader(doctorPath);
        }
        return instance;
    }

    public List<Doctor> read() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(doctorPath));
        List<String> fields = Arrays.asList(csvReader.readLine().split(","));
        String row;
        List<Doctor> doctors = new ArrayList<Doctor>();
        while ((row = csvReader.readLine()) != null) {
            List<String> listDoctor = Arrays.asList(row.split(","));
            HashMap<String, String> mapDoctor = new HashMap<String, String>();
            for (int i = 0; i < listDoctor.size(); ++i) {
                mapDoctor.put(fields.get(i), listDoctor.get(i));
            }
            doctors.add(new Doctor(mapDoctor));
        }

        return doctors;
    }

}
