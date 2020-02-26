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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ultimatetictactoe.GUI.UTTTButton;
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
    @FXML
    private AnchorPane MainPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameState = new GameState();
        gm = new GameManager(gameState);
        createAllCells();
      }
    private void createAllCells(){
        int btnWidth = 30;
        int btnHeight = 30;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                
        UTTTButton btn = new UTTTButton();
        btn.setPrefSize(btnWidth, btnHeight);
        btn.setLayoutX(27+(btnWidth+4)*x);
        btn.setLayoutY(145+btnHeight*y);
        btn.setMove(new Move(x,y));
        btn.setOnMouseClicked(event->{
      UTTTButton b =  (UTTTButton)event.getSource();
      boolean isSucces = gm.updateGame(b.getMove());
      if(isSucces){
        if(gameState.getMoveNumber()%
                2==0)
        b.setText("X");
        else 
        b.setText("O");
           }
      
        });
        MainPane.getChildren().add(btn);
                
            }
 
        }   
       
    }

    @FXML
    private void clickCell(ActionEvent event) {
      
      }
       
     
    }
  