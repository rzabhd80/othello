package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    private boolean greenTurn = true;

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
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.black))) {
            //if the next piece has diff color or its null, it doesnt need
            if (pieces[i][j + 1].getPieceColor() != null) {
                if (!pieces[i][j + 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i][j + 1].getPieceColor() != null) {
                    //checks all next buttons to see if any of them is white
                    boolean accept = true;
                    for (int k = j + 1; k < 8; k++) {
                        if (pieces[i][k].getPieceColor() != null && !foundGreenPiece(pieces[i][k])) {
                            accept = false;
                        }
                    }
                    // checking if there is a empty cell after white buttons
                    for (int p = j; p < 8; p++) {
                        if (pieces[i][p].getPieceColor() == null && accept) {
                            pieces[i][p].setPieceSelectable();
                            break;
                        }
                    }
                }
            }
        }
    }

    private void checkingTheRightRowForGreen(Piece piece, int i, int j) {

        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.green))) {
            //if the next piece has diff color or its null, it doesnt need
            if (pieces[i][j + 1].getPieceColor() != null) {
                if (!pieces[i][j + 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i][j + 1].getPieceColor() != null) {
                    //checks all next buttons to see if any of them is white
                    boolean accept = true;
                    for (int k = j + 1; k < 8; k++) {
                        if (pieces[i][k].getPieceColor() != null && !foundWhitePiece(pieces[i][k])) {
                            accept = false;
                        }
                    }
                    // checking if there is a empty cell after white buttons
                    for (int p = j; p < 8; p++) {
                        if (pieces[i][p].getPieceColor() == null && accept) {
                            pieces[i][p].setPieceSelectable();
                            break;
                        }
                    }
                }
            }
        }

    }

    /**
     * method is used to check if the items on the left side of black piece are selectable
     *
     * @param piece
     * @param i
     * @param j
     * @author reza bh
     */
    private void checkLeftRowForBlack(Piece piece, int i, int j) {
        boolean accept = true;
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.black))) {
            //if the next piece has diff color or its null, it doesnt need
            if (j - 1 >= 0 && pieces[i][j - 1].getPieceColor() != null) {
                if (!pieces[i][j - 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i][j - 1].getPieceColor() != null) {
                    //checks all next buttons to see if any of them is white
                    for (int k = j - 1; k >= 0; k--) {
                        if (pieces[i][k].getPieceColor() != null && !foundGreenPiece(pieces[i][k])) {
                            accept = false;
                        }
                    }
                    // checking if there is a empty cell after white buttons
                    for (int p = j; p >= 0; p--) {
                        if (pieces[i][p].getPieceColor() == null && accept) {
                            pieces[i][p].setPieceSelectable();
                            break;
                        }
                    }
                }
                //then we need to check previous pieces
                return;
            }
        }
    }

    private void checkingTheLeftRowForGreen(Piece piece, int i, int j) {

        boolean accept = true;
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.green))) {
            //if the next piece has diff color or its null, it doesnt need
            if (j - 1 >= 0 && pieces[i][j - 1].getPieceColor() != null) {
                if (!pieces[i][j - 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i][j - 1].getPieceColor() != null) {
                    //checks all next buttons to see if any of them is white
                    for (int k = j - 1; k >= 0; k--) {
                        if (pieces[i][k].getPieceColor() != null && !foundWhitePiece(pieces[i][k])) {
                            accept = false;
                        }
                    }
                    // checking if there is a empty cell after white buttons
                    for (int p = j; p >= 0; p--) {
                        if (pieces[i][p].getPieceColor() == null && accept) {
                            pieces[i][p].setPieceSelectable();
                            break;
                        }
                    }
                }
                //then we need to check previous pieces
                return;
            }
        }
    }

    /**
     * method is used for checking the electability of pieces up the black piece
     *
     * @param piece
     * @param i
     * @param j
     * @author reza bh
     */
    private void checkTopOfBlack(Piece piece, int i, int j) {
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.black))) {
            //if the next piece has diff color or its null, it doesnt need
            if (i - 1 >= 0 && pieces[i - 1][j].getPieceColor() != null) {
                if (!pieces[i - 1][j].getPieceColor().equals(piece.getPieceColor()) && pieces[i - 1][j].getPieceColor() != null) {
                    boolean accept = true;
                    //checks all next buttons to see if any of them is white
                    for (int k = i - 1; k >= 0; k--) {
                        if (pieces[k][j].getPieceColor() != null && !foundGreenPiece(pieces[k][j])) {
                            accept = false;
                        }
                    }
                    // checking if there is a empty cell after white buttons
                    for (int p = i; p >= 0; p--) {
                        if (pieces[p][j].getPieceColor() == null && accept) {
                            pieces[p][j].setPieceSelectable();
                            break;
                        }
                    }
                }
            }
        }
    }

    private void checkingTopOfGreen(Piece piece, int i, int j) {

        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.green))) {
            //if the next piece has diff color or its null, it doesnt need
            if (i - 1 >= 0 && pieces[i - 1][j].getPieceColor() != null) {
                if (!pieces[i - 1][j].getPieceColor().equals(piece.getPieceColor()) && pieces[i - 1][j].getPieceColor() != null) {
                    boolean accept = true;
                    //checks all next buttons to see if any of them is white
                    for (int k = i - 1; k >= 0; k--) {
                        if (pieces[k][j].getPieceColor() != null && !foundWhitePiece(pieces[k][j])) {
                            accept = false;
                        }
                    }
                    // checking if there is a empty cell after white buttons
                    for (int p = i; p >= 0; p--) {
                        if (pieces[p][j].getPieceColor() == null && accept) {
                            pieces[p][j].setPieceSelectable();
                            break;
                        }
                    }
                }
            }
        }

    }

    /**
     * method is used to check if any button must be selectable in the top on given black piece
     *
     * @param piece
     * @param i
     * @param j
     * @author reza BH
     */
    private void checkingDownOfBlack(Piece piece, int i, int j) {
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.black))) {
            //if the next piece has not got diff color or its null, it doesnt need checking
            if (i - 1 > 0 && pieces[i + 1][j].getPieceColor() != null) {
                if (!pieces[i + 1][j].getPieceColor().equals(piece.getPieceColor()) && pieces[i + 1][j].getPieceColor() != null) {
                    //checks all next buttons to see if any of them is white
                    boolean accept = true;
                    for (int k = i + 1; k < 8; k++) {
                        if (pieces[k][j].getPieceColor() != null && !foundGreenPiece(pieces[k][j])) {
                            accept = false;
                        }
                    }
                    // checking if there is a empty cell after white buttons
                    for (int p = i; p <= 8; p++) {
                        if (pieces[p][j].getPieceColor() == null && accept) {
                            pieces[p][j].setPieceSelectable();
                            break;
                        }
                    }
                }
            }
        }
    }

    private void checkingDownOfGreen(Piece piece, int i, int j) {
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.green))) {
            //if the next piece has not got diff color or its null, it doesnt need checking
            if (i - 1 > 0 && pieces[i + 1][j].getPieceColor() != null) {
                if (!pieces[i + 1][j].getPieceColor().equals(piece.getPieceColor()) && pieces[i + 1][j].getPieceColor() != null) {
                    //checks all next buttons to see if any of them is white
                    boolean accept = true;
                    for (int k = i + 1; k < 8; k++) {
                        if (pieces[k][j].getPieceColor() != null && !foundWhitePiece(pieces[k][j])) {
                            accept = false;
                        }
                    }
                    // checking if there is a empty cell after white buttons
                    for (int p = i; p <= 8; p++) {
                        if (pieces[p][j].getPieceColor() == null && accept) {
                            pieces[p][j].setPieceSelectable();
                            break;
                        }
                    }
                }
            }
        }

    }


    /**
     * this method is used to check and find the selectable pieces in the upper diagonal
     * its the same as previous method but instead of looping in either row or column , you loop through both
     *
     * @param piece
     * @param i
     * @param j
     * @author reza bh
     */

    private void checkDownOfMainDiagonalBlack(Piece piece, int i, int j) {
        boolean accept = true;
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.black))) {
            boolean found = true;
            if (i + 1 <= 8 && j + 1 <= 8 && pieces[i + 1][j + 1].getPieceColor() != null) {
                if (!pieces[i + 1][j + 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i + 1][j + 1].
                        getPieceColor() != null) {
                    int k, p;
                    for (k = i + 1, p = j + 1; k < 8 && p < 8; k++, p++) {
                        if (pieces[k][p].getPieceColor() != null && !foundGreenPiece(pieces[k][p])) {
                            found = false;
                        }
                    }

                    int l, m;
                    for (l = i, m = j; l <= 8 && m <= 8; l++, m++) {
                        if (found && pieces[l][m].getPieceColor() == null) {
                            pieces[l][m].setPieceSelectable();
                            break;
                        }
                    }
                }
            }
        }
    }

    private void checkingDownOfMainDiagonalGreen(Piece piece, int i, int j) {

        boolean accept = true;
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.green))) {
            boolean found = true;
            if (i + 1 <= 8 && j + 1 <= 8 && pieces[i + 1][j + 1].getPieceColor() != null) {
                if (!pieces[i + 1][j + 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i + 1][j + 1].
                        getPieceColor() != null) {
                    int k, p;
                    for (k = i + 1, p = j + 1; k < 8 && p < 8; k++, p++) {
                        if (pieces[k][p].getPieceColor() != null && !foundWhitePiece(pieces[k][p])) {
                            found = false;
                        }
                    }

                    int l, m;
                    for (l = i, m = j; l <= 8 && m <= 8; l++, m++) {
                        if (found && pieces[l][m].getPieceColor() == null) {
                            pieces[l][m].setPieceSelectable();
                            break;
                        }
                    }
                }
            }
        }

    }


    private void checkUpperOfMainDiagonalBlack(Piece piece, int i, int j) {
        boolean accept = true;
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.black))) {
            boolean found = true;
            if (i - 1 >= 0 && j - 1 >= 0 && pieces[i - 1][j - 1].getPieceColor() != null) {
                if (!pieces[i - 1][j - 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i - 1][j - 1].
                        getPieceColor() != null) {
                    int k, p;
                    for (k = i - 1, p = j - 1; k >= 0 && p >= 0; k--, p--) {
                        if (pieces[k][p].getPieceColor() != null && !foundGreenPiece(pieces[k][p])) {
                            found = false;
                        }
                    }

                    int l, m;
                    for (l = i, m = j; l >= 0 && m >= 0; l--, m--) {
                        if (found && pieces[l][m].getPieceColor() == null) {
                            pieces[l][m].setPieceSelectable();
                            break;
                        }
                    }
                }
            }
        }
    }

    private void checkUpperOfMainDiagonalGreen(Piece piece, int i, int j) {

        boolean accept = true;
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.green))) {
            boolean found = true;
            if (i - 1 >= 0 && j - 1 >= 0 && pieces[i - 1][j - 1].getPieceColor() != null) {
                if (!pieces[i - 1][j - 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i - 1][j - 1].
                        getPieceColor() != null) {
                    int k, p;
                    for (k = i - 1, p = j - 1; k >= 0 && p >= 0; k--, p--) {
                        if (pieces[k][p].getPieceColor() != null && !foundWhitePiece(pieces[k][p])) {
                            found = false;
                        }
                    }

                    int l, m;
                    for (l = i, m = j; l >= 0 && m >= 0; l--, m--) {
                        if (found && pieces[l][m].getPieceColor() == null) {
                            pieces[l][m].setPieceSelectable();
                            break;
                        }
                    }
                }
            }
        }

    }

    /**
     * this methods check the down part and upper part of second diagonal and see if any piece must be selectable
     *
     * @param piece
     * @param i
     * @param j
     * @author reza Bh
     */

    private void checkDownOfSecondDiagonalBlack(Piece piece, int i, int j) {
        boolean accept = true;
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.black))) {
            boolean found = true;
            if (i + 1 < 8 && j - 1 >= 0 && pieces[i + 1][j - 1].getPieceColor() != null) {
                if (!pieces[i + 1][j - 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i + 1][j - 1].
                        getPieceColor() != null) {
                    int k, p;
                    for (k = i + 1, p = j - 1; k < 8 && p >= 0; k++, p--) {
                        if (pieces[k][p].getPieceColor() != null && !foundGreenPiece(pieces[k][p])) {
                            found = false;
                        }
                    }

                    int l, m;
                    for (l = i, m = j; l < 8 && m >= 0; l++, m--) {
                        if (found && pieces[l][m].getPieceColor() == null) {
                            pieces[l][m].setPieceSelectable();
                            break;
                        }
                    }
                }
            }
        }
    }

    private void checkDownOfSecondDiagonalGreen(Piece piece, int i, int j) {
        boolean accept = true;
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.green))) {
            boolean found = true;
            if (i + 1 < 8 && j - 1 >= 0 && pieces[i + 1][j - 1].getPieceColor() != null) {
                if (!pieces[i + 1][j - 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i + 1][j - 1].
                        getPieceColor() != null) {
                    int k, p;
                    for (k = i + 1, p = j - 1; k < 8 && p >= 0; k++, p--) {
                        if (pieces[k][p].getPieceColor() != null && !foundWhitePiece(pieces[k][p])) {
                            found = false;
                        }
                    }

                    int l, m;
                    for (l = i, m = j; l < 8 && m >= 0; l++, m--) {
                        if (found && pieces[l][m].getPieceColor() == null) {
                            pieces[l][m].setPieceSelectable();
                            break;
                        }
                    }
                }
            }
        }
    }

    private void checkUpperOfSecondDiagonalBlack(Piece piece, int i, int j) {

        boolean accept = true;
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.black))) {
            boolean found = true;
            if (i - 1 >= 0 && j + 1 <= 8 && pieces[i - 1][j + 1].getPieceColor() != null) {
                if (!pieces[i - 1][j + 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i - 1][j + 1].
                        getPieceColor() != null) {
                    int k, p;
                    for (k = i - 1, p = j + 1; k >= 0 && p < 8; k--, p++) {
                        if (pieces[k][p].getPieceColor() != null && !foundGreenPiece(pieces[k][p])) {
                            found = false;
                        }
                    }

                    int l, m;
                    for (l = i, m = j; l >= 0 && m < 8; l--, m++) {
                        if (found && pieces[l][m].getPieceColor() == null) {
                            pieces[l][m].setPieceSelectable();
                            break;
                        }
                    }
                }
            }
        }
    }

    private void checkUpperOfSecondDiagonalGreen(Piece piece,int i,int j){

        boolean accept = true;
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.green))) {
            boolean found = true;
            if (i - 1 >= 0 && j + 1 <= 8 && pieces[i - 1][j + 1].getPieceColor() != null) {
                if (!pieces[i - 1][j + 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i - 1][j + 1].
                        getPieceColor() != null) {
                    int k, p;
                    for (k = i - 1, p = j + 1; k >= 0 && p < 8; k--, p++) {
                        if (pieces[k][p].getPieceColor() != null && !foundWhitePiece(pieces[k][p])) {
                            found = false;
                        }
                    }

                    int l, m;
                    for (l = i, m = j; l >= 0 && m < 8; l--, m++) {
                        if (found && pieces[l][m].getPieceColor() == null) {
                            pieces[l][m].setPieceSelectable();
                            break;
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
        if (piece.getPieceColor() == null)
            return false;
        if (piece.getPieceColor().equals(Color.green))
            return true;
        return false;
    }

    public boolean foundWhitePiece(Piece piece) {
        if (piece.getPieceColor() == null)
            return false;
        if (piece.getPieceColor().equals(Color.black))
            return true;
        return false;
    }


    /**
     * setting the selectables of table
     *
     * @author reza Bh
     */
    public void setSelectables() {
        // we need to check the trun... ->
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (!greenTurn) {
                    // for black
                    checkingTheRightRowForBlack(pieces[i][j], i, j);
                    checkLeftRowForBlack(pieces[i][j], i, j);
                    checkTopOfBlack(pieces[i][j], i, j);
                    checkingDownOfBlack(pieces[i][j], i, j);
                    checkDownOfMainDiagonalBlack(pieces[i][j], i, j);
                    checkUpperOfMainDiagonalBlack(pieces[i][j], i, j);
                    checkDownOfSecondDiagonalBlack(pieces[i][j], i, j);
                    checkUpperOfSecondDiagonalBlack(pieces[i][j], i, j);
                }
                // for green
                if (greenTurn) {
                    checkingTheRightRowForGreen(pieces[i][j], i, j);
                    checkingTheLeftRowForGreen(pieces[i][j], i, j);
                    checkingTopOfGreen(pieces[i][j], i, j);
                    checkingDownOfGreen(pieces[i][j], i, j);
                    checkingDownOfMainDiagonalGreen(pieces[i][j], i, j);
                    checkUpperOfMainDiagonalGreen(pieces[i][j], i, j);
                    checkDownOfSecondDiagonalGreen(pieces[i][j], i, j);
                    checkUpperOfSecondDiagonalGreen(pieces[i][j], i, j);
                }
            }
        }
    }

    public void selectPieceForPlay(){
        setSelectables();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int finalI = i;
                int finalJ = j;
                pieces[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (greenTurn){
                            pieces[finalI][finalJ].setPieceGreen();
                            setPieceColorAfterPlay();
                            changeTurn();
                            setSelectables();
                        }else {
                            pieces[finalI][finalJ].setPieceBlack();
                            changeTurn();
                        }
                    }
                });
            }
        }
    }

    public void rotatePiece(Piece piece, int i, int j){
        if (j==7){
            return;
        }
        Color newPieceColor = piece.getPieceColor();
        if (pieces[i][j+1].getPieceColor() != null && !pieces[i][j+1].getPieceColor().equals(newPieceColor)){
            int startRotating=j+1;
            int endRotating =-1;
            boolean find=false;
            for (int k = j+1; k < 8; k++) {
                if (pieces[i][k].getPieceColor() != null && pieces[i][k].getPieceColor().equals(newPieceColor)){
                    endRotating=k;
                    find = true;
                    break;
                }
            }
            if (find) {
                for (int k = startRotating; k <= endRotating; k++) {
                    pieces[i][k].setPieceGivenColor(newPieceColor);
                }
            }
        }
    }

    public void setPieceColorAfterPlay(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j].getPieceColor() != null) {
                    rotatePiece(pieces[i][j], i, j);
                }
            }
        }
    }

    public void changeTurn(){
        if (greenTurn){
            greenTurn=false;
        }
        else{
            greenTurn = true;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Player player1 = new Player("reza");
        Player player2 = new Player("amir");
        playerName1.setText(player1.getName());
        playerName2.setText(player2.getName());
        initPieces();
        selectPieceForPlay();

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
        for (int i = 0; i < 8; i++) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(1);
            for (int j = 0; j < 8; j++) {
                if (i == 3 && j == 3 || i == 4 && j == 4) {
                    pieces[i][j] = new Piece(Status.selected, Color.green);
                    hBox.getChildren().add(pieces[i][j]);
                } else if (i == 3 && j == 4 || i == 4 && j == 3) {
                    pieces[i][j] = new Piece(Status.selected, Color.black);
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