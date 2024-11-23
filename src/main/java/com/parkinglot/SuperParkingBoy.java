package com.parkinglot;

import java.util.Comparator;

public class SuperParkingBoy extends ParkingBoy{
    @Override
    public Ticket park(Car car) {
        ParkingLot parkingLotWithMaxSlots = parkingLots.stream().max(
                Comparator.comparingDouble(ParkingLot::getAvailablePositionRate)).
                orElseThrow(NoAvailablePositionException::new);

        Ticket ticket = parkingLotWithMaxSlots.park(car);
        ticketToParkingLotRecord.put(ticket, parkingLotWithMaxSlots);
        return ticket;
    }
}
