package com.parking.admin.application.mapper;

import com.parking.admin.application.command.CreatePolicyCommand;
import com.parking.admin.application.command.UpdatePolicyCommand;
import com.parking.admin.domain.entity.PolicyEntity;
import com.parking.admin.domain.vo.DurationTime;
import com.parking.admin.domain.vo.Money;
import org.springframework.stereotype.Component;

@Component
public class PolicyMapper {
    public PolicyEntity toEntity(CreatePolicyCommand command){
        return new PolicyEntity(
                Money.of(command.getBaseFee()),
                DurationTime.of(command.getFreeTime()),
                Money.of(command.getAdditionalFee()),
                DurationTime.of(command.getAdditionalTime())
        );
    }

    public void change(UpdatePolicyCommand command, PolicyEntity entity){
        if (command.getBaseFee() != null)
            entity.changeBaseFee(Money.of(command.getBaseFee()));

        if (command.getFreeTime() != null)
            entity.changeFreeTime(DurationTime.of(command.getFreeTime()));

        if (command.getAdditionalFee() != null)
            entity.changeAdditionalFee(Money.of(command.getAdditionalFee()));

        if (command.getAdditionalTime() != null)
            entity.changeAdditionalTime(DurationTime.of(command.getAdditionalTime()));
    }
}
