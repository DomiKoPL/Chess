package Chess;

import javafx.beans.value.ObservableValue;
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

    private int tileSize = 128;
    private double tileScale = 0.5d;

    public void start(Stage stage){
        Board.getInstance().makeNewBoard(stage);
        stage.show();
    }

    private boolean pieceSelected;
    private boolean whiteMove = true;

    public int getTileSize(){
        return tileSize;
    }

    public double getTileScale(){
        return tileScale;
    }

    public void setTileScale(double tileScale){
        this.tileScale = tileScale;
    }

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
