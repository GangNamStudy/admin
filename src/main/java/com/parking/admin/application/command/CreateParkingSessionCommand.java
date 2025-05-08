package com.parking.admin.application.command;

import com.parking.admin.domain.vo.EntityId;
import com.parking.admin.domain.vo.Time;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateParkingSessionCommand {
    private EntityId id;
    private String plate;
    private Time entryTime;
    private Time exitTime;
    private boolean isParked;
}
