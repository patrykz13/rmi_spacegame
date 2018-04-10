import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerBean extends VBox implements Serializable {

    @FXML
    public Label labelTimeToTheEnd;
    public VBox vBoxplayer2;
    public VBox vBoxPlayer1;
    public VBox vBoxPlayer3;
    public Label labelPoints;
    public Label labelCommander;
    public Label labelPlayer;
    @FXML
    public Label   labelNumberOfPlayers, labelPanelName,
            labelCommanderCommand, labelDevice1Player1_Name,
            labelDevice2Player1_Name, labelDevice3Player1_Name, lavelDevice3Player1_MinValue,
            lavelDevice3Player1_MaxValue, labelDevice1Player2_Name, labelDevice2Player1_Name1, labelDevice2Player2_Parameter1_Name,
            labelDevice2Player2_Parameter1_Value, labelDevice2Player2_Parameter2_Name, labelDevice2Player2_Parameter3_Name,
            labelDevice2Player2_Parameter3_Value, labelDevice3Player2_Name,
            lavelDevice3Player2_MinValue, lavelDevice3Player2_MaxValue,
            labelDevice1Player3_Name, labelDevice2Player3_Name,
            labelDevice3Player3_Name;

    @FXML
    public TextField  textFieldDevice1Player1_Value, textFieldDevice1Player2_Value,
            textFieldDevice2Player2_Parameter2_Value, textFieldDevice1Player3_Value;


    @FXML
    public RadioButton radioButtonDevice2Player1_Option1, radioButtonDevice2Player1_Option2,
            radioButtonDevice2Player3_Option1, radioButtonDevice2Player3_Option2;

    @FXML
    public Slider sliderDevice3Player1_Value, sliderDevice3Player2_Value;

    @FXML
    public Button buttonDevice1Player3;
    public TextArea textAreaCommanderCommand;

    public PlayerBean() {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/fxml/playerBean.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
