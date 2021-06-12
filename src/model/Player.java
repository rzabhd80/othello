package model;

import java.util.ArrayList;

public class Player {
    private String name;
    // every player has a specific color , e.g -> player one is green and player two is black,
    private String colorPic;
    private ArrayList<Piece>pieces = new ArrayList<>();
    private int point;
    public int getPoint() {
        return point;
    }

    public String getColorPic() {
        return colorPic;
    }

    public void setColorPic(String colorPic) {
        this.colorPic = colorPic;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
