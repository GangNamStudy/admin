package com.parking.admin.application.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateParkingSessionCommand {
    private Long id;
    private String plate;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private boolean isParked;
}
