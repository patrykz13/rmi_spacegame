package controller;

import common.ServerInterface;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WelcomeController extends UnicastRemoteObject implements Initializable {


    public TextField textFieldLogin;
    public ComboBox comboBoxCommander;
    private ServerInterface server;

    public void enterTheGame_onAction(ActionEvent actionEvent) {
        Main.commander=comboBoxCommander.getSelectionModel().getSelectedItem().toString();
        Main.login=textFieldLogin.getText();

        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getClassLoader().getResource("fxml/main.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
            Stage primaryStage = new Stage();
            Main.setMainStage(primaryStage);
            primaryStage.setTitle("Listen To Your Captain - ver. Client no. 1");
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(900);
            primaryStage.setScene(new Scene(parent, 1600, 900));
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
            server.g
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
    }
}
