package com.parking.admin.domain.common;

import com.parking.admin.domain.common.exception.BusinessLogicException;
import com.parking.admin.domain.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public final class Money {
    private final Long amount;

    private Money(Long amount){
        if (amount < 0) throw new BusinessLogicException(ErrorCode.NEGATIVE_AMOUNT);
        this.amount = amount;
    }

    public static Money of(Long amount){
        return new Money(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;
        return amount.equals(money.amount);
    }
}
