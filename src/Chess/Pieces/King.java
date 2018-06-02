package Chess.Pieces;

import Chess.Board;

import javafx.scene.image.ImageView;

public class King extends Piece{
    public King(byte x, byte y, ImageView imageView, boolean white){
        super(x,y,imageView,white);
    }

    @Override
    protected void updateAvailableMoves(boolean show) {
        for (int dx = -1; dx <= 1; dx++){
            for(int dy = -1; dy <= 1; dy++){
                byte x = getX();x += dx;
                byte y = getY();y += dy;
                if(dx == 0 && dy == 0)continue;
                if(x < 0 || x >= 8 || y < 0 || y >= 8)continue;
                if(!Board.getInstance().getSpot(x,y).isOccupied()){
                    Board.getInstance().getSpot(x,y).setCanMove(show);
                }else if(Board.getInstance().getSpot(x,y).getPiece().isWhite() != this.isWhite()){
                    Board.getInstance().getSpot(x,y).setCanBeat(show);
                }
            }
        }
    }
}
