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
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
    @FXML
    private JFXButton closeBtn;
    @FXML
    private Pane miniPane;
    @FXML
    private Label usrplayer1;
    @FXML
    private Label usrplayer2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameState = new GameState();
        gm = new GameManager(gameState);
        createAllCells();
    }

    private void createAllCells() {
        int btnWidth = 65;
        int btnHeight = 65;

        for (int x = 0; x < 3 + 3 + 3; x++) {
            for (int y = 0; y < 3 + 3 + 3; y++) {
                UTTTButton btn = new UTTTButton();
                btn.setPrefSize(btnWidth, btnHeight);
                btn.setMove(new Move(x, y));

                int verticalSpaceBetween = 10 * (x / 3);
                int horizontalSpaceBetween = 10 * (y / 3);
                btn.setLayoutX(6 + (btnWidth + 2) * x + verticalSpaceBetween);
                btn.setLayoutY(6 + (btnHeight + 2) * y + horizontalSpaceBetween);

                btn.setOnMouseClicked(event -> {
                    UTTTButton b = (UTTTButton) event.getSource();
                    boolean isSucces = gm.updateGame(b.getMove());
                    String imageSource;
                    if (isSucces) {
                        if (gameState.getMoveNumber() % 2 == 0) {
                            imageSource = "/icons/player1icon.png";
                        } else {
                            imageSource = "/icons/player2icon.png";
                        }
                        b.setGraphic(new ImageView(new Image(imageSource)));
                    }
                });

                MainPane.getChildren().add(btn);

            }

        }

    }

    @FXML
    private void clickClose(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();

    }

    void setUpUsernames(String firstName, String secondName) {
        if (!firstName.equals("")) {
            usrplayer1.setText(firstName);
        }
        if (!secondName.equals("")) {
            usrplayer2.setText(secondName);
        }

    }

}
