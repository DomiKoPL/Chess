package Chess.Pieces;

import Chess.Board;

import javafx.scene.image.ImageView;

public class Queen extends Piece{
    public Queen(byte x, byte y, ImageView imageView,boolean white){
        super(x,y,imageView,white);
    }

    @Override
    protected void updateAvailableMoves(boolean show) {
        updateAvailableMoves(show,1,1);
        updateAvailableMoves(show,1,-1);
        updateAvailableMoves(show,-1,-1);
        updateAvailableMoves(show,-1,1);
        updateAvailableMoves(show,1,0);
        updateAvailableMoves(show,-1,0);
        updateAvailableMoves(show,0,1);
        updateAvailableMoves(show,0,-1);
    }

    private void updateAvailableMoves(boolean show, int dx, int dy){
        byte x = getX(), y = getY();
        x += dx;y+=dy;
        while(x >= 0 && x < 8 && y >= 0 && y < 8){
            if(!Board.getInstance().getSpot(x,y).isOccupied()){
                Board.getInstance().getSpot(x,y).setCanMove(show);
            }else{
                if(Board.getInstance().getSpot(x,y).getPiece().isWhite() != this.isWhite()){
                    Board.getInstance().getSpot(x,y).setCanBeat(show);
                }
                break;
            }
            x += dx;y+=dy;
        }
    }
}
