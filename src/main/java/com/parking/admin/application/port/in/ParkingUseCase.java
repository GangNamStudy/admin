package com.parking.admin.application.port.in;

import com.parking.admin.domain.parking.ParkingInfo;
import lombok.Getter;

import java.time.LocalDateTime;

public interface ParkingUseCase {
    ParkingInfo entryCar(ParkingCommand parkingCommand);
    ParkingInfo exitCar(ParkingCommand parkingCommand);

    @Getter
    class ParkingCommand {
        private final String plate;
        private final LocalDateTime time;

        public ParkingCommand(String plate) {
            this.plate = plate;
            this.time = LocalDateTime.now();
        }
    }
}
