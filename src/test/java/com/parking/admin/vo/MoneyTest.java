package com.parking.admin.vo;

import com.parking.admin.domain.common.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MoneyTest {

    @Test
    @DisplayName("금액이 음수일 경우 예외가 발생한다")
    void createMoneyWithNegativeAmount() {
        // Given
        long negativeAmount = -1000L;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> Money.of(negativeAmount));
    }

    @Test
    @DisplayName("금액이 양수일 경우 Money 객체가 생성된다")
    void createMoneyWithPositiveAmount() {
        // Given
        long amount = 1000L;

        // When
        Money money = Money.of(amount);

        // Then
        assertEquals(amount, money.getAmount());
    }

    @Test
    @DisplayName("금액이 0인 경우 Money 객체가 생성된다")
    void createMoneyWithZeroAmount() {
        // Given
        long amount = 0L;

        // When
        Money money = Money.of(amount);

        // Then
        assertEquals(amount, money.getAmount());
    }

    @Test
    @DisplayName("같은 금액을 가진 Money 객체는 동등하다")
    void equalMoneyTest() {
        // Given
        long amount = 1000L;

        // When
        Money money1 = Money.of(amount);
        Money money2 = Money.of(amount);

        // Then
        assertEquals(money1, money2);
    }

    @Test
    @DisplayName("다른 금액을 가진 Money 객체는 동등하지 않다")
    void notEqualMoneyTest() {
        // Given
        long amount1 = 1000L;
        long amount2 = 2000L;

        // When
        Money money1 = Money.of(amount1);
        Money money2 = Money.of(amount2);

        // Then
        assertNotEquals(money1, money2);
    }
}
