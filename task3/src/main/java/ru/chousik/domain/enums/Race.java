package ru.chousik.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Расы/виды персонажей из «Автостопом по галактике».
 */
@Getter
@AllArgsConstructor
public enum Race {
    HUMAN("человек"),
    VOGON("вогон"),
    TRAALIAN("траллианец"),
    GOLGAFRINCHAMITE("голгафринчамец"),
    MAGRATHEAN("магратеец"),
    ANDROID("андроид"),
    UNKNOWN("неизвестная раса");

    private final String description;
}
