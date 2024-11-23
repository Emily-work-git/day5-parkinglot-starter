package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
    @Test
    public void should_return_second_parking_lot_when_park_given_the_second_parking_lot_has_more_available_positions_and_a_car(){
        // Given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        smartParkingBoy.addParkingLot(parkingLot1);
        smartParkingBoy.addParkingLot(parkingLot2);
        Car car1 = new Car();
        Ticket ticket1 = smartParkingBoy.park(car1);
        Car car2 = new Car();
        // When
        Ticket ticket2 = smartParkingBoy.park(car2);
        // Then
        ParkingLot parkedParkingLot = smartParkingBoy.getParkingLotByTicket(ticket2);
        assertEquals(parkingLot2,parkedParkingLot);
    }
    @Test
    public void should_right_car_for_each_ticket_when_fetch_with_two_ticket_given_the_two_parking_lot_and_two_ticket(){
        // Given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        smartParkingBoy.addParkingLot(parkingLot1);
        smartParkingBoy.addParkingLot(parkingLot2);
        Car car1 = new Car();
        Ticket ticket1 = smartParkingBoy.park(car1);
        Car car2 = new Car();
        Ticket ticket2 = smartParkingBoy.park(car2);
        // When
        Car fetchedCar1 = smartParkingBoy.fetch(ticket1);
        Car fetchedCar2 = smartParkingBoy.fetch(ticket2);
        // Then
        assertEquals(car1,fetchedCar1);
        assertEquals(car2,fetchedCar2);
    }
    @Test
    public void should_return_unrecognized_ticket_error_when_fetch_given_two_parking_lot_and_a_wrong_ticket(){
        // Given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        smartParkingBoy.addParkingLot(parkingLot1);
        smartParkingBoy.addParkingLot(parkingLot2);
        Ticket wrongTicket = new Ticket();
        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class,
                () -> smartParkingBoy.fetch(wrongTicket));
        // Then
        String expectedOutput = "Unrecognized parking ticket.";
        assertThat(exception.getMessage()).isEqualTo(expectedOutput);
    }
    @Test
    public void should_return_unrecognized_ticket_error_when_fetch_given_two_parking_lot_and_a_used_ticket(){
        // Given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        smartParkingBoy.addParkingLot(parkingLot1);
        smartParkingBoy.addParkingLot(parkingLot2);
        Car car = new Car();
        Ticket usedTicket = smartParkingBoy.park(car);
        Car fetchedCar = smartParkingBoy.fetch(usedTicket);
        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class,
                () -> smartParkingBoy.fetch(usedTicket));
        // Then
        String expectedOutput = "Unrecognized parking ticket.";
        assertThat(exception.getMessage()).isEqualTo(expectedOutput);
    }
    @Test
    public void should_return_no_available_position_error_when_park_given_two_parking_lot_with_no_available_position(){
        // Given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        smartParkingBoy.addParkingLot(parkingLot1);
        smartParkingBoy.addParkingLot(parkingLot2);
        for (int i=0;i<20;i++){
            smartParkingBoy.park(new Car());
        }
        // When
        NoAvailablePositionException exception = assertThrows(NoAvailablePositionException.class,
                () -> smartParkingBoy.park(new Car()));
        // Then
        String expectedOutput = "No available position.";
        assertThat(exception.getMessage()).isEqualTo(expectedOutput);
    }
}
