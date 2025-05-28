package com.parking.admin.domain.policy;

import com.parking.admin.domain.common.Money;
import com.parking.admin.domain.common.exception.BusinessLogicException;
import com.parking.admin.domain.common.exception.ErrorCode;
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
            throw new BusinessLogicException(ErrorCode.ADDITIONAL_TIME_TOO_SHORT);
    }
}
