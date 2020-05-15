package Server;

import UsersTypes.Client;
import UsersTypes.Doctor;

import java.sql.*;

public class Server {

    public static final String URL = "jdbc:mysql://localhost:3306/pao";
    public static Connection connection;

    private void addClient(Client client) throws SQLException {
        Statement stm = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into clients(username, password, email, first_name, last_name, phone_number, age) VALUES (?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, client.getUsername());
        preparedStatement.setString(2, client.getPassword());
        preparedStatement.setString(3, client.getEmail());
        preparedStatement.setString(4, client.getFirstName());
        preparedStatement.setString(5, client.getLastName());
        preparedStatement.setString(6, client.getPhoneNumber());
        preparedStatement.setInt(7, client.getAge());
        int resultSet = preparedStatement.executeUpdate();
    }

    private void addDoctor(Doctor doctor) throws SQLException {
        Statement stm = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into doctors(username, password, email, first_name, last_name, age, absolvation_year, phone_number, city, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, doctor.getUsername());
        preparedStatement.setString(2, doctor.getPassword());
        preparedStatement.setString(3, doctor.getEmail());
        preparedStatement.setString(4, doctor.getFirstName());
        preparedStatement.setString(5, doctor.getLastName());
        preparedStatement.setInt(6, doctor.getAge());
        preparedStatement.setInt(7, doctor.getAbsolvationYear());
        preparedStatement.setString(8, doctor.getPhoneNumber());
        preparedStatement.setString(9, doctor.getCity());
        preparedStatement.setString(10, doctor.getCountry());
        int resultSet = preparedStatement.executeUpdate();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        connection = DriverManager.getConnection(URL, "root", "");
    }

}
