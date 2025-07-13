package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TuwaiqBank extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/Views/LoginPage.fxml"));
        Scene scene = new Scene(root, 600, 450, Color.BEIGE);
        primaryStage.setResizable(false);

        Image icon = new Image("/Images/icon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Tuwaiq Bank");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
