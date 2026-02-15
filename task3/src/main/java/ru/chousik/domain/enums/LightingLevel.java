package ru.chousik.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LightingLevel {
    DARK("темно"),
    DIM("тускло"),
    NORMAL("обычно"),
    BRIGHT("ярко");

    private final String description;
}
