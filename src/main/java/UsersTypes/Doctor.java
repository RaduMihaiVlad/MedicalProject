package UsersTypes;

import org.json.simple.JSONObject;

import java.util.HashMap;

public class Doctor extends User {

    private String phoneNumber;
    private int age;
    private String city;
    private String country;
    private int absolvationYear;

    public Doctor(String username, String password, String email, String firstName,
                  String lastName, int age, int absolvationYear, String phoneNumber, String city, String country) {
        super(username, password, email, firstName, lastName);
        this.age = age;
        this.absolvationYear = absolvationYear;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.country = country;
    }

    public Doctor(HashMap<String, String> doctorMap) {
        super(doctorMap.get("username"), doctorMap.get("password"),
                doctorMap.get("email"), doctorMap.get("firstname"), doctorMap.get("lastname"));
        this.age = Integer.parseInt(doctorMap.get("age"));
        this.phoneNumber = doctorMap.get("phone_number");
        this.absolvationYear = Integer.parseInt(doctorMap.get("absolvation_year"));
        this.city = doctorMap.get("city");
        this.country = doctorMap.get("country");
    }

    public String getCountry() { return country;}
    public void setCountry(String country) { this.country = country;}

    public String getCity() { return city;}
    public void setCity(String city) { this.city = city;}

    public int getAge() { return age;}
    public void setAge(int age) { this.age = age;}

    public static Doctor fromJSONObjectToDoctor(JSONObject jsonDoctor) {
        return new Doctor(jsonDoctor.get("username").toString(),
                jsonDoctor.get("password").toString(),
                jsonDoctor.get("email").toString(),
                jsonDoctor.get("firstName").toString(),
                jsonDoctor.get("lastName").toString(),
                Integer.parseInt(jsonDoctor.get("age").toString()),
                Integer.parseInt(jsonDoctor.get("absolvation_year").toString()),
                jsonDoctor.get("phoneNumber").toString(),
                jsonDoctor.get("city").toString(),
                jsonDoctor.get("country").toString());

    }

    public String getPhoneNumber() { return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber;}

    public int getAbsolvationYear() {
        return absolvationYear;
    }

    public void setAbsolvationYear(int absolvationYear) {
        this.absolvationYear = absolvationYear;
    }

    public String getByField(String field) {
        if (field.equals("username")) {
            return getUsername();
        }
        if (field.equals("password")) {
            return getPassword();
        }
        if (field.equals("email")) {
            return getEmail();
        }
        if (field.equals("firstname")) {
            return getFirstName();
        }
        if (field.equals("lastname")) {
            return getLastName();
        }
        if (field.equals("phone_number")) {
            return getPhoneNumber();
        }
        if (field.equals("age")) {
            return String.valueOf(getAge());
        }
        if (field.equals("absolvation_year")) {
            return String.valueOf(getAbsolvationYear());
        }
        if (field.equals("city")) {
            return getCity();
        }
        if (field.equals("country")) {
            return getCountry();
        }
        return "Unknown field";
    }
}
