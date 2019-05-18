package controllers;

import enums.Direction;

public class Visitor {
    private int floor;
    private int floorTo;

    private boolean reachedFloorTo;

    public Visitor() {}

    public Visitor(int floor, int floorTo) {
        this.floor = floor;
        this.floorTo = floorTo;
    }

    public int getFloor() {
        return this.floor;
    }

    public int getFloorTo() {
        return this.floorTo;
    }

    public Direction getDirection() {
        return this.floor < this.floorTo ? Direction.UP : Direction.DOWN;
    }

    public boolean isFloorToReached() {
        return this.reachedFloorTo;
    }

    public void markReachedFloorTo() {
        this.reachedFloorTo = true;
    }
}
