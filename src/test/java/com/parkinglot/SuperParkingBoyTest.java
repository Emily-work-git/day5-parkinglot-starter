package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot(20);
        superParkingBoy.addParkingLot(parkingLot1);
        superParkingBoy.addParkingLot(parkingLot2);
        Car car1 = new Car();
        Ticket ticket1 = superParkingBoy.park(car1);
        Car car2 = new Car();
        Ticket ticket2 = superParkingBoy.park(car2);
        Car car3 = new Car();
        // When
        Ticket ticket3 = superParkingBoy.park(car3);
        // Then
        ParkingLot parkedParkingLot = superParkingBoy.getParkingLotByTicket(ticket3);
        assertEquals(parkingLot2,parkedParkingLot);
    }
    @Test
    public void should_right_car_for_each_ticket_when_fetch_with_two_ticket_given_the_two_parking_lot_and_two_ticket(){
        // Given
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        superParkingBoy.addParkingLot(parkingLot1);
        superParkingBoy.addParkingLot(parkingLot2);
        Car car1 = new Car();
        Ticket ticket1 = superParkingBoy.park(car1);
        Car car2 = new Car();
        Ticket ticket2 = superParkingBoy.park(car2);
        // When
        Car fetchedCar1 = superParkingBoy.fetch(ticket1);
        Car fetchedCar2 = superParkingBoy.fetch(ticket2);
        // Then
        assertEquals(car1,fetchedCar1);
        assertEquals(car2,fetchedCar2);
    }
    @Test
    public void should_return_unrecognized_ticket_error_when_fetch_given_two_parking_lot_and_a_wrong_ticket(){
        // Given
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        superParkingBoy.addParkingLot(parkingLot1);
        superParkingBoy.addParkingLot(parkingLot2);
        Ticket wrongTicket = new Ticket();
        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class,
                () -> superParkingBoy.fetch(wrongTicket));
        // Then
        String expectedOutput = "Unrecognized parking ticket.";
        assertThat(exception.getMessage()).isEqualTo(expectedOutput);
    }
    @Test
    public void should_return_unrecognized_ticket_error_when_fetch_given_two_parking_lot_and_a_used_ticket(){
        // Given
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        superParkingBoy.addParkingLot(parkingLot1);
        superParkingBoy.addParkingLot(parkingLot2);
        Car car = new Car();
        Ticket usedTicket = superParkingBoy.park(car);
        Car fetchedCar = superParkingBoy.fetch(usedTicket);
        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class,
                () -> superParkingBoy.fetch(usedTicket));
        // Then
        String expectedOutput = "Unrecognized parking ticket.";
        assertThat(exception.getMessage()).isEqualTo(expectedOutput);
    }
    @Test
    public void should_return_no_available_position_error_when_park_given_two_parking_lot_with_no_available_position(){
        // Given
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        superParkingBoy.addParkingLot(parkingLot1);
        superParkingBoy.addParkingLot(parkingLot2);
        for (int i=0;i<20;i++){
            superParkingBoy.park(new Car());
        }
        // When
        NoAvailablePositionException exception = assertThrows(NoAvailablePositionException.class,
                () -> superParkingBoy.park(new Car()));
        // Then
        String expectedOutput = "No available position.";
        assertThat(exception.getMessage()).isEqualTo(expectedOutput);
    }
}
