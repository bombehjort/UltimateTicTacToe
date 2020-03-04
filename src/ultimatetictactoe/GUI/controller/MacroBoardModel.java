/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.GUI.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import ultimatetictactoe.game.GameManager;
import ultimatetictactoe.game.GameState;

/**
 *
 * @author mac
 */
public class MacroBoardModel {
    private final List<InvalidationListener> listeners = new ArrayList<>();
    private final GameManager game;
    
    
    public MacroBoardModel(){
    
        game= new GameManager(new GameState());
    
    }
 
    

    String[][] getMacroboard() {
        return game.getCurrentState().getField().getMacroboard();
    }
    
}
