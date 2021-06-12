package model;

import javafx.scene.control.Button;

public class Piece extends Button {
    private Color pieceColor;
    private boolean selected;
    private Player ownerPLayer;
    public Color getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(Color pieceColor) {
        this.pieceColor = pieceColor;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Player getOwnerPLayer() {
        return ownerPLayer;
    }

    public void setOwnerPLayer(Player ownerPLayer) {
        this.ownerPLayer = ownerPLayer;
    }
}
