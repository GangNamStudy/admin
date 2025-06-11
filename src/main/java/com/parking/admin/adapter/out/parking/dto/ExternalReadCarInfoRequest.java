package com.parking.admin.adapter.out.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExternalReadCarInfoRequest {
    private final Long id;
    private final String plate;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private boolean isParked;
}
