package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<Ticket, Car> parkingRecord = new HashMap<>();
    private int availablePositions;

    public ParkingLot() {
        capacity = 10;
        availablePositions = capacity;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        availablePositions = capacity;
    }

    public Ticket park(Car car) {
        if (availablePositions == 0) {
            throw new NoAvailablePositionException();
        }
        Ticket ticket = new Ticket();
        parkingRecord.put(ticket, car);
        availablePositions--;
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        if (ticket.isUsed() || parkingRecord.get(ticket) == null) {
            throw new UnrecognizedTicketException();
        }
        availablePositions++;
        ticket.setUsed();
        return parkingRecord.get(ticket);
    }

    public void setAvailablePositions(int availablePositions) {
        this.availablePositions = availablePositions;
    }

    public int getAvailablePositions() {
        return this.availablePositions;
    }

    public int getCapacity() {
        return this.capacity;
    }
    public double getAvailablePositionRate(){
        return ((double) this.availablePositions /this.capacity);
    }
}
