package controllers;

import java.util.ArrayList;

public class Environment {
    private Thread elevatorSystemThread;
    private ArrayList<Visitor> visitors;
    private ArrayList<Elevator> elevators;

    public Environment() {
        this.visitors = new ArrayList<Visitor>();
        this.elevators = new ArrayList<Elevator>();
    }

    public void turnElevatorSystemOn() {
        ElevatorManager elevatorManager = new ElevatorManager(this.visitors, this.elevators);
        this.elevatorSystemThread = new Thread(elevatorManager);
        this.elevatorSystemThread.start();
    }

    public void turnElevatorSystemOff() {
        if (this.elevatorSystemThread == null) {
            return;
        }

        this.elevatorSystemThread.interrupt();
    }

    public void addVisitor(Visitor visitor) {
        this.visitors.add(visitor);
    }

    public void setElevators(ArrayList<Elevator> elevators) {
        if (elevatorSystemThread != null) {
            this.turnElevatorSystemOff();

            System.out.println("Система была отключена для установки новых лифтов");
        }

        this.elevators = elevators;
    }
}
