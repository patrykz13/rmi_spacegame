package main;

import common.ServerInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    private static Stage mainStage;
    public static String login;
    public static String commander;
    public static ServerInterface server;

    @Override
    public void start(Stage primaryStage) {


            FXMLLoader loader = new FXMLLoader();
            try {
                Main.mainStage = primaryStage;
                loader.setLocation(getClass().getClassLoader().getResource("fxml/welcome.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                mainStage.setTitle("Spaceteam v.2");
                mainStage.resizableProperty().setValue(Boolean.FALSE);
                mainStage.setScene(new Scene(root, 391, 222));
                mainStage.centerOnScreen();
                mainStage.show();
            } catch (IOException ioEcx) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ioEcx);
            }
    }

    @Override
    public void stop(){
        if (server != null) {
            try {
                server.removePlayer(login);
                server.broadcastNumberOfPlayers(Main.commander);
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
