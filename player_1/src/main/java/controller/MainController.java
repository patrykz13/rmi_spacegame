package controller;

import common.ServerInterface;
import customBox.CustomMessageBox;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import main.Main;
import player1.Player1;
import player_fx_bean.PlayerBean;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML
    PlayerBean playerBean;
    private Player1 player;
    private ServerInterface server;
    private Integer seconds;
    private CustomMessageBox customMessageBox;

    public void initialize(URL location, ResourceBundle resources) {
        customMessageBox = new CustomMessageBox();
        playerBean.initPlayerAndCaptainNicknames(Main.login, Main.commander, "kabina pilota");

        try {
            player = new Player1(Main.login, this, "kabina pilota", Main.commander);
            server = player.getServer();
            server.broadcastNumberOfPlayers(Main.commander);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void startThread(Integer integer) {
        Timeline time = new Timeline();
        seconds = integer;
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                seconds--;
                playerBean.setIntegerPropertyTimeToEndOfRound(seconds);
                if (seconds <= 0) {
                    try {
                        if (playerBean.isFuelCombustionMode_1())
                            server.broadcastRoundAnswer("GRACZ " + Main.login + " - Z KABINY PILOTA ODPOWIEDZIAŁ: \n" +
                                    "Prędkość rakiety: " + playerBean.textFieldDevice1Player1_Value.getText() + "\n" +
                                    "Kąt skrętu : " + playerBean.getDoublePropertyAngleOfAttack() + "\n" +
                                    "tryb silnika: " + playerBean.radioButtonDevice2Player1_Option1.getText() + "\n", Main.commander);
                        else if (playerBean.isFuelCombustionMode_2()) {
                            server.broadcastRoundAnswer("GRACZ " + Main.login + " - Z KABINY PILOTA ODPOWIEDZIAŁ: \n" +
                                    "Prędkość rakiety: " + playerBean.textFieldDevice1Player1_Value.getText() + "\n" +
                                    "Kąt skrętu : " + playerBean.getDoublePropertyAngleOfAttack() + "\n" +
                                    "tryb silnika: " + playerBean.radioButtonDevice2Player1_Option2.getText() + "\n", Main.commander);

                        } else {
                            server.broadcastRoundAnswer("GRACZ " + Main.login + " - Z KABINY PILOTA ODPOWIEDZIAŁ: \n" +
                                    "Prędkość rakiety: " + playerBean.textFieldDevice1Player1_Value.getText() + "\n" +
                                    "Kąt skrętu : " + playerBean.getDoublePropertyAngleOfAttack() + "\n" +
                                    "tryb silnika: " + "nie ustawiono" + "\n", Main.commander);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    time.stop();
                    playerBean.initNewRoundComponentsValues();

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

    public void enterTheGame_onAction(ActionEvent actionEvent) {
    }

    public PlayerBean getPlayerBean() {
        return playerBean;
    }

    public void setPlayerBean(PlayerBean playerBean) {
        this.playerBean = playerBean;
    }

    public void exitFromApplication() {
        Platform.runLater(() -> {
            try {
                server.broadcastNumberOfPlayers(Main.commander);
                server.removePlayer(Main.login);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Main.server = null;
            playerBean.booleanPropertyKickFromServerProperty().setValue(true);
            customMessageBox.showMessageBox(Alert.AlertType.ERROR, "BŁĄD KRYTYCZNY",
                    "Gra została przerwana.",
                    "Powód: utracono połączenie z serwerem.").showAndWait();
        });
    }

}
