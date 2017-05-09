import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by andreasstrand on 29.04.2017.
 */
public class Controller implements Initializable {
    @FXML
    Canvas canvas;

    GraphicsContext gc;
    Game game;
    PaintEngine paintEngine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GRAY);

        game = new Game();
        paintEngine = new PaintEngine(canvas.getHeight(),canvas.getWidth(),game.getBoard().length,gc);

        initListeners();
        paintBoard();
    }



    private void initListeners(){
        canvas.setOnKeyPressed(e -> {
            game.move(e.getCode());
            paintBoard();
            /*if (e.getCode() == KeyCode.UP){
                game.move(MoveCode.UP);
            }else if (e.getCode() == KeyCode.RIGHT){
                game.move(MoveCode.RIGHT);
            }else if (e.getCode() == KeyCode.LEFT){
                game.move(MoveCode.LEFT);
            }else if (e.getCode() == KeyCode.DOWN){
                game.move(MoveCode.DOWN);
            }*/
        });
    }

    private void paintBoard(){
        paintEngine.paintCells(game.getBoard());
        paintEngine.paintGrid();
    }

}
