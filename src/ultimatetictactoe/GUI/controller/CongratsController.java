/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.GUI.controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class CongratsController implements Initializable {

    @FXML
    private Label winnerlabel;
    @FXML
    private JFXButton newgamebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickNewGame(ActionEvent event) throws IOException {
            
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ultimatetictactoe/GUI/view/introScene.fxml"));
            Parent root = loader.load();
            IntroSceneController ictrl = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
        
    }
    
    @FXML
    private void clickclosenewbtn(MouseEvent event) {
         Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickPlayAgain(ActionEvent event) {
        
    }

    @FXML
    private void clickCloseWinner(ActionEvent event) {
        
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    
}
