package com.parking.admin.domain.common;

import com.parking.admin.domain.common.exception.BusinessErrorCode;
import com.parking.admin.domain.common.exception.BusinessLogicException;
import lombok.Value;

@Value
public final class Money {
    private final Long amount;

    private Money(Long amount){
        if (amount < 0) throw new BusinessLogicException(BusinessErrorCode.NEGATIVE_AMOUNT);
        this.amount = amount;
    }

    public static Money of(Long amount){
        return new Money(amount);
    }

}
