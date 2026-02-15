package ru.chousik.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Grade {
    BUDGET("бюджетный"),
    STANDARD("средний"),
    FLAGSHIP("флагманский");

    private final String description;
}
