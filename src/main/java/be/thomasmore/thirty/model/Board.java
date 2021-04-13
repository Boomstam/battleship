package be.thomasmore.thirty.model;

import be.thomasmore.thirty.helpers.Direction;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Board {

    private final static int maxNumIterations = 9999;
    private final static String ocean = "~", shipStart = "o", ship = "-", invisible = "~";
    private final static String invisibleClass = "invisible", oceanClass = "water", shipClass ="ship",
            player1Class = "player_1", player2Class = "player_2";

    private int width;
    private int height;

    private Map<Point, Tile> tileMap;
    private Tile[] tiles;

    private Ship[] ships;

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
        Tile tile = tileMap.get(location);
        return tile.getSegment();
    }

    public Ship getShipAt(Point location){
        Segment segment = getSegmentAt(location);
        Ship ship = segment.getShip();
        return ship;
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
                if(tile.hasSegment()){
                    if(tile.getSegment().getShip().equals(ship) == false){
                        throw new Exception("There is already another ship at this location_" + tile.toString());
                    }
                }
                if(segment.getTile() != null){
                    segment.getTile().setSegment(null);
                }
                segment.setTile(tile);
                tile.setSegment(segment);
            } catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }
    }

    public Point[] nextFreeShipSpace(int size, float minMapLimit, float maxMapLimit) throws Exception {
        Random rand = new Random();
        int iteration = 0;
        int minY = Math.round(height * minMapLimit);
        int range = Math.round(height * (maxMapLimit - minMapLimit));
        while (iteration < maxNumIterations){
            iteration++;
            int x = rand.nextInt(width);
            int y = rand.nextInt(range) + minY;
            Point point = new Point(x, y);
            Tile tile = getTileAt(point);
            if(tile.hasSegment()){
                continue;
            }
            Point direction = Direction.randomDirection();
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

    public String getTileContent(Point location){
        if(isVisibleToPlayer(location) == false){
            return invisible;
        }
        try {
            if(hasShipAt(location)){
                if(getSegmentAt(location).isFirst()){
                    return shipStart;
                } else {
                    return ship;
                }
            } else {
                return ocean;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
/*
${board.hasShipAt(tile.location) ?
                (board.getShipAt(tile.location).playerIndex == 0 ? 'ship player_1' : 'ship player_2')
                : 'water'}
 */
    public String getTileClasses(Point location){
        if(isVisibleToPlayer(location) == false){
            return invisibleClass;
        }
        try {
            if(hasShipAt(location)){
                if(getShipAt(location).getPlayer() == Player.PLAYER_1){
                    return shipClass + " " + player1Class;
                } else {
                    return shipClass + " " + player2Class;
                }
            } else {
                return oceanClass;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public boolean isVisibleToPlayer(Point location){
        for(Ship ship : ships){
            if(ship.getPlayer().isHuman() == false){
                continue;
            }
            Point[] visibleLocations = ship.visibleLocations();
            List<Point> locationsAsList = Arrays.asList(visibleLocations);
            if(locationsAsList.contains(location)){
                return true;
            }
        }
        return false;
    }

    public void setShips(Ship[] ships) {
        this.ships = ships;
    }

    public Ship[] getShips() {
        return ships;
    }
}