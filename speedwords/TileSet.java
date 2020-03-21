package speedwords;

import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;

public class TileSet{
    private static final int HIGHLIGHT_WIDTH = 2;
    private ArrayList<LetterTile> tiles = new ArrayList<LetterTile>();
    private int x = 0;
    private int y = 0;
    private boolean valid = false;

    public TileSet(String word, int x, int y){
        this.x = x;
        this.y = y;

        for(int i = 0; i < word.length(); i++){
            String letter = word.substring(i, i+1);
            LetterTile tile = new LetterTile(letter);
            tiles.add(tile);
        }
    }

    public void draw(Graphics g){
        if(valid){
            g.setColor(Color.YELLOW);
            int borderX = x - HIGHLIGHT_WIDTH;
            int borderY = y - HIGHLIGHT_WIDTH;
            int borderWidth = getWidth() + (HIGHLIGHT_WIDTH * 2);
            int borderHeight = LetterTile.SIZE + (HIGHLIGHT_WIDTH * 2);
            g.fillRect(borderX, borderY, borderWidth, borderHeight);
        }
        for(int i = 0; i < tiles.size(); i++){
            LetterTile tile = tiles.get(i);
            int xPos = x + (i * LetterTile.SIZE);
            tile.draw(g, xPos, y);
        }
    }

    public void changeXY(int changeX, int changeY){
        x += changeX;
        y += changeY;
    }

    public TileSet removeAndReturn1TileAt(int clickedX, int clickedY){
        TileSet tileSet = null;
        for(int i = 0; i < tiles.size(); i++){
            int tileLeftEdge = x + LetterTile.SIZE * i;
            int tileRightEdge = tileLeftEdge + LetterTile.SIZE;
            int tileTopEdge = y;
            int tileBottomEdge = y + LetterTile.SIZE;
            if(clickedX >= tileLeftEdge && clickedX <= tileRightEdge && clickedY >= tileTopEdge && clickedY <= tileBottomEdge){
                LetterTile tile = tiles.get(i);
                String letter = tile.getLetter();
                tileSet = new TileSet(letter, tileLeftEdge, tileTopEdge);
                tiles.remove(i);
                if(i == 0){
                    x += LetterTile.SIZE;
                }
            }
        }
        return tileSet;
    }

    public Boolean contains(int pointX, int pointY){
        boolean contains = false;
        int width = getWidth();
        int height = LetterTile.SIZE;
        if(pointX >= x && pointX < x + width && pointY >= y && pointY < y + height){
            contains = true;
        }
        return contains;
    }

    public String toString(){
        String s = "";
        for(int i = 0; i < tiles.size(); i++){
            LetterTile tile = tiles.get(i);
            String s2 = tile.getLetter();
            s += s2;
        }
        return s;
    }

    public boolean insertTiles(TileSet droppedTiles){
        boolean inserted = false;
        int droppedTilesWidth = droppedTiles.getWidth();
        int droppedTilesHeight = LetterTile.SIZE;
        int droppedTilesLeft = droppedTiles.getX();
        int droppedTilesRight = droppedTilesLeft + droppedTilesWidth;
        int droppedTilesTop = droppedTiles.getY();
        int droppedTilesBottom = droppedTilesTop + droppedTilesHeight;

        //if the dropped tiles overlap the row
        if(droppedTilesBottom > y && droppedTilesTop < y + droppedTilesHeight){
            //if the dropped tiles overlap the left edge of the first tile
            if(droppedTilesRight >= x && droppedTilesRight <= x + LetterTile.SIZE){
                //insert the dropped tiles before the tiles onto which they were dropped
                for(int i = 0; i < droppedTiles.getNumberOfTiles(); i++){
                    LetterTile tile = droppedTiles.getTile(i);
                    tiles.add(i, tile);
                    inserted = true;
                }
                x -= droppedTilesWidth;
            } else {
                //otherwise, if the dropped tiles overlap any other tile
                for(int i = 0; i < tiles.size() && !inserted; i++){
                    int compareX = x + i * LetterTile.SIZE;
                    if(droppedTilesLeft >= compareX && droppedTilesLeft <= compareX + LetterTile.SIZE){
                        //insert the dropped tiles after the first overlapped tile
                        for(int j = 0; j < droppedTiles.getNumberOfTiles(); j++){
                            LetterTile tile = droppedTiles.getTile(j);
                            tiles.add(i + j + 1, tile);
                            inserted = true;
                        }
                    }
                }
            }
        }
        return inserted;
    }

    public Integer getPoints(){
        int points = 0;
        for(int i = 0; i < tiles.size(); i++){
            LetterTile tile = tiles.get(i);
            points += tile.getPoints();
        }
        points *= tiles.size();
        return points;
    }

    public Integer getWidth(){return tiles.size() * LetterTile.SIZE;}

    public Integer getX(){return x;}

    public Integer getY(){return y;}

    public Integer getNumberOfTiles(){return tiles.size();}

    public LetterTile getTile(int i){return tiles.get(i);}

    public void setValid(boolean valid){this.valid = valid;}
}