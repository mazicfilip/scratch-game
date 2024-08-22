package com.scratchgame.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ImpactType {
    MULTIPLY_REWARD,
    EXTRA_BONUS,
    MISS;

    @JsonCreator
    public static ImpactType fromString(String key) {
        return key == null ? null : ImpactType.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }
}
