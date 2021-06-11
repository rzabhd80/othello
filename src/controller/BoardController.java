package controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BoardController implements Initializable {
    ArrayList<ArrayList<Button>>buttons = new ArrayList<>();
    @FXML
    private BorderPane pane;
    @FXML
    private Label score1;

    @FXML
    private Label score2;

    @FXML
    private Label turn;
    @FXML
    private VBox board;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Image image = new Image("view/icons/test.png");
//        pane.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));

        pane.getStylesheets().add("view/style.css");

        for (int i = 0; i <8 ; i++) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            ArrayList<Button>buttsinHbx = new ArrayList<>();
            for (int j = 0; j <8 ; j++) {
                Button button = new Button();
                button.setPrefHeight(100);
                button.setPrefWidth(100);
                String id = i+""+j;
                button.setId(id);
                hBox.getChildren().add(button);
                buttsinHbx.add(button);
            }
            buttons.add(buttsinHbx);
            board.getChildren().add(hBox);
            board.setPrefHeight(500);
        }
    }
}
