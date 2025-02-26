package com.parking.admin.adapter.in.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EntryAndExitRequest {
    private String carId;
    private LocalDateTime time;
}
