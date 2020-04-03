package UsersTypes;

import java.util.ArrayList;
import java.util.List;

public class DoctorManager {
    private List<Doctor> doctorList;

    DoctorManager() {
        doctorList = new ArrayList<Doctor>();
    }
    DoctorManager(List<Doctor> doctorsList) {
        this.doctorList = doctorsList;
    }


    public void addDoctor(Doctor doctor) {
        doctorList.add(doctor);
    }
    public List<Doctor> getDoctorList() { return doctorList;}
    public int getDoctorListSize() { return doctorList.size();}
    public Boolean containsDoctor(Doctor doctor) {
        for (Doctor doctorElem: doctorList) {
            if (doctorElem.getUsername().equals(doctor.getUsername())) {
                return true;
            }
        }
        return false;
    }

}
