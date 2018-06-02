package Chess.Pieces;

import Chess.Board;

import javafx.scene.image.ImageView;

public class Pawn extends Piece {
    private byte direction;

    public Pawn(byte x, byte y, ImageView imageView, boolean white){
        super(x,y,imageView,white);
        direction = (byte)(white == true ? -1 : 1);
    }

    @Override
    protected void updateAvailableMoves(boolean show) {
        byte x = getX(), y = (byte)(getY() + direction);

        //Move
        if(y >= 8 || y < 0)
            return;

        if(!Board.getInstance().getSpot(x,y).isOccupied()){
            Board.getInstance().getSpot(x,y).setCanMove(show);
            y += direction;
            if(!moved() && !Board.getInstance().getSpot(x,y).isOccupied()){
                Board.getInstance().getSpot(x,y).setCanMove(show);
            }
        }

        //Beat
        x = getX(); y = (byte)(getY() + direction);
        x += 1;

        if(x < 8){
            Piece piece = Board.getInstance().getSpot(x,y).getPiece();
            if(piece != null && piece.isWhite() != this.isWhite()){
                Board.getInstance().getSpot(x,y).setCanBeat(show);
            }
        }

        x-=2;
        if(x >= 0){
            Piece piece = Board.getInstance().getSpot(x,y).getPiece();
            if(piece != null && piece.isWhite() != this.isWhite()){
                Board.getInstance().getSpot(x,y).setCanBeat(show);
            }
        }

    }
}
