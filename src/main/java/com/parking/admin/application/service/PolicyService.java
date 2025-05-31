package com.parking.admin.application.service;

import com.parking.admin.application.mapper.PolicyMapper;
import com.parking.admin.application.port.in.PolicyUseCase;
import com.parking.admin.application.port.out.PolicyPort;
import com.parking.admin.common.UseCase;
import com.parking.admin.domain.policy.PolicyEntity;
import com.parking.admin.domain.policy.PolicyInfo;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class PolicyService implements PolicyUseCase {

    private final PolicyPort policyPort;
    private final PolicyMapper policyMapper;

    @Override
    public PolicyInfo updatePolicy(UpdatePolicyCommand command) {
        PolicyEntity entity = policyPort.load();
        policyMapper.change(command, entity);
        return policyPort.save(entity);
    }

    @Override
    public PolicyInfo getPolicy() {
        PolicyEntity entity = policyPort.load();
        return entity.toInfo();
    }
}
