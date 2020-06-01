import UsersTypes.Client;
import UsersTypes.Doctor;
import Utils.Constants;

import javax.print.Doc;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Server {

    public static final String URL = "jdbc:mysql://localhost:3306/pao";
    public static Connection connection;
    public static String doctorComand = "";
    public static String clientComand = "";

    private static int addClient(Client client, String clientsThread) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into clients(username, password, email, first_name, last_name, phone_number, age, thread_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, client.getUsername());
        preparedStatement.setString(2, client.getPassword());
        preparedStatement.setString(3, client.getEmail());
        preparedStatement.setString(4, client.getFirstName());
        preparedStatement.setString(5, client.getLastName());
        preparedStatement.setString(6, client.getPhoneNumber());
        preparedStatement.setInt(7, client.getAge());
        preparedStatement.setString(8, clientsThread);
        int resultSet = preparedStatement.executeUpdate();
        return 1;
    }

    private static int addDoctor(Doctor doctor, String thread_name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into doctors(username, password, email, first_name, last_name, age, absolvation_year, phone_number, city, country, thread_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
        preparedStatement.setString(11, thread_name);
        int resultSet = preparedStatement.executeUpdate();
        return 1;
    }

    private static int updateDoctor(Doctor doctor) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update doctors set username=?, password=?, email=?, first_name=?, last_name=?, age=?, absolvation_year=?, phone_number=?, city=?, country=? where email=?");
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
        preparedStatement.setString(11, doctor.getEmail());
        int resultSet = preparedStatement.executeUpdate();
        return 1;
    }

    private static int updateClient(Client client) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update clients set username=?, password=?, email=?, first_name=?, last_name=?, phone_number=?, age=? where email=?");
        preparedStatement.setString(1, client.getUsername());
        preparedStatement.setString(2, client.getPassword());
        preparedStatement.setString(3, client.getEmail());
        preparedStatement.setString(4, client.getFirstName());
        preparedStatement.setString(5, client.getLastName());
        preparedStatement.setString(6, client.getPhoneNumber());
        preparedStatement.setInt(7, client.getAge());
        preparedStatement.setString(8, client.getEmail());
        int resultSet = preparedStatement.executeUpdate();
        return 1;
    }

    private static int removeClient(Client client) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from clients where email=?");
        preparedStatement.setString(1, client.getEmail());
        int resultSet = preparedStatement.executeUpdate();
        return 1;
    }

    private static int removeDoctor(Doctor doctor) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from doctors where email=?");
        preparedStatement.setString(1, doctor.getEmail());
        int resultSet = preparedStatement.executeUpdate();
        return 1;
    }

    private static ArrayList<Client> getClients() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from clients");
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Client> clientArrayList = new ArrayList<>();

        while(resultSet.next()) {
            clientArrayList.add(new Client(resultSet.getString("username"),
                                           resultSet.getString("password"),
                                           resultSet.getString("email"),
                                           resultSet.getString("first_name"),
                                           resultSet.getString("last_name"),
                                           resultSet.getString("phone_number"),
                                           resultSet.getInt("age")));
        }
        return clientArrayList;
    }

    private static ArrayList<Doctor> getDoctors() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from doctors");
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Doctor> doctorArrayList = new ArrayList<>();

        while(resultSet.next()) {
            doctorArrayList.add(new Doctor(resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age"),
                    resultSet.getInt("absolvation_year"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("city"),
                    resultSet.getString("country")));
        }
        return doctorArrayList;
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
        doctorComand = componentData[10];
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
        clientComand = componentData[7];
        return new Client(username, password, email, firstName, lastName, phoneNumber, age);
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        System.out.println("Server is starting");
        connection = DriverManager.getConnection(URL, "root", "");
        System.out.println("Server connected to DB");
        Thread doctorsThread = new Thread();
        Thread finalDoctorsThread = doctorsThread;
        doctorsThread = new Thread() {
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(3333);
                    System.out.println("Doctors socket is running on port 3333");
                    while (true) {
                        Socket s = serverSocket.accept();
                        DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        java.util.Date date = new Date();
                        ActionMoves actionMoves = ActionMoves.getInstance("src/main/StoringFiles/actions.csv");

                        String msg = dataInputStream.readUTF();
                        if (msg.equals(Constants.GET)) {
                            ArrayList<Doctor> doctorList = getDoctors();
                            for (Doctor doctor: doctorList) {
                                System.out.println("Doctor is: " + doctor.toString());
                            }
                            actionMoves.write("get_doctor_db", dateFormat.format(date), finalDoctorsThread.getName());
                            continue;
                        }
                        Doctor doctor = convertSocketMessageToDoctor(msg);
                        if (doctorComand.equals(Constants.ADD)) {
                            if (addDoctor(doctor, finalDoctorsThread.getName()) == 1) {
                                System.out.println("Doctor successfully added to DB");
                                actionMoves.write("add_doctor_db", dateFormat.format(date), finalDoctorsThread.getName());
                            }
                        } else if (doctorComand.equals(Constants.REMOVE)) {
                            if (removeDoctor(doctor) == 1) {
                                System.out.println("Doctor successfully removed from DB");
                                actionMoves.write("remove_doctor_db", dateFormat.format(date), finalDoctorsThread.getName());
                            }
                        } else if (doctorComand.equals(Constants.UPDATE)) {
                            if (updateDoctor(doctor) == 1) {
                                System.out.println("Doctor successfully updated");
                                actionMoves.write("update_doctor_db", dateFormat.format(date), finalDoctorsThread.getName());

                            }
                        }

                        s.close();
                    }
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread clientsThread = new Thread();

        Thread finalClientsThread = clientsThread;
        clientsThread = new Thread() {
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(3334);
                    System.out.println("Clients server is running on port 3334");
                    while (true) {
                        Socket s = serverSocket.accept();
                        DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
                        String msg = dataInputStream.readUTF();

                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        java.util.Date date = new Date();
                        ActionMoves actionMoves = ActionMoves.getInstance("src/main/StoringFiles/actions.csv");
                        if (msg.equals(Constants.GET)) {
                            ArrayList<Client> clientList = getClients();
                            for (Client client: clientList) {
                                System.out.println("Client is: " + client.toString());
                                actionMoves.write("get_clients_db", dateFormat.format(date), finalClientsThread.getName());
                            }
                            continue;
                        }
                        Client client = convertSocketMessageToClient(msg);
                        if (clientComand.equals(Constants.ADD)) {
                            if (addClient(client, finalClientsThread.getName()) == 1) {
                                System.out.println("Client successfully added to DB");
                                actionMoves.write("add_clients_db", dateFormat.format(date), finalClientsThread.getName());
                            }
                        } else if (clientComand.equals(Constants.REMOVE)) {
                            if (removeClient(client) == 1) {
                                System.out.println("Client successfully removed from DB");
                                actionMoves.write("remove_clients_db", dateFormat.format(date), finalClientsThread.getName());
                            }
                        } else if (clientComand.equals(Constants.UPDATE)) {
                            if (updateClient(client) == 1) {
                                System.out.println("Client successfully updated");
                                actionMoves.write("update_clients_db", dateFormat.format(date), finalClientsThread.getName());
                            }
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

}
