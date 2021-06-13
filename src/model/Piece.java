package model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;



public class Piece extends Button {
    private Status status;
    private Color pieceColor;

    /**
     * creates a piece with a status and a color
     * status and color can be changed
     * but this constructor just made green or white piece with "selected" status
     * and always status is selected in this
     * @param status is selected.
     * @param pieceColor is green or white
     */
    public Piece(Status status, Color pieceColor) {
        this.status = status;
        this.pieceColor = pieceColor;
        this.setPrefSize(100,100);

        Image pieceImage;
        ImageView pieceImageView;

        if (status == Status.selected && pieceColor==Color.green){
            pieceImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../view/icons/greenButton.png")));
            pieceImageView = new ImageView(pieceImage);
            pieceImageView.setFitWidth(55);
            pieceImageView.setFitHeight(55);
            this.setGraphic(pieceImageView);
        }
        else if (status == Status.selected && pieceColor == Color.white){
            pieceImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../view/icons/blackButt.png")));
            pieceImageView = new ImageView(pieceImage);
            pieceImageView.setFitWidth(55);
            pieceImageView.setFitHeight(55);
            this.setGraphic(pieceImageView);
        }
    }

    /**
     * creates a piece
     * status and color can be changed
     * but this constructor just made pieces with "unselectable" status and with no color (color is null)
     * and always status is selected in this
     * @param status is always unselectable
     */
    public Piece(Status status) {
        this.status = status;
        if (status == Status.unselectable){
            this.setPrefSize(100,100);
            this.setOpacity(0.2);
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Color getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(Color pieceColor) {
        this.pieceColor = pieceColor;
    }
}
