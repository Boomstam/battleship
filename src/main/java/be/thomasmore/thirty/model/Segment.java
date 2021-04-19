package be.thomasmore.thirty.model;

import java.awt.*;
import java.util.Objects;

public class Segment {
    private Ship ship;
    private Tile tile;

    private int health;

    public Segment(Ship ship, int startHealth) {
        this.ship = ship;
        health = startHealth;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Point getLocation(){
        return tile.getLocation();
    }

    public Ship getShip() {
        return ship;
    }

    public int getHealth() {
        return health;
    }

    public boolean isFirst(){
        boolean isFirst = (getSegmentIndex() == 0);
        return isFirst;
    }

    public boolean applyDamage(int damage){
        health = health - damage;
        if(health <= 0){
            return true;
        }
        return false;
    }

    private int getSegmentIndex(){
        Segment[] segments = ship.getSegments();
        int numSegments = segments.length;
        for(int i = 0; i < numSegments; i++){
            Segment segment = segments[i];
            if(equals(segment)){
                return i;
            }
        }
        System.out.println("Segment index not found!_" + toString());
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment = (Segment) o;
        return Objects.equals(tile, segment.tile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tile);
    }

    @Override
    public String toString() {
        return "Segment{" +
                "tile=" + tile +
                ", health=" + health +
                '}';
    }
}