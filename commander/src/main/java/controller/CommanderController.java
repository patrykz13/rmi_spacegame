package controller;

import commander.Commander;
import common.ServerInterface;
import common.SpaceCommand;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;
import main.Main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static main.Main.customMessageBox;

public class CommanderController implements Initializable {

    public Button buttonStartRound;
    public Button buttonGivePoints;
    public TextField textFieldCockpit;
    public TextField textFieldBattleCannon;
    public TextField textFieldLaserGun;
    public TableView<String> tableViewPlayer;
    public TableColumn<String, String> tableColumnPlayerName;
    public Button buttonRefresh;
    public TextField textFieldPoints;
    public Label labelCommander;
    public TextField labelRoundTime;
    public TextArea textAreaAnswer;
    public Label labelTimeToTheEndOfRound;
    private Commander commander;
    private ServerInterface server;
    private ObservableList<String> players = FXCollections.observableArrayList();
    private Integer totalScore;
    private Integer seconds;

    public void givePoints_onAction(ActionEvent actionEvent) {
        totalScore += Integer.parseInt(textFieldPoints.getText());
        System.out.println(totalScore);
        System.out.println(Main.login);

        sendPoints(Main.login, totalScore);
    }

    public void startRound_onAction(ActionEvent actionEvent) {

        if (!players.isEmpty()) {
            if (!labelRoundTime.getText().equals("")) {
                if (textFieldCockpit != null)
                    broadcastCommand("kabina pilota", textFieldCockpit.getText(), Main.login);
                if (textFieldBattleCannon != null)
                    broadcastCommand("Działko bojowe", textFieldBattleCannon.getText(), Main.login);
                if (textFieldLaserGun != null)
                    broadcastCommand("działko laserowe", textFieldLaserGun.getText(), Main.login);
                startRound(Integer.parseInt(labelRoundTime.getText()));
            } else {
                customMessageBox.showMessageBox(Alert.AlertType.WARNING, "błąd",
                        "Runda nie zostałą rozpoczęta.",
                        "Powód: czas nie został podany.").showAndWait();
            }
        } else {
            customMessageBox.showMessageBox(Alert.AlertType.WARNING, "błąd",
                    "Runda nie zostałą rozpoczęta.",
                    "Powód: brak graczy.").showAndWait();
        }


    }

    private void startRound(Integer roundTime) {
        try {
            startThread(roundTime);
            server.startRound(roundTime, Main.login);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelCommander.setText(Main.login);
        tableColumnPlayerName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        totalScore = 0;
        try {
            commander = new Commander(Main.login, this);
            server = commander.getServer();
            Main.server = server;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        totalScore = 0;
        tableViewPlayer.setItems(players);

    }

    private void startThread(Integer integer) {
        Timeline time = new Timeline();
        seconds = integer;
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                seconds--;
                buttonStartRound.setDisable(true);
                labelTimeToTheEndOfRound.setText(seconds.toString());
                if (seconds <= 0) {
                    buttonStartRound.setDisable(false);
                    initNewRoundComponents();
                    time.stop();

                }
            }
        });

        time.setCycleCount(Timeline.INDEFINITE);
        time.getKeyFrames().add(frame);
        if (time != null) {
            time.stop();
        }
        time.play();
    }

    private void initNewRoundComponents() {
        textFieldLaserGun.setText("");
        textFieldBattleCannon.setText("");
        textFieldCockpit.setText("");
        labelRoundTime.setText("");
        labelTimeToTheEndOfRound.setText("---");
    }

    public void refresh_onAction(ActionEvent actionEvent) {
    }

    private void broadcastCommand(String type, List<Integer> parameters) {
        try {
            server.broadcastCommand(new SpaceCommand(type, parameters));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void broadcastCommand(String type, String message, String commanderName) {
        try {
            server.broadcastCommand(type, message, commanderName);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void sendPoints(String commanderName, Integer points) {
        try {
            server.sendPoints(commanderName, points);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updatePlayerList(List<String> list) {

        players.clear();
        players.setAll(list);
    }

    public void exitFromApplication() {
        Platform.runLater(() -> {
            Main.server = null;
            customMessageBox.showMessageBox(Alert.AlertType.ERROR, "BŁĄD KRYTYCZNY",
                    "Gra została przerwana.",
                    "Powód: utracono połączenie z serwerem.").showAndWait();
        });
    }
}
