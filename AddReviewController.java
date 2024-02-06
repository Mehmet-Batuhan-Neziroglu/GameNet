import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddReviewController {

    @FXML
    private TextField reviewTextField;

    @FXML
    private Button saveButton;

    @FXML
    void saveButtonAction(MouseEvent event) {
        //Database.addReview(Navigator.getUser().getUserID(), , reviewTextField.getText());
    }

}
