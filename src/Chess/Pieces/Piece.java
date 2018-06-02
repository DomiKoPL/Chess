package Chess.Pieces;

import Chess.Board;

import Chess.Game;
import Chess.Spot;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.lang.reflect.GenericArrayType;
import java.util.Comparator;

public class Piece {
    private byte x;
    private byte y;
    private boolean moved;
    private ImageView imageView;
    private boolean white;
    private boolean selected;

    public Piece(byte x, byte y, ImageView imageView, boolean white){
        this.x = x;
        this.y = y;
        this.imageView = imageView;
        this.white = white;
        selected = false;
        moved = false;
        setReactions();
    }

    protected void setReactions(){

        imageView.setOnMouseDragged(e -> {
            if(Game.getInstance().isWhiteMove() != this.isWhite()){
                return;
            }

            if(!Game.getInstance().isPieceSelected()){
                Game.getInstance().setPieceSelected(true);
                selected = true;
                updateAvailableMoves(true);
            }

            if(selected){
                Point p = MouseInfo.getPointerInfo().getLocation();
                int X = p.x;
                X-= imageView.getScene().getWindow().getX();
                X-= imageView.getScene().getX();

                int Y = p.y;
                Y-= imageView.getScene().getWindow().getY();
                Y-= imageView.getScene().getY();

                int width = (int)(Game.getInstance().getTileSize() * Game.getInstance().getTileScale());
                int newX = X - X % width, newY = Y - Y % width;
                imageView.setX(newX);
                imageView.setY(newY);
            }
        });

        imageView.setOnMouseReleased(e ->{
            if(selected) {
                Game.getInstance().setPieceSelected(false);
                selected = false;

                int width = (int)(Game.getInstance().getTileSize() * Game.getInstance().getTileScale());
                byte newX = (byte) (imageView.getX() / width), newY = (byte) (imageView.getY() / width);

                if(newX < 0 || newX >= 8 || newY < 0 || newY >= 8){
                    updateAvailableMoves(false);
                    imageView.setX(this.x * width);
                    imageView.setY(this.y * width);
                    imageView.setTranslateX(-Game.getInstance().getTileSize()  * (1 - Game.getInstance().getTileScale() ) / 2);
                    imageView.setTranslateY(-Game.getInstance().getTileSize()  * (1 - Game.getInstance().getTileScale() ) / 2);
                    return;
                }

                boolean canMove = Board.getInstance().getSpot(newX,newY).canMove();
                boolean canBeat = Board.getInstance().getSpot(newX,newY).canBeat();

                updateAvailableMoves(false);
                if (canMove || canBeat) {
                    Game.getInstance().setWhiteMove(!this.isWhite());
                    move(newX, newY);
                } else {
                    imageView.setX(this.x * width);
                    imageView.setY(this.y * width);
                    imageView.setTranslateX(-Game.getInstance().getTileSize()  * (1 - Game.getInstance().getTileScale() ) / 2);
                    imageView.setTranslateY(-Game.getInstance().getTileSize()  * (1 - Game.getInstance().getTileScale() ) / 2);
                }

            }
        });
    }

    protected void updateAvailableMoves(boolean show){
    }

    public boolean moved(){
        return moved;
    }

    public void move(byte newX, byte newY){
        moved = true;
        Board.getInstance().movePiece(this.x,this.y,newX,newY);
        setX(newX);
        setY(newY);
    }

    public void setX(byte x) {
        this.x = x;
    }

    public void setY(byte y) {
        this.y = y;
    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }

    public boolean isWhite() {
        return white;
    }

    public void delete(){
        imageView.setVisible(false);
    }
}
