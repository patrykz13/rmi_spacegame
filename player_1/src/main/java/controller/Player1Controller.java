package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.Main;
import player_fx_bean.PlayerBean;

import java.net.URL;
import java.util.ResourceBundle;

public class Player1Controller implements Initializable {

    @FXML
    PlayerBean playerBean;
    public void initialize(URL location, ResourceBundle resources) {

        /*
        try
        {
            player = new Player1(playerName, this, clientType);
            server = player.getServer();
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        */
    }

    public void enterTheGame_onAction(ActionEvent actionEvent) {
    }
}
