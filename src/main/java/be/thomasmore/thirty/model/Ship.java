package be.thomasmore.thirty.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ship {
    @Id
    private int id;
    private String shipType;
    private int amount;
    private int size;
    private int weaponType;
    private int weaponRange;
    private int ordnance;

    public Ship(){

    }

    public Ship(String shipType, int amount, int size, int weaponType, int weaponRange, int ordnance){
        this.shipType = shipType;
        this.amount = amount;
        this.size = size;
        this.weaponType = weaponType;
        this.weaponRange = weaponRange;
        this.ordnance = ordnance;
    }

    public String getShipType() {
        return shipType;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", shipType='" + shipType + '\'' +
                ", amount=" + amount +
                ", size=" + size +
                ", weaponType=" + weaponType +
                ", weaponRange=" + weaponRange +
                ", ordnance=" + ordnance +
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
}