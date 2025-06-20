package com.parking.admin.adapter.in.web.controller;

import com.parking.admin.adapter.in.web.dto.request.EntryRequest;
import com.parking.admin.adapter.in.web.dto.response.EntryResponse;
import com.parking.admin.adapter.in.web.dto.response.ExitResponse;
import com.parking.admin.adapter.in.web.mapper.ParkingWebMapper;
import com.parking.admin.application.port.in.ParkingUseCase;
import com.parking.admin.domain.parking.ParkingInfo;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/parking")
@Log4j2
public class ParkingController {

    private final ParkingUseCase parkingUseCase;
    private final ParkingWebMapper parkingWebMapper;

    @GetMapping("/history")
    public ResponseEntity<List<ParkingInfo>> searchParkingHistory(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "검색할 시간범위 시작(~부터)")
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "검색할 시간범위 끝(~까지)")
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            @Parameter(description = "검색할 차량 번호판")
            @RequestParam(value = "plate", required = false) String plate,
            @Parameter(description = "주차 여부")
            @RequestParam(value = "isParked", required = false) Boolean isParked) {
        ParkingUseCase.searchHistoryCommand cmd=new ParkingUseCase.searchHistoryCommand(
                startDate, endDate,plate,isParked);
        return ResponseEntity.ok(parkingUseCase.getHistoryByCommand(cmd));
    }

    @PostMapping("/entrance")
    public ResponseEntity<EntryResponse> entrance(@RequestBody EntryRequest dto) {
        ParkingUseCase.ParkingCommand command = parkingWebMapper.toCommand(dto);
        ParkingInfo info = parkingUseCase.entryCar(command);
        EntryResponse response = parkingWebMapper.toEntryResponse(info);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{plate}/departure")
    public ResponseEntity<ExitResponse> departure(@PathVariable("plate") String plate) {
        log.info("departure plate: {}", plate);
        ParkingUseCase.ParkingCommand command = parkingWebMapper.toCommand(plate);
        ParkingInfo info = parkingUseCase.exitCar(command);
        log.info("departure info: {}", info);
        ExitResponse response = parkingWebMapper.toExitResponse(info);
        return ResponseEntity.ok(response);
    }
}
