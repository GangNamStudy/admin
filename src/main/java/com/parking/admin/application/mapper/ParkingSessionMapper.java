package com.parking.admin.application.mapper;

import com.parking.admin.application.command.CreateParkingSessionCommand;
import com.parking.admin.domain.entity.ParkingSessionEntity;
import com.parking.admin.domain.vo.EntityId;
import org.springframework.stereotype.Component;

@Component
public class ParkingSessionMapper {
    public ParkingSessionEntity toEntity(CreateParkingSessionCommand command) {
        return new ParkingSessionEntity(
                EntityId.of(command.getId()),
                command.getPlate(),
                command.getEntryTime(),
                command.getExitTime(),
                command.isParked()
        );
    }
}
