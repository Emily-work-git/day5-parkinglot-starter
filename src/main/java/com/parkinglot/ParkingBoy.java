package com.parkinglot;

public class ParkingBoy extends ParkingLot{
    private ParkingLot parkingLot = new ParkingLot();
    public void ParkingBoy(){
    }
    public Ticket park (Car car){
        return parkingLot.park(car);
    }
}
