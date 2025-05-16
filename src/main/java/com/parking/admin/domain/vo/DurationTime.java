package com.parking.admin.domain.vo;

import lombok.Getter;

/**
 * 시점(예: 2024년 12월 23일 11시 10분 31초)이 아닌
 * 기간(30분, 1시간) 을 의미하는 VO
**/
@Getter
public final class DurationTime {
    private final Long duration;

    private DurationTime(Long duration){
        if (duration < 0) throw new IllegalArgumentException("시간(분)은 음수일 수 없습니다.");
        this.duration = duration;
    }

    public static DurationTime of(Long duration){
        return new DurationTime(duration);
    }
}
