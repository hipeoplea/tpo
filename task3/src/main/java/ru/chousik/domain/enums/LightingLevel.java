package ru.chousik.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LightingLevel {
    DARK(1),
    DIM(2),
    NORMAL(3),
    BRIGHT(4);

    private final int intensity;

    public static LightingLevel getMaxLightingLevel() {
        return LightingLevel.BRIGHT;
    }

    public static LightingLevel fromIntensity(int intensity) {
        for (LightingLevel level : values()) {
            if (level.intensity == intensity) {
                return level;
            }
        }
        return null;
    }
}
