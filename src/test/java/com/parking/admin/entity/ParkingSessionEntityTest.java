package com.parking.admin.entity;

import com.parking.admin.application.command.CreateParkingSessionCommand;
import com.parking.admin.application.mapper.ParkingSessionMapper;
import com.parking.admin.domain.common.exception.BusinessLogicException;
import com.parking.admin.domain.parking.ParkingSessionEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParkingSessionEntityTest {

    @InjectMocks
    ParkingSessionMapper mapper;

    @Test
    @DisplayName("주차된 차량의 세션을 생성할 수 있다")
    void createParkedSession() {
        // Given
        CreateParkingSessionCommand command = CreateParkingSessionCommand.builder()
                .id(1L)
                .plate("ABC123")
                .entryTime(LocalDateTime.now())
                .exitTime(null)
                .isParked(true)
                .build();

        // When
        ParkingSessionEntity session = mapper.toEntity(command);

        // Then
        assertTrue(session.isParked());
        assertNull(session.getExitTime());
    }

    @Test
    @DisplayName("출차된 차량의 세션을 생성할 수 있다")
    void createExitedSession() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        CreateParkingSessionCommand command = CreateParkingSessionCommand.builder()
                .id(1L)
                .plate("ABC123")
                .entryTime(now)
                .exitTime(now)
                .isParked(false)
                .build();

        // When
        ParkingSessionEntity session = mapper.toEntity(command);

        // Then
        assertFalse(session.isParked());
        assertNotNull(session.getExitTime());
    }

    @Test
    @DisplayName("주차된 차량을 출차할 수 있다")
    void exitParkedCar() {
        // Given
        CreateParkingSessionCommand command = CreateParkingSessionCommand.builder()
                .id(1L)
                .plate("ABC123")
                .entryTime(LocalDateTime.now())
                .exitTime(null)
                .isParked(true)
                .build();
        ParkingSessionEntity session = mapper.toEntity(command);

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
        CreateParkingSessionCommand command = CreateParkingSessionCommand.builder()
                .id(1L)
                .plate("ABC123")
                .entryTime(now)
                .exitTime(now)
                .isParked(false)
                .build();
        ParkingSessionEntity session = mapper.toEntity(command);

        // When & Then
        assertThrows(BusinessLogicException.class, session::exitCar);
    }


    @Test
    @DisplayName("입차 시간은 출차 시간보다 빨라야 한다")
    void entryTimeMustBeBeforeExitTime() {
        // Given
        LocalDateTime earlyExit = LocalDateTime.now();
        CreateParkingSessionCommand command = CreateParkingSessionCommand.builder()
                .id(1L)
                .plate("ABC123")
                .entryTime(LocalDateTime.now())
                .exitTime(earlyExit)
                .isParked(false)
                .build();

        // When & Then
        assertThrows(BusinessLogicException.class, () -> mapper.toEntity(command));
    }
}
