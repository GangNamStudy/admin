package com.parking.admin.domain.parking;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ParkingInfo {
    private final String plate;
    private final LocalDateTime entryTime;
    private final LocalDateTime exitTime;
    private final boolean isParked;

    public ParkingInfo(String plate, LocalDateTime entryTime, boolean isParked) {
        this.plate = plate;
        this.entryTime = entryTime;
        this.exitTime = null;
        this.isParked = isParked;
    }

    public ParkingInfo(String plate, LocalDateTime entryTime, LocalDateTime exitTime, boolean isParked) {
        this.plate = plate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.isParked = isParked;
    }
}
