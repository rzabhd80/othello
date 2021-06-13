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
import model.Status;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class BoardController implements Initializable {
    @FXML private BorderPane pane;
    @FXML private Label score1;
    @FXML private Label score2;
    @FXML private Label turn;
    @FXML private Button withdraw;
    @FXML private VBox board;
    @FXML private VBox leftVbox;
    @FXML private VBox rightVbox;
    @FXML private Label playerName1;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPieces();
        System.out.println();
    }



    /**
     * it will be called only at the beginning of the game.
     * and creates the pieces for start playing the game.
     * only 4 pieces is selected. 2 green and 2 white at the center of board.
     * so the other pieces are unselectable
     * @author AmirMahdi
     */
    private final Piece[][] pieces = new Piece[8][8];
    private void initPieces(){
    for (int i = 0; i <8 ; i++) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        for (int j = 0; j <8 ; j++) {
            if (i==3 && j==3 || i==4 && j==4) {
                pieces[i][j] = new Piece(Status.selected, Color.green);
                hBox.getChildren().add(pieces[i][j]);
//                hBox.getChildren().add(new Piece(Status.selected, Color.green));
            }
            else if (i==3 && j==4 || i==4 && j==3){
                pieces[i][j] = new Piece(Status.selected, Color.white);
                hBox.getChildren().add(pieces[i][j]);
//                hBox.getChildren().add(new Piece(Status.selected, Color.white));
            }
            else {
                pieces[i][j] = new Piece(Status.unselectable);
                hBox.getChildren().add(pieces[i][j]);
//                hBox.getChildren().add(new Piece(Status.unselectable));
            }
        }
        board.getChildren().add(hBox);


    }
}
}