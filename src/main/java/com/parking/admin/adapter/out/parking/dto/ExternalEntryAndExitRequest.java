package com.parking.admin.adapter.out.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExternalEntryAndExitRequest {
    private final String plate;
    private final LocalDateTime time;
}
