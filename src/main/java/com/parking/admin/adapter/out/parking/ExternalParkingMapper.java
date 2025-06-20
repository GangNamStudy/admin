package com.parking.admin.adapter.out.parking;

import com.parking.admin.adapter.out.parking.dto.ExternalEntryAndExitRequest;
import com.parking.admin.adapter.out.parking.dto.ExternalEntryAndExitResponse;
import com.parking.admin.adapter.out.parking.dto.ExternalReadCarInfoResponse;
import com.parking.admin.application.port.in.ParkingUseCase;
import com.parking.admin.domain.common.EntityId;
import com.parking.admin.domain.parking.ParkingInfo;
import com.parking.admin.domain.parking.ParkingSessionEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExternalParkingMapper {
    public ExternalEntryAndExitRequest toEntryRequest(ParkingUseCase.ParkingCommand parkingCommand){
        return new ExternalEntryAndExitRequest(
                parkingCommand.getPlate(),
                parkingCommand.getTime()
        );
    }

    public ExternalEntryAndExitRequest toEntryRequest(ParkingSessionEntity entity){
        return new ExternalEntryAndExitRequest(
                entity.getPlate(),
                entity.getEntryTime()
        );
    }
    public ParkingInfo toInfo(ExternalReadCarInfoResponse response){
        return new ParkingInfo(
                response.getPlate(),
                response.getEntryTime(),
                response.getExitTime(),
                response.isParked()
        );
    }

    public ParkingInfo entryToInfo(ExternalEntryAndExitResponse response){
        return new ParkingInfo(
                response.getPlate(),
                response.getEntryTime(),
                true
        );
    }

    public ParkingInfo exitToInfo(ExternalEntryAndExitResponse response, LocalDateTime exitTime){
        return new ParkingInfo(
                response.getPlate(),
                response.getEntryTime(),
                exitTime,
                false
        );
    }

    public ParkingSessionEntity toEntity(ExternalReadCarInfoResponse response){
        return new ParkingSessionEntity(
                EntityId.of(response.getId()),
                response.getPlate(),
                response.getEntryTime(),
                response.getExitTime(),
                response.isParked()
        );
    }
}
