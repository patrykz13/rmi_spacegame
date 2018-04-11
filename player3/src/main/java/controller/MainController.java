package controller;

import common.ServerInterface;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.Duration;
import main.Main;
import player3.Player3;
import player_fx_bean.PlayerBean;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    PlayerBean playerBean;
    private Player3 player;
    private ServerInterface server;
    private Integer seconds;

    public void initialize(URL location, ResourceBundle resources) {

        playerBean.initPlayerAndCaptainNicknames(Main.login, Main.commander, "Działko bojowe");

        try
        {
            player = new Player3(Main.login, this, "Działko bojowe",Main.commander);
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

    public PlayerBean getPlayerBean() {
        return playerBean;
    }

}
