package ru.chousik.domain.objects.abstracts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class Place {
    private final String name;
}
