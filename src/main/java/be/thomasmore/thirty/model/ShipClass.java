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

    public static final String sortableProperties[] = {"Initiative", "Amount", "Size", "Speed", "Range", "Ordnance", "Health"};

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

    public String getWeaponTypeString() {
        return WeaponType.values()[weaponType].toString();
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