package UsersTypes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
}
