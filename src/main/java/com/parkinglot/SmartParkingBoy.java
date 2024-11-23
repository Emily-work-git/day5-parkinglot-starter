package com.parkinglot;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy{
    public void SmartParkingBoy(){}
    @Override
    public Ticket park(Car car) {
        ParkingLot parkingLotWithMaxSlots = parkingLots.stream().max(
                Comparator.comparingInt(ParkingLot::getAvailableSlots)).
                orElseThrow(NoAvailablePositionException::new);

        Ticket ticket = parkingLotWithMaxSlots.park(car);
        ticketToParkingLotRecord.put(ticket, parkingLotWithMaxSlots);
        return ticket;
    }

}
