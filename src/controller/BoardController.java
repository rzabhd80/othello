package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Color;
import model.Piece;
import model.Player;
import model.Status;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    @FXML
    private Label playerColor1;
    @FXML
    private Label playerColor2;
    @FXML
    private Button scoreTable;
    @FXML
    private Button ref;

    public static Player player1;
    public static Player player2;
    public static Stage boardStage;

    private boolean greenTurn = true;


    //this global array is meant to store all the pieces of the field
    private final Piece[][] pieces = new Piece[8][8];


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPieces();
        selectPieceForPlay();
        refresh();
        setScoreTable();
    }

    /**
     * this method is used for incrementing the score of given player
     *
     * @author rezaBh
     */
    private void calculateScore() {
        player1.setScore(0);
        player2.setScore(0);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j].getPieceColor() != null && pieces[i][j].getPieceColor().equals(Color.green))
                    player2.score++;
                if (pieces[i][j].getPieceColor() != null && pieces[i][j].getPieceColor().equals(Color.black))
                    player1.score++;
            }
        }
        score1.setText(Integer.toString(player1.getScore()));
        score2.setText(Integer.toString(player2.getScore()));
    }

    private void setScoreTable() {
        scoreTable.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/scoreTable.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.getRoot()));
            stage.setResizable(false);
            stage.show();

        });
    }

    /**
     * When the player has taken their turn,
     * the continuation of the game will be performed by calling the functions in this method
     *
     * @author AmirMahdi
     */
    public void selectPieceForPlay() {
        setSelectables();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int finalI = i;
                int finalJ = j;
                pieces[i][j].setOnAction(event -> {
                    if (greenTurn && pieces[finalI][finalJ].getStatus().equals(Status.selectable)) {
                        pieces[finalI][finalJ].setPieceGreen();
                        rotatePiecesInRightRow(pieces[finalI][finalJ], finalI, finalJ);
                        rotatePiecesInLeftRow(pieces[finalI][finalJ], finalI, finalJ);
                        rotatePiecesInDownColumn(pieces[finalI][finalJ], finalI, finalJ);
                        rotatePiecesInUpColumn(pieces[finalI][finalJ], finalI, finalJ);
                        rotatePiecesInUpMainDiameter(pieces[finalI][finalJ], finalI, finalJ);
                        rotatePiecesInDownMainDiameter(pieces[finalI][finalJ], finalI, finalJ);
                        rotatePiecesInDownSubDiameter(pieces[finalI][finalJ], finalI, finalJ);
                        rotatePiecesInUpSubDiameter(pieces[finalI][finalJ], finalI, finalJ);
                        clearSelectable();
                        changeTurn();
                        calculateScore();
                        isFinished();
                    } else if (!greenTurn && pieces[finalI][finalJ].getStatus().equals(Status.selectable)) {
                        pieces[finalI][finalJ].setPieceBlack();
                        rotatePiecesInRightRow(pieces[finalI][finalJ], finalI, finalJ);
                        rotatePiecesInLeftRow(pieces[finalI][finalJ], finalI, finalJ);
                        rotatePiecesInDownColumn(pieces[finalI][finalJ], finalI, finalJ);
                        rotatePiecesInUpColumn(pieces[finalI][finalJ], finalI, finalJ);
                        rotatePiecesInUpMainDiameter(pieces[finalI][finalJ], finalI, finalJ);
                        rotatePiecesInDownMainDiameter(pieces[finalI][finalJ], finalI, finalJ);
                        rotatePiecesInDownSubDiameter(pieces[finalI][finalJ], finalI, finalJ);
                        rotatePiecesInUpSubDiameter(pieces[finalI][finalJ], finalI, finalJ);
                        clearSelectable();
                        changeTurn();
                        calculateScore();
                        isFinished();
                    }
                });
            }
        }
    }

    public void rotatePiecesInRightRow(Piece piece, int i, int j) {
        if (j == 7) {
            return;
        }
        Color newPieceColor = piece.getPieceColor();
        if (pieces[i][j + 1].getPieceColor() != null && !pieces[i][j + 1].getPieceColor().equals(newPieceColor)) {
            int startRotating = j + 1;
            int endRotating = -1;
            boolean find = false;
            for (int k = j + 1; k <= 7; k++) {
                if (pieces[i][k].getPieceColor() != null && pieces[i][k].getPieceColor().equals(newPieceColor)) {
                    endRotating = k - 1;
                    find = true;
                    break;
                } else if (pieces[i][k].getPieceColor() == null) {
                    return;
                }
            }
            if (find) {
                for (int k = startRotating; k <= endRotating; k++) {
                    pieces[i][k].setPieceGivenColor(newPieceColor);
                }
            }
        }
    }

    public void rotatePiecesInLeftRow(Piece piece, int i, int j) {
        if (j == 0) {
            return;
        }
        Color newPieceColor = piece.getPieceColor();
        if (pieces[i][j - 1].getPieceColor() != null && !pieces[i][j - 1].getPieceColor().equals(newPieceColor)) {
            int startRotating = j - 1;
            int endRotating = -1;
            boolean find = false;
            for (int k = j - 1; k >= 0; k--) {
                if (pieces[i][k].getPieceColor() != null && pieces[i][k].getPieceColor().equals(newPieceColor)) {
                    endRotating = k + 1;
                    find = true;
                    break;
                } else if (pieces[i][k].getPieceColor() == null) {
                    return;
                }
            }
            if (find) {
                for (int k = startRotating; k >= endRotating; k--) {
                    pieces[i][k].setPieceGivenColor(newPieceColor);
                }
            }
        }
    }

    public void rotatePiecesInDownColumn(Piece piece, int i, int j) {
        if (i == 7) {
            return;
        }
        Color newPieceColor = piece.getPieceColor();
        if (pieces[i + 1][j].getPieceColor() != null && !pieces[i + 1][j].getPieceColor().equals(newPieceColor)) {
            int startRotating = i + 1;
            int endRotating = -1;
            boolean find = false;
            for (int k = i + 1; k <= 7; k++) {
                if (pieces[k][j].getPieceColor() != null && pieces[k][j].getPieceColor().equals(newPieceColor)) {
                    endRotating = k - 1;
                    find = true;
                    break;
                } else if (pieces[k][j].getPieceColor() == null) {
                    return;
                }
            }
            if (find) {
                for (int k = startRotating; k <= endRotating; k++) {
                    pieces[k][j].setPieceGivenColor(newPieceColor);
                }
            }
        }
    }

    public void rotatePiecesInUpColumn(Piece piece, int i, int j) {
        if (i == 0) {
            return;
        }
        Color newPieceColor = piece.getPieceColor();
        if (pieces[i - 1][j].getPieceColor() != null && !pieces[i - 1][j].getPieceColor().equals(newPieceColor)) {
            int startRotating = i - 1;
            int endRotating = -1;
            boolean find = false;
            for (int k = i - 1; k >= 0; k--) {
                if (pieces[k][j].getPieceColor() != null && pieces[k][j].getPieceColor().equals(newPieceColor)) {
                    endRotating = k + 1;
                    find = true;
                    break;
                } else if (pieces[k][j].getPieceColor() == null) {
                    return;
                }
            }
            if (find) {
                for (int k = startRotating; k >= endRotating; k--) {
                    pieces[k][j].setPieceGivenColor(newPieceColor);
                }
            }
        }
    }

    public void rotatePiecesInDownMainDiameter(Piece piece, int i, int j) {
        if (i == 7 || j == 7) {
            return;
        }
        Color newPieceColor = piece.getPieceColor();
        if (pieces[i + 1][j + 1].getPieceColor() != null && !pieces[i + 1][j + 1].getPieceColor().equals(newPieceColor)) {
            int startRotatingRow = i + 1;
            int startRotatingColumn = j + 1;
            int endRotatingRow = -1;
            int endRotatingColumn = -1;
            boolean find = false;
            for (int k = i + 1, p = j + 1; k <= 7 && p <= 7; k++, p++) {
                if (pieces[k][p].getPieceColor() != null && pieces[k][p].getPieceColor().equals(newPieceColor)) {
                    endRotatingRow = k - 1;
                    endRotatingColumn = p - 1;
                    find = true;
                    break;
                } else if (pieces[k][p].getPieceColor() == null) {
                    return;
                }
            }
            if (find) {
                for (int k = startRotatingRow, p = startRotatingColumn; k <= endRotatingRow && p <= endRotatingColumn; k++, p++) {
                    pieces[k][p].setPieceGivenColor(newPieceColor);
                }
            }
        }
    }

    public void rotatePiecesInUpMainDiameter(Piece piece, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        Color newPieceColor = piece.getPieceColor();
        if (pieces[i - 1][j - 1].getPieceColor() != null && !pieces[i - 1][j - 1].getPieceColor().equals(newPieceColor)) {
            int startRotatingRow = i - 1;
            int startRotatingColumn = j - 1;
            int endRotatingRow = -1;
            int endRotatingColumn = -1;
            boolean find = false;
            for (int k = i - 1, p = j - 1; k >= 0 && p >= 0; k--, p--) {
                if (pieces[k][p].getPieceColor() != null && pieces[k][p].getPieceColor().equals(newPieceColor)) {
                    endRotatingRow = k + 1;
                    endRotatingColumn = p + 1;
                    find = true;
                    break;
                } else if (pieces[k][p].getPieceColor() == null) {
                    return;
                }
            }
            if (find) {
                for (int k = startRotatingRow, p = startRotatingColumn; k >= endRotatingRow && p >= endRotatingColumn; k--, p--) {
                    pieces[k][p].setPieceGivenColor(newPieceColor);
                }
            }
        }
    }

    public void rotatePiecesInDownSubDiameter(Piece piece, int i, int j) {
        if (i == 7 || j == 0) {
            return;
        }
        Color newPieceColor = piece.getPieceColor();
        if (pieces[i + 1][j - 1].getPieceColor() != null && !pieces[i + 1][j - 1].getPieceColor().equals(newPieceColor)) {
            int startRotatingRow = i + 1;
            int startRotatingColumn = j - 1;
            int endRotatingRow = -1;
            int endRotatingColumn = -1;
            boolean find = false;
            for (int k = i + 1, p = j - 1; k <= 7 && p >= 0; k++, p--) {
                if (pieces[k][p].getPieceColor() != null && pieces[k][p].getPieceColor().equals(newPieceColor)) {
                    endRotatingRow = k - 1;
                    endRotatingColumn = p + 1;
                    find = true;
                    break;
                } else if (pieces[k][p].getPieceColor() == null) {
                    return;
                }
            }
            if (find) {
                for (int k = startRotatingRow, p = startRotatingColumn; k <= endRotatingRow && p >= endRotatingColumn; k++, p--) {
                    pieces[k][p].setPieceGivenColor(newPieceColor);
                }
            }
        }
    }

    public void rotatePiecesInUpSubDiameter(Piece piece, int i, int j) {
        if (i == 0 || j == 7) {
            return;
        }
        Color newPieceColor = piece.getPieceColor();
        if (pieces[i - 1][j + 1].getPieceColor() != null && !pieces[i - 1][j + 1].getPieceColor().equals(newPieceColor)) {
            int startRotatingRow = i - 1;
            int startRotatingColumn = j + 1;
            int endRotatingRow = -1;
            int endRotatingColumn = -1;
            boolean find = false;
            for (int k = i - 1, p = j + 1; k >= 0 && p <= 7; k--, p++) {
                if (pieces[k][p].getPieceColor() != null && pieces[k][p].getPieceColor().equals(newPieceColor)) {
                    endRotatingRow = k + 1;
                    endRotatingColumn = p - 1;
                    find = true;
                    break;
                } else if (pieces[k][p].getPieceColor() == null) {
                    return;
                }
            }
            if (find) {
                for (int k = startRotatingRow, p = startRotatingColumn; k >= endRotatingRow && p <= endRotatingColumn; k--, p++) {
                    pieces[k][p].setPieceGivenColor(newPieceColor);
                }
            }
        }
    }

    /**
     * this method is used for checking the entire table and announcing the end of the game
     * if there was no selectable piece left, it means that game has finished
     *
     * @author reza bh
     */
    private void isFinished() {
        if (noPieceIsSelectable()) {
            changeTurn();
            if (noPieceIsSelectable()) {
                Player.insertInFile();
                congratulation();
            }
        }
    }

    public boolean noPieceIsSelectable() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j].getStatus().equals(Status.selectable)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void congratulation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Final result");
        String text;
        if (score1.getText().equals(score2.getText())) {
            text = "Draw";
        } else if (Integer.parseInt(score1.getText()) > Integer.parseInt(score2.getText())) {
            text = player1.getName() + " Is winner";
        } else {
            text = player2.getName() + " Is winner";
        }

        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
    }

    /**
     * After each move, each player takes turns
     *
     * @author AmirMahdi, reza bh
     */
    public void changeTurn() {
        if (greenTurn) {
            greenTurn = false;
            turn1.setVisible(true);
            turn2.setVisible(false);
        } else {
            greenTurn = true;
            turn1.setVisible(false);
            turn2.setVisible(true);
        }
        setSelectables();
    }


    /**
     * After selecting each of the selectable pieces,
     * it is the opponent's turn.
     * For this reason, the pieces that can be selected for the previous player must be removed
     *
     * @author AmirMahdi
     */
    public void clearSelectable() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j].getPieceColor() == null) {
                    pieces[i][j].setPieceUnselectable();
                }
            }
        }
    }

    /**
     * this method is used for refreshing the game and make a new one
     *
     * @author rezaBh
     */
    private void refresh() {
        ref.setOnAction(event -> {
            boardStage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/board.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            boardStage = new Stage();
            boardStage.setScene(new Scene(fxmlLoader.getRoot()));
            boardStage.show();

        });
    }

    /**
     * it will be called only at the beginning of the game.
     * and creates the pieces for start playing the game.
     * only 4 pieces is selected. 2 green and 2 white at the center of board.
     * so the other pieces are unselectable
     * setting players names and colors is done in this method
     *
     * @author AmirMahdi, reza bh
     */
    private void initPieces() {
        playerName1.setText(player1.getName());
        playerName2.setText(player2.getName());
        player1.setPlayerColor(Color.black);
        player2.setPlayerColor(Color.green);
        playerColor1.setText(player1.getPlayerColor().toString());
        playerColor2.setText(player2.getPlayerColor().toString());
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

    /**
     * makes pieces for player selectable.
     * player does her own move
     * author AmirMahdi
     */
    public void setSelectables() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (greenTurn) {
                    checkingTheRightRowForGreen(pieces[i][j], i, j);
                    checkingTheLeftRowForGreen(pieces[i][j], i, j);
                    checkingTheUpColumnForGreen(pieces[i][j], i, j);
                    checkingTheDownColumnForGreen(pieces[i][j], i, j);
                    checkingTheUpRightDiameterForGreen(pieces[i][j], i, j);
                    checkingTheUpLeftDiameterForGreen(pieces[i][j], i, j);
                    checkingTheDownRightDiameterForGreen(pieces[i][j], i, j);
                    checkingTheDownLeftDiameterForGreen(pieces[i][j], i, j);
                } else {
                    checkingTheRightRowForBlack(pieces[i][j], i, j);
                    checkingTheLeftRowForBlack(pieces[i][j], i, j);
                    checkingTheUpColumnForBlack(pieces[i][j], i, j);
                    checkingTheDownColumnForBlack(pieces[i][j], i, j);
                    checkingTheUpRightDiameterForBlack(pieces[i][j], i, j);
                    checkingTheUpLeftDiameterForBlack(pieces[i][j], i, j);
                    checkingTheDownRightDiameterForBlack(pieces[i][j], i, j);
                    checkingTheDownLeftDiameterForBlack(pieces[i][j], i, j);

                }
            }
        }
    }

    // Functions to identify selectable pieces for green player
    private void checkingTheRightRowForGreen(Piece piece, int i, int j) {
        if (j == 7) {
            return;
        }
        if (piece.getPieceColor() == Color.green && pieces[i][j + 1].getPieceColor() == Color.black) {
            for (int k = j + 1; k < 8; k++) {
                if (pieces[i][k].getPieceColor() != null) {
                    if (pieces[i][k].getPieceColor() != Color.black) {
                        return;
                    }
                } else {
                    pieces[i][k].setPieceSelectable();
                    return;
                }
            }
        }
    }

    private void checkingTheLeftRowForGreen(Piece piece, int i, int j) {
        if (j == 0) {
            return;
        }
        if (piece.getPieceColor() == Color.green && pieces[i][j - 1].getPieceColor() == Color.black) {
            for (int k = j - 1; k >= 0; k--) {
                if (pieces[i][k].getPieceColor() != null) {
                    if (pieces[i][k].getPieceColor() != Color.black) {
                        return;
                    }
                } else {
                    pieces[i][k].setPieceSelectable();
                    return;
                }
            }
        }
    }

    private void checkingTheUpColumnForGreen(Piece piece, int i, int j) {
        if (i == 0) {
            return;
        }
        if (piece.getPieceColor() == Color.green && pieces[i - 1][j].getPieceColor() == Color.black) {
            for (int k = i - 1; k >= 0; k--) {
                if (pieces[k][j].getPieceColor() != null) {
                    if (pieces[k][j].getPieceColor() != Color.black) {
                        return;
                    }
                } else {
                    pieces[k][j].setPieceSelectable();
                    return;
                }
            }
        }
    }

    private void checkingTheDownColumnForGreen(Piece piece, int i, int j) {
        if (i == 7) {
            return;
        }
        if (piece.getPieceColor() == Color.green && pieces[i + 1][j].getPieceColor() == Color.black) {
            for (int k = i + 1; k <= 7; k++) {
                if (pieces[k][j].getPieceColor() != null) {
                    if (pieces[k][j].getPieceColor() != Color.black) {
                        return;
                    }
                } else {
                    pieces[k][j].setPieceSelectable();
                    return;
                }
            }
        }
    }

    private void checkingTheUpRightDiameterForGreen(Piece piece, int i, int j) {
        if (i == 0 || j == 7) {
            return;
        }
        if (piece.getPieceColor() == Color.green && pieces[i - 1][j + 1].getPieceColor() == Color.black) {
            for (int k = i - 1, h = j + 1; k >= 0 && h <= 7; k--, h++) {
                if (pieces[k][h].getPieceColor() != null) {
                    if (pieces[k][h].getPieceColor() != Color.black) {
                        return;
                    }
                } else {
                    pieces[k][h].setPieceSelectable();
                    return;
                }
            }
        }
    }

    private void checkingTheUpLeftDiameterForGreen(Piece piece, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (piece.getPieceColor() == Color.green && pieces[i - 1][j - 1].getPieceColor() == Color.black) {
            for (int k = i - 1, h = j - 1; k >= 0 && h >= 0; k--, h--) {
                if (pieces[k][h].getPieceColor() != null) {
                    if (pieces[k][h].getPieceColor() != Color.black) {
                        return;
                    }
                } else {
                    pieces[k][h].setPieceSelectable();
                    return;
                }
            }
        }
    }

    private void checkingTheDownRightDiameterForGreen(Piece piece, int i, int j) {
        if (i == 7 || j == 7) {
            return;
        }
        if (piece.getPieceColor() == Color.green && pieces[i + 1][j + 1].getPieceColor() == Color.black) {
            for (int k = i + 1, h = j + 1; k <= 7 && h <= 7; k++, h++) {
                if (pieces[k][h].getPieceColor() != null) {
                    if (pieces[k][h].getPieceColor() != Color.black) {
                        return;
                    }
                } else {
                    pieces[k][h].setPieceSelectable();
                    return;
                }
            }
        }
    }

    private void checkingTheDownLeftDiameterForGreen(Piece piece, int i, int j) {
        if (i == 7 || j == 0) {
            return;
        }
        if (piece.getPieceColor() == Color.green && pieces[i + 1][j - 1].getPieceColor() == Color.black) {
            for (int k = i + 1, h = j - 1; k <= 7 && h >= 0; k++, h--) {
                if (pieces[k][h].getPieceColor() != null) {
                    if (pieces[k][h].getPieceColor() != Color.black) {
                        return;
                    }
                } else {
                    pieces[k][h].setPieceSelectable();
                    return;
                }
            }
        }
    }

    // Functions to identify selectable pieces for black player
    private void checkingTheRightRowForBlack(Piece piece, int i, int j) {
        if (j == 7) {
            return;
        }
        if (piece.getPieceColor() == Color.black && pieces[i][j + 1].getPieceColor() == Color.green) {
            for (int k = j + 1; k < 8; k++) {
                if (pieces[i][k].getPieceColor() != null) {
                    if (pieces[i][k].getPieceColor() != Color.green) {
                        return;
                    }
                } else {
                    pieces[i][k].setPieceSelectable();
                    return;
                }
            }
        }
    }

    private void checkingTheLeftRowForBlack(Piece piece, int i, int j) {
        if (j == 0) {
            return;
        }
        if (piece.getPieceColor() == Color.black && pieces[i][j - 1].getPieceColor() == Color.green) {
            for (int k = j - 1; k >= 0; k--) {
                if (pieces[i][k].getPieceColor() != null) {
                    if (pieces[i][k].getPieceColor() != Color.green) {
                        return;
                    }
                } else {
                    pieces[i][k].setPieceSelectable();
                    return;
                }
            }
        }
    }

    private void checkingTheUpColumnForBlack(Piece piece, int i, int j) {
        if (i == 0) {
            return;
        }
        if (piece.getPieceColor() == Color.black && pieces[i - 1][j].getPieceColor() == Color.green) {
            for (int k = i - 1; k >= 0; k--) {
                if (pieces[k][j].getPieceColor() != null) {
                    if (pieces[k][j].getPieceColor() != Color.green) {
                        return;
                    }
                } else {
                    pieces[k][j].setPieceSelectable();
                    return;
                }
            }
            System.out.println("nothing");
        }
    }

    private void checkingTheDownColumnForBlack(Piece piece, int i, int j) {
        if (i == 7) {
            return;
        }
        if (piece.getPieceColor() == Color.black && pieces[i + 1][j].getPieceColor() == Color.green) {
            for (int k = i + 1; k <= 7; k++) {
                if (pieces[k][j].getPieceColor() != null) {
                    if (pieces[k][j].getPieceColor() != Color.green) {
                        return;
                    }
                } else {
                    pieces[k][j].setPieceSelectable();
                    return;
                }
            }
        }
    }

    private void checkingTheUpRightDiameterForBlack(Piece piece, int i, int j) {
        if (i == 0 || j == 7) {
            return;
        }
        if (piece.getPieceColor() == Color.black && pieces[i - 1][j + 1].getPieceColor() == Color.green) {
            for (int k = i - 1, h = j + 1; k >= 0 && h <= 7; k--, h++) {
                if (pieces[k][h].getPieceColor() != null) {
                    if (pieces[k][h].getPieceColor() != Color.green) {
                        return;
                    }
                } else {
                    pieces[k][h].setPieceSelectable();
                    return;
                }
            }
        }
    }

    private void checkingTheUpLeftDiameterForBlack(Piece piece, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (piece.getPieceColor() == Color.black && pieces[i - 1][j - 1].getPieceColor() == Color.green) {
            for (int k = i - 1, h = j - 1; k >= 0 && h >= 0; k--, h--) {
                if (pieces[k][h].getPieceColor() != null) {
                    if (pieces[k][h].getPieceColor() != Color.green) {
                        return;
                    }
                } else {
                    pieces[k][h].setPieceSelectable();
                    return;
                }
            }
        }
    }

    private void checkingTheDownRightDiameterForBlack(Piece piece, int i, int j) {
        if (i == 7 || j == 7) {
            return;
        }
        if (piece.getPieceColor() == Color.black && pieces[i + 1][j + 1].getPieceColor() == Color.green) {
            for (int k = i + 1, h = j + 1; k <= 7 && h <= 7; k++, h++) {
                if (pieces[k][h].getPieceColor() != null) {
                    if (pieces[k][h].getPieceColor() != Color.green) {
                        return;
                    }
                } else {
                    pieces[k][h].setPieceSelectable();
                    return;
                }
            }
        }
    }

    private void checkingTheDownLeftDiameterForBlack(Piece piece, int i, int j) {
        if (i == 7 || j == 0) {
            return;
        }
        if (piece.getPieceColor() == Color.black && pieces[i + 1][j - 1].getPieceColor() == Color.green) {
            for (int k = i + 1, h = j - 1; k <= 7 && h >= 0; k++, h--) {
                if (pieces[k][h].getPieceColor() != null) {
                    if (pieces[k][h].getPieceColor() != Color.green) {
                        return;
                    }
                } else {
                    pieces[k][h].setPieceSelectable();
                    return;
                }
            }
        }
    }

}