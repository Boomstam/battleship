package be.thomasmore.thirty.model;

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
            segments[i] = new Segment(startHealth);
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
}