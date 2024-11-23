package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SuperParkingBoyTest {
    @Test
    public void should_return_first_parking_lot_when_park_given_two_parking_lot_with_same_available_positions_rate_and_a_car(){
        // Given
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        superParkingBoy.addParkingLot(parkingLot1);
        superParkingBoy.addParkingLot(parkingLot2);
        Car car = new Car();
        // When
        Ticket ticket = superParkingBoy.park(car);
        // Then
        ParkingLot parkedParkingLot = superParkingBoy.getParkingLotByTicket(ticket);
        assertEquals(parkingLot1,parkedParkingLot);
    }
}
