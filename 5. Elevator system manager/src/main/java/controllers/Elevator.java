package controllers;

import enums.Direction;

import java.util.ArrayList;
import java.util.Iterator;

public class Elevator {
    public static int DEFAULT_FLOOR = 1;

    private int floorMin;
    private int floorMax;
    private int maxPersonNumber;

    private int currentFloor = DEFAULT_FLOOR;
    private Direction currentDirection = null;
    private ArrayList<Visitor> currentVisitors = new ArrayList<Visitor>();

    public Elevator() {
        this.maxPersonNumber = 0;
    }

    public Elevator(int floorFrom, int floorTo, int maxPersonNumber) {
        this.floorMin = floorFrom;
        this.floorMax = floorTo;
        this.maxPersonNumber = maxPersonNumber;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    public boolean canTakeVisitor(Visitor visitor) {
        return this.currentVisitors.size() < this.maxPersonNumber
                && (visitor.getDirection() == this.currentDirection || this.currentDirection == null);
    }

    public void addVisitor(Visitor visitor) {
        if (this.currentVisitors.size() == 0) {
            this.currentDirection = visitor.getDirection();
        }

        if (!this.canTakeVisitor(visitor)) {
            return;
        }

        this.currentVisitors.add(visitor);
    }

    public int move() {
        this.currentFloor = calculateFloorAfterMove();
        this.ridOutVisitors();

        return this.currentFloor;
    }

    private int calculateFloorAfterMove() {
        if (this.currentDirection == null) {
            return this.currentFloor;
        }

        switch (this.currentDirection) {
            case UP:
                return this.up();
            case DOWN:
                return this.down();
            default:
                return this.currentFloor;
        }
    }

    private int up() {
        if (this.floorMax == this.currentFloor) {
            return this.currentFloor;
        }

        return this.currentFloor + 1;
    }

    private int down() {
        if (this.floorMin == this.currentFloor) {
            return this.currentFloor;
        }

        return this.currentFloor - 1;
    }

    private void ridOutVisitors() {
        Iterator<Visitor> visitorIterator = this.currentVisitors.iterator();

        while(visitorIterator.hasNext()) {
            Visitor visitor = visitorIterator.next();

            if (visitor.getFloorTo() == this.currentFloor) {
                visitor.markReachedFloorTo();
                visitorIterator.remove();
            }
        }

        if (this.currentVisitors.size() == 0) {
            this.currentDirection = null;
        }
    }
}
