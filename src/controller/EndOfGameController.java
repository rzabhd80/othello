package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EndOfGameController implements Initializable {

    @FXML
    private TextField announcer;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(BoardController.player1.score>BoardController.player2.score){
            announcer.setText("Congratulations to player1, YOU WON");
        } else {
            announcer.setText("Congratulations to player1, YOU WON");
        }
    }
}
