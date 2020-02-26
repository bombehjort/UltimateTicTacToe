/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.GUI.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ultimatetictactoe.game.GameManager;
import ultimatetictactoe.game.GameState;
import ultimatetictactoe.game.IGameState;
import ultimatetictactoe.move.Move;

/**
 * FXML Controller class
 *
 * @author Martin Park Broderse
 */
public class MainExampleController implements Initializable {
    private GameManager gm;
    IGameState gameState;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameState = new GameState();
        gm = new GameManager(gameState);
    }    

    @FXML
    private void clickCell(ActionEvent event) {
      JFXButton b =  (JFXButton)event.getSource();
      boolean isSucces = gm.updateGame(new Move(0, 0));
      if(isSucces){
        if(gameState.getMoveNumber()%2==0)
        b.setText("X");
        else 
        b.setText("O");
      }
      
     
    }
  }
