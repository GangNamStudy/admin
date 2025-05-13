package com.parking.admin.domain.entity;

import com.parking.admin.domain.vo.DurationTime;
import com.parking.admin.domain.vo.Money;
import lombok.Getter;

@Getter
public class PolicyEntity {
    private Money baseFee;
    private DurationTime freeTime;
    private Money additionalFee;
    private DurationTime additionalTime;

    public PolicyEntity(Money baseFee, DurationTime freeTime, Money additionalFee, DurationTime additionalTime) {
        this.baseFee = baseFee;
        this.freeTime = freeTime;
        this.additionalFee = additionalFee;
        this.additionalTime = additionalTime;
        validate();
    }

    public void changeBaseFee(Money baseFee) {
        this.baseFee = baseFee;
    }

    public void changeFreeTime(DurationTime freeTime) {
        this.freeTime = freeTime;
    }

    public void changeAdditionalFee(Money additionalFee) {
        this.additionalFee = additionalFee;
    }

    public void changeAdditionalTime(DurationTime additionalTime) {
        this.additionalTime = additionalTime;
        validateAdditionalTime();
    }

    private void validate(){
        validateAdditionalTime();
    }

    private void validateAdditionalTime(){
        if(this.additionalTime.getDuration() < 1L)
            throw new IllegalArgumentException("추가 요금 시간은 1분 이상이어야 합니다");
    }
}
