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
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.black))) {
            //if the next piece has diff color or its null, it doesnt need
            if (pieces[i][j + 1].getPieceColor() != null) {
                if (!pieces[i][j + 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i][j + 1].getPieceColor() != null) {
                    //checks all next buttons to see if any of them is white
                    for (int k = j; k < 8; k++) {
                        if (foundGreenPiece(pieces[i][k])) {
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
        }
    }

    private void checkingTheRightRowForWhite(Piece piece, int i, int j) {
        if (piece.getPieceColor() != null && piece.getPieceColor().equals(Color.black)) {
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
     * method is used to check if the items on the left side of black piece are selectable
     *
     * @param piece
     * @param i
     * @param j
     * @author reza bh
     */
    private void checkLeftRowForBlack(Piece piece, int i, int j) {
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.black))) {
            //if the next piece has diff color or its null, it doesnt need
            if (j - 1 > 0 && pieces[i][j - 1].getPieceColor() != null) {
                if (!pieces[i][j - 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i][j - 1].getStatus().
                        equals(Status.selected)) {
                    //checks all next buttons to see if any of them is white
                    for (int k = j; k > 0; k--) {
                        if (foundGreenPiece(pieces[i][k])) {
                            // checking if there is a empty cell after white buttons
                            for (int p = k - 1; p > 0; p--) {
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
            if (i - 1 > 0 && pieces[i - 1][j].getPieceColor() != null) {
                if (!pieces[i - 1][j].getPieceColor().equals(piece.getPieceColor()) && pieces[i - 1][j].getStatus().
                        equals(Status.selected)) {
                    //checks all next buttons to see if any of them is white
                    for (int k = i; k > 0; k--) {
                        if (foundGreenPiece(pieces[k][j])) {
                            // checking if there is a empty cell after white buttons
                            for (int p = k - 1; p > 0; p--) {
                                if (pieces[p][j].getStatus().equals(Status.unselectable)) {
                                    pieces[p][j].setPieceSelectable();
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    return;
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
                if (!pieces[i + 1][j].getPieceColor().equals(piece.getPieceColor()) && pieces[i + 1][j].getStatus().
                        equals(Status.selected)) {
                    //checks all next buttons to see if any of them is white
                    for (int k = i; k < 8; k++) {
                        if (foundGreenPiece(pieces[k][j])) {
                            // checking if there is a empty cell after white buttons
                            for (int p = k + 1; p < 8; p++) {
                                if (pieces[p][j].getStatus().equals(Status.unselectable)) {
                                    pieces[p][j].setPieceSelectable();
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
        if (piece.getPieceColor() != null && (piece.getPieceColor().equals(Color.black))) {
            boolean found = false;
            if (i + 1 < pieces.length && j + 1 < pieces[i].length && pieces[i + 1][j + 1].getPieceColor() != null) {
                if (!pieces[i + 1][j + 1].getPieceColor().equals(piece.getPieceColor()) && pieces[i + 1][j + 1].getStatus().
                        equals(Status.selected)) {
                    for (int k = i; k < 8; k++) {
                        for (int l = j; l < 8; l++) {
                            if (k == l) {
                                if (foundGreenPiece(pieces[k][l])) {
                                    for (int p = k + 1; p < 8; p++) {
                                        for (int m = l + 1; m < 8; m++) {
                                            if (pieces[p][m].getStatus().equals(Status.unselectable) && p == m) {
                                                pieces[p][m].setPieceSelectable();
                                                found = true;
                                                break;
                                            }
                                        }
                                        if (found)
                                            break;
                                    }
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
        if(piece.getPieceColor()==null)
            return false;
        if (piece.getPieceColor().equals(Color.green))
            return true;
        return false;
    }

    public boolean foundWhitePiece(Piece piece) {
        if (piece.getPieceColor()==null)
            return false;
        if (piece.getPieceColor().equals(Color.black))
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
        // we need to check the trun... ->
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                checkingTheRightRowForBlack(pieces[i][j], i, j);
                checkLeftRowForBlack(pieces[i][j], i, j);
                checkTopOfBlack(pieces[i][j], i, j);
                checkingDownOfBlack(pieces[i][j], i, j);
                checkDownOfMainDiagonalBlack(pieces[i][j], i, j);
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
//        pieces[5][5].setPieceSelectable();
//        pieces[5][5].setPieceBlack();
//        pieces[6][6].setPieceSelectable();
//        pieces[6][6].setPieceGreen();
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