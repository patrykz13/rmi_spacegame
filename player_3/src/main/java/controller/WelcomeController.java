package controller;

import common.ServerInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WelcomeController extends UnicastRemoteObject implements Initializable {


    public TextField textFieldLogin;
    public ComboBox<String> comboBoxCommander;
    private ServerInterface server;
    private List<String> listOfCommanders;
    private ObservableList<String> commandersNames = FXCollections.observableArrayList();

    public WelcomeController() throws RemoteException
    {}


    public void enterTheGame_onAction(ActionEvent actionEvent) {
        Main.commander=comboBoxCommander.getSelectionModel().getSelectedItem().toString();
        Main.login=textFieldLogin.getText();
        Main.server=server;
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getClassLoader().getResource("fxml/main.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
            Stage primaryStage = new Stage();
            Main.setMainStage(primaryStage);
            primaryStage.setTitle("Spaceteam v.2");
            primaryStage.setScene(new Scene(parent, 800, 459));

            Stage stage = (Stage) textFieldLogin.getScene().getWindow();
            stage.hide();
            primaryStage.show();
        } catch (IOException ioEcx) {
            Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    public void initialize(URL location, ResourceBundle resources){
        try
        {
            String url = "rmi://localhost/sserver";
            server = (ServerInterface) Naming.lookup(url);
            listOfCommanders=server.getListOfCommanders();
        } catch (RemoteException ex)
        {
            System.out.println("Server RemoteException.");
            System.out.println(ex.getMessage());
        } catch (NotBoundException ex)
        {
            System.out.println("Server NotBoundException.");
            System.out.println(ex.getMessage());
        } catch (MalformedURLException ex)
        {
            System.out.println("Server MalformedURLException.");
            System.out.println(ex.getMessage());
        }
        comboBoxCommander.setItems(commandersNames);
        if(listOfCommanders!=null)
            commandersNames.addAll(listOfCommanders);

    }
}
