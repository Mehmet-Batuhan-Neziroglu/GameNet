import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AddReviewController {

    @FXML
    private TextField reviewTextField;

    @FXML
    private Button saveButton;

    @FXML
    void saveButtonAction(MouseEvent event) throws IOException {
        FXMLLoader theLoader = Navigator.clickedOnGameList.get(Navigator.clickedOnGameList.size() - 1);
        ClickedOnGameController controller = theLoader.getController();
        Database.addReview(Navigator.getUser().getUserID(), controller.getGameName() , reviewTextField.getText());

        FXMLLoader theLoaderTwo = new FXMLLoader(getClass().getResource("FXML/CommentBox.fxml"));
        Parent root = theLoaderTwo.load();
        CommentBoxController cbController = theLoaderTwo.getController();

        cbController.setNameLabel(Navigator.getUser().getUserName());
        cbController.setRateLabel(controller.getRate());
        cbController.setCommentLabel(reviewTextField.getText());
        controller.addToTheVBox(cbController.getTheAnchorPane());


    }
}
