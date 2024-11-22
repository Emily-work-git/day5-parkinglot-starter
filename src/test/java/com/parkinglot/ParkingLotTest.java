package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ParkingLotTest {
    @Test
    public void should_return_ticket_when_park_given_a_parking_lot_and_a_car(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        // When
        Ticket ticket = parkingLot.park(car);
        // Then
        assertNotNull(ticket);
    }
    @Test
    public void should_return_car_when_fetch_given_a_parking_lot_with_a_car(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        // When
        Car fetchedCar = parkingLot.fetch(ticket);
        // Then
        assertEquals(car, fetchedCar);
    }
    @Test
    public void should_return_the_corresponding_ticket_of_a_car_when_fetch_given_two_fetch_request(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2= parkingLot.park(car2);
        // When
        Car fetchedCar1 = parkingLot.fetch(ticket1);
        Car fetchedCar2 = parkingLot.fetch(ticket2);
        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }
    @Test
    public void should_return_nothing_when_park_given_parkingLot_is_full_and_a_car(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAvailableSlots(0);
        Car car = new Car();
        // When
        Ticket ticket = parkingLot.park(car);
        // Then
        assertNull(ticket);
    }
    @Test
    public void should_print_unrecognized_ticket_error_when_fetch_given_wrong_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);
        Ticket wrongTicket = new Ticket();
        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class,
                ()->parkingLot.fetch(wrongTicket));
        // Then
        String expectedOutput = "Unrecognized parking ticket.";
        assertThat(exception.getMessage()).isEqualTo(expectedOutput);
    }
    @Test
    public void should_print_unrecognized_ticket_error_when_fetch_given_used_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket usedTicket = parkingLot.park(car);
        usedTicket.setUsed();
        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class,
                ()->parkingLot.fetch(usedTicket));
        // Then
        String expectedOutput = "Unrecognized parking ticket.";
        assertThat(exception.getMessage()).isEqualTo(expectedOutput);
    }
}
