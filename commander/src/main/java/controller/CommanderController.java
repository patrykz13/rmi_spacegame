package controller;

import commander.Commander;
import common.ServerInterface;
import common.SpaceCommand;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public TableView<String> tableViewPlayer;
    public TableColumn<String,String> tableColumnPlayerName;
    public Button buttonRefresh;
    public TextField textFieldPoints;
    public Label labelCommander;
    public TextField labelRoundTime;
    private Commander commander;
    private ServerInterface server;
    private ObservableList<String> players = FXCollections.observableArrayList();

    private Integer totalScore;
    public void givePoints_onAction(ActionEvent actionEvent) {
        totalScore+=Integer.parseInt(textFieldPoints.getText());
        System.out.println(totalScore);
        System.out.println(Main.login);

        sendPoints(Main.login,totalScore);
    }

    public void startRound_onAction(ActionEvent actionEvent) {

        if (commander != null)
        {
            if (textFieldCockpit!=null)
                broadcastCommand("kabina pilota", textFieldCockpit.getText(),Main.login);
            if (textFieldBattleCannon!=null)
                broadcastCommand("Działko bojowe", textFieldBattleCannon.getText(),Main.login);
            if (textFieldLaserGun!=null)
                broadcastCommand("działko laserowe", textFieldLaserGun.getText(),Main.login);
            startRound(Integer.parseInt(labelRoundTime.getText()));
        }

    }

    private void startRound(Integer roundTime) {
        try
        {
            server.startRound(roundTime,Main.login);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelCommander.setText(Main.login);
        tableColumnPlayerName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        totalScore=0;
        try
        {
            commander = new Commander(Main.login, this);
            server = commander.getServer();
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        totalScore = 0;
        tableViewPlayer.setItems(players);

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

    private void broadcastCommand(String type, String message,String commanderName)
    {
        try
        {
            server.broadcastCommand(type, message,commanderName);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private void sendPoints(String commanderName, Integer points)
    {
        try
        {
            server.sendPoints(commanderName, points);
        } catch (Exception ex)
        {
            System.out.println("kurwa");
            System.out.println(ex.getMessage());
        }
    }

    public void updatePlayerList(List<String> list) {

        players.clear();
        players.setAll(list);
    }
}
