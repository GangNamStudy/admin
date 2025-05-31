package com.parking.admin.application.port.out;

import com.parking.admin.domain.policy.PolicyEntity;
import com.parking.admin.domain.policy.PolicyInfo;

public interface PolicyPort {
    PolicyEntity load();
    PolicyInfo save(PolicyEntity entity);
}
