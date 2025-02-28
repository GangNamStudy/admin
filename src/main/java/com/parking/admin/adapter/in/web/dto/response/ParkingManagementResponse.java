package com.parking.admin.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ParkingManagementResponse {
    private String carId;
    private Long fee;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String paymentMethod;
}
