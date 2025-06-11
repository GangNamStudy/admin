package com.parking.admin.adapter.in.web.controller;

import com.parking.admin.adapter.in.web.dto.request.EntryRequest;
import com.parking.admin.adapter.in.web.dto.response.EntryResponse;
import com.parking.admin.adapter.in.web.dto.response.ExitResponse;
import com.parking.admin.adapter.in.web.dto.response.ParkingManagementListResponse;
import com.parking.admin.adapter.in.web.mapper.ParkingWebMapper;
import com.parking.admin.application.port.in.ParkingUseCase;
import com.parking.admin.domain.parking.ParkingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/management")
public class ManagementController {

    private final ParkingUseCase parkingUseCase;
    private final ParkingWebMapper parkingWebMapper;

    @GetMapping
    public ResponseEntity<ParkingManagementListResponse> readParkingHistory() {
        throw new UnsupportedOperationException();}

    @PostMapping("/entrance")
    public ResponseEntity<EntryResponse> entrance(@RequestBody EntryRequest dto) {
        ParkingUseCase.ParkingCommand command = parkingWebMapper.toCommand(dto);
        ParkingInfo info = parkingUseCase.entryCar(command);
        EntryResponse response = parkingWebMapper.toEntryResponse(info);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{plate}/departure")
    public ResponseEntity<ExitResponse> departure(@PathVariable("plate") String plate) {
        ParkingUseCase.ParkingCommand command = parkingWebMapper.toCommand(plate);
        ParkingInfo info = parkingUseCase.exitCar(command);
        ExitResponse response = parkingWebMapper.toExitResponse(info);
        return ResponseEntity.ok(response);
    }
}
