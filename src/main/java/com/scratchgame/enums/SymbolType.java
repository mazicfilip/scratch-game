package com.scratchgame.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SymbolType {
    STANDARD,
    BONUS;

    @JsonCreator
    public static SymbolType fromString(String key) {
        return key == null ? null : SymbolType.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }
}
