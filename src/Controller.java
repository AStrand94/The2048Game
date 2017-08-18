import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by andreasstrand on 29.04.2017.
 */
public class Controller implements Initializable {
    @FXML
    Canvas canvas;
    @FXML
    Text scoreText;
    final String score = "Score : ";
    AnimationTimer at;

    GraphicsContext gc;
    Game game;
    PaintEngine paintEngine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        game = new Game();
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GRAY);


        System.out.println("HEI");
        System.out.println(game.toString());
        paintEngine = new PaintEngine(canvas.getHeight(),canvas.getWidth(),game.getBoard().length,gc);

        initListeners();
        paintBoard();
    }



    private void initListeners(){
        canvas.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) newGame();
            game.move(e.getCode());
            paintBoard();

        });
    }

    private void paintBoard(){
        paintEngine.paintCells(game.getBoard());
        paintEngine.paintGrid();
        setScore();
    }

    private void newGame(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("New Game");
        alert.setHeaderText("Are you sure you want to start a new game?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK)
            game = new Game();
    }

    private void setScore(){
        scoreText.setText(score + game.getScore());
    }
/*
    private void startRandomMoves(){
        at = new AnimationTimer() {
            @Override
            public void handle(long now) {

                KeyEvent ke = new KeyEvent(KeyEvent.KEY_PRESSED,
                        KeyCode.RIGHT.toString(), KeyCode.RIGHT.toString(),
                        KeyCode.RIGHT, false, false, false, false);
                canvas.fireEvent(ke);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        at.start();
    }*/

}
