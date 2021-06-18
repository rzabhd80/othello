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
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("view/SignUp.fxml"));
        fxmlLoader.load();
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(fxmlLoader.getRoot()));
        primaryStage.show();
    }
}