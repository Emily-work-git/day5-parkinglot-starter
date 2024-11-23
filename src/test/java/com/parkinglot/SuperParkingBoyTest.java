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
    @Test
    public void should_return_second_parking_lot_when_park_given_the_second_parking_lot_has_larger_available_position_rate(){
        // Given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot(20);
        smartParkingBoy.addParkingLot(parkingLot1);
        smartParkingBoy.addParkingLot(parkingLot2);
        Car car = new Car();
        // When
        Ticket ticket = smartParkingBoy.park(car);
        // Then
        ParkingLot parkedParkingLot = smartParkingBoy.getParkingLotByTicket(ticket);
        assertEquals(parkingLot2,parkedParkingLot);
    }
}
