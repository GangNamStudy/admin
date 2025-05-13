package com.parking.admin.entity;

import com.parking.admin.application.command.CreatePolicyCommand;
import com.parking.admin.application.command.UpdatePolicyCommand;
import com.parking.admin.application.mapper.PolicyMapper;
import com.parking.admin.domain.entity.PolicyEntity;
import com.parking.admin.domain.vo.DurationTime;
import com.parking.admin.domain.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PolicyEntityTest {
    
    @Autowired
    private PolicyMapper policyMapper;

    @Test
    @DisplayName("주차 정책 생성 시 모든 필드가 정상적으로 설정된다")
    void createPolicy() {
        // given
        CreatePolicyCommand command = CreatePolicyCommand.builder()
                .baseFee(1000L)
                .freeTime(30L)
                .additionalFee(500L)
                .additionalTime(10L)
                .build();

        // when
        PolicyEntity policy = policyMapper.toEntity(command);

        // then
        assertEquals(1000L, policy.getBaseFee().getAmount());
        assertEquals(30L, policy.getFreeTime().getDuration());
        assertEquals(500L, policy.getAdditionalFee().getAmount());
        assertEquals(10L, policy.getAdditionalTime().getDuration());
    }

    @Test
    @DisplayName("기본 요금을 변경할 수 있다")
    void changeBaseFee() {
        // given
        CreatePolicyCommand command = CreatePolicyCommand.builder()
                .baseFee(1000L)
                .freeTime(30L)
                .additionalFee(500L)
                .additionalTime(10L)
                .build();

        PolicyEntity policy = policyMapper.toEntity(command);
        UpdatePolicyCommand updateCommand = UpdatePolicyCommand.builder()
                .baseFee(2000L)
                .build();

        // when
        policyMapper.change(updateCommand, policy);

        // then
        assertEquals(2000L, policy.getBaseFee().getAmount());
    }

    @Test
    @DisplayName("기본 시간을 변경할 수 있다")
    void changeFreeTime() {
        // given
        CreatePolicyCommand command = CreatePolicyCommand.builder()
                .baseFee(1000L)
                .freeTime(30L)
                .additionalFee(500L)
                .additionalTime(10L)
                .build();

        PolicyEntity policy = policyMapper.toEntity(command);
        UpdatePolicyCommand updateCommand = UpdatePolicyCommand.builder()
                .freeTime(60L)
                .build();

        // when
        policyMapper.change(updateCommand, policy);

        // then
        assertEquals(60L, policy.getFreeTime().getDuration());
    }

    @Test
    @DisplayName("추가 요금을 변경할 수 있다")
    void changeAdditionalFee() {
        // given
        CreatePolicyCommand command = CreatePolicyCommand.builder()
                .baseFee(1000L)
                .freeTime(30L)
                .additionalFee(500L)
                .additionalTime(10L)
                .build();

        PolicyEntity policy = policyMapper.toEntity(command);
        UpdatePolicyCommand updateCommand = UpdatePolicyCommand.builder()
                .additionalFee(1000L)
                .build();

        // when
        policyMapper.change(updateCommand, policy);

        // then
        assertEquals(1000L, policy.getAdditionalFee().getAmount());
    }

    @Test
    @DisplayName("추가 시간을 변경할 수 있다")
    void changeAdditionalTime() {
        // given
        CreatePolicyCommand command = CreatePolicyCommand.builder()
                .baseFee(1000L)
                .freeTime(30L)
                .additionalFee(500L)
                .additionalTime(10L)
                .build();

        PolicyEntity policy = policyMapper.toEntity(command);
        UpdatePolicyCommand updateCommand = UpdatePolicyCommand.builder()
                .additionalTime(20L)
                .build();

        // when
        policyMapper.change(updateCommand, policy);

        // then
        assertEquals(20L, policy.getAdditionalTime().getDuration());
    }

    @Test
    @DisplayName("추가 시간이 1분 미만일 경우 예외가 발생한다")
    void validateAdditionalTime() {
        
        // given
        CreatePolicyCommand command = CreatePolicyCommand.builder()
                .baseFee(1000L)
                .freeTime(30L)
                .additionalFee(500L)
                .additionalTime(0L)
                .build();

        // when & then
        assertThrows(IllegalArgumentException.class, () -> policyMapper.toEntity(command));
    }

    @Test
    @DisplayName("추가 시간을 1분 미만으로 변경할 경우 예외가 발생한다")
    void validateChangeAdditionalTime() {
        // given
        CreatePolicyCommand command = CreatePolicyCommand.builder()
                .baseFee(1000L)
                .freeTime(30L)
                .additionalFee(500L)
                .additionalTime(10L)
                .build();

        PolicyEntity policy = policyMapper.toEntity(command);
        UpdatePolicyCommand updateCommand = UpdatePolicyCommand.builder()
                .additionalTime(0L)
                .build();

        // when & then
        assertThrows(IllegalArgumentException.class, () -> policyMapper.change(updateCommand, policy));
    }
}
