package com.parking.admin.domain.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Time {
    private final LocalDateTime time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Time t = (Time) o;
        return time.equals(t.time);
    }

    public static Time of(LocalDateTime time) {
        return new Time(time);
    }

    public static Time now() {
        return new Time(LocalDateTime.now());
    }

    /**
     *     Time a = Time.of(2012, 6, 30, 12, 00);
     *     Time b = Time.of(2012, 7, 1, 12, 00);
     *     a.isBefore(b) == true
     *     a.isBefore(a) == false
     *     b.isBefore(a) == false
     */
    public boolean isBefore(Time time) {
        return this.time.isBefore(time.time);
    }

    /**
     *     Time a = Time.of(2012, 6, 30, 12, 00);
     *     Time b = Time.of(2012, 7, 1, 12, 00);
     *     a.isAfter(b) == false
     *     a.isAfter(a) == false
     *     b.isAfter(a) == true
     */
    public boolean isAfter(Time time) {
        return this.time.isAfter(time.time);
    }
}
