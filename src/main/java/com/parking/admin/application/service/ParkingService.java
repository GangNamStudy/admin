package com.parking.admin.application.service;

import com.parking.admin.application.port.in.ParkingUseCase;
import com.parking.admin.application.port.out.ParkingPort;
import com.parking.admin.common.UseCase;
import com.parking.admin.domain.parking.ParkingInfo;
import com.parking.admin.domain.parking.ParkingSessionEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ParkingService implements ParkingUseCase {

    private final ParkingPort parkingPort;

    @Override
    public ParkingInfo entryCar(ParkingCommand parkingCommand) {
        return parkingPort.entry(parkingCommand);
    }

    @Override
    public ParkingInfo exitCar(ParkingCommand parkingCommand) {
        ParkingSessionEntity entity = parkingPort.loadByPlate(parkingCommand.getPlate());
        entity.exitCar();
        return parkingPort.exit(entity);
    }

    @Override
    public List<ParkingInfo> getHistoryByCommand(searchHistoryCommand command) {
        return parkingPort.loadByCommand(command);
    }
}
