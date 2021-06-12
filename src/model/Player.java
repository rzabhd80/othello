package model;

import java.util.ArrayList;

public class Player {
    private String name;
    // every player has a specific color , e.g -> player one is green and player two is black,
    private Color colorPic;
    private int point;
    public int getPoint() {
        return point;
    }

    public Color getColorPic() {
        return colorPic;
    }

    public void setColorPic(Color colorPic) {
        this.colorPic = colorPic;
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
