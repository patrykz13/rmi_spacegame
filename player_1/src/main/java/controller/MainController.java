package controller;

import common.ServerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.Main;
import player1.Player1;
import player_fx_bean.PlayerBean;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    PlayerBean playerBean;
    private Player1 player;
    private ServerInterface server;


    public void initialize(URL location, ResourceBundle resources) {

        playerBean.initPlayerAndCaptainNicknames(Main.login, Main.commander, "kabina pilota");

        try
        {
            player = new Player1(Main.login, this, "Cockpit",Main.commander);
            server = player.getServer();
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public void enterTheGame_onAction(ActionEvent actionEvent) {
    }
}
