import com.sun.javaws.jnl.JavaFXAppDesc;
import javafx.scene.input.KeyCode;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by andreasstrand on 29.04.2017.
 */
public class Game {

    int[][] board = new int[4][4];
    int[][] prevBoard;
    boolean changed = false;

    int score = 0;

    public Game(){
        initGame();
        System.out.println(toString());
    }

    /*
     * Sets two start values
     */
    private void initGame(){
        int n = 2;

        while (n > 0){
            int x = (int)(Math.random()*4);
            int y = (int)(Math.random()*4);
            if (board[x][y] != 2) {
                board[x][y] = 2;
                n--;
            }
        }
    }

    public int[][] getBoard(){
        return board;
    }

    private void setCopy(){
        prevBoard = new int[4][];

        for (int i = 0; i < board.length; i++)
            prevBoard[i] = Arrays.copyOf(board[i],board.length);
    }

    /**
     *
     * @return true if the array has changed, false if not
     */
    private boolean hasChanged(){
        return !Arrays.deepEquals(prevBoard,board);
    }

    public void move(KeyCode direction){
        setCopy();
        switch (direction){
            case DOWN:
                moveDown();
                break;
            case RIGHT:
                moveRight();
                break;
            case UP:
                moveUp();
                break;
            case LEFT:
                moveLeft();
                break;
            default:
                return;
        }

        if (hasChanged()) addNewCell();
    }

    private void addNewCell(){
        if (isFull()){
            //do something
            System.out.println("THE BOARD HAS NO FREE SPOTS");
        }else{
            boolean cellAdded = false;
            while (!cellAdded){
                int x = (int)(Math.random()*board.length);
                int y = (int)(Math.random()*board.length);

                if (board[x][y] != 0) continue;

                cellAdded = true;
                board[x][y] = getNewValue();
            }

        }


    }

    private int getNewValue(){
        int n = (int)(Math.random()*5);

        if (n%4 == 0) return 4;
        else return 2;
    }

    /**
     * Checks if the board is full
     * @return true if the board is full
     */
    public boolean isFull(){
        int n = 0;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == 0) n++;
            }
        }
        return n == 0;

    }

    private void moveLeft(){
        for (int x = 0; x < board.length; x++){
            pushLeft(x);
            for (int y = 0; y < board.length-1; y++){
                if (board[x][y] == 0){
                    pushLeft(x);
                }else if (board[x][y] == board[x][y+1]){
                    board[x][y] += board[x][y+1];
                    board[x][y+1] = 0;
                    score += board[x][y];
                    pushLeft(x);
                }
            }
        }
    }


    private void moveRight(){
        for (int x = board.length-1; x >= 0; x--){
            pushRight(x);
            for (int y = board.length-1; y > 0; y--){
                if (board[x][y] == 0){
                    pushRight(x);
                }else if (board[x][y] == board[x][y-1]){
                    board[x][y] += board[x][y-1];
                    board[x][y-1] = 0;
                    score += board[x][y];
                    pushRight(x);
                }
            }
        }

    }

    private void moveDown(){
        for (int y = 0; y < board.length; y++){
            pushDown(y);
            for (int x = board.length-1; x > 0; x--){
                if (board[x][y] == 0){
                    pushDown(y);
                } else if (board[x-1][y] == board[x][y]) {
                    board[x][y] += board[x-1][y];
                    board[x-1][y] = 0;
                    score += board[x][y];
                    pushDown(y);
                }
            }
        }

    }


    private void moveUp(){
        for (int y = 0; y < board.length; y++){
            pushUp(y);
            for (int x = 0; x < board.length-1; x++){
                if (board[x][y] == 0){
                    pushUp(y);
                }else if (board[x+1][y] == board[x][y]){
                    board[x][y] += board[x+1][y];
                    board[x+1][y] = 0;
                    score += board[x][y];
                    pushUp(y);
                }
            }
        }
    }

    private void pushRight(int x){
        for (int y = board.length-2; y >= 0; y--){
            if (board[x][y] != 0 && board[x][y+1] == 0){
                int i = y;
                while (i < board.length-1 && board[x][i+1] == 0){
                    moveTo(x,i,x,i+1);
                    i++;
                }
            }
        }
    }

    private void pushLeft(int x){
        for (int y = 1; y < board.length; y++){
            if (board[x][y] != 0 && board[x][y-1] == 0){
                int i = y;
                while (i > 0 && board[x][i-1] == 0){
                    moveTo(x,i,x,i-1);
                    i--;
                }
            }
        }
    }

    private void pushDown(int y){
        for (int x = board.length-2; x >= 0; x--){
            if (board[x][y] != 0 && board[x+1][y] == 0){
                int i = x;
                while (i < board.length-1 && board[i+1][y] == 0){
                    moveTo(i,y,i+1,y);
                    i++;
                }
            }
        }
    }

    private void pushUp(int y){
        for (int x = 1; x < board.length; x++){
            if (board[x][y] != 0 && board[x-1][y] == 0) {
                int i = x;
                while (i > 0 && board[i-1][y] == 0){
                    moveTo(i,y,i-1,y);
                    i--;
                }
            }
        }
    }

    public int getScore(){
        return score;
    }

    public void setBoard(int[][] board){
        this.board = board;
    }

    /*
    Sets (x1,y1) = (x2,y2)
     */
    private void moveTo(int x1, int y1, int x2, int y2){
        board[x2][y2] = board[x1][y1];
        board[x1][y1] = 0;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < board.length; i++){
            sb.append(Arrays.toString(board[i])).append('\n');
        }
        return sb.toString();
    }
}
