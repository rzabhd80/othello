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
import model.Piece;
import model.Player;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class BoardController implements Initializable {


    //declaring the board...
    Piece[][] pieces = new Piece[8][8];

    @FXML
    private BorderPane pane;

    @FXML
    private Label player1Name;

    @FXML
    private Label score1;

    @FXML
    private Label turn;

    @FXML
    private Label player2Name;
    @FXML
    private Label score2;

    @FXML
    private Label turn1;

    @FXML
    private VBox board;

    public static String blackButtLink = "../view/icons/blackButt.png";
    public static String greenButtLink = "../view/icons/greenButton.png";
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //.....setting up players...

        Player player1 = new Player();
        Player player2 = new Player();
        player2.setName("reza");
        player1.setName("amir");
        player1Name.setText(player1.getName());
        player2Name.setText(player2.getName());
        player1.setColorPic(greenButtLink);
        player2.setColorPic(blackButtLink);
        pane.getStylesheets().add("../view/style.css");


        //preparing image views for being set....
        Image image1 =null;
        Image image2 =null;
        image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(player1.getColorPic())));
        ImageView imageViewOfPlayer1 = new ImageView(image1);
        imageViewOfPlayer1.setFitWidth(60);
        imageViewOfPlayer1.setFitHeight(60);

        image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(player2.getColorPic())));
        ImageView imageViewOfPlayer2 = new ImageView(image2);
        imageViewOfPlayer2.setFitWidth(60);
        imageViewOfPlayer2.setFitHeight(60);
        // piece.setGraphic!

        //.....setting up pieces of the board.....

        for (int i = 0; i <8 ; i++) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            for (int j = 0; j <8 ; j++) {
                Piece piece = new Piece();
                piece.setPrefHeight(100);
                piece.setPrefWidth(100);
                String id = i+""+j;
                piece.setId(id);
                hBox.getChildren().add(piece);
                pieces[i][j] = piece;
            }
            board.getChildren().add(hBox);
            board.setPrefHeight(500);
        }

        //...finding the four central pieces....
        pieces[3][3].setOwnerPLayer(player1);
    }
}
