package com.parkinglot;

import java.util.Comparator;

public class SuperParkingBoy extends ParkingBoy{
    @Override
    public Ticket park(Car car) {
        ParkingLot parkingLotWithMaxAvailablePositionRate = parkingLots.stream().max(
                Comparator.comparingDouble(ParkingLot::getAvailablePositionRate)).
                orElseThrow(NoAvailablePositionException::new);

        Ticket ticket = parkingLotWithMaxAvailablePositionRate.park(car);
        ticketToParkingLotRecord.put(ticket, parkingLotWithMaxAvailablePositionRate);
        return ticket;
    }
}
