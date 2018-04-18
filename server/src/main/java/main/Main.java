package main;

import controller.ServerController;
import customBox.CustomMessageBox;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import server.ConnectedCommander;
import server.ConnectedPlayer;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application
{

    private static Stage mainStage;
    public static String login;
    public static ObservableList<ConnectedCommander> commanders = FXCollections.observableArrayList();
    public static ObservableList<ConnectedPlayer> players = FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) {
        try {
            setMainStage(primaryStage);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/server.fxml"));
            primaryStage.getIcons().add(new Image("/image/server.png"));

            loader.load();
            Parent root = loader.getRoot();
            primaryStage.setScene(new Scene(root, 906, 406));
            //primaryStage.getIcons().add(new Image("/images/phone.png"));
            primaryStage.centerOnScreen();


            primaryStage.show();
        } catch (IOException ioEcx) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    @Override
    public void stop() {
        Main.players.forEach(connectedPlayer ->{
            try {
                connectedPlayer.getConnection().lossConnectionWithServer();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        Main.commanders.forEach(connectedCaptain ->{
            try {
                connectedCaptain.getConnection().lossConnectionWithServer();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });


        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage) {
        Main.mainStage = mainStage;
    }


}
