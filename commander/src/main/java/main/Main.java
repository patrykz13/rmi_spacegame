package main;

import common.ServerInterface;
import customBox.CustomMessageBox;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application
{

    private static Stage mainStage;
    public static String login;
    public static ServerInterface server;
    public static CustomMessageBox customMessageBox;
    @Override
    public void start(Stage primaryStage) {
        try {
            customMessageBox = new CustomMessageBox();
            setMainStage(primaryStage);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/welcome.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            primaryStage.setScene(new Scene(root, 391, 222));
            //primaryStage.getIcons().add(new Image("/images/phone.png"));
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException ioEcx) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    @Override
    public void stop(){
        if (server != null) {
            try {
                server.removeCommander(login);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

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