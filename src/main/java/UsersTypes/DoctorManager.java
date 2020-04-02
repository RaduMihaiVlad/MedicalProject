package UsersTypes;

import java.util.List;

public class DoctorManager {
    private List<Doctor> doctorList;

    public List<Doctor> getDoctorList() { return doctorList;}
    public int getDoctorLenght() { return doctorList.size();}
}
