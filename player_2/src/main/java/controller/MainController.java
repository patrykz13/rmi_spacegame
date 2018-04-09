package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.Main;

public class MainController {


    public TextField textFieldLogin;
    public ComboBox comboBoxCommander;

    public void enterTheGame_onAction(ActionEvent actionEvent) {
        Main.commander=comboBoxCommander.getSelectionModel().getSelectedItem().toString();
        Main.login=textFieldLogin.getText();
    }
}
