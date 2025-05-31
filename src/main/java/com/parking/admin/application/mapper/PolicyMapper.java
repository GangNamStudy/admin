package com.parking.admin.application.mapper;

import com.parking.admin.application.port.in.PolicyUseCase;
import com.parking.admin.domain.policy.PolicyEntity;
import org.springframework.stereotype.Component;

@Component
public class PolicyMapper {

    public void change(PolicyUseCase.UpdatePolicyCommand command, PolicyEntity entity){
        if (command.getBaseFee() != null)
            entity.changeBaseFee(command.getBaseFee());

        if (command.getFreeTime() != null)
            entity.changeFreeTime(command.getFreeTime());

        if (command.getAdditionalFee() != null)
            entity.changeAdditionalFee(command.getAdditionalFee());

        if (command.getAdditionalTime() != null)
            entity.changeAdditionalTime(command.getAdditionalTime());
    }
}
