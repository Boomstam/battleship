package be.thomasmore.thirty.model;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ship {
    @Id
    private int id;
    private String shipType;
    private int amount;
    private int size;
    @Type(type = "be.thomasmore.model.WeaponWrapper")
    private Weapon weapon;

    public Ship(){

    }

    public Ship(String shipType, int amount){
        this.shipType = shipType;
        this.amount = amount;
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
                '}';
    }
}