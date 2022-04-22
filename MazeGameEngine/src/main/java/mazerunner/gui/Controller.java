package mazerunner.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import mazerunner.engine.GameEngine;
import javafx.scene.control.TextField;
import javafx.scene.image.Image ;
import javafx.scene.control.Label;

public class Controller  {
    GameEngine map;
    @FXML
    GridPane pane;
    @FXML
    TextField txt1;
    @FXML
    Label actionresult;
    @FXML
    Label stamina;
    @FXML
    Label gold;

    /**
     * Start Game Button
     * @param event
     */
    @FXML
    void StartGameButton(ActionEvent event) {
        System.out.println("Starting Game...");
        String difficultyEntryText = txt1.getText();

        map = new GameEngine(10, Integer.parseInt(difficultyEntryText));

        UpdateBoard();

    }

    /**
     * Updates GUI Board
     */
    void UpdateBoard(){
        Image appleImg = new Image("mazerunner/gui/grn.jpg");
        Image coinImg = new Image("mazerunner/gui/yel.jpg");
        Image trapImg = new Image("mazerunner/gui/red.jpg");
        Image playerImg = new Image("mazerunner/gui/blk.jpg");
        Image blankImg = new Image("mazerunner/gui/white.jpg");
        //Image escapeImg = new Image("mazerunner/gui/blk.jpg");
        for (int i = 0; i < map.map.length; i++) {
            for (int j = 0; j < map.map.length; j++) {

                //Apple
                if (map.map[i][j] == 1) {
                    ImageView imageView = new ImageView(appleImg);
                    imageView.setFitHeight(50);
                    imageView.setY(-50);
                    imageView.setFitWidth(80);
                    pane.setConstraints(imageView, j, i);
                    pane.getChildren().add(imageView);

                }
                //Coins
                else if (map.map[i][j] == 2) {
                    ImageView imageView = new ImageView(coinImg);
                    imageView.setFitHeight(50);
                    imageView.setY(-50);
                    imageView.setFitWidth(80);
                    pane.setConstraints(imageView, j, i);
                    pane.getChildren().add(imageView);

                }

                //Traps
                else if (map.map[i][j] == 3) {
                    ImageView imageView = new ImageView(trapImg);
                    imageView.setFitHeight(50);
                    imageView.setY(-50);
                    imageView.setFitWidth(80);
                    pane.setConstraints(imageView, j, i);
                    pane.getChildren().add(imageView);
                }
                //Player
                else if (map.map[i][j] == 8) {
                    ImageView imageView = new ImageView(playerImg);
                    imageView.setFitHeight(50);
                    imageView.setY(-50);
                    imageView.setFitWidth(80);
                    pane.setConstraints(imageView, j, i);
                    pane.getChildren().add(imageView);
                } else if (map.map[i][j] == 9) {
                    ImageView imageView = new ImageView(playerImg);
                    imageView.setFitHeight(50);
                    imageView.setY(-50);
                    imageView.setFitWidth(80);
                    pane.setConstraints(imageView, j, i);
                    pane.getChildren().add(imageView);
                }
                //empty cell
                else {
                    ImageView imageView = new ImageView(blankImg);
                    imageView.setFitHeight(50);
                    imageView.setY(-50);
                    imageView.setFitWidth(80);
                    pane.setConstraints(imageView, j, i);
                    pane.getChildren().add(imageView);
                }
            }
            actionresult.setText("Action Result: " + map.actionResult);
            stamina.setText("Stamina: " + map.stats.getStamina());
            gold.setText("Gold Coins: " + map.stats.getCoinCount());
        }
    }

    /**
     * move up and update
     */
    @FXML
    void Up(){
        map.setDirectionValue(8);
        map.MovePosition();
        UpdateBoard();
    }

    /**
     * move down and update
     */
    @FXML
    void Down(){
        map.setDirectionValue(2);
        map.MovePosition();
        UpdateBoard();
    }

    /**
     * move left and update
     */
    @FXML
    void Left(){
        map.setDirectionValue(4);
        map.MovePosition();
        UpdateBoard();
    }

    /** move right and update
     *
     */
    @FXML
    void Right(){
        map.setDirectionValue(6);
        map.MovePosition();
        UpdateBoard();
    }



}
