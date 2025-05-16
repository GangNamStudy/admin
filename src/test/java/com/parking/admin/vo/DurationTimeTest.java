package com.parking.admin.vo;

import com.parking.admin.domain.vo.DurationTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DurationTimeTest {

    @Test
    @DisplayName("유효한 기간이 주어졌을 때 DurationTime이 정상적으로 생성되어야 한다")
    void should_CreateDurationTime_When_ValidDurationProvided() {
        // given
        Long validDuration = 30L;

        // when
        DurationTime durationTime = DurationTime.of(validDuration);

        // then
        assertEquals(validDuration, durationTime.getDuration());
    }

    @Test
    @DisplayName("0이 주어졌을 때 DurationTime이 정상적으로 생성되어야 한다")
    void should_CreateDurationTime_When_ZeroDurationProvided() {
        // given
        Long zeroDuration = 0L;

        // when
        DurationTime durationTime = DurationTime.of(zeroDuration);

        // then
        assertEquals(zeroDuration, durationTime.getDuration());
    }

    @Test
    @DisplayName("음수 값이 주어졌을 때 예외가 발생해야 한다")
    void should_ThrowException_When_NegativeDurationProvided() {
        // given
        Long negativeDuration = -1L;

        // when & then
        assertThrows(IllegalArgumentException.class,
                () -> DurationTime.of(negativeDuration));
    }
}
