package model;

import javafx.scene.control.Button;

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
     * @author AmirMahdi
     */
    public Piece(Status status, Color pieceColor) {
        this.status = status;
        this.pieceColor = pieceColor;
        this.setPrefSize(100,100);


        if (status == Status.selected && pieceColor==Color.black){
            this.setStyle("-fx-background-radius: 100;\n-fx-background-color: #000000");
        }
        else if (status == Status.selected && pieceColor == Color.green){
            this.setStyle("-fx-background-radius: 100;\n-fx-background-color: #006c04");
        }
    }

    /**
     * creates a piece
     * status and color can be changed
     * but this constructor just made pieces with "unselectable" status and with no color (color is null)
     * and always status is selected in this
     * @param status is always unselectable
     * @author AmirMahdi
     */
    public Piece(Status status) {
        this.setStyle("-fx-background-radius: 100");
        this.status = status;
        if (status == Status.unselectable){
            this.setPrefSize(100,100);
            this.setOpacity(0.2);
        }
    }

    /**
     * set the piece white
     * @author AmirMahdi
     */
    public void setPieceWhite(){
        if (status == Status.selectable) {
            status = Status.selected;
            pieceColor = Color.black;
            this.setStyle("-fx-background-radius: 100;\n-fx-background-color: #000000");
            this.setOpacity(1);
        }
    }


    /**
     * set the piece green
     * @author AmirMahdi
     */
    public void setPieceGreen(){
        if (status == Status.selectable) {
            status = Status.selected;
            pieceColor = Color.green;
            this.setStyle("-fx-background-radius: 100;\n-fx-background-color: #006c04");
            this.setOpacity(1);
        }
    }

    /**
     * set the piece selectable
     * @author AmirMahdi
     */
    public void setPieceSelectable(){
        if (status==Status.unselectable) {
            status = Status.selectable;
            pieceColor = null;
            this.setOpacity(0.6);
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