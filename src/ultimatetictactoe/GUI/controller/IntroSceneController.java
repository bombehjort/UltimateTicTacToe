/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.GUI.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Martin Park Broderse
 */
public class IntroSceneController implements Initializable {

    @FXML
    private JFXButton HumanVSHumanButton;
    @FXML
    private JFXButton HumanVSBotButton;
    @FXML
    private JFXButton BotVSBotButton;
    @FXML
    private Pane HvHPane;
    @FXML
    private JFXButton confirmbtn;
    @FXML
    private Pane HvBPane;
    @FXML
    private JFXButton confirmbtnHvB;
    @FXML
    private Pane BvBPane;
    @FXML
    private JFXButton confirmbtnbvb;
    @FXML
    private JFXTextField username1;
    @FXML
    private JFXTextField username2;
    @FXML
    private JFXTextField usernameHvB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void clickHumanVSHuman(ActionEvent event) {

        HvHPane.setVisible(true);
        HvBPane.setVisible(false);
        BvBPane.setVisible(false);

    }

    @FXML
    private void clickHvB(ActionEvent event) {

        HvBPane.setVisible(true);
        HvHPane.setVisible(false);
        BvBPane.setVisible(false);
    }

    @FXML
    private void clickBvB(ActionEvent event) {
        BvBPane.setVisible(true);
        HvHPane.setVisible(false);
        HvBPane.setVisible(false);
    }

    @FXML
    private void clickCloseIntro(ActionEvent event) {

        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickConfirm(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ultimatetictactoe/GUI/view/MainView.fxml"));
        Parent root = loader.load();
        MainController mctrl = loader.getController();

        mctrl.setUpUsernames(username1.getText(), username2.getText());

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void clickConfirmHvB(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ultimatetictactoe/GUI/view/MainView.fxml"));
        Parent root = loader.load();
        MainController mctrl = loader.getController();

        mctrl.setUpUsernames(usernameHvB.getText(), null);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void clickConfirmBvB(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ultimatetictactoe/GUI/view/MainView.fxml"));
        Parent root = loader.load();
        MainController mctrl = loader.getController();
mctrl.setUpUsernames(null, null);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
