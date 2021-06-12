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
    @FXML private VBox leftVbox;

    @FXML
    private VBox rightVbox;
    @FXML private Label playerName1;

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
                button.setOpacity(0.2);
                String id = i+""+j;
                button.setId(id);
                if (i==3 && j==3 ||
                        i==4 && j==4) {
                    Image image = null;
                    //checking if the images work properly..
                    image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../view/icons/blackButt.png")));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(56);
                    imageView.setFitHeight(56);
                    button.setGraphic(imageView);
                    button.setOpacity(1.00);
                    button.setStyle("-fx-background-color: transparent");
                }else if (i==3 && j==4 ||
                        i==4 && j==3){
                    Image image = null;
                    //checking if the images work properly..
                    image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../view/icons/greenButton.png")));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(55);
                    imageView.setFitHeight(55);
                    button.setGraphic(imageView);
                    button.setOpacity(1.00);
                    button.setStyle("-fx-background-color: transparent");
                }
                hBox.getChildren().add(button);
                buttsinHbx.add(button);
            }
            buttons.add(buttsinHbx);
            board.getChildren().add(hBox);
            board.setPrefHeight(500);
        }
    }
}