import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javax.accessibility.AccessibleEditableText;
import javax.xml.soap.Text;


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

        HBox hbox = new HBox(grid, submitButton);
        // , passwordHbox, emailHbox, firstNameHbox, lastNameHbox, phoneNumberHbox, ageHbox
//        hbox.setSpacing(10);
//        hbox.setAlignment(Pos.BASELINE_CENTER);
//        hbox.getChildren().add(passwordHbox);


        Scene scene = new Scene(hbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("HBox Experiment 1");

        buttonClient.setOnAction(value ->  {
            createClientSignUpView(primaryStage);
        });

        buttonDoctor.setOnAction(value ->  {
            buttonClient.setVisible(false);
            buttonDoctor.setVisible(false);
        });

        HBox hbox = new HBox(buttonClient, buttonDoctor);
        hbox.setSpacing(10);

        Scene scene = new Scene(hbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
