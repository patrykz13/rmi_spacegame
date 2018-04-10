package player_fx_bean;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

public class PlayerBean extends VBox implements Serializable {

    private final StringProperty stringPropertyPanelName = new SimpleStringProperty("");
    private final IntegerProperty integerPropertyNumberOfPoints = new SimpleIntegerProperty(0);
    private final IntegerProperty integerPropertyNumberOfPlayers = new SimpleIntegerProperty(0);
    private final IntegerProperty integerPropertyTimeToEndOfRound = new SimpleIntegerProperty(0);
    private final StringProperty stringPropertyCaptainCommand = new SimpleStringProperty("");
    private final BooleanProperty booleanPropertyKickFromServer = new SimpleBooleanProperty(true);
    private final StringProperty stringPropertyCaptainNickname = new SimpleStringProperty("");
    private final DoubleProperty doublePropertyEngineTemperature = new SimpleDoubleProperty(0.0);
    private final DoubleProperty doublePropertyCombustionTemperature = new SimpleDoubleProperty(215.0);
    private final DoubleProperty doublePropertyAngleOfAttack = new SimpleDoubleProperty(13.0);
    private final IntegerProperty integerPropertyNumberOfVentilators = new SimpleIntegerProperty(1);
    private final BooleanProperty fuelCombustionMode_1 = new SimpleBooleanProperty(false);
    private final BooleanProperty fuelCombustionMode_2 = new SimpleBooleanProperty(false);
    private final BooleanProperty typeOfTriggerMechanism_1 = new SimpleBooleanProperty(false);
    private final BooleanProperty typeOfTriggerMechanism_2 = new SimpleBooleanProperty(false);
    private final BooleanProperty booleanPropertyLoadBullet = new SimpleBooleanProperty(false);

    @FXML
    public Label labelTimeToTheEnd;
    public VBox vBoxPlayer2;
    public VBox vBoxPlayer1;
    public VBox vBoxPlayer3;
    public Label labelPoints;
    public Label labelCommander;
    public Label labelGameStatus;
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
        fxmlconnection();
        initRadioButtons();
        addPropertyBindings();
        addPropertyListeners();

        booleanPropertyKickFromServer.setValue(false);
        buttonDevice1Player3.setOnAction(event -> booleanPropertyLoadBullet.setValue(true));

