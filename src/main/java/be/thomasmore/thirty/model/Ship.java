package be.thomasmore.thirty.model;

import be.thomasmore.thirty.helpers.Direction;
import be.thomasmore.thirty.helpers.PointHelpers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Ship {
    private int id;
    private int initiative;
    private String shipType;
    private int index;
    private int shipSize;
    private int speed;
    private WeaponType weaponType;
    private int weaponRange;
    private int currentOrdnance;
    private Segment[] segments;
    private Player player;

    private static final int visibleRange = 2;
    private static final int patrolRange = 4;

    private static int currentNumShips = 0;

    public Ship(ShipClass shipClass, int index, Player player) {
        this(shipClass.getInitiative(), shipClass.getShipType(), index, shipClass.getShipSize(), shipClass.getSpeed(), WeaponType.values()[shipClass.getWeaponType()],
                shipClass.getWeaponRange(), shipClass.getOrdnance(), shipClass.getStartHealth());
        this.player = player;
    }

    public Ship(int initiative, String shipType, int index, int shipSize, int speed, WeaponType weaponType, int weaponRange, int currentOrdnance, int startHealth) {
        id = currentNumShips;
        currentNumShips++;
        this.initiative = initiative;
        this.shipType = shipType;
        this.index = index;
        this.shipSize = shipSize;
        this.speed = speed;
        this.weaponType = weaponType;
        this.weaponRange = weaponRange;
        this.currentOrdnance = currentOrdnance;
        segments = new Segment[shipSize];
        for (int i = 0; i < shipSize; i++){
            segments[i] = new Segment(this, startHealth);
        }
    }

    public int getInitiative() {
        return initiative;
    }

    public String getShipType() {
        return shipType;
    }

    public int getIndex() {
        return index;
    }

    public int getShipSize() {
        return shipSize;
    }

    public int getSpeed() {
        return speed;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public int getWeaponRange() {
        return weaponRange;
    }

    public int getCurrentOrdnance() {
        return currentOrdnance;
    }

    public void decrementOrdnance() {
        currentOrdnance = currentOrdnance--;
    }

    public void move(boolean halfThrottle){

    }

    public Point[] visibleLocations(boolean onPatrol){
        HashSet<Point> locations = new HashSet<>();
        for(Segment segment : segments){
             Point point = segment.getLocation();
             locations.add(point);
        }
        int range = onPatrol ? patrolRange : visibleRange;
        Point[] currentLocations = (Point[]) locations.toArray();
        for(int i = 0; i < range; i++){
            for(Point point : currentLocations){
                Point[] neighbors = Direction.getNeighbors(point);
                for(Point neighbor : neighbors){
                    if(locations.contains(neighbor) == false){
                        locations.add(neighbor);
                    }
                }
            }
        }
        Point[] locationArray = (Point[]) locations.toArray();
        return locationArray;
    }

    public Segment[] getSegments(){
        return segments;
    }

    public ArrayList<Point> locationsInRange(Board board){
        Point[] perpendicularDirections = Direction.perpendicularDirections(getDirectionType());
        ArrayList<Point> foundLocations = new ArrayList<>();
        for(int segmentIndex = 0; segmentIndex < segments.length; segmentIndex++){
            Point segmentLocation = segments[segmentIndex].getLocation();
            for(int rangeIndex = 1; rangeIndex <= weaponRange; rangeIndex++){
                for(int perpDirIndex = 0; perpDirIndex < perpendicularDirections.length; perpDirIndex++){
                    Point perpDir = perpendicularDirections[perpDirIndex];
                    Point location = new Point(segmentLocation.x + (perpDir.x * (rangeIndex)),
                                               segmentLocation.y + (perpDir.y * (rangeIndex)));
                    if(board.hasTileAt(location)){
                        foundLocations.add(location);
                    }
                }
            }
        }
        return foundLocations;
    }

    public String formattedLocationsInRange(Board board){
        ArrayList<Point> locations = locationsInRange(board);
        String formattedLocations = PointHelpers.pointArrayStrFormat(locations.toArray(new Point[0]));
        return formattedLocations;
    }

    public int getDirection(){
        Point first = segments[0].getLocation();
        Point second = segments[1].getLocation();
        int direction = Direction.getDirectionTo(first, second);
        return direction;
    }

    public Direction getDirectionType(){
        int direction = getDirection();
        return Direction.values()[direction];
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPlayerIndex() {
        return player.ordinal();
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", shipType='" + shipType + '\'' +
                '}';
    }
}