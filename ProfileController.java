import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {

    private Scene scene;
    private Stage stage;

    @FXML
    private Button logoutButton;


    @FXML
    private Button changePasswordButton;

    @FXML
    private AnchorPane changePasswordPane;

    @FXML
    private Label changePasswordText;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private Label newPasswordText;

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private Label oldPasswordText;

    @FXML
    private PasswordField repeatPasswordField;

    @FXML
    private Label repeatPasswordText;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label wrongPassword;

    @FXML
    private Label applyChangesLabel;

    @FXML
    private Button applyChangesButton;

    private Stage primaryStage;

    /*public ProfileController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }*/

    @FXML
    void changePasswordButtonAction(ActionEvent event) {
        if (newPasswordField.getText().toString().equals(repeatPasswordField.getText().toString()) && Database.checkPassword(Navigator.getUser().getUserName(), oldPasswordField.getText())) {
            Navigator.getUser().setPassword(newPasswordField.getText());
            wrongPassword.setTextFill(Color.color(0, 1, 0));
            wrongPassword.setText("Succesfully changed");
        }
        else{
            wrongPassword.setTextFill(Color.color(1, 0, 0));
            wrongPassword.setText("Retry to change the password");
        }
    }

    @FXML
    void applyChangesButtonAction(MouseEvent event) { //Buraya ActionEvent girince neden olmuyor da changePasswordButtonAction methodunda oluyor?
        if (!usernameTextField.getText().equals(Navigator.getUser().getUserName()) && Database.checkUsername(usernameTextField.getText())) {
            applyChangesLabel.setTextFill(Color.color(1, 0, 0));
            applyChangesLabel.setText("Username already exist");
        }
        else {
            Navigator.getUser().setFirstName(firstNameTextField.getText());
            Navigator.getUser().setLastName(lastNameTextField.getText());
            Navigator.getUser().setUserName(usernameTextField.getText());

            applyChangesLabel.setTextFill(Color.color(0, 1, 0));
            applyChangesLabel.setText("Successfully Changed");
        }
    }

    @FXML
    void logoutButtonListener(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/LoginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        primaryStage.close();
    }

}
