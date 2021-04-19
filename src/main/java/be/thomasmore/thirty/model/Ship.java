package be.thomasmore.thirty.model;

import be.thomasmore.thirty.helpers.Direction;
import be.thomasmore.thirty.helpers.PointHelpers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class Ship {
    private int id;
    private int initiative;
    private String shipType;
    private int index;
    private int shipSize;
    private int speed;
    private WeaponType weaponType;
    private int weaponRange;
    private Segment[] segments;
    private Player player;
    private Action action;
    private boolean onPatrol;

    private static final int visibleRange = 4;
    private static final int patrolRange = 6;

    private static int currentNumShips = 0;

    public Ship(ShipClass shipClass, int index, Player player) {
        this(shipClass.getInitiative(), shipClass.getShipType(), index, shipClass.getShipSize(), shipClass.getSpeed(), WeaponType.values()[shipClass.getWeaponType()],
                shipClass.getWeaponRange(), shipClass.getStartHealth());
        this.player = player;
    }

    public Ship(int initiative, String shipType, int index, int shipSize, int speed, WeaponType weaponType, int weaponRange, int startHealth) {
        id = currentNumShips;
        currentNumShips++;
        this.initiative = initiative;
        this.shipType = shipType;
        this.index = index;
        this.shipSize = shipSize;
        this.speed = speed;
        this.weaponType = weaponType;
        this.weaponRange = weaponRange;
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

    public boolean isSubmarine(){
        return shipType.equals("Submarine");
    }

    public Point[] visibleLocations(){
        HashSet<Point> locations = new HashSet<>();
        for(Segment segment : segments){
             Point point = segment.getLocation();
             locations.add(point);
        }
        int range = onPatrol ? patrolRange : visibleRange;
        for(int i = 0; i < range; i++){
            HashSet<Point> currentLocations = new HashSet<>();
            for(Point point : locations){
                Point[] neighbors = Direction.getNeighbors(point);
                for(Point neighbor : neighbors){
                    if(locations.contains(neighbor) == false && currentLocations.contains(neighbor) == false){
                        currentLocations.add(neighbor);
                    }
                }
            }
            locations.addAll(currentLocations);
        }
        Point[] locationArray = locations.toArray(new Point[locations.size()]);
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

    public Direction getFormattedDirectionType(){
        Direction directionType = getDirectionType();
        if(Direction.isHorizontal(directionType) == false){
            directionType = Direction.opposite(directionType);
        }
        return directionType;
    }

    public boolean isOnPatrol() {
        return onPatrol;
    }

    public void setOnPatrol(boolean onPatrol) {
        this.onPatrol = onPatrol;
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public Point[] getSegmentLocations(){
        Point[] locations = new Point[shipSize];
        for(int i = 0; i < shipSize; i++){
            locations[i] = segments[i].getLocation();
        }
        return locations;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return id == ship.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                "initiative=" + initiative +
                ", shipType='" + shipType + '\'' +
                '}';
    }
}