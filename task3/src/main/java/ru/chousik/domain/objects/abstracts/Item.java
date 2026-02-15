package ru.chousik.domain.objects.abstracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
abstract public class Item {
    private String name;
    private String description;
}
