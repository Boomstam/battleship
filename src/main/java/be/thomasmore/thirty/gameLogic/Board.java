package be.thomasmore.thirty.gameLogic;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private int width;
    private int height;

    private Map<Point, Tile> tileMap;
    private Tile[] tiles;

    public Board(){
        this(50, 50);
    }

    public Board(int width, int height){
        this.width = width;
        this.height = height;
        createTiles();
    }

    public int getWidth(){ return width; }
    public int getHeight(){ return height; }

    public Tile[] getTiles(){
        return tiles;
    }

    public Tile getTileAt(Point location){
        return tileMap.get(location);
    }

    public Tile getTileAt(int x, int y){
        Point location = new Point(x, y);
        return tileMap.get(location);
    }

    private void createTiles(){
        tileMap = new HashMap<>();
        tiles = new Tile[width * height];
        int tileIndex = 0;
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                Tile tile = new Tile(x, y);
                tileMap.put(tile.getLocation(), tile);
                tiles[tileIndex] = tile;
                tileIndex++;
            }
        }
    }


}