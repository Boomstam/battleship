package be.thomasmore.thirty.model;

import java.awt.*;

public class Segment {
    private Tile tile;
    private int health;

    public Segment(int startHealth) {
        health = startHealth;
    }

    public Segment(Tile tile, int startHealth) {
        this.tile = tile;
        health = startHealth;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "tile=" + tile +
                ", health=" + health +
                '}';
    }

    public Point getLocation(){
        return tile.getLocation();
    }
}