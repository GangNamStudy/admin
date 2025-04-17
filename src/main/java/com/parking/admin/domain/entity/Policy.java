package com.parking.admin.domain.entity;

import com.parking.admin.application.command.CreatePolicyCommand;
import com.parking.admin.application.command.UpdatePolicyCommand;
import com.parking.admin.domain.vo.DurationTime;
import com.parking.admin.domain.vo.Money;
import lombok.Getter;

@Getter
public class Policy {
    private Money baseFee;
    private DurationTime freeTime;
    private Money additionalFee;
    private DurationTime additionalTime;

    public Policy(CreatePolicyCommand command) {
        this.baseFee = command.getBaseFee();
        this.freeTime = command.getFreeTime();
        this.additionalFee = command.getAdditionalFee();
        this.additionalTime = command.getAdditionalTime();
    }

    public void change(UpdatePolicyCommand command) {
        if (command.hasBaseFee()) this.baseFee = command.getBaseFee();
        if (command.hasAdditionalFee()) this.additionalFee = command.getAdditionalFee();
        if (command.hasFreeTime()) this.freeTime = command.getFreeTime();
        if (command.hasAdditionalTime()) this.additionalTime = command.getAdditionalTime();
    }
}
