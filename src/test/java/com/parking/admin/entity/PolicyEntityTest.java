package com.parking.admin.entity;

import com.parking.admin.application.mapper.PolicyMapper;
import com.parking.admin.application.port.in.PolicyUseCase;
import com.parking.admin.domain.common.Money;
import com.parking.admin.domain.common.exception.BusinessLogicException;
import com.parking.admin.domain.policy.DurationTime;
import com.parking.admin.domain.policy.PolicyEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PolicyEntityTest {

    @InjectMocks
    private PolicyMapper policyMapper;

    @Test
    @DisplayName("주차 정책 생성 시 모든 필드가 정상적으로 설정된다")
    void createPolicy() {
        // given

        // when
        PolicyEntity actual = new PolicyEntity(
                Money.of(1000L),
                DurationTime.of(30L),
                Money.of(500L),
                DurationTime.of(10L)
        );

        // then
        assertEquals(1000L, actual.getBaseFee().getAmount());
        assertEquals(30L, actual.getFreeTime().getDuration());
        assertEquals(500L, actual.getAdditionalFee().getAmount());
        assertEquals(10L, actual.getAdditionalTime().getDuration());
    }

    @Test
    @DisplayName("기본 요금을 변경할 수 있다")
    void changeBaseFee() {
        // given
        PolicyEntity policy = new PolicyEntity(
                Money.of(1000L),
                DurationTime.of(30L),
                Money.of(500L),
                DurationTime.of(10L)
        );
        PolicyUseCase.UpdatePolicyCommand updateCommand =
                new PolicyUseCase.UpdatePolicyCommand(
                        Money.of(2000L),
                        null,
                        null,
                        null
                );

        // when
        policyMapper.change(updateCommand, policy);

        // then
        assertEquals(2000L, policy.getBaseFee().getAmount());
    }

    @Test
    @DisplayName("기본 시간을 변경할 수 있다")
    void changeFreeTime() {
        // given
        PolicyEntity policy = new PolicyEntity(
                Money.of(1000L),
                DurationTime.of(30L),
                Money.of(500L),
                DurationTime.of(10L)
        );

        // when
        policy.changeFreeTime(DurationTime.of(60L));

        // then
        assertEquals(60L, policy.getFreeTime().getDuration());
    }

    @Test
    @DisplayName("추가 요금을 변경할 수 있다")
    void changeAdditionalFee() {
        // given
        PolicyEntity policy = new PolicyEntity(
                Money.of(1000L),
                DurationTime.of(30L),
                Money.of(500L),
                DurationTime.of(10L)
        );

        PolicyUseCase.UpdatePolicyCommand updateCommand =
                new PolicyUseCase.UpdatePolicyCommand(
                        null,
                        null,
                        Money.of(1000L),
                        null
                );

        // when
        policyMapper.change(updateCommand, policy);

        // then
        assertEquals(1000L, policy.getAdditionalFee().getAmount());
    }

    @Test
    @DisplayName("추가 시간을 변경할 수 있다")
    void changeAdditionalTime() {
        // given
        PolicyEntity policy = new PolicyEntity(
                Money.of(1000L),
                DurationTime.of(30L),
                Money.of(500L),
                DurationTime.of(10L)
        );

        // when
        policy.changeAdditionalTime(DurationTime.of(20L));

        // then
        assertEquals(20L, policy.getAdditionalTime().getDuration());
    }

    @Test
    @DisplayName("추가 시간이 1분 미만일 경우 예외가 발생한다")
    void validateAdditionalTime() {
        // when & then
        assertThrows(BusinessLogicException.class,
                () -> new PolicyEntity(
                        Money.of(1000L),
                        DurationTime.of(30L),
                        Money.of(500L),
                        DurationTime.of(0L)
                ));
    }

    @Test
    @DisplayName("추가 시간을 1분 미만으로 변경할 경우 예외가 발생한다")
    void validateChangeAdditionalTime() {
        // given
        PolicyEntity policy = new PolicyEntity(
                Money.of(1000L),
                DurationTime.of(30L),
                Money.of(500L),
                DurationTime.of(10L)
        );

        // when & then
        assertThrows(BusinessLogicException.class, () -> policy.changeAdditionalTime(DurationTime.of(0L)));
    }
}
