package controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
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
    private Button withdraw;

    @FXML
    private Button exit;
    @FXML
    private VBox board;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                Image image = null;
                //checking if the images work properly..
                image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../view/icons/blackButt.png")));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(60);
                imageView.setFitHeight(60);
                button.setGraphic(imageView);
                hBox.getChildren().add(button);
                buttsinHbx.add(button);
            }
            buttons.add(buttsinHbx);
            board.getChildren().add(hBox);
            board.setPrefHeight(500);
        }
    }
}
