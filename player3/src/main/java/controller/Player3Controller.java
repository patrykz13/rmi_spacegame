package controller;

import common.ServerInterface;
import javafx.fxml.Initializable;
import main.Main;
import player3.Player3;

import java.net.URL;
import java.util.ResourceBundle;

public class Player3Controller implements Initializable {
    private Player3 player;
    private ServerInterface server;


    public void initialize(URL location, ResourceBundle resources) {


        try
        {
            player = new Player3(Main.login, this, "BattleCannon",Main.commander);
            server = player.getServer();
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }
}
