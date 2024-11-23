package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<Ticket, Car> parkingRecord = new HashMap<>();
    private int availableSlots;

    public ParkingLot() {
        capacity = 10;
        availableSlots = capacity;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        availableSlots = this.capacity;
    }

    public Ticket park(Car car) {
        if (availableSlots == 0) {
            throw new NoAvailablePositionException();
        }
        Ticket ticket = new Ticket();
        parkingRecord.put(ticket, car);
        availableSlots--;
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        if (ticket.isUsed() || parkingRecord.get(ticket) == null) {
            throw new UnrecognizedTicketException();
        }
        availableSlots++;
        ticket.setUsed();
        return parkingRecord.get(ticket);
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    public int getAvailableSlots() {
        return this.availableSlots;
    }

    public int getCapacity() {
        return this.capacity;
    }
}
