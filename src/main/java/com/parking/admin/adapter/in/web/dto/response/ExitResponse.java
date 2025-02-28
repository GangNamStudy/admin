package com.parking.admin.adapter.in.web.dto.response;

import com.parking.admin.domain.vo.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExitResponse {
    private String carId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private CarStatus status;
}
