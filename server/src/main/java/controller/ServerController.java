package controller;

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
import java.util.Map;
import java.util.ResourceBundle;

public class ServerController implements Initializable {
    public VBox vBoxMain;
    public TableView<ConnectedCommander> tableViewCommander;
    private Server ss;
    @FXML
    private TableColumn<ConnectedCommander, String> tableColumnCommanderName;
    private ObservableList<ConnectedCommander> commanders = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnCommanderName.setCellValueFactory(new PropertyValueFactory<ConnectedCommander,String>("name"));
        tableViewCommander.setItems(commanders);
    }

    public void buttonKickPlayer_onAction(ActionEvent actionEvent) {
    }

    public void buttonRefresh_onAction(ActionEvent actionEvent) {
        for (Map.Entry<String, ConnectedCommander> entry : ss.getCommanders().entrySet())
        {
            commanders.add(entry.getValue());
        }
    }

    public void buttonKickCommander_onAction(ActionEvent actionEvent) {
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
