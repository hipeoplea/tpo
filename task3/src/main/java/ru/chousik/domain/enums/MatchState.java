package ru.chousik.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MatchState {
    LIT("зажжена"),
    UNLIT("не зажжена");

    private final String description;
}
