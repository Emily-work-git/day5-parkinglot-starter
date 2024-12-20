package com.parkinglot;

public class NoAvailablePositionException extends RuntimeException {
    public static final String NO_AVAILABLE_POSITION_ERROR_MESSAGE = "No available position.";

    public NoAvailablePositionException() {
        super(NO_AVAILABLE_POSITION_ERROR_MESSAGE);
    }
}
