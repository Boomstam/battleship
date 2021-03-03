package be.thomasmore.thirty.model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Board {

    private final static int maxNumIterations = 9999;

    private final static Point[] directions = {
        new Point(-1, 0),
        new Point(1, 0),
        new Point(0, 1),
        new Point(0, -1),
    };

    private int width;
    private int height;

    private Map<Point, Tile> tileMap;
    private Tile[] tiles;

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

    public Tile getTileAt(Point location) throws Exception {
        if(tileMap.containsKey(location) == false){
            throw new Exception("Tile " + location + " not found");
        }
        return tileMap.get(location);
    }

    public boolean hasTileAt(Point location){
        return tileMap.containsKey(location);
    }

    public Tile getTileAt(int x, int y){
        Point location = new Point(x, y);
        return tileMap.get(location);
    }

    public boolean hasShipAt(Point location)throws Exception {
        if(tileMap.containsKey(location) == false){
            throw new Exception("Tile " + location + " doesn't exist");
        }
        Tile tile = tileMap.get(location);
        return tile.hasSegment();
    }

    public Segment getSegmentAt(Point location){
        //Point location = new Point(x, y);
        Tile tile = tileMap.get(location);
        return tile.getSegment();
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

    public void place(Ship ship, Point[] segmentLocations) throws Exception {
        for(int i = 0; i < segmentLocations.length; i++){
            Segment segment = ship.getSegments()[i];
            Point location = segmentLocations[i];
            try {
                Tile tile = tileMap.get(location);
                segment.setTile(tile);
                tile.setSegment(segment);
            } catch (Exception e){
                throw new Exception("Can't place ship, segment location out of bounds " + segment);
            }
        }
    }

    public Point[] nextFreeShipSpace(int size) throws Exception {
        Random rand = new Random();
        int iteration = 0;
        while (iteration < maxNumIterations){
            iteration++;
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            Point point = new Point(x, y);
            Tile tile = getTileAt(point);
            if(tile.hasSegment()){
                continue;
            }
            Point direction = nextDirection();
            Point[] segments = segmentLocations(point, direction, size);
            if(segments != null){
                return segments;
            }
        }
        throw new Exception("No free ship space found");
    }

    private Point[] segmentLocations(Point point, Point direction, int size){
        Point[] locations = new Point[size];
        Point currentLocation = point;
        for (int i = 0; i < size; i++){
            currentLocation = translated(currentLocation, direction);
            try {
                Tile tile = getTileAt(currentLocation);
                if(tile.hasSegment()) {
                    return null;
                }
            } catch (Exception e){
                return null;
            }
            locations[i] = (Point)currentLocation.clone();
        }
        return locations;
    }

    private Point translated(Point origin, Point translation){
        return new Point(origin.x + translation.x, origin.y + translation.y);
    }

    private Point nextDirection(){
        Random rand = new Random();
        int nextIndex = rand.nextInt(directions.length);
        Point direction = directions[nextIndex];
        return direction;
    }
}