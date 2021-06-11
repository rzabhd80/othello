package controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BoardController implements Initializable {
    Button[][] buttons = new Button[8][8];
    @FXML
    private BorderPane pane;

    @FXML
    private Label score1;

    @FXML
    private Label turn;

    @FXML
    private Label score11;

    @FXML
    private Label turn1;

    @FXML
    private VBox board;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i <8 ; i++) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            for (int j = 0; j <8 ; j++) {
                Button button = new Button();
                button.setPrefHeight(100);
                button.setPrefWidth(100);
                String id = i+""+j;
                button.setId(id);
                hBox.getChildren().add(button);
                buttons[i][j]=button;
            }
            board.getChildren().add(hBox);
            board.setPrefHeight(500);
        }

    }
}
