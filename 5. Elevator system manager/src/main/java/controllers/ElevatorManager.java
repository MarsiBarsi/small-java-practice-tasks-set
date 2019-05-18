package controllers;

import controllers.Elevator;
import controllers.Visitor;

import java.util.ArrayList;
import java.util.Iterator;

public class ElevatorManager implements Runnable {
    public static int ELEVATOR_MOVE_DELAY_MS = 600;

    private ArrayList<Visitor> visitors;
    private ArrayList<Elevator> elevators;

    ElevatorManager() { }

    ElevatorManager(ArrayList<Visitor> visitors) {
        this.visitors = visitors;
        this.elevators = new ArrayList<Elevator>();
    }

    ElevatorManager(ArrayList<Visitor> visitors, ArrayList<Elevator> elevators) {
        this.visitors = visitors;
        this.elevators = elevators;
    }

    public void run() {
        this.startManagement();
    }

    private void startManagement() {
        while (true) {
            if (this.visitors == null) {
                return;
            }

            this.spreadVisitors();
            this.moveElevators();

            try {
                Thread.sleep(ELEVATOR_MOVE_DELAY_MS);
            }
            catch (Exception ex) {}
        }
    }

    private void spreadVisitors() {
        synchronized (this.visitors) {
            Iterator<Visitor> visitorIterator = this.visitors.iterator();
            while (visitorIterator.hasNext()) {
                Visitor visitor = visitorIterator.next();
                Elevator elevator = this.getElevatorForVisitor(visitor);

                boolean success = setVisitorIntoElevator(visitor, elevator);

                if (success) {
                    visitorIterator.remove();
                }
            }
        }
    }

    private Elevator getElevatorForVisitor(Visitor visitor) {
        Iterator<Elevator> elevatorIterator = this.elevators.iterator();

        while(elevatorIterator.hasNext()) {
            Elevator elevator = elevatorIterator.next();

            if (elevator.canTakeVisitor(visitor)) {
                return elevator;
            }
        }

        return null;
    }

    private boolean setVisitorIntoElevator(Visitor visitor, Elevator elevator) {
        if (elevator == null) {
            return false;
        }

        elevator.addVisitor(visitor);

        return true;
    }

    private void moveElevators() {
        Iterator<Elevator> elevatorIterator = this.elevators.iterator();

        while(elevatorIterator.hasNext()) {
            Elevator elevator = elevatorIterator.next();

            elevator.move();
        }
    }
}
