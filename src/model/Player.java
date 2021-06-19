package model;


import controller.BoardController;

import java.io.*;
import java.util.ArrayList;

public class Player implements Serializable {
    public String name;
    public Color playerColor;
    public int score;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * this methods are used for serialization and deserialization of player objects
     *
     * @throws IOException
     * @author reza Bh
     */
    public static void insertInFile() {
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("src/controller/players",true);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert objectOutputStream != null;
            objectOutputStream.writeObject(BoardController.players);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert objectOutputStream != null;
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void readFile() {
        try {
           FileInputStream fileInputStream = new FileInputStream("src/controller/players");
           ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            try {
                BoardController.players = (ArrayList<Player>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}