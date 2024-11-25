package com.parkinglot;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy{
    public void SmartParkingBoy(){}
    @Override
    public Ticket park(Car car) {
        ParkingLot parkingLotWithMaxPositions = parkingLots.stream().max(
                Comparator.comparingInt(ParkingLot::getAvailablePositions)).
                orElseThrow(NoAvailablePositionException::new);

        Ticket ticket = parkingLotWithMaxPositions.park(car);
        ticketToParkingLotRecord.put(ticket, parkingLotWithMaxPositions);
        return ticket;
    }

}
