package com.parkinglot;

public class Ticket {
    private boolean useStatus;

    public Ticket(){
        this.useStatus = false;
    }

    public void setUsed() {
        useStatus = true;
    }
    public boolean isUsed(){
        return useStatus;
    }
}
