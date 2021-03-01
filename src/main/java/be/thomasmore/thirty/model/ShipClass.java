package be.thomasmore.thirty.model;

import javax.persistence.*;

@Entity
public class ShipClass {
    @Id
    private int initiative;
    private String shipType;
    private int amount;
    private int shipSize;
    private int speed;
    private int weaponType;
    private int weaponRange;
    private int ordnance;
    private int startHealth;

    public ShipClass(){

    }

    public ShipClass(int initiative, String shipType, int amount, int shipSize, int weaponType, int weaponRange, int ordnance, int startHealth){
        this.initiative = initiative;
        this.shipType = shipType;
        this.amount = amount;
        this.shipSize = shipSize;
        this.speed = speed;
        this.weaponType = weaponType;
        this.weaponRange = weaponRange;
        this.ordnance = ordnance;
        this.startHealth = startHealth;
    }

    public String getShipType() {
        return shipType;
    }

    public int getAmount() {
        return amount;
    }

    public int getShipSize() {
        return shipSize;
    }

    @Override
    public String toString() {
        return "ShipClass{" +
                "initiative=" + initiative +
                ", shipType='" + shipType + '\'' +
                ", amount=" + amount +
                ", shipSize=" + shipSize +
                ", speed=" + speed +
                ", weaponType=" + weaponType +
                ", weaponRange=" + weaponRange +
                ", ordnance=" + ordnance +
                ", startHealth=" + startHealth +
                '}';
    }

    public int getWeaponType() {
        return weaponType;
    }

    public int getWeaponRange() {
        return weaponRange;
    }

    public int getOrdnance() {
        return ordnance;
    }

    public void setOrdnance(int ordnance) {
        this.ordnance = ordnance;
    }

    public int getStartHealth() {
        return startHealth;
    }

    public int getSpeed() {
        return speed;
    }

    public int getInitiative() {
        return initiative;
    }
}