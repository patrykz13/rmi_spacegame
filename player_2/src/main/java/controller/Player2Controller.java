package controller;

import common.ServerInterface;
import javafx.fxml.Initializable;
import main.Main;
import player2.Player2;

import java.net.URL;
import java.util.ResourceBundle;

public class Player2Controller implements Initializable {

    private Player2 player;
    private ServerInterface server;


    public void initialize(URL location, ResourceBundle resources) {


        try
        {
            player = new Player2(Main.login, this, "LaserGun",Main.commander);
            server = player.getServer();
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }
}
