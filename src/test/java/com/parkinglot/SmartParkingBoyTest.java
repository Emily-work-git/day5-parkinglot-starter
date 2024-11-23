package com.parkinglot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SmartParkingBoyTest {
    @Test
    public void should_return_first_parking_lot_when_park_given_two_parking_lot_with_same_available_positions_and_a_car(){
        // Given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        smartParkingBoy.addParkingLot(parkingLot1);
        smartParkingBoy.addParkingLot(parkingLot2);
        Car car = new Car();
        // When
        Ticket ticket = smartParkingBoy.park(car);
        // Then
        ParkingLot parkedParkingLot = smartParkingBoy.getParkingLotByTicket(ticket);
        assertEquals(parkingLot1,parkedParkingLot);

    }
}
