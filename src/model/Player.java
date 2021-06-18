package model;


public class Player {
    public String name;
    public Color playerColor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public Player(String name) {
        this.name = name;
    }
    public Player(){}
}