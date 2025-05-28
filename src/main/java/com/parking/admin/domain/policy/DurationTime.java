package com.parking.admin.domain.policy;

import com.parking.admin.domain.common.exception.BusinessLogicException;
import com.parking.admin.domain.common.exception.ErrorCode;
import lombok.Getter;

/**
 * 시점(예: 2024년 12월 23일 11시 10분 31초)이 아닌
 * 기간(30분, 1시간) 을 의미하는 VO
**/
@Getter
public final class DurationTime {
    private final Long duration;

    private DurationTime(Long duration){
        if (duration < 0) throw new BusinessLogicException(ErrorCode.NEGATIVE_DURATION);
        this.duration = duration;
    }

    public static DurationTime of(Long duration){
        return new DurationTime(duration);
    }
}
