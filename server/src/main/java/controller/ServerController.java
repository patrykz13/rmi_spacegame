package controller;

import common.SpaceCommand;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.ConnectedCommander;
import server.ConnectedPlayer;
import server.Server;

import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.ResourceBundle;

public class ServerController implements Initializable {
    public VBox vBoxMain;
    public TableView<ConnectedCommander> tableViewCommander;
    public TableView<ConnectedPlayer> tableViewPlayer;
    public TableColumn<ConnectedPlayer,String> tableColumnPlayerName;
    public TableColumn<ConnectedPlayer,String>  tableColumnPlayerCommander;
    private Server ss;
    @FXML
    private TableColumn<ConnectedCommander, String> tableColumnCommanderName;
    private ObservableList<ConnectedCommander> commanders = FXCollections.observableArrayList();
    private ObservableList<ConnectedPlayer> players = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnCommanderName.setCellValueFactory(new PropertyValueFactory<ConnectedCommander,String>("name"));
        tableColumnPlayerName.setCellValueFactory(new PropertyValueFactory<ConnectedPlayer,String>("name"));
        tableColumnPlayerCommander.setCellValueFactory(new PropertyValueFactory<ConnectedPlayer,String>("s"));
        //(cellData -> new SimpleStringProperty(cellData.getValue().getCommander().name))
        tableViewCommander.setItems(commanders);
        tableViewPlayer.setItems(players);
    }

    public void buttonKickPlayer_onAction(ActionEvent actionEvent) throws RemoteException {
           // users.getUsers().get(PlayerList.getSelectedIndex()).sendOrder("---------------------------"+"\n");
            //users.getUsers().get(PlayerList.getSelectedIndex()).sendOrder("Zostałeś wyrzucony z gry"+"\n");
            //kapitan.setMsg("---------------------------"+"\n");
            //kapitan.setMsg("Gracz :"+users.getUsers().get(PlayerList.getSelectedIndex()).getName()+" został wyrzucony z gry"+"\n");
        players.remove(tableViewPlayer.getSelectionModel().getSelectedItem());
        ConnectedPlayer c =  ss.getPlayers().get(tableViewPlayer.getSelectionModel().getSelectedItem().getName());
        c.getConnection().becomeKickout(true);
        c.getConnection().receiveCommand("\n zostałeś wykickowany :(");

        ss.getPlayers().remove(tableViewPlayer.getSelectionModel().getSelectedItem().getName());
        refreshTables();
    }

    public void buttonRefresh_onAction(ActionEvent actionEvent) {
        refreshTables();
    }

    private void refreshTables() {
        commanders.clear();
        players.clear();
        if(ss.getCommanders()!=null) {
            for (Map.Entry<String, ConnectedCommander> entry : ss.getCommanders().entrySet()) {
                commanders.add(entry.getValue());
            }
        }
        if(ss.getPlayers()!=null) {
            for (Map.Entry<String, ConnectedPlayer> entry : ss.getPlayers().entrySet()) {
                players.add(entry.getValue());
            }
        }
    }

    public void buttonKickCommander_onAction(ActionEvent actionEvent) {
        commanders.remove(tableViewCommander.getSelectionModel().getSelectedItem());
        ss.getCommanders().remove(tableViewCommander.getSelectionModel().getSelectedItem().getName());
        refreshTables();
    }

    public void buttonStop_onAction(ActionEvent actionEvent) {
        Stage stage = (Stage) vBoxMain.getScene().getWindow();
        stage.close();
    }

    public void buttonStart_onAction(ActionEvent actionEvent) {

        try
        {
            int port = 1099;
            String url = "rmi://localhost/sserver";
            java.rmi.registry.LocateRegistry.createRegistry(port);
            ss = new Server();
            Naming.rebind(url, ss);
            System.out.println("Server ready.");
        } catch (Exception ex)
        {
            System.out.println("Server exception.");
            System.out.println(ex.getMessage());
        }
    }
}
