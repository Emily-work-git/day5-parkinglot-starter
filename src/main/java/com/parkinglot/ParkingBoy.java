package com.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingBoy extends ParkingLot {
    protected List<ParkingLot> parkingLots = new ArrayList<>();
    protected Map<Ticket, ParkingLot> ticketToParkingLotRecord = new HashMap<>();

    public void ParkingBoy() {}

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public Ticket park(Car car) {
        for (int i = 0; i < parkingLots.size(); i++) {
            if (parkingLots.get(i).getAvailableSlots() > 0) {
                Ticket ticket = parkingLots.get(i).park(car);
                ticketToParkingLotRecord.put(ticket, parkingLots.get(i));
                return ticket;
            }
        }
        throw new NoAvailablePositionException();
    }

    public Car fetch(Ticket ticket) {
        if (ticketToParkingLotRecord.get(ticket) == null) {
            throw new UnrecognizedTicketException();
        }
        return ticketToParkingLotRecord.get(ticket).fetch(ticket);
    }

    public ParkingLot getParkingLotByTicket(Ticket ticket) {
        if (ticketToParkingLotRecord.get(ticket) == null) {
            throw new UnrecognizedTicketException();
        }
        return ticketToParkingLotRecord.get(ticket);
    }
}
