package model;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Piece extends Button {
    private Color pieceColor;
    private ImageView imageView;
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

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
