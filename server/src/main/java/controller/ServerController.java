package controller;

import customBox.CustomMessageBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

import static main.Main.commanders;
import static main.Main.players;

public class ServerController implements Initializable {
    public VBox vBoxMain;
    public TableView<ConnectedCommander> tableViewCommander;
    public TableView<ConnectedPlayer> tableViewPlayer;
    public TableColumn<ConnectedPlayer, String> tableColumnPlayerName;
    public TableColumn<ConnectedPlayer, String> tableColumnPlayerCommander;
    public TableColumn<ConnectedCommander, String> tableColumnCommandersPlayers;
    public CustomMessageBox customMessageBox;
    private Server ss;
    @FXML
    private TableColumn<ConnectedCommander, String> tableColumnCommanderName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customMessageBox = new CustomMessageBox();
        tableColumnCommanderName.setCellValueFactory(new PropertyValueFactory<ConnectedCommander, String>("name"));
        tableColumnCommandersPlayers.setCellValueFactory(new PropertyValueFactory<ConnectedCommander, String>("numberOfPlayers"));
        tableColumnPlayerName.setCellValueFactory(new PropertyValueFactory<ConnectedPlayer, String>("name"));
        tableColumnPlayerCommander.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCommander().getName()));        //
        tableViewCommander.setItems(commanders);
        tableViewPlayer.setItems(players);
    }

    public void buttonKickPlayer_onAction(ActionEvent actionEvent) throws RemoteException {
        if (tableViewPlayer.getSelectionModel().getSelectedItem() != null) {
            ConnectedPlayer selectedPlayer = tableViewPlayer.getSelectionModel().getSelectedItem();
            ss.removePlayer(selectedPlayer.getName());
            players.remove(tableViewPlayer.getSelectionModel().getSelectedItem());
            selectedPlayer.getConnection().becomeKickout(true);
            selectedPlayer.getConnection().receiveCommand(" zostałeś wykickowany :(");
            ConnectedPlayer c = ss.getPlayers().get(tableViewPlayer.getSelectionModel().getSelectedItem().getName());
            ss.removePlayer(c.getName());

        } else
            customMessageBox.showMessageBox(Alert.AlertType.WARNING, "Ostrzeżenie",
                    "Operacja wyrzucenia kapitana z serwera nie powiedzie się.",
                    "Powód: nie zaznaczono kapitana.")
                    .showAndWait();



        //refreshTables();
    }

    public void buttonRefresh_onAction(ActionEvent actionEvent) {
        refreshTables();
    }

    public void refreshTables() {
        commanders.clear();
        players.clear();
        try {
            if (ss.getCommanders() != null) {
                for (Map.Entry<String, ConnectedCommander> entry : ss.getCommanders().entrySet()) {
                    commanders.add(entry.getValue());
                }
            }
            if (ss.getPlayers() != null) {
                for (Map.Entry<String, ConnectedPlayer> entry : ss.getPlayers().entrySet()) {
                    players.add(entry.getValue());
                }
            }
        }catch (NullPointerException e){
            e.getMessage();
        }
    }

    public void buttonKickCommander_onAction(ActionEvent actionEvent) throws RemoteException {
        if (tableViewCommander.getSelectionModel().getSelectedItem() != null) {
            ConnectedCommander selectedCaptain = tableViewCommander.getSelectionModel().getSelectedItem();
            ss.removeCommander(selectedCaptain.getName());
            commanders.remove(tableViewCommander.getSelectionModel().getSelectedItem());
            selectedCaptain.getConnection().receivePlayersAnswer("\n zostałeś wykickowany :(");

            //  Main.captainObservableList.remove(selectedCaptain);
        } else
            customMessageBox.showMessageBox(Alert.AlertType.WARNING, "Ostrzeżenie",
                    "Operacja wyrzucenia kapitana z serwera nie powiedzie się.",
                    "Powód: nie zaznaczono kapitana.")
                    .showAndWait();
        refreshTables();
    }

    public void buttonStop_onAction(ActionEvent actionEvent) {
        Stage stage = (Stage) vBoxMain.getScene().getWindow();
        stage.close();
    }

    public void buttonStart_onAction(ActionEvent actionEvent) {

        try {
            int port = 1099;
            String url = "rmi://localhost/sserver";
            java.rmi.registry.LocateRegistry.createRegistry(port);
            ss = new Server(this);
            Naming.rebind(url, ss);
            System.out.println("Server ready.");
        } catch (Exception ex) {
            System.out.println("Server exception.");
            System.out.println(ex.getMessage());
        }
    }

}
