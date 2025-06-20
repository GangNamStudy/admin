package com.parking.admin.application.port.in;

import com.parking.admin.domain.parking.ParkingInfo;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ParkingUseCase {
    ParkingInfo entryCar(ParkingCommand parkingCommand);
    ParkingInfo exitCar(ParkingCommand parkingCommand);
    List<ParkingInfo> getHistoryByCommand(searchHistoryCommand command);

    @Getter
    class ParkingCommand {
        private final String plate;
        private final LocalDateTime time;

        public ParkingCommand(String plate) {
            this.plate = plate;
            this.time = LocalDateTime.now();
        }
    }

    @Getter
    class searchHistoryCommand {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String plate;
        private final Boolean isParked;

        public searchHistoryCommand(LocalDate startDate, LocalDate endDate, String plate, Boolean isParked) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.plate = plate;
            this.isParked = isParked;
        }
    }
}
