import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class CommentBoxController {

    @FXML
    private AnchorPane theAnchorPane;

    @FXML
    private Label commentLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label rateLabel;

    public AnchorPane getTheAnchorPane(){
        return theAnchorPane;
    }
    public void setCommentLabel(String comment){
        commentLabel.setText(comment);
    }

    public void setNameLabel(String name){
        nameLabel.setText(name);
    }

    public void setRateLabel(String rate){
        rateLabel.setText(rate);
    }


}
