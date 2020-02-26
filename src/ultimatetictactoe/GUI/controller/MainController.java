/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.GUI.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ultimatetictactoe.GUI.UTTTButton;
import ultimatetictactoe.game.GameManager;
import ultimatetictactoe.game.GameState;
import ultimatetictactoe.game.IGameState;
import ultimatetictactoe.move.Move;

/**
 * FXML Controller class
 *
 * @author mega_
 */
public class MainController implements Initializable {


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
        int btnWidth = 65;
        int btnHeight = 65;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                
        UTTTButton btn = new UTTTButton();
        btn.setPrefSize(btnWidth, btnHeight);
        btn.setLayoutX(6+(btnWidth+3)*x);
        btn.setLayoutY(6+(btnHeight+3)*y);
        btn.setMove(new Move(x,y));
        
        btn.setOnMouseClicked(event-> {
            UTTTButton b =  (UTTTButton)event.getSource();
            boolean isSucces = gm.updateGame(b.getMove());
            String imageSource;
            if(isSucces){
                if(gameState.getMoveNumber()%2==0) {
                    imageSource = "/icons/player1icon.png";
                }
                else{
                    imageSource = "/icons/player2icon.png";
                }
                b.setGraphic(new ImageView(new Image(imageSource)));
            }
        });
                
        MainPane.getChildren().add(btn);
                
            }
 
        }   
       
    }
    
}    
    

