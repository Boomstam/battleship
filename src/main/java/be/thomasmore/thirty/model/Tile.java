package be.thomasmore.thirty.model;

import java.awt.*;
import java.util.Objects;

public class Tile {

    private Point location;

    public Tile(int x, int y) {
        location = new Point(x, y);
    }

    public Point getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return location.x + "_" + location.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return location.equals(tile.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }
}