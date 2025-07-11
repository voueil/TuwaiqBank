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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CreateAccountController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label createAccount;

    @FXML
    Label FullName;

    @FXML
    Label username;

    @FXML
    Label email;

    @FXML
    Label phoneNumber;

    @FXML
    Label password;

    @FXML
    TextField name;

    @FXML
    TextField usernameText;

    @FXML
    TextField emailText;

    @FXML
    TextField phoneNumberTXT;

    @FXML
    PasswordField passwordText;

    @FXML
    Button signup;

    @FXML
    CheckBox agreement;

    @FXML
    Button back;

    @FXML
    private VBox vboxContainer;

    @FXML
    public void initialize() {
        String imagePath = "/Images/createAccount.png";
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(285);
        imageView.setFitHeight(450);
        vboxContainer.getChildren().add(imageView);
    }

    @FXML
    private void handleBackbtn(ActionEvent event) throws IOException {
        switchWithAnimation((Node) event.getSource(), "/Views/LoginPage.fxml");
    }

    @FXML
    private void handlesignupbtn(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        stage = (Stage) source.getScene().getWindow();

        String nametxt = name.getText();
        String usernameText2 = usernameText.getText();
        String emailText2 = emailText.getText();
        String phoneNumberTXT2 = phoneNumberTXT.getText();
        String passwordText2 = passwordText.getText();

        if (source == signup) {
            if (!nametxt.isEmpty() && !usernameText2.isEmpty() && !emailText2.isEmpty()
                    && !phoneNumberTXT2.isEmpty() && !passwordText2.isEmpty() && agreement.isSelected()) {

                root = FXMLLoader.load(getClass().getResource("/Views/UserPage.fxml"));

            } else if (nametxt.isEmpty() || usernameText2.isEmpty() || emailText2.isEmpty()
                    || phoneNumberTXT2.isEmpty() || passwordText2.isEmpty() || !agreement.isSelected()) {
                showAlert("error", "you must fill out all the riquired fields");
                return;
            }
        }

        scene = new Scene(root, 600, 450, Color.BEIGE);
        stage.setScene(scene);
        stage.show();
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
    nextRoot.setTranslateX(-30); 

    FadeTransition fade = new FadeTransition(Duration.millis(500), nextRoot);
    fade.setFromValue(0);
    fade.setToValue(1);

    TranslateTransition slide = new TranslateTransition(Duration.millis(500), nextRoot);
    slide.setFromX(-30);
    slide.setToX(0);

    ParallelTransition animation = new ParallelTransition(fade, slide);

    stage.setScene(nextScene);
    animation.play();
}
}
