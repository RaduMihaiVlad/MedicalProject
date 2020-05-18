import UsersTypes.Doctor;
import Utils.Constants;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import UsersTypes.Client;

import javax.accessibility.AccessibleEditableText;
import javax.xml.soap.Text;
import java.io.IOException;


public class Graphics extends Application {

    Button buttonClient = new Button("Sign up as Client");
    Button buttonDoctor = new Button("Sign up as Doctor");

    public void createClientSignUpView(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);


        Label usernameLabel = new Label("Username:");
        TextField usernameTextField = new TextField();
        HBox usernameHbox = new HBox(usernameLabel, usernameTextField);
        usernameHbox.setSpacing(46);
        GridPane.setConstraints(usernameHbox, 0, 0);
        grid.getChildren().add(usernameHbox);

        Label passwordLabel = new Label("Password:");
        TextField passwordTextField = new TextField();
        HBox passwordHbox = new HBox(passwordLabel, passwordTextField);
        passwordHbox.setSpacing(50);
        GridPane.setConstraints(passwordHbox, 0, 1);
        grid.getChildren().add(passwordHbox);

        Label emailLabel = new Label("Email:");
        TextField emailTextField = new TextField ();
        HBox emailHbox = new HBox(emailLabel, emailTextField);
        emailHbox.setSpacing(76);
        GridPane.setConstraints(emailHbox, 0, 2);
        grid.getChildren().add(emailHbox);


        Label firstNameLabel = new Label("First Name:");
        TextField firstNameTextField = new TextField ();
        HBox firstNameHbox = new HBox(firstNameLabel, firstNameTextField);
        firstNameHbox.setSpacing(40);
        GridPane.setConstraints(firstNameHbox, 0, 3);
        grid.getChildren().add(firstNameHbox);


        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameTextField = new TextField ();
        HBox lastNameHbox = new HBox(lastNameLabel, lastNameTextField);
        lastNameHbox.setSpacing(40);
        GridPane.setConstraints(lastNameHbox, 0, 4);
        grid.getChildren().add(lastNameHbox);



        Label phoneNumberLabel = new Label("Phone Number:");
        TextField phoneNumberTextField = new TextField ();
        HBox phoneNumberHbox = new HBox(phoneNumberLabel, phoneNumberTextField);
        phoneNumberHbox.setSpacing(10);
        GridPane.setConstraints(phoneNumberHbox, 0, 5);
        grid.getChildren().add(phoneNumberHbox);


        Label ageLabel = new Label("Age:");
        TextField ageTextField = new TextField ();
        HBox ageHbox = new HBox(ageLabel, ageTextField);
        ageHbox.setSpacing(85);
        GridPane.setConstraints(ageHbox, 0, 6);
        grid.getChildren().add(ageHbox);


        Button submitButton = new Button("SUBMIT");

        submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                String email = emailTextField.getText();
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String phoneNumber = phoneNumberTextField.getText();
                int age = Integer.parseInt(ageTextField.getText());
                Client client = new Client(username, password, email, firstName, lastName, phoneNumber, age);

