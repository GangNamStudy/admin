package com.parking.admin.domain.parking;

import com.parking.admin.domain.common.EntityId;
import com.parking.admin.domain.common.exception.BusinessLogicException;
import com.parking.admin.domain.common.exception.ErrorCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ParkingSessionEntity {
    private final EntityId id;
    private final String plate;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private boolean isParked;

    public ParkingSessionEntity(EntityId id, String plate, LocalDateTime entryTime,
                                LocalDateTime exitTime, boolean isParked) {
        this.id = id;
        this.plate = plate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.isParked = isParked;
        validate();
    }

    public void exitCar(){
        validateExit();
        this.exitTime = LocalDateTime.now();
        this.isParked = false;
    }

    private void validateExit(){
        if (!this.isParked) {
            throw new BusinessLogicException(ErrorCode.CANNOT_EXIT_UNPARKED_CAR);
        }
    }

    private void validate(){
        if (!this.isParked()) {
            validateTime();
        }
    }

    private void validateTime(){
        if (this.entryTime.isAfter(this.exitTime)) {
            throw new BusinessLogicException(ErrorCode.EXIT_BEFORE_ENTRY);
        }
    }
}
