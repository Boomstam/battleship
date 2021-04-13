package be.thomasmore.thirty.model;

import be.thomasmore.thirty.helpers.PointHelpers;

import java.awt.*;
import java.util.Objects;

public class Tile {

    private Point location;
    private Segment segment;
    private boolean hasMine;

    public Tile(int x, int y) {
        location = new Point(x, y);
    }

    public Point getLocation() {
        return location;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public boolean hasSegment(){
        if(segment == null){
            return false;
        }
        return true;
    }
    public boolean hasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public String locationStr(){
        return PointHelpers.pointStrFormat(location);
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