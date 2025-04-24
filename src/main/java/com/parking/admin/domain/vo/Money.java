package com.parking.admin.domain.vo;

import lombok.Getter;

@Getter
public final class Money {
    private final Long amount;

    public Money(Long amount){
        if (amount < 0) throw new IllegalArgumentException("금액은 음수일 수 없습니다.");
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;
        return amount.equals(money.amount);
    }
}
