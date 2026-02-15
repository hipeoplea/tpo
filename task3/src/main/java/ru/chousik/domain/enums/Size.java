package ru.chousik.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Size {
    SMALL("маленькой"),
    MEDIUM("средней"),
    LARGE("большой");

    private final String description;
}
