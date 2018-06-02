package Chess;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.InputStream;

public class Game {
    private static Game INSTANCE;

    private Game(){

    }

    public static Game getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Game();
        }
        return INSTANCE;
    }

    public void start(Stage stage){
        stage.setScene(new Scene(Board.getInstance().makeNewBoard(),128 * 8 - 10, 128 * 8));
        stage.setResizable(false);
        stage.show();
    }

    private boolean pieceSelected;
    private boolean whiteMove = true;

    public void setPieceSelected(boolean pieceSelected) {
        this.pieceSelected = pieceSelected;
    }

    public boolean isPieceSelected() {
        return pieceSelected;
    }

    public boolean isWhiteMove(){
        return whiteMove;
    }

    public void setWhiteMove(boolean whiteMove){
        this.whiteMove = whiteMove;
    }
}