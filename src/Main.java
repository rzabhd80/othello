import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("view/board.fxml"));
        fxmlLoader.load();
<<<<<<< HEAD
=======
//        primaryStage.setFullScreen(true);
>>>>>>> 07bd01ebdecf7273d9f5762965e369de7832d458
        primaryStage.setScene(new Scene(fxmlLoader.getRoot()));
        primaryStage.show();
    }
}