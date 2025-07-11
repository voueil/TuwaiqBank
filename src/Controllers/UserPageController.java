package Controllers;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserPageController {

    Stage stage;
    Scene scene;
    Parent root;

    @FXML
    Label currentAcountMoney;

    @FXML
    TextField Payee;

    @FXML
    TextField MoneyAmount;

    @FXML
    TextArea messageTxt;

    @FXML
    Button sendMoney;

    @FXML
    Button signOut;

    @FXML
    ListView<String> listView;

    @FXML
    private void handleSendMoney(ActionEvent event) {
        String payee = Payee.getText().trim();
        String amountStr = MoneyAmount.getText().trim();
        String message = messageTxt.getText().trim();

        if (payee.isEmpty() || amountStr.isEmpty()) {
            showAlert("Error", "Payee and amount cannot be empty.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                showAlert("Error", "Amount must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Amount must be a valid number.");
            return;
        }

        String balanceText = currentAcountMoney.getText().replace("$", "").replace(",", "").trim();
        double currentBalance;

        try {
            currentBalance = Double.parseDouble(balanceText);
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid current account balance format.");
            return;
        }

        if (amount > currentBalance) {
            showAlert("Error", "Insufficient funds in current account.");
            return;
        }

        currentBalance -= amount;
        currentAcountMoney.setText(String.format("$%.2f", currentBalance));

        String detail = "To: " + payee + " | Amount: $" + String.format("%.2f", amount);
        if (!message.isEmpty()) {
            detail += " | Msg: " + message;
        }
        listView.getItems().add(detail);

        Payee.clear();
        MoneyAmount.clear();
        messageTxt.clear();
    }

    @FXML
    private void handleSignOut(ActionEvent event) throws IOException {
        switchWithAnimation((Node) event.getSource(), "/Views/LoginPage.fxml"); 
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
