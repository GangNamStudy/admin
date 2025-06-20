package com.parking.admin.adapter.out.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExternalVehicleExitRequest {
    private LocalDateTime exitTime;
}
