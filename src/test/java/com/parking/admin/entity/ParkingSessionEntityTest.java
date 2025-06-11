package com.parking.admin.entity;

import com.parking.admin.domain.common.EntityId;
import com.parking.admin.domain.common.exception.BusinessLogicException;
import com.parking.admin.domain.parking.ParkingSessionEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParkingSessionEntityTest {

    @Test
    @DisplayName("주차된 차량을 출차할 수 있다")
    void exitParkedCar() {
        // Given
        ParkingSessionEntity session = new ParkingSessionEntity(
                EntityId.of(1L),
                "ABC123",
                LocalDateTime.now(),
                null,
                true
        );

        // When
        session.exitCar();

        // Then
        assertFalse(session.isParked());
        assertNotNull(session.getExitTime());
    }

    @Test
    @DisplayName("출차된 차량은 출차할 수 없다")
    void cannotExitAlreadyExitedCar() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        ParkingSessionEntity session = new ParkingSessionEntity(
                EntityId.of(1L),
                "ABC123",
                now,
                now,
                false
        );

        // When & Then
        assertThrows(BusinessLogicException.class, session::exitCar);
    }


    @Test
    @DisplayName("입차 시간은 출차 시간보다 빨라야 한다")
    void entryTimeMustBeBeforeExitTime() {
        // Given
        LocalDateTime exitTime = LocalDateTime.now();
        LocalDateTime entryTime = exitTime.plusMinutes(1);

        // When & Then
        assertThrows(BusinessLogicException.class, () -> new ParkingSessionEntity(
                EntityId.of(1L),
                "ABC123",
                entryTime,
                exitTime,
                false
        ));
    }
}
