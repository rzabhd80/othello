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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class scoreTableController implements Initializable {
    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<Player> table;

    @FXML
    private TableColumn<Player, String> player1;

    @FXML
    private TableColumn<Player, Integer> score1;

    @FXML
    private Button button;

    public  void addMatch(ArrayList<Player> players){
        /**
         * when method is called , all the players saved in players arraylist , will be sorted and inserted in table
         *
         * @author rezaBh
         */
        for(Player player:players){
            table.getItems().add(player);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player1.setCellValueFactory(new PropertyValueFactory<>("name"));
        score1.setCellValueFactory(new PropertyValueFactory<>("score"));
        addMatch(BoardController.players);
        score1.setSortType(TableColumn.SortType.DESCENDING);
        table.getSortOrder().add(score1);
        table.sort();
    }

}
