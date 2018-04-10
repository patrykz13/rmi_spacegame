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
    public Commander commander;
    public ServerInterface server;

    Integer totalScore = 0;
    public void givePoints_onAction(ActionEvent actionEvent) {
    }

    public void startRound_onAction(ActionEvent actionEvent) {

        if (commander != null)
        {
            List<Integer> parameters = new ArrayList<Integer>();
            parameters.add(Integer.valueOf(tex.getText()));
            broadcastCommand(SpaceCommandType.BOOST, parameters);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try
        {
            commander = new Commander("Kapitan Test", this);
            server = commander.getServer();
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        totalScore = 0;
    }

    public void refresh_onAction(ActionEvent actionEvent) {
    }

    private void broadcastCommand(SpaceCommandType type, List<Integer> parameters)
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
