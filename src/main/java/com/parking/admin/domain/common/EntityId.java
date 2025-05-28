package com.parking.admin.domain.common;

import lombok.Getter;

import java.util.UUID;

@Getter
public final class EntityId {
    private final String value;
    private final IdType originalType;

    private EntityId(String value, IdType originalType) {
        this.value = value;
        this.originalType = originalType;
    }

    //팩토리 메서드
    public static EntityId of(Long id) {
        return new EntityId(id.toString(), IdType.LONG);
    }

    public static EntityId of(UUID id) {
        return new EntityId(id.toString(), IdType.UUID);
    }

    public static EntityId of(String id, IdType type) {
        return new EntityId(id, type);
    }

    // 원래 타입으로 변환 메서드
    public Long toLong() {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Cannot convert ID to Long: " + value);
        }
    }

    public UUID toUUID() {
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Cannot convert ID to UUID: " + value);
        }
    }

    public enum IdType{
        LONG,UUID,STRING
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityId domainId = (EntityId) o;
        return value.equals(domainId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