                try {
                    ClientsAndDoctorsStorageManipulator.modifyClientToDatabase(client, Constants.ADD);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        Button backButton = new Button("BACK");
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createStartMenuView(primaryStage);
            }
        });

        HBox hbox = new HBox(grid, submitButton, backButton);
        hbox.setSpacing(10);

        Scene scene = new Scene(hbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void createDoctorSignUpView(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);


        Label usernameLabel = new Label("Username:");
        TextField usernameTextField = new TextField();
        HBox usernameHbox = new HBox(usernameLabel, usernameTextField);
        usernameHbox.setSpacing(46);
        GridPane.setConstraints(usernameHbox, 0, 0);
        grid.getChildren().add(usernameHbox);

        Label passwordLabel = new Label("Password:");
        TextField passwordTextField = new TextField();
        HBox passwordHbox = new HBox(passwordLabel, passwordTextField);
        passwordHbox.setSpacing(50);
        GridPane.setConstraints(passwordHbox, 0, 1);
        grid.getChildren().add(passwordHbox);

        Label emailLabel = new Label("Email:");
        TextField emailTextField = new TextField ();
        HBox emailHbox = new HBox(emailLabel, emailTextField);
        emailHbox.setSpacing(76);
        GridPane.setConstraints(emailHbox, 0, 2);
        grid.getChildren().add(emailHbox);


        Label firstNameLabel = new Label("First Name:");
        TextField firstNameTextField = new TextField ();
        HBox firstNameHbox = new HBox(firstNameLabel, firstNameTextField);
        firstNameHbox.setSpacing(40);
        GridPane.setConstraints(firstNameHbox, 0, 3);
        grid.getChildren().add(firstNameHbox);


        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameTextField = new TextField ();
        HBox lastNameHbox = new HBox(lastNameLabel, lastNameTextField);
        lastNameHbox.setSpacing(40);
        GridPane.setConstraints(lastNameHbox, 0, 4);
        grid.getChildren().add(lastNameHbox);



        Label phoneNumberLabel = new Label("Phone Number:");
        TextField phoneNumberTextField = new TextField ();
        HBox phoneNumberHbox = new HBox(phoneNumberLabel, phoneNumberTextField);
        phoneNumberHbox.setSpacing(10);
        GridPane.setConstraints(phoneNumberHbox, 0, 5);
        grid.getChildren().add(phoneNumberHbox);


        Label ageLabel = new Label("Age:");
        TextField ageTextField = new TextField ();
        HBox ageHbox = new HBox(ageLabel, ageTextField);
        ageHbox.setSpacing(85);
        GridPane.setConstraints(ageHbox, 0, 6);
        grid.getChildren().add(ageHbox);

        Label absolvationYearLabel = new Label("Absolvation Year:");
        TextField absolvationYearField = new TextField ();
        HBox absolvationYearHbox = new HBox(absolvationYearLabel, absolvationYearField);
        absolvationYearHbox.setSpacing(1);
        GridPane.setConstraints(absolvationYearHbox, 0, 7);
        grid.getChildren().add(absolvationYearHbox);

        Label cityLabel = new Label("City:");
        TextField cityTextField = new TextField ();
        HBox cityHbox = new HBox(cityLabel, cityTextField);
        cityHbox.setSpacing(87);
        GridPane.setConstraints(cityHbox, 0, 8);
        grid.getChildren().add(cityHbox);

        Label countryLabel = new Label("Country:");
        TextField countryTextField = new TextField ();
        HBox countryHbox = new HBox(countryLabel, countryTextField);
        countryHbox.setSpacing(60);
        GridPane.setConstraints(countryHbox, 0, 9);
        grid.getChildren().add(countryHbox);

        Button submitButton = new Button("SUBMIT");
        Button backButton = new Button("BACK");

        submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                String email = emailTextField.getText();
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String phoneNumber = phoneNumberTextField.getText();
                int age = Integer.parseInt(ageTextField.getText());
                int absolvationYear = Integer.parseInt(absolvationYearField.getText());
                String city = cityTextField.getText();
                String country = countryTextField.getText();
                Doctor doctor = new Doctor(username, password, email, firstName, lastName, age, absolvationYear, phoneNumber, city, country);

                try {
                    ClientsAndDoctorsStorageManipulator.modifyDoctorToDatabase(doctor, Constants.ADD);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createStartMenuView(primaryStage);
            }
        });

        HBox hbox = new HBox(grid, submitButton, backButton);
        hbox.setSpacing(10);

        Scene scene = new Scene(hbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void createStartMenuView(Stage primaryStage) {
        primaryStage.setTitle("Medical Project");

        buttonClient.setOnAction(value ->  {
            createClientSignUpView(primaryStage);
        });

        buttonDoctor.setOnAction(value ->  {
            createDoctorSignUpView(primaryStage);
        });

        HBox hbox = new HBox(buttonClient, buttonDoctor);
        hbox.setSpacing(10);

        Scene scene = new Scene(hbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        createStartMenuView(primaryStage);

    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
