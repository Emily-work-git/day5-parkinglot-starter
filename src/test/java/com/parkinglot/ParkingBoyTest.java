package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    @Test
    public void should_return_ticket_when_park_given_a_car() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot);
        // When
        Ticket ticket = parkingBoy.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_car_when_fetch_given_a_car() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot);
        Ticket ticket = parkingBoy.park(car);
        // When
        Car fetchedCar = parkingBoy.fetch(ticket);
        // Then
        assertEquals(car, fetchedCar);
    }

    @Test
    public void should_return_the_corresponding_ticket_of_a_car_when_fetch_given_two_fetch_request() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot);
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
        ParkingLot parkingLot = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot);
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
        ParkingLot parkingLot = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot);
        Ticket usedTicket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(usedTicket);
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
        ParkingLot parkingLot = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot);
        for (int i = 0; i < 10; i++) {
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

    @Test
    public void should_park_to_first_parking_lot_when_park_given_two_available_parking_lot() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        ParkingLot parkedParkingLot = parkingBoy.getParkingLotByTicket(ticket);
        // Then
        assertEquals(parkedParkingLot, parkingLot1);
    }

    @Test
    public void should_park_to_second_parking_lot_when_park_given_two_available_parking_lot_and_the_first_parkinglot_is_full() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
        for (int i = 0; i < 10; i++) {
            parkingBoy.park(new Car());
        }
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        ParkingLot parkedParkingLot = parkingBoy.getParkingLotByTicket(ticket);
        // Then
        assertEquals(parkedParkingLot, parkingLot2);
    }

    @Test
    public void should_return_correct_car_with_each_ticket_when_fetch_twice_given_two_parking_lots_and_two_tickets() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
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
    public void should_return_unrecognized_ticket_error_when_fetch_given_two_parking_lot_and_wrong_ticket() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
        Ticket wrongticket = new Ticket();
        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class,
                () -> parkingBoy.fetch(wrongticket));
        // Then
        String expectedOutput = "Unrecognized parking ticket.";
        assertThat(exception.getMessage()).isEqualTo(expectedOutput);
    }

    @Test
    public void should_return_unrecognized_ticket_error_when_fetch_given_two_parking_lot_and_a_used_ticket() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
        Car car = new Car();
        Ticket usedTicket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(usedTicket);
        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class,
                () -> parkingBoy.fetch(usedTicket));
        // Then
        String expectedOutput = "Unrecognized parking ticket.";
        assertThat(exception.getMessage()).isEqualTo(expectedOutput);
    }

    @Test
    public void should_return_no_available_position_error_when_park_given_two_parking_lot_without_any_position() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
        for (int i = 0; i < 20; i++) {
            parkingBoy.park(new Car());
        }
        // When
        NoAvailablePositionException exception = assertThrows(NoAvailablePositionException.class,
                () -> parkingBoy.park(new Car()));
        // Then
        String expectedOutput = "No available position.";
        assertThat(exception.getMessage()).isEqualTo(expectedOutput);
    }
}