        initNewRoundComponentsValues();
    }

    void fxmlconnection() {
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

    private void prepareContactModeComponents(String mode) {
        switch (mode) {
            case "kabina pilota":
                vBoxPlayer1.setVisible(true);
                vBoxPlayer1.setDisable(false);
                vBoxPlayer1.setMinWidth(Control.USE_COMPUTED_SIZE);
                vBoxPlayer1.setMinHeight(Control.USE_COMPUTED_SIZE);
                vBoxPlayer1.setPrefWidth(Control.USE_COMPUTED_SIZE);
                vBoxPlayer1.setPrefHeight(Control.USE_COMPUTED_SIZE);
                vBoxPlayer1.setMaxWidth(Control.USE_COMPUTED_SIZE);
                vBoxPlayer1.setMaxHeight(Control.USE_COMPUTED_SIZE);

                vBoxPlayer2.setVisible(false);
                vBoxPlayer2.setDisable(true);
                vBoxPlayer2.setMinWidth(0);
                vBoxPlayer2.setMinHeight(0);
                vBoxPlayer2.setPrefWidth(0);
                vBoxPlayer2.setPrefHeight(0);
                vBoxPlayer2.setMaxWidth(0);
                vBoxPlayer2.setMaxHeight(0);

                vBoxPlayer3.setVisible(false);
                vBoxPlayer3.setDisable(true);
                vBoxPlayer3.setMinWidth(0);
                vBoxPlayer3.setMinHeight(0);
                vBoxPlayer3.setPrefWidth(0);
                vBoxPlayer3.setPrefHeight(0);
                vBoxPlayer3.setMaxWidth(0);
                vBoxPlayer3.setMaxHeight(0);
                break;

            case "działko laserowe":
                vBoxPlayer1.setVisible(false);
                vBoxPlayer1.setDisable(true);
                vBoxPlayer1.setMinWidth(0);
                vBoxPlayer1.setMinHeight(0);
                vBoxPlayer1.setPrefWidth(0);
                vBoxPlayer1.setPrefHeight(0);
                vBoxPlayer1.setMaxWidth(0);
                vBoxPlayer1.setMaxHeight(0);

                vBoxPlayer2.setVisible(true);
                vBoxPlayer2.setDisable(false);
                vBoxPlayer2.setMinWidth(Control.USE_COMPUTED_SIZE);
                vBoxPlayer2.setMinHeight(Control.USE_COMPUTED_SIZE);
                vBoxPlayer2.setPrefWidth(Control.USE_COMPUTED_SIZE);
                vBoxPlayer2.setPrefHeight(Control.USE_COMPUTED_SIZE);
                vBoxPlayer2.setMaxWidth(Control.USE_COMPUTED_SIZE);
                vBoxPlayer2.setMaxHeight(Control.USE_COMPUTED_SIZE);

                vBoxPlayer3.setVisible(false);
                vBoxPlayer3.setDisable(true);
                vBoxPlayer3.setMinWidth(0);
                vBoxPlayer3.setMinHeight(0);
                vBoxPlayer3.setPrefWidth(0);
                vBoxPlayer3.setPrefHeight(0);
                vBoxPlayer3.setMaxWidth(0);
                vBoxPlayer3.setMaxHeight(0);
                break;

            case "Działko bojowe":
                vBoxPlayer1.setVisible(false);
                vBoxPlayer1.setDisable(true);
                vBoxPlayer1.setMinWidth(0);
                vBoxPlayer1.setMinHeight(0);
                vBoxPlayer1.setPrefWidth(0);
                vBoxPlayer1.setPrefHeight(0);
                vBoxPlayer1.setMaxWidth(0);
                vBoxPlayer1.setMaxHeight(0);

                vBoxPlayer2.setVisible(false);
                vBoxPlayer2.setDisable(true);
                vBoxPlayer2.setMinWidth(0);
                vBoxPlayer2.setMinHeight(0);
                vBoxPlayer2.setPrefWidth(0);
                vBoxPlayer2.setPrefHeight(0);
                vBoxPlayer2.setMaxWidth(0);
                vBoxPlayer2.setMaxHeight(0);

                vBoxPlayer3.setVisible(true);
                vBoxPlayer3.setDisable(false);
                vBoxPlayer3.setMinWidth(Control.USE_COMPUTED_SIZE);
                vBoxPlayer3.setMinHeight(Control.USE_COMPUTED_SIZE);
                vBoxPlayer3.setPrefWidth(Control.USE_COMPUTED_SIZE);
                vBoxPlayer3.setPrefHeight(Control.USE_COMPUTED_SIZE);
                vBoxPlayer3.setMaxWidth(Control.USE_COMPUTED_SIZE);
                vBoxPlayer3.setMaxHeight(Control.USE_COMPUTED_SIZE);
                break;
        }
    }

    public void initNewRoundComponentsValues() {
        textFieldDevice1Player1_Value.setText("");
        radioButtonDevice2Player1_Option1.setSelected(false);
        radioButtonDevice2Player1_Option2.setSelected(false);
        sliderDevice3Player1_Value.setValue(13.0);

        textFieldDevice1Player2_Value.setText("");
        integerPropertyNumberOfVentilators.setValue(new Random().nextInt(20));
        textFieldDevice2Player2_Parameter2_Value.setText("");
        sliderDevice3Player2_Value.setValue(215.0);

        textFieldDevice1Player3_Value.setText("");
        radioButtonDevice2Player3_Option1.setSelected(false);
        radioButtonDevice2Player3_Option2.setSelected(false);
        booleanPropertyLoadBullet.setValue(false);
    }

    private void addPropertyBindings() {
        labelPanelName.textProperty().bind(stringPropertyPanelName);
        labelPoints.textProperty().bind(integerPropertyNumberOfPoints.asString());
        labelNumberOfPlayers.textProperty().bind(integerPropertyNumberOfPlayers.asString());
        labelTimeToTheEnd.textProperty().bind(integerPropertyTimeToEndOfRound.asString());
        textAreaCommanderCommand.textProperty().bind(stringPropertyCaptainCommand);
        labelCommander.textProperty().bind(stringPropertyCaptainNickname);

        // Gracz 1:
        fuelCombustionMode_1.bind(radioButtonDevice2Player1_Option1.selectedProperty());
        fuelCombustionMode_2.bind(radioButtonDevice2Player1_Option2.selectedProperty());
        doublePropertyAngleOfAttack.bind(sliderDevice3Player1_Value.valueProperty());

        // Gracz 2:
        labelDevice2Player2_Parameter1_Value.textProperty().bind(integerPropertyNumberOfVentilators.asString());
        doublePropertyCombustionTemperature.bind(sliderDevice3Player2_Value.valueProperty());
        labelDevice2Player2_Parameter3_Value.textProperty().bind(Bindings.format("%.2f", doublePropertyEngineTemperature));

        // Gracz 3:
        typeOfTriggerMechanism_1.bind(radioButtonDevice2Player3_Option1.selectedProperty());
        typeOfTriggerMechanism_2.bind(radioButtonDevice2Player3_Option2.selectedProperty());
        }

    private void addPropertyListeners() {
        stringPropertyPanelName.addListener((o, oldVal, newVal) -> prepareContactModeComponents(newVal));

        booleanPropertyKickFromServer.addListener((o, oldVal, newVal) -> {
            if (!newVal)
                labelGameStatus.setText("Status aktywnej gry: połączono.");
            else
                labelGameStatus.setText("Status aktywnej gry: zostałeś wyrzucony z gry.");
        });

        textFieldDevice2Player2_Parameter2_Value.textProperty().addListener((o, oldVal, newVal) -> {
            try {
                Double result = (double) integerPropertyNumberOfVentilators.get() / Double.valueOf(newVal);
                if (Double.POSITIVE_INFINITY != result)
                    doublePropertyEngineTemperature.setValue(result);
                else
                    doublePropertyEngineTemperature.setValue(0.0);
            } catch (NumberFormatException e) {
                doublePropertyEngineTemperature.setValue(0.0);
            }
        });


    }

    // Metody publiczne:
    public void initPlayerAndCaptainNicknames(String nickname, String captainNickname, String panelName) {
        labelPlayer.setText(nickname);
        stringPropertyCaptainNickname.setValue(captainNickname);
        stringPropertyPanelName.setValue(panelName);
    }

    private void initRadioButtons() {
        ToggleGroup toggleGroupDevice2Player1 = new ToggleGroup();
        radioButtonDevice2Player1_Option1.setToggleGroup(toggleGroupDevice2Player1);
        radioButtonDevice2Player1_Option2.setToggleGroup(toggleGroupDevice2Player1);

        ToggleGroup toggleGroupDevice2Player3 = new ToggleGroup();
        radioButtonDevice2Player3_Option1.setToggleGroup(toggleGroupDevice2Player3);
        radioButtonDevice2Player3_Option2.setToggleGroup(toggleGroupDevice2Player3);
    }    // Gettery i settery (właściwości):

    public String getStringPropertyPanelName() {
        return stringPropertyPanelName.get();
    }

    public StringProperty stringPropertyPanelNameProperty() {
        return stringPropertyPanelName;
    }

    public void setStringPropertyPanelName(String stringPropertyPanelName) {
        this.stringPropertyPanelName.set(stringPropertyPanelName);
    }

    public int getIntegerPropertyNumberOfPoints() {
        return integerPropertyNumberOfPoints.get();
    }

    public IntegerProperty integerPropertyNumberOfPointsProperty() {
        return integerPropertyNumberOfPoints;
    }

    public void setIntegerPropertyNumberOfPoints(int integerPropertyNumberOfPoints) {
        this.integerPropertyNumberOfPoints.set(integerPropertyNumberOfPoints);
    }

    public int getIntegerPropertyNumberOfPlayers() {
        return integerPropertyNumberOfPlayers.get();
    }

    public IntegerProperty integerPropertyNumberOfPlayersProperty() {
        return integerPropertyNumberOfPlayers;
    }

    public void setIntegerPropertyNumberOfPlayers(int integerPropertyNumberOfPlayers) {
        this.integerPropertyNumberOfPlayers.set(integerPropertyNumberOfPlayers);
    }

    public int getIntegerPropertyTimeToEndOfRound() {
        return integerPropertyTimeToEndOfRound.get();
    }

    public IntegerProperty integerPropertyTimeToEndOfRoundProperty() {
        return integerPropertyTimeToEndOfRound;
    }

    public void setIntegerPropertyTimeToEndOfRound(int integerPropertyTimeToEndOfRound) {
        this.integerPropertyTimeToEndOfRound.set(integerPropertyTimeToEndOfRound);
    }

    public String getStringPropertyCaptainCommand() {
        return stringPropertyCaptainCommand.get();
    }

    public StringProperty stringPropertyCaptainCommandProperty() {
        return stringPropertyCaptainCommand;
    }

    public void setStringPropertyCaptainCommand(String stringPropertyCaptainCommand) {
        this.stringPropertyCaptainCommand.set(stringPropertyCaptainCommand);
    }

    public boolean isBooleanPropertyKickFromServer() {
        return booleanPropertyKickFromServer.get();
    }

    public BooleanProperty booleanPropertyKickFromServerProperty() {
        return booleanPropertyKickFromServer;
    }

    public void setBooleanPropertyKickFromServer(boolean booleanPropertyKickFromServer) {
        this.booleanPropertyKickFromServer.set(booleanPropertyKickFromServer);
    }

    public String getStringPropertyCaptainNickname() {
        return stringPropertyCaptainNickname.get();
    }

    public StringProperty stringPropertyCaptainNicknameProperty() {
        return stringPropertyCaptainNickname;
    }

    public void setStringPropertyCaptainNickname(String stringPropertyCaptainNickname) {
        this.stringPropertyCaptainNickname.set(stringPropertyCaptainNickname);
    }

    public double getDoublePropertyEngineTemperature() {
        return doublePropertyEngineTemperature.get();
    }

    public DoubleProperty doublePropertyEngineTemperatureProperty() {
        return doublePropertyEngineTemperature;
    }

    public void setDoublePropertyEngineTemperature(double doublePropertyEngineTemperature) {
        this.doublePropertyEngineTemperature.set(doublePropertyEngineTemperature);
    }

    public double getDoublePropertyCombustionTemperature() {
        return doublePropertyCombustionTemperature.get();
    }

    public DoubleProperty doublePropertyCombustionTemperatureProperty() {
        return doublePropertyCombustionTemperature;
    }

    public void setDoublePropertyCombustionTemperature(double doublePropertyCombustionTemperature) {
        this.doublePropertyCombustionTemperature.set(doublePropertyCombustionTemperature);
    }

    public double getDoublePropertyAngleOfAttack() {
        return doublePropertyAngleOfAttack.get();
    }

    public DoubleProperty doublePropertyAngleOfAttackProperty() {
        return doublePropertyAngleOfAttack;
    }

    public void setDoublePropertyAngleOfAttack(double doublePropertyAngleOfAttack) {
        this.doublePropertyAngleOfAttack.set(doublePropertyAngleOfAttack);
    }

    public int getIntegerPropertyNumberOfVentilators() {
        return integerPropertyNumberOfVentilators.get();
    }

    public IntegerProperty integerPropertyNumberOfVentilatorsProperty() {
        return integerPropertyNumberOfVentilators;
    }

    public void setIntegerPropertyNumberOfVentilators(int integerPropertyNumberOfVentilators) {
        this.integerPropertyNumberOfVentilators.set(integerPropertyNumberOfVentilators);
    }

    public boolean isFuelCombustionMode_1() {
        return fuelCombustionMode_1.get();
    }

    public BooleanProperty fuelCombustionMode_1Property() {
        return fuelCombustionMode_1;
    }

    public void setFuelCombustionMode_1(boolean fuelCombustionMode_1) {
        this.fuelCombustionMode_1.set(fuelCombustionMode_1);
    }

    public boolean isFuelCombustionMode_2() {
        return fuelCombustionMode_2.get();
    }

    public BooleanProperty fuelCombustionMode_2Property() {
        return fuelCombustionMode_2;
    }

    public void setFuelCombustionMode_2(boolean fuelCombustionMode_2) {
        this.fuelCombustionMode_2.set(fuelCombustionMode_2);
    }

    public boolean isTypeOfTriggerMechanism_1() {
        return typeOfTriggerMechanism_1.get();
    }

    public BooleanProperty typeOfTriggerMechanism_1Property() {
        return typeOfTriggerMechanism_1;
    }

    public void setTypeOfTriggerMechanism_1(boolean typeOfTriggerMechanism_1) {
        this.typeOfTriggerMechanism_1.set(typeOfTriggerMechanism_1);
    }

    public boolean isTypeOfTriggerMechanism_2() {
        return typeOfTriggerMechanism_2.get();
    }

    public BooleanProperty typeOfTriggerMechanism_2Property() {
        return typeOfTriggerMechanism_2;
    }

    public void setTypeOfTriggerMechanism_2(boolean typeOfTriggerMechanism_2) {
        this.typeOfTriggerMechanism_2.set(typeOfTriggerMechanism_2);
    }

    public boolean isBooleanPropertyLoadBullet() {
        return booleanPropertyLoadBullet.get();
    }

    public BooleanProperty booleanPropertyLoadBulletProperty() {
        return booleanPropertyLoadBullet;
    }

    public void setBooleanPropertyLoadBullet(boolean booleanPropertyLoadBullet) {
        this.booleanPropertyLoadBullet.set(booleanPropertyLoadBullet);
    }


    }

