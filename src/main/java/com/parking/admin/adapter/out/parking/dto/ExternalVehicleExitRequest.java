package com.parking.admin.adapter.out.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExternalVehicleExitRequest {
    private LocalDateTime exitTime;
}
