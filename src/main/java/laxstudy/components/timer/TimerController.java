package laxstudy.components.timer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class TimerController {

	@FXML private Text textTimeLeft;
	@FXML private ComboBox<String> DefinirHoras;
    @FXML private Button btnStart;
    @FXML private Button btnPause;
    @FXML private Button btnStop;
    @FXML private Button btnSet;

    private TimerLogic logic;
    private boolean toggle = false;

    @FXML
    public void initialize() {

    	// opções de min e hrs para o timer
    	
    	DefinirHoras.getItems().addAll(
                "10min",
                "20min",
                "30min",
                "1h",
                "2h",
                "3h"
        );
    	
    	
        logic = new TimerLogic(
                this::updateText,
                this::flashText
        );

        btnStart.setOnAction(e -> logic.start());
        btnPause.setOnAction(e -> logic.pause());
        btnStop.setOnAction(e -> {
            logic.pause();
            logic.resetToZero();
            textTimeLeft.setText("00:00:00");
        });
        
        btnSet.setOnAction(e -> {
        	String selected = DefinirHoras.getValue();
            if (selected == null) return;

            String hhmmss = convertToHHMMSS(selected);

            try {
                logic.setTime(hhmmss);
                textTimeLeft.setText(hhmmss);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
              	
    }

    private String convertToHHMMSS(String value) {
        switch (value) {
            case "10min": return "00:10:00";
            case "20min": return "00:20:00";
            case "30min": return "00:30:00";
            case "1h":    return "01:00:00";
            case "2h":    return "02:00:00";
            case "3h":    return "03:00:00";
        }
        return "00:00:00";
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
