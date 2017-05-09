import javafx.scene.input.KeyCode;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by strand117 on 09.05.2017.
 */
public class GameTest {
    @Test
    public void move() throws Exception {
        Game game = new Game();

        int[][] board = {
                {2,0,0,0},
                {2,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };

        game.setBoard(board);

        game.move(KeyCode.UP);

        System.out.println("HER:\n" + game.toString());

        assertEquals("[4, 0, 0, 0]\n[0, 0, 0, 0]\n[0, 0, 0, 0]\n[0, 0, 0, 0]",game.toString());
    }

}