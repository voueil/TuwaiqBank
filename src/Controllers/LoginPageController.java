package Controllers;

import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginPageController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label title;

    @FXML
    Label username;

    @FXML
    Label password;

    @FXML
    TextField usernameText;

    @FXML
    PasswordField passwordTxt;

    @FXML
    Button login;

    @FXML
    Label CreateAccountQuestion;

    @FXML
    Button CreateAccount;

    @FXML
    private VBox vboxContainer;

    @FXML
    public void initialize() {
        String imagePath = "/Images/login.png";
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(285);
        imageView.setFitHeight(450);
        vboxContainer.getChildren().add(imageView);
    }

    @FXML
    public void handleLoginButton(ActionEvent event) throws IOException {
        String name = usernameText.getText();
        String pass = passwordTxt.getText();

        if (!name.isEmpty() && !pass.isEmpty()) {
            switchWithAnimation((Node) event.getSource(), "/Views/UserPage.fxml"); 
        } else {
            showAlert("error", "name and password cannot be empty");
        }
    }

    @FXML
    public void handleCreateAccount(ActionEvent event) throws IOException {
        switchWithAnimation((Node) event.getSource(), "/Views/CreateAccount.fxml");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void switchWithAnimation(Node source, String fxmlPath) throws IOException {
        Parent nextRoot = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene nextScene = new Scene(nextRoot, 600, 450, Color.BEIGE);
        Stage stage = (Stage) source.getScene().getWindow();

        nextRoot.setOpacity(0);
        nextRoot.setTranslateX(30);

        FadeTransition fade = new FadeTransition(Duration.millis(500), nextRoot);
        fade.setFromValue(0);
        fade.setToValue(1);

        TranslateTransition slide = new TranslateTransition(Duration.millis(500), nextRoot);
        slide.setFromX(30);
        slide.setToX(0);

        ParallelTransition animation = new ParallelTransition(fade, slide);

        stage.setScene(nextScene);
        animation.play();
    }
}
