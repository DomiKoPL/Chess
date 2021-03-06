package Chess;

import Chess.Pieces.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.InputStream;

public class Board {
    private static Board INSTANCE;

    private Board(){

    }

    public static Board getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Board();
        }
        return INSTANCE;
    }

    private Spot[][] spots = new Spot[8][8];
    private String[] names = {"pawn", "rook","knight","bishop","king","queen"};
    Pane root = new Pane();

    public void setupImage(ImageView imageView, int x , int y){
        double width = Game.getInstance().getTileSize() * Game.getInstance().getTileScale();
        imageView.setX(x * width);
        imageView.setY(y * width + 15);
        imageView.setScaleX(Game.getInstance().getTileScale());
        imageView.setTranslateX(-Game.getInstance().getTileSize()  * (1 - Game.getInstance().getTileScale() ) / 2);
        imageView.setScaleY(Game.getInstance().getTileScale());
        imageView.setTranslateY(-Game.getInstance().getTileSize()  * (1 - Game.getInstance().getTileScale() ) / 2);
    }

    public void makeNewBoard(Stage stage){
        InputStream file = this.getClass().getResourceAsStream("/Images/PNGs/With Shadow/128px/square brown dark_png_shadow_128px.png");
        Image image = new Image(file);

        InputStream file2 = this.getClass().getResourceAsStream("/Images/PNGs/With Shadow/128px/square brown light_png_shadow_128px.png");
        Image image2 = new Image(file2);
        boolean dark = false;

        InputStream file4 = this.getClass().getResourceAsStream("/Images/PNGs/With Shadow/128px/canMove.png");
        Image imageCanMove = new Image(file4);
        InputStream file5 = this.getClass().getResourceAsStream("/Images/PNGs/With Shadow/128px/canBeat.png");
        Image imageCanBeat= new Image(file5);

        for(int i = 0 ; i < 8 ; i++){
            for(int j = 0 ; j < 8 ; j++){
                ImageView imageView;
                if(!dark){
                    imageView = new ImageView(image2);
                }else{
                    imageView = new ImageView(image);
                }
                ImageView canMove = new ImageView(imageCanMove);
                ImageView canBeat = new ImageView(imageCanBeat);
                this.spots[i][j] = new Spot(imageView,canMove,canBeat);
                setupImage(canMove,j,i);
                setupImage(canBeat,j,i);
                setupImage(imageView,j,i);
                root.getChildren().addAll(imageView,canMove,canBeat);
                dark = !dark;
            }
            dark = !dark;
        }



        byte y;
        for(int col = 0 ; col < 2; col++){
            for(int j = 0 ; j < 6; j++){
                InputStream file3 = this.getClass().getResourceAsStream("/Images/PNGs/With Shadow/128px/"+(col == 0 ? "b" : "w") + "_" + names[j] + "_png_shadow_128px.png");
                Image pieceImage = new Image(file3);
                Piece piece;
                y = (byte)(col == 0 ? 0 : 7);
                boolean white = (col == 0 ? false : true);
                switch (j){
                    case 0:
                        y = (byte)(col == 0 ? 1 : 6);
                        for(byte x = 0 ; x < 8; x++){
                            ImageView imageView = new ImageView(pieceImage);
                            setupImage(imageView,x,y);
                            piece = new Pawn(x,y,imageView,white);
                            spots[y][x].occupySpot(piece);
                            root.getChildren().add(imageView);
                        }
                        break;
                    case 1:
                        for(byte x = 0 ; x < 8; x += 7){
                            ImageView imageView = new ImageView(pieceImage);
                            setupImage(imageView,x,y);
                            piece = new Rook(x,y,imageView,white);
                            spots[y][x].occupySpot(piece);
                            root.getChildren().add(imageView);
                        }
                        break;
                    case 2:
                        for(byte x = 1 ; x < 7; x += 5){
                            ImageView imageView = new ImageView(pieceImage);
                            setupImage(imageView,x,y);
                            piece = new Knight(x,y,imageView,white);
                            spots[y][x].occupySpot(piece);
                            root.getChildren().add(imageView);
                        }
                        break;
                    case 3:
                        for(byte x = 2 ; x < 6; x += 3){
                            ImageView imageView = new ImageView(pieceImage);
                            setupImage(imageView,x,y);
                            piece = new Bishop(x,y,imageView,white);
                            spots[y][x].occupySpot(piece);
                            root.getChildren().add(imageView);
                        }
                        break;
                    case 4:
                        for(byte x = 4 ; x < 5; x++){
                            ImageView imageView = new ImageView(pieceImage);
                            setupImage(imageView,x,y);
                            piece = new King(x,y,imageView,white);
                            spots[y][x].occupySpot(piece);
                            root.getChildren().add(imageView);
                        }
                        break;
                    case 5:
                        for(byte x = 3 ; x < 4; x++){
                            ImageView imageView = new ImageView(pieceImage);
                            setupImage(imageView,x,y);
                            piece = new Queen(x,y,imageView,white);
                            spots[y][x].occupySpot(piece);
                            root.getChildren().add(imageView);
                        }
                        break;
                }
            }
        }

        final Slider slider = new Slider(0.2,1,0.5);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov
                    , Number oldValue, Number newValue) {
                Game.getInstance().setTileScale(newValue.doubleValue());
                double width = Game.getInstance().getTileScale() * Game.getInstance().getTileSize() * 8 + 6;

                stage.setWidth(width);
                stage.setHeight(width + 23 + 15);

                for (int i = 0 ; i < 8; i++){
                    for(int j = 0 ; j < 8; j++){
                        spots[i][j].updateScale(j,i);
                    }
                }
            }
        });

        GridPane.setConstraints(slider,1,1);
        root.getChildren().add(slider);

        double width = Game.getInstance().getTileScale() * Game.getInstance().getTileSize() * 8 +  6;

        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));
        stage.setScene(new Scene(root,width,width));

        stage.setWidth(width);
        stage.setHeight(width + 23 + 15);
        stage.setResizable(false);
    }

    public Spot getSpot(byte x, byte y){
        return spots[y][x];
    }

    public void movePiece(byte fromX, byte fromY, byte toX,byte toY){
        Piece temp = getSpot(fromX,fromY).getPiece();
        spots[fromY][fromX].clearSpot();
        spots[toY][toX].occupySpot(temp);
    }

}
