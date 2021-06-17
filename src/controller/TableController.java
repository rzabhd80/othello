package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Player;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class TableController  implements Initializable {
    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<Player>table;

    @FXML
    private TableColumn<Player, String> player1;

    @FXML
    private TableColumn<Integer, Integer> score1;

    @FXML
    private Button button;

    public void addMatch(ArrayList<Player>players){
        players.sort(((o1, o2) -> {
            return Math.max(o1.getScore(), o2.getScore());
        }));
        for(Player player:players){
            table.getItems().add(player);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player1.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
        player1.setCellValueFactory(new PropertyValueFactory<Player,String>("score"));
    }
}
