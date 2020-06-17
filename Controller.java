package Adeel;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Controller {

    @FXML
     private Text myScore;

    //getter for the score field
    public Text getMyScore() {
        return myScore;
    }

    //setter for the score field
    public void setMyScore(Text myScore) {
        this.myScore = myScore;
    }


}
