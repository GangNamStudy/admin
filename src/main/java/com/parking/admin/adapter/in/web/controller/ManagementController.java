package com.parking.admin.adapter.in.web.controller;

import com.parking.admin.adapter.in.web.dto.request.EntryAndExitRequest;
import com.parking.admin.adapter.in.web.dto.response.EntryResponse;
import com.parking.admin.adapter.in.web.dto.response.ParkingManagementListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/management")
public class ManagementController {

    @GetMapping
    public ResponseEntity<ParkingManagementListResponse> readParkingHistory() {
        throw new UnsupportedOperationException();}

    @PostMapping("/entrance")
    public ResponseEntity<EntryResponse> entrance(@RequestBody EntryAndExitRequest dto) {throw new UnsupportedOperationException();}

    @PatchMapping("/{parking-id}/departure")
    public String departure(@PathVariable("parking-id") String parkingId, @RequestBody EntryAndExitRequest dto) {
        throw new UnsupportedOperationException();}
}
