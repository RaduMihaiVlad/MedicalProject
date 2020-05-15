package Server;

import UsersTypes.Client;
import UsersTypes.Doctor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server {

    public static final String URL = "jdbc:mysql://localhost:3306/pao";
    public static Connection connection;

    private static int addClient(Client client) throws SQLException {
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
        return 1;
    }

    private static int addDoctor(Doctor doctor) throws SQLException {
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
        return 1;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        System.out.println("Server is starting");
        connection = DriverManager.getConnection(URL, "root", "");
        System.out.println("Server connected to DB");
        Thread doctorsThread = new Thread() {
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(3333);
                    System.out.println("Doctors socket is running on port 3333");
                    while (true) {
                        Socket s = serverSocket.accept();
                        DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
                        Doctor doctor = convertSocketMessageToDoctor(dataInputStream.readUTF());
                        if (addDoctor(doctor) == 1) {
                            System.out.println("Doctor successfully added to DB");
                        }

                        s.close();
                    }
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread clientsThread = new Thread() {
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(3334);
                    System.out.println("Clients server is running on port 3334");
                    while (true) {
                        Socket s = serverSocket.accept();
                        DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
                        Client client = convertSocketMessageToClient(dataInputStream.readUTF());
                        if (addClient(client) == 1) {
                            System.out.println("Client successfully added to DB");
                        }

                        s.close();
                    }
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        };

        doctorsThread.start();
        clientsThread.start();
    }

    private static Doctor convertSocketMessageToDoctor(String doctorString) {
        String[] componentData = doctorString.split(" ");
        String username = componentData[0];
        String password = componentData[1];
        String email = componentData[2];
        String firstName = componentData[3];
        String lastName = componentData[4];
        int age = Integer.parseInt(componentData[5]);
        int absolvationYear = Integer.parseInt(componentData[6]);
        String phoneNumber = componentData[7];
        String city = componentData[8];
        String country = componentData[9];
        return new Doctor(username, password, email, firstName, lastName, age, absolvationYear, phoneNumber, city, country);
    }

    private static Client convertSocketMessageToClient(String clientString) {
        String[] componentData = clientString.split(" ");
        String username = componentData[0];
        String password = componentData[1];
        String email = componentData[2];
        String firstName = componentData[3];
        String lastName = componentData[4];
        String phoneNumber = componentData[5];
        int age = Integer.parseInt(componentData[6]);
        return new Client(username, password, email, firstName, lastName, phoneNumber, age);
    }

}
