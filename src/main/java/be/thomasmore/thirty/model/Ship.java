package be.thomasmore.thirty.model;

import java.util.Arrays;

public class Ship {
    private int initiative;
    private String shipType;
    private int amount;
    private int shipSize;
    private int speed;
    private WeaponType weaponType;
    private int weaponRange;
    private int currentOrdnance;
    private int[] health;

    public Ship(ShipClass shipClass) {
        this(shipClass.getInitiative(), shipClass.getShipType(), shipClass.getAmount(), shipClass.getShipSize(), shipClass.getSpeed(), WeaponType.values()[shipClass.getWeaponType()],
                shipClass.getWeaponRange(), shipClass.getOrdnance(), initHealth(shipClass.getShipSize(), shipClass.getStartHealth()));
    }

    public Ship(int initiative, String shipType, int amount, int shipSize, int speed, WeaponType weaponType, int weaponRange, int currentOrdnance, int[] health) {
        this.initiative = initiative;
        this.shipType = shipType;
        this.amount = amount;
        this.shipSize = shipSize;
        this.speed = speed;
        this.weaponType = weaponType;
        this.weaponRange = weaponRange;
        this.currentOrdnance = currentOrdnance;
        this.health = health;
    }

    private static int[] initHealth(int shipSize, int startHealth){
        int[] health = new int[shipSize];
        for(int i = 0; i < shipSize; i++){
            health[i] = startHealth;
        }
        return health;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "initiative=" + initiative +
                ", shipType='" + shipType + '\'' +
                ", amount=" + amount +
                ", shipSize=" + shipSize +
                ", speed=" + speed +
                ", weaponType=" + weaponType +
                ", weaponRange=" + weaponRange +
                ", currentOrdnance=" + currentOrdnance +
                ", health=" + Arrays.toString(health) +
                '}';
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getShipSize() {
        return shipSize;
    }

    public void setShipSize(int shipSize) {
        this.shipSize = shipSize;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public int getWeaponRange() {
        return weaponRange;
    }

    public void setWeaponRange(int weaponRange) {
        this.weaponRange = weaponRange;
    }

    public int getCurrentOrdnance() {
        return currentOrdnance;
    }

    public void setCurrentOrdnance(int currentOrdnance) {
        this.currentOrdnance = currentOrdnance;
    }

    public int[] getHealth() {
        return health;
    }

    public void setHealth(int[] health) {
        this.health = health;
    }
}