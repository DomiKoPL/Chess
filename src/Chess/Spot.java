package Chess;

import Chess.Pieces.Piece;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Spot {
    private Piece piece;
    private ImageView boardImage;
    private ImageView canMove;
    private ImageView canBeat;

    public Spot(ImageView boardImage, ImageView canMove , ImageView canBeat){
        piece = null;
        this.boardImage = boardImage;
        this.canMove = canMove;
        this.canBeat = canBeat;

        canBeat.setOpacity(0.2);
        canBeat.setVisible(false);
        canMove.setOpacity(0.5);
        canMove.setVisible(false);
    }

    public void clearSpot(){
        piece = null;
    }

    public Piece getPiece(){
        return piece;
    }

    public void setCanMove(boolean visible){
        canMove.setVisible(visible);
    }

    public boolean canMove() {
        return canMove.isVisible();
    }

    public boolean canBeat() {
        return canBeat.isVisible();
    }

    public void setCanBeat(boolean visible){
        canBeat.setVisible(visible);
    }

    public void occupySpot(Piece piece){
        if(isOccupied()){
            this.piece.delete();
        }
        this.piece = piece;
    }

    public boolean isOccupied(){
        if(piece == null){
            return false;
        }
        return true;
    }

}
