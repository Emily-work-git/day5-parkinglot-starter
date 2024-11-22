package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    @Test
    public void should_return_ticket_when_park_given_a_car(){
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        // Then
        assertNotNull(ticket);
    }
    @Test
    public void should_return_car_when_fetch_given_a_car(){
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        // When
        Car fetchedCar = parkingBoy.fetch(ticket);
        // Then
        assertEquals(car,fetchedCar);
    }
    @Test
    public void should_return_the_corresponding_ticket_of_a_car_when_fetch_given_two_fetch_request() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);
        // When
        Car fetchedCar1 = parkingBoy.fetch(ticket1);
        Car fetchedCar2 = parkingBoy.fetch(ticket2);
        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }
    @Test
    public void should_print_unrecognized_ticket_error_when_fetch_given_wrong_ticket() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        parkingBoy.park(car);
        Ticket wrongTicket = new Ticket();
        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class,
                () -> parkingBoy.fetch(wrongTicket));
        // Then
        String expectedOutput = "Unrecognized parking ticket.";
        assertThat(exception.getMessage()).isEqualTo(expectedOutput);
    }
    @Test
    public void should_print_unrecognized_ticket_error_when_fetch_given_used_ticket() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        Ticket usedTicket = parkingBoy.park(car);
        usedTicket.setUsed();
        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class,
                () -> parkingBoy.fetch(usedTicket));
        // Then
        String expectedOutput = "Unrecognized parking ticket.";
        assertThat(exception.getMessage()).isEqualTo(expectedOutput);
    }
    @Test
    public void should_print_no_available_position_error_when_park_given_full_parking_lot() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        for (int i=0;i<10;i++){
            parkingBoy.park(new Car());
        }
        Car car = new Car();
        // When
        NoAvailablePositionException exception = assertThrows(NoAvailablePositionException.class,
                () -> parkingBoy.park(car));
        // Then
        String expectedOutput = "No available position.";
        assertThat(exception.getMessage()).isEqualTo(expectedOutput);
    }
}
