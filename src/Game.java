import javafx.scene.input.KeyCode;
import java.util.Arrays;

/**
 * Created by andreasstrand on 29.04.2017.
 */
public class Game {

    int[][] board = new int[4][4];

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

    public void move(KeyCode direction){
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

        addNewCell();
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
    private boolean isFull(){
        int n = 0;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == 0) n++;
            }
        }
        return n == 0;

    }

    private void moveUp(){
        for (int i = board.length-2; i >= 0; i--){
            for (int j = board[0].length - 1; j >= 0; j--) {
                if (board[j][i] == 0){
                    board[j][i] = board[j][i+1];
                    board[j][i+1] = 0;
                }else if (board[j][i+1] != 0 && board[j][i+1] == board[j][i]) {
                    board[j][i] += board[j][i + 1];
                    board[j][i + 1] = 0;
                    j--;
                }
            }
        }
        System.out.println(toString());
    }

    private void moveDown(){
        for (int i = 1; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++) {
                if (board[j][i] == 0){
                    board[j][i] = board[j][i-1];
                    board[j][i-1] = 0;
                }else if (board[j][i-1] != 0 && board[j][i-1] == board[j][i]){
                    board[j][i] += board[j][i - 1];
                    board[j][i - 1] = 0;
                    j++;
                }
            }
        }
        System.out.println(toString());
    }

    private void moveRight(){
        for (int i = 0; i < board.length; i++){
            for (int j = 1; j < board.length; j++){
                if (board[j][i] == 0){
                    board[j][i] = board[j-1][i];
                    board[j-1][i] = 0;
                }else if (board[j-1][i] != 0 && board[j-1][i] == board[j][i]){
                    board[j][i] += board[j-1][i];
                    board[j-1][i] = 0;
                    //j++; //bør kanskje ikke kommenteres ut??
                }
            }
        }
        System.out.println(toString());

    }

    private void moveLeft(){
        for (int i = board.length-1; i >= 0; i--){
            for (int j = board.length-2; j >= 0; j--){
                if (board[j][i] == 0){
                    board[j][i] = board[j+1][i];
                    board[j+1][i] = 0;
                }else if (board[j+1][i] != 0 && board[j+1][i] == board[j][i]){
                    board[j][i] += board[j+1][i];
                    board[j+1][i] = 0;
                    j++;
                }
            }
        }
        System.out.println(toString());
    }

    private void moveCellDown(int x, int y){
        
    }

    private void moveCellRight(int x, int y){

    }

    private void moveCellUp(int x, int y){

    }

    private void moveCellLeft(int x, int y){

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
