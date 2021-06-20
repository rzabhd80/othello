package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Player;
import sun.applet.Main;;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.BoardController.*;

public class SignUpController implements Initializable {
    /**
     * once the app starts running , the first thing popping up will be sign up stage and players have to set
     * their name , repetitive names are not allowed
     *
     * @author rezaBh
     */
    @FXML
    private TextField name1;

    @FXML
    private TextField name2;

    @FXML
    private Button submitButton;

    @FXML
    private Label error;


    public static Stage thisStage;
    public TextField getName1() {
        return name1;
    }

    public void setName1(TextField name1) {
        this.name1 = name1;
    }

    public TextField getName2() {
        return name2;
    }

    public void setName2(TextField name2) {
        this.name2 = name2;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(Button submitButton) {
        this.submitButton = submitButton;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Player.readFile();
        player1 = new Player();
        player2 = new Player();
        error.setText("");
        submitButton.setOnAction(event -> {
            if (name1.getText().isEmpty() || name2.getText().isEmpty()) {
                error.setText("please fill all the blanks");
                error.setTextFill(Color.RED);
            } else {
                boolean found1 = false, found2 = false;
                for (Player player : BoardController.players) {
                    if (player.name.equals(name1.getText())) {
                        found1 = true;
                        player1 = player;
                    }
                    if (player.name.equals(name2.getText())) {
                        player2 = player;
                        found2 = true;
                    }
                }
                if (!found1 && !found2) {

                    player1.setName(name1.getText());
                    player2.setName(name2.getText());
                    BoardController.players.add(player1);
                    BoardController.players.add(player2);
                } else {
                    player1.setName(name1.getText());
                    player2.setName(name2.getText());
                }
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/board.fxml"));
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BoardController.boardStage = new Stage();
                boardStage.setScene(new Scene(fxmlLoader.getRoot()));
                boardStage.show();
                thisStage.close();
            }
        });
    }
}