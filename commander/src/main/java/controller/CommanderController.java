package controller;

import commander.Commander;
import common.ServerInterface;
import common.SpaceCommand;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CommanderController implements Initializable {

    public Button buttonStartRound;
    public Button buttonGivePoints;
    public TextField textFieldCockpit;
    public TextField textFieldBattleCannon;
    public TextField textFieldLaserGun;
    public TableView tableViewPlayer;
    public TableColumn tableColumnPlayerName;
    public Button buttonRefresh;
    private Commander commander;
    private ServerInterface server;

    Integer totalScore = 0;
    public void givePoints_onAction(ActionEvent actionEvent) {
    }

    public void startRound_onAction(ActionEvent actionEvent) {

        if (commander != null)
        {
            List<Integer> parameters = new ArrayList<Integer>();
            parameters.add(Integer.valueOf(textFieldCockpit.getText()));
            broadcastCommand("Cockpit", parameters);

            List<Integer> parameters2 = new ArrayList<Integer>();
            parameters2.add(Integer.valueOf(textFieldBattleCannon.getText()));
            broadcastCommand("BattleCanon", parameters2);

            List<Integer> parameters3 = new ArrayList<Integer>();
            parameters3.add(Integer.valueOf(textFieldLaserGun.getText()));
            broadcastCommand("LaserGun", parameters3);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try
        {
            commander = new Commander(Main.login, this);
            server = commander.getServer();
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        totalScore = 0;
    }

    public void refresh_onAction(ActionEvent actionEvent) {
    }

    private void broadcastCommand(String type, List<Integer> parameters)
    {
        try
        {
            server.broadcastCommand(new SpaceCommand(type, parameters));
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
