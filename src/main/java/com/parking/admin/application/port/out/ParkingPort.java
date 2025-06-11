package com.parking.admin.application.port.out;

import com.parking.admin.application.port.in.ParkingUseCase;
import com.parking.admin.domain.parking.ParkingInfo;
import com.parking.admin.domain.parking.ParkingSessionEntity;

public interface ParkingPort {
    ParkingInfo entry(ParkingUseCase.ParkingCommand parkingCommand);
    ParkingInfo exit(ParkingSessionEntity entity);
    ParkingSessionEntity loadById(Long id);
    ParkingSessionEntity loadByPlate(String plate);
}

