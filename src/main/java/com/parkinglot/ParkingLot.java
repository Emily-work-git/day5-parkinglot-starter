package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int CAPACITY = 10;
    private Map<Ticket, Car> parkingRecord = new HashMap<>();
    private int availableSlots;

    public ParkingLot(){
        availableSlots = CAPACITY;
    }

    public Ticket park(Car car) {
        if (availableSlots == 0){
            return null;
        }
        Ticket ticket = new Ticket();
        parkingRecord.put(ticket, car);
        availableSlots--;
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        if (ticket.isUsed()){
            return null;
        }
        Car fetchedCar = parkingRecord.get(ticket);
        if (fetchedCar != null){
            availableSlots++;
        }
        return fetchedCar;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }
}
