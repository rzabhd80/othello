package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Color;
import model.Piece;
import model.Player;
import model.Status;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {
    @FXML
    private Label score1;
    @FXML
    private Label score2;
    @FXML
    private Label turn1;
    @FXML
    private Label turn2;
    @FXML
    private VBox board;
    @FXML
    private Label playerName1;
    @FXML
    private Label playerName2;

    /**
     * this global array is meant to store all the pieces of the field
     *
     * @author rezaBh
     */
    private final Piece[][] pieces = new Piece[8][8];

    /**
     * this method is used for maintaining the possible selectable  pieces
     * we gotta check the row,left and right of the piece
     * it makes the pieces selectable or not selectable
     * if it returns true, it means that given piece is selectable
     *
     * @param piece
     * @author rezaBH
     */

    private void checkingTheRightRowForBlack(Piece piece, int i, int j) {
        System.out.println(i + "" + j);
        if (piece.getPieceColor() != null) {
            if (piece.getPieceColor().equals(Color.green)) {
                //if the next piece has diff color or its null, it doesnt need
                if (pieces[i][j + 1].getStatus().equals(Status.selected)) {
                    if (!pieces[i][j + 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i][j + 1].getStatus().
                            equals(Status.selected)) {
                        //checks all next buttons to see if any of them is white
                        for (int k = j; k < 8; k++) {
                            if (foundWhitePiece(pieces[i][k])) {
                                // checking if there is a empty cell after white buttons
                                for (int p = k + 1; p < 8; p++) {
                                    if (pieces[i][p].getStatus().equals(Status.unselectable)) {
                                        pieces[i][p].setPieceSelectable();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        //then we need to check previous pieces
                        return;
                    }
                }
            } else {
                return;
            }
        }
    }

    private void checkingTheRightRowForWhite(Piece piece, int i, int j) {
        if (piece.getPieceColor().equals(Color.white)) {
            //if the next or previous piece has diff color return false
            if (pieces[i][j + 1].getPieceColor() != null) {
                if (!pieces[i][j + 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i][j + 1].getPieceColor() != null) {
                    //checks all next buttons to see if any of them is white
                    for (int k = i; k < 8; k++) {
                        if (foundGreenPiece(pieces[k][j])) {
                            // checking if there is a empty cell after white buttons
                            for (int p = k; p < 8; p++) {
                                if (pieces[p][j].getStatus().equals(Status.unselectable)) {
                                    pieces[p][j].setStatus(Status.selectable);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * this methods are meant to check if the given piece has the color of green
     *
     * @param piece
     * @return
     * @author reza bh
     */
    public boolean foundGreenPiece(Piece piece) {
        if (piece.getPieceColor().equals(Color.green))
            return true;
        return false;
    }

    public boolean foundWhitePiece(Piece piece) {
        if (piece.getPieceColor().equals(Color.white))
            return true;
        return false;
    }

    /**
     * flipper method is used for flipping the coins
     *
     * @author reza BH
     */
    public void flipper() {

    }

    /**
     * setting the selectables of table
     *
     * @author reza Bh
     */
    public void setSelectables() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                checkingTheRightRowForBlack(pieces[i][j], i, j);
                //BUG HERE
                //checkingTheRightRowForWhite(pieces[i][j], i, j);
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Player player1 = new Player("reza");
        Player player2 = new Player("amir");
        playerName1.setText(player1.getName());
        playerName2.setText(player2.getName());
        initPieces();
        setSelectables();

    }


    /**
     * it will be called only at the beginning of the game.
     * and creates the pieces for start playing the game.
     * only 4 pieces is selected. 2 green and 2 white at the center of board.
     * so the other pieces are unselectable
     *
     * @author AmirMahdi
     */
    private void initPieces() {
        board.setSpacing(2);
        for (int i = 0; i < 8; i++) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(1);
            for (int j = 0; j < 8; j++) {
                if (i == 3 && j == 3 || i == 4 && j == 4) {
                    pieces[i][j] = new Piece(Status.selected, Color.green);
                    hBox.getChildren().add(pieces[i][j]);
                } else if (i == 3 && j == 4 || i == 4 && j == 3) {
                    pieces[i][j] = new Piece(Status.selected, Color.white);
                    hBox.getChildren().add(pieces[i][j]);
                } else {
                    pieces[i][j] = new Piece(Status.unselectable);
                    hBox.getChildren().add(pieces[i][j]);
                }
            }
            board.getChildren().add(hBox);
        }
    }
}