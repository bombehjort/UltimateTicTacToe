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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import ultimatetictactoe.GUI.UTTTButton;
import ultimatetictactoe.field.IField;
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
    private AnchorPane MainPane;
    @FXML
    private JFXButton closeBtn;
    @FXML
    private Pane miniPane;
    @FXML
    private Label usrplayer1;
    @FXML
    private Label usrplayer2;
    @FXML
    private GridPane gridMacro;
    @FXML
    private ImageView imageviewLeft;
    @FXML
    private ImageView imageviewRight;

    private final GridPane[][] gridMicros = new GridPane[3][3];
    private final Button[][] buttons = new Button[9][9];
    private final String imagePathBot = "/icons/robotsweet .png";
    private final String imagePathBot2 = "/icons/robotcute.png";

    private final String imagePathPlayer = "/icons/ninja.png";
    private final String imagePathPlayer2 = "/icons/blueman.png";

    
    MacroBoardModel model;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameState = new GameState();
        gm = new GameManager(gameState);
        //createAllCells();
        gridMacro.toFront();
        createMicroGridPanes();

    }
    int btnWidth = 64;
    int btnHeight = 64;

    /*
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

    } */
    private void createMicroGridPanes() {
        for (int i = 0; i < 3; i++) {
            gridMacro.addRow(i);
            for (int k = 0; k < 3; k++) {
                GridPane gp = new GridPane();
                for (int m = 0; m < 3; m++) {
                    gp.addColumn(m);
                    gp.addRow(m);
                }
                gridMicros[i][k] = gp;
                for (int j = 0; j < 3; j++) {
                    ColumnConstraints cc = new ColumnConstraints();
                    cc.setPercentWidth(33);
                    cc.setHgrow(Priority.ALWAYS); // allow column to grow
                    cc.setFillWidth(true); // ask nodes to fill space for column
                    gp.getColumnConstraints().add(cc);

                    RowConstraints rc = new RowConstraints();
                    rc.setVgrow(Priority.ALWAYS); // allow row to grow always
                    rc.setFillHeight(true);
                    rc.setPercentHeight(33);
                    gp.getRowConstraints().add(rc);
                }

                gp.setGridLinesVisible(true);
                gridMacro.addColumn(k);
                gridMacro.add(gp, i, k);
            }
        }
        insertButtonsIntoGridPanes();
    }

    private void insertButtonsIntoGridPanes() {

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                GridPane gp = gridMicros[i][k];
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        UTTTButton btn = new UTTTButton();
                        btn.setPrefSize(btnWidth, btnHeight);
                        btn.setMove(new Move(x, y));

                        int verticalSpaceBetween = 10 * (x / 3);
                        int horizontalSpaceBetween = 10 * (y / 3);
                        btn.setLayoutX(6 + (btnWidth + 1) * x + verticalSpaceBetween);
                        btn.setLayoutY(6 + (btnHeight + 1) * y + horizontalSpaceBetween);

                        btn.setUserData(new Move(x + i * 3, y + k * 3));
                        btn.setFocusTraversable(false);
                        btn.setOnMouseClicked(
                                event -> {

                                    btn.getUserData();
                                    UTTTButton b = (UTTTButton) event.getSource();
                                    boolean isSucces = gm.updateGame(b.getMove());
                                    String imageSource;
                                    if (isSucces) {
                                        if (gameState.getMoveNumber() % 2 == 0) {
                                            imageSource = "/icons/ninja.png";
                                        } else {
                                            imageSource = "/icons/blueman.png";
                                        }
                                        b.setGraphic(new ImageView(new Image(imageSource)));
                                    }
                                });
                        gp.add(btn, x, y);
                        buttons[x + i * 3][y + k * 3] = btn;

                    }
                }
            }
        }

    }

    @FXML
    private void clickClose(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();

    }

    public void setUpUsernames(String firstName, String secondName) {
        Image botImg = new Image(this.getClass().getResource(imagePathBot).toExternalForm());
        Image botImg2 = new Image(this.getClass().getResource(imagePathBot2).toExternalForm());
        Image usrImg2 = new Image(this.getClass().getResource(imagePathPlayer2).toExternalForm());

        Image usrImg = new Image(this.getClass().getResource(imagePathPlayer).toExternalForm());
        imageviewLeft.setImage(botImg2);
        imageviewRight.setImage(botImg);

        if (firstName != null) {
            usrplayer1.setText(firstName);

            if (firstName.trim().equals("")) {
                usrplayer1.setText("Player 1");
            }

            imageviewLeft.setImage(usrImg);
        }
        if (secondName != null) {
            usrplayer2.setText(secondName);

            if (secondName.trim().equals("")) {
                usrplayer2.setText("Player 2");
            }


            imageviewRight.setImage(usrImg);

            imageviewRight.setImage(usrImg2);

        }
        

    }

    //Highlight Gridpane, but first we need to make the game work again
    public void updateGUI() {

        String[][] macroBoard = model.getMacroboard();
        for (int i = 0; i < macroBoard.length; i++) {
            for (int k = 0; k < macroBoard[i].length; k++) {
                if (gridMicros[i][k] != null) {
                    // Highlight available plays
                    if (macroBoard[i][k].equals(IField.AVAILABLE_FIELD)) {
                        gridMicros[i][k].getStyleClass().add("highlight");
                    } else {
                        gridMicros[i][k].getStyleClass().removeAll("highlight");
                    }
                }

            }
        }
    }
}
