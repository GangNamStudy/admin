package com.parking.admin.adapter.in.web.mapper;

import com.parking.admin.adapter.in.web.dto.request.EntryRequest;
import com.parking.admin.adapter.in.web.dto.response.EntryResponse;
import com.parking.admin.adapter.in.web.dto.response.ExitResponse;
import com.parking.admin.application.port.in.ParkingUseCase;
import com.parking.admin.domain.parking.ParkingInfo;
import org.springframework.stereotype.Component;

@Component
public class ParkingWebMapper {
    public ParkingUseCase.ParkingCommand toCommand(EntryRequest dto){
        return new ParkingUseCase.ParkingCommand(dto.getPlate());
    }

    public ParkingUseCase.ParkingCommand toCommand(String plate){
        return new ParkingUseCase.ParkingCommand(plate);
    }

    public EntryResponse toEntryResponse(ParkingInfo info){
        return new EntryResponse(
                info.getPlate(),
                info.getEntryTime(),
                info.isParked()
        );
    }

    public ExitResponse toExitResponse(ParkingInfo info){
        return new ExitResponse(
                info.getPlate(),
                info.getEntryTime(),
                info.getExitTime(),
                info.isParked()
        );
    }
}
