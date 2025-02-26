package com.parking.admin.adapter.in.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingManagementRequest {
    private String carId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
