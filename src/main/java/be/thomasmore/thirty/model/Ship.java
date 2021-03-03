package be.thomasmore.thirty.model;

import be.thomasmore.thirty.helpers.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Ship {
    private int initiative;
    private String shipType;
    private int index;
    private int shipSize;
    private int speed;
    private WeaponType weaponType;
    private int weaponRange;
    private int currentOrdnance;
    private Segment[] segments;

    public Ship(ShipClass shipClass, int index) {
        this(shipClass.getInitiative(), shipClass.getShipType(), index, shipClass.getShipSize(), shipClass.getSpeed(), WeaponType.values()[shipClass.getWeaponType()],
                shipClass.getWeaponRange(), shipClass.getOrdnance(), shipClass.getStartHealth());
    }

    public Ship(int initiative, String shipType, int index, int shipSize, int speed, WeaponType weaponType, int weaponRange, int currentOrdnance, int startHealth) {
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

    @Override
    public String toString() {
        return "Ship{" +
                "initiative=" + initiative +
                ", shipType='" + shipType + '\'' +
                ", index=" + index +
                ", shipSize=" + shipSize +
                ", speed=" + speed +
                ", weaponType=" + weaponType +
                ", weaponRange=" + weaponRange +
                ", currentOrdnance=" + currentOrdnance +
                ", segments=" + Arrays.toString(segments) +
                '}';
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

    public void lift(){
        for(Segment segment : segments){
            if(segment.getTile() != null){
                segment.getTile().removeShip();
                segment.setTile(null);
            }
        }
    }

    public void setSegments(Segment[] segments){
        this.segments = segments;
    }

    public void setSegmentTile(Tile tile, int i){
        this.segments[i].setTile(tile);
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
}