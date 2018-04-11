package controller;

import common.ServerInterface;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.Main;
import player2.Player2;
import player_fx_bean.PlayerBean;
import java.lang.Thread;

import java.net.URL;
import javafx.util.Duration;
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


    public void initialize(URL location, ResourceBundle resources) {

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
                    time.stop();
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
}

