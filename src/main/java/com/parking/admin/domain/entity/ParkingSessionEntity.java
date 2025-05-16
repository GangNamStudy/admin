package com.parking.admin.domain.entity;

import com.parking.admin.domain.vo.EntityId;
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
            throw new IllegalStateException("주차되지 않은 차량은 내보낼 수 없습니다.");
        }
    }

    private void validate(){
        if (this.isParked()) {
            validateParkedStateHasNoExitTime();
        }
        else {
            validateExitedStateHasExitTime();
            validateTime();
        }
    }

    private void validateTime(){
        if (this.entryTime.isAfter(this.exitTime)) {
            throw new IllegalStateException("출차 시간이 입차시간보다 빠릅니다.");
        }
    }

    private void validateParkedStateHasNoExitTime() {
        if (this.exitTime != null) {
            throw new IllegalStateException("차량이 주차되어 있는데 출차 시각이 기록되어 있습니다.");
        }
    }

    private void validateExitedStateHasExitTime() {
        if (this.exitTime == null) {
            throw new IllegalStateException("차량이 출차했는데 출차 시각이 기록되어 있지 않습니다");
        }
    }
}
