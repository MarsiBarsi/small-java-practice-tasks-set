package utils;

import controllers.Elevator;

import java.util.ArrayList;


public class TypicalElevatorsFactory {
    static public ArrayList<Elevator> generate(int n, int floorFrom, int floorTo, int maxPersonNumber) {
        ArrayList<Elevator> elevators = new ArrayList<Elevator>();

        for (int i = 0; i < n; i++) {
            Elevator elevator = new Elevator(floorFrom, floorTo, maxPersonNumber);

            elevators.add(elevator);
        }

        return elevators;
    }
}
