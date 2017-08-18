import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by andreasstrand on 29.04.2017.
 */
public class PaintEngine {

    double rectSize;
    int size;
    GraphicsContext gc;

    public PaintEngine(double height, double width, int boardSize,GraphicsContext gc){
        size = boardSize;
        this.gc = gc;
        setRectSize(height, width, boardSize);
    }

    private void setRectSize(double height, double width, int boardSize){

        double usableSize = Math.min(height,width);
        rectSize = usableSize/boardSize;
    }

    public void paintGrid(){
        for (int i = 0; i <= size; i++){
            gc.strokeLine(i*rectSize, 0, i*rectSize,size*rectSize);
            gc.strokeLine(0,i*rectSize,size*rectSize,i*rectSize);
        }
    }

    public void paintCells(int[][] board){
        int i = 1;
        for (int y = 0; y < size; y++){
            for (int x = 0; x < size; x++){
                gc.setFill(getColor(board[y][x]));
                gc.fillRect(x*rectSize,y*rectSize,rectSize,rectSize);
                gc.setFill(Color.BLACK);
                gc.fillText(Integer.toString(board[y][x]),(x+1)*rectSize - rectSize/2,(y+1)*rectSize - rectSize/2);
            }
        }
    }

    public void clearBoard(){
        gc.clearRect(0,0,size*rectSize,size*rectSize);
    }

    private Color getColor(int value){
        switch (value){
            case 0:
                return Color.WHITE;
            case 2:
                return Color.LIGHTGRAY;
            case 4:
                return Color.LIGHTGREEN;
            case 8:
                return Color.GREEN;
            case 16:
                return Color.ORANGERED;
            case 32:
                return Color.RED;
            case 64:
                return Color.DARKRED;
            case 128:
                return Color.LIGHTYELLOW;
            case 256:
                return Color.LIGHTYELLOW;
            case 512:
                return Color.BLUE;
            default:
                return Color.BEIGE;
        }
    }
}
