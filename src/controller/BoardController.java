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
import model.Color;
import model.Piece;
import model.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class BoardController implements Initializable {
    private String greenButtLink = "../view/icons/greenButton.png";
    private String blackButtLink = "../view/icons/blackButt.png";
    Piece[][]pieces = new Piece[8][8];
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
    @FXML
    private Label playerName2;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //.....setting up players...

        Player player1 = new Player();
        Player player2 = new Player();
        playerName1.setText(player1.getName());
        playerName2.setText(player2.getName());
        Color colorPLayer1 = new Color(greenButtLink);
        Color colorPLayer2 = new Color(blackButtLink);
        player1.setColorPic(colorPLayer1);
        player2.setColorPic(colorPLayer2);
        pane.getStylesheets().add("../view/style.css");


        //preparing image views for being set....
        Image image1 =null;
        Image image2 =null;
        image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(player1.getColorPic().getPicLink())));
        ImageView imageViewOfPlayer1 = new ImageView(image1);
        imageViewOfPlayer1.setFitWidth(56);
        imageViewOfPlayer1.setFitHeight(56);

        image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(player2.getColorPic().getPicLink())));
        ImageView imageViewOfPlayer2 = new ImageView(image2);
        imageViewOfPlayer2.setFitWidth(60);
        imageViewOfPlayer2.setFitHeight(60);
        // piece.setGraphic!


        for (int i = 0; i <8 ; i++) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            ArrayList<Button>buttsinHbx = new ArrayList<>();
            for (int j = 0; j <8 ; j++) {
                Piece piece = new Piece();
                piece.setPrefHeight(100);
                piece.setPrefWidth(100);
                piece.setOpacity(0.2);
                String id = i+""+j;
                piece.setId(id);
                if (i==3 && j==3 ||
                        i==4 && j==4) {
                    Image image = null;
                    //checking if the images work properly..
                    piece.setGraphic(imageViewOfPlayer1);
                    piece.setOpacity(1.00);
                    piece.setStyle("-fx-background-color: transparent");
                }else if (i==3 && j==4 ||
                        i==4 && j==3){
                    Image image = null;
                    piece.setGraphic(imageViewOfPlayer2);
                    piece.setOpacity(1.00);
                    piece.setStyle("-fx-background-color: transparent");
                    image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../view/icons/greenButton.png")));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(55);
                    imageView.setFitHeight(55);
                    piece.setGraphic(imageViewOfPlayer2);
                    piece.setOpacity(1.00);
                    piece.setStyle("-fx-background-color: transparent");
                }
                pieces[i][j] = piece;
                hBox.getChildren().add(piece);
                buttsinHbx.add(piece);
            }
            board.getChildren().add(hBox);
            board.setPrefHeight(500);
        }
    }
}