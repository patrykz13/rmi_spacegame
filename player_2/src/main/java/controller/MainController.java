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
import main.Main;
import player2.Player2;
import player_fx_bean.PlayerBean;
import java.lang.Thread;

import java.net.URL;
import javafx.util.Duration;

import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class MainController implements Initializable{

    public PlayerBean getPlayerBean() {
        return playerBean;
    }

    @FXML
    PlayerBean playerBean;
    private Player2 player;
    private ServerInterface server;
    private Integer seconds=0;
    private CustomMessageBox customMessageBox;

    public void initialize(URL location, ResourceBundle resources) {
        customMessageBox=new CustomMessageBox();
        playerBean.initPlayerAndCaptainNicknames(Main.login, Main.commander, "działko laserowe");

        try
        {
            player = new Player2(Main.login, this, "działko laserowe",Main.commander);
            server = player.getServer();
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }


    public void startThread(Integer integer) {
        Timeline time= new Timeline();
        seconds=integer;
        KeyFrame frame= new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                seconds--;
                playerBean.setIntegerPropertyTimeToEndOfRound(seconds);
                if(seconds<=0){
                    try {
                        server.broadcastRoundAnswer("GRACZ "+Main.login+" - DZIAŁKO LASEROWE ODPOWIEDZIAŁ: \n"+
                                        "Temperatura promienia  "+playerBean.textFieldDevice1Player2_Value.getText()+"\n"+
                                        "Ilość aktywnych promieni: "+playerBean.textFieldDevice2Player2_Parameter2_Value.getText()+"\n"+
                                        "Długość promienia: " + playerBean.getDoublePropertyImpulseValue()+"\n"
                                ,Main.commander);
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
        if(time!=null){
            time.stop();
        }
        time.play();
    }

    public void exitFromApplication() {
        try {
            server.removePlayer(Main.login);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> {
            Main.server = null;
            playerBean.booleanPropertyKickFromServerProperty().setValue(true);
            customMessageBox.showMessageBox(Alert.AlertType.ERROR, "BŁĄD KRYTYCZNY",
                    "Gra została przerwana.",
                    "Powód: utracono połączenie z serwerem.").showAndWait();
        });
    }
}

