package laxstudy.components.timer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TimerController {

	@FXML private Text textTimeLeft;
    @FXML private TextField textSet;
    @FXML private Button btnStart;
    @FXML private Button btnPause;
    @FXML private Button btnSet;

    private TimerLogic logic;
    private boolean toggle = false;

    @FXML
    public void initialize() {

        logic = new TimerLogic(
                this::updateText,
                this::flashText
        );

        btnStart.setOnAction(e -> logic.start());
        btnPause.setOnAction(e -> logic.pause());
        btnSet.setOnAction(e -> {
            try {
                logic.setTime(textSet.getText());
                textTimeLeft.setText(textSet.getText());
            } catch (Exception ex) {}
        });
    }

    private void updateText(String s) {
        Platform.runLater(() -> textTimeLeft.setText(s));
    }

    private void flashText() {
        Platform.runLater(() -> {
            toggle = !toggle;
            
            if (toggle) textTimeLeft.getStyleClass().add("flash");
            else textTimeLeft.getStyleClass().remove("flash");
        });
    }
}
