package com.parking.admin.domain.entity;

import com.parking.admin.application.command.CreateParkingSessionCommand;
import com.parking.admin.domain.vo.EntityId;
import com.parking.admin.domain.vo.Time;
import lombok.Getter;

@Getter
public class ParkingSession {
    private EntityId id;
    private String plate;
    private Time entryTime;
    private Time exitTime;
    private boolean isParked;

    public ParkingSession(CreateParkingSessionCommand command){
        validate(command);
        this.id = command.getId();
        this.plate = command.getPlate();
        this.entryTime = command.getEntryTime();
        this.exitTime = command.getExitTime();
        this.isParked = command.isParked();
    }

    public void exitCar(){
        validateExit();
        this.exitTime = Time.now();
        this.isParked = false;
    }

    private void validateExit(){
        if (!this.isParked) {
            throw new IllegalStateException("주차되지 않은 차량은 내보낼 수 없습니다.");
        }
    }

    private void validate(CreateParkingSessionCommand command){
        if (command.isParked()) {
            validateParkedStateHasNoExitTime(command.getExitTime());
        }
        else {
            validateExitedStateHasExitTime(command.getExitTime());
            validateTime(command.getEntryTime(), command.getExitTime());
        }
    }

    private void validateTime(Time entryTime, Time exitTime){
        if (entryTime.isAfter(exitTime)) {
            throw new IllegalStateException("출차 시간이 입차시간보다 빠릅니다.");
        }
    }

    private void validateParkedStateHasNoExitTime(Time exitTime) {
        if (exitTime != null) {
            throw new IllegalStateException("차량이 주차되어 있는데 출차 시각이 기록되어 있습니다.");
        }
    }

    private void validateExitedStateHasExitTime(Time exitTime) {
        if (exitTime == null) {
            throw new IllegalStateException("차량이 출차했는데 출차 시각이 기록되어 있지 않습니다");
        }
    }
}
