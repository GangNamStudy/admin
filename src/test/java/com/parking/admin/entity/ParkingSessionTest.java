package com.parking.admin.entity;

import com.parking.admin.application.command.CreateParkingSessionCommand;
import com.parking.admin.domain.entity.ParkingSession;
import com.parking.admin.domain.vo.EntityId;
import com.parking.admin.domain.vo.Time;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ParkingSessionTest {

    @Test
    @DisplayName("주차된 차량의 세션을 생성할 수 있다")
    void createParkedSession() {
        // Given
        CreateParkingSessionCommand command = CreateParkingSessionCommand.builder()
                .id(EntityId.of(1L))
                .plate("ABC123")
                .entryTime(Time.now())
                .exitTime(null)
                .isParked(true)
                .build();

        // When
        ParkingSession session = new ParkingSession(command);

        // Then
        assertTrue(session.isParked());
        assertNull(session.getExitTime());
    }

    @Test
    @DisplayName("출차된 차량의 세션을 생성할 수 있다")
    void createExitedSession() {
        // Given
        Time now = Time.now();
        CreateParkingSessionCommand command = CreateParkingSessionCommand.builder()
                .id(EntityId.of(1L))
                .plate("ABC123")
                .entryTime(now)
                .exitTime(now)
                .isParked(false)
                .build();

        // When
        ParkingSession session = new ParkingSession(command);

        // Then
        assertFalse(session.isParked());
        assertNotNull(session.getExitTime());
    }

    @Test
    @DisplayName("주차된 상태에서는 출차 시각이 없어야 한다")
    void validateParkedStateHasNoExitTime() {
        // Given
        CreateParkingSessionCommand command = CreateParkingSessionCommand.builder()
                .id(EntityId.of(1L))
                .plate("ABC123")
                .entryTime(Time.now())
                .exitTime(Time.now())
                .isParked(true)
                .build();

        // When & Then
        assertThrows(IllegalStateException.class, () -> new ParkingSession(command));
    }

    @Test
    @DisplayName("출차된 상태에서는 출차 시각이 있어야 한다")
    void validateExitedStateHasExitTime() {
        // Given
        CreateParkingSessionCommand command = CreateParkingSessionCommand.builder()
                .id(EntityId.of(1L))
                .plate("ABC123")
                .entryTime(Time.now())
                .exitTime(null)
                .isParked(false)
                .build();

        // When & Then
        assertThrows(IllegalStateException.class, () -> new ParkingSession(command));
    }

    @Test
    @DisplayName("주차된 차량을 출차할 수 있다")
    void exitParkedCar() {
        // Given
        CreateParkingSessionCommand command = CreateParkingSessionCommand.builder()
                .id(EntityId.of(1L))
                .plate("ABC123")
                .entryTime(Time.now())
                .exitTime(null)
                .isParked(true)
                .build();
        ParkingSession session = new ParkingSession(command);

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
        Time now = Time.now();
        CreateParkingSessionCommand command = CreateParkingSessionCommand.builder()
                .id(EntityId.of(1L))
                .plate("ABC123")
                .entryTime(now)
                .exitTime(now)
                .isParked(false)
                .build();
        ParkingSession session = new ParkingSession(command);

        // When & Then
        assertThrows(IllegalStateException.class, session::exitCar);
    }
}
