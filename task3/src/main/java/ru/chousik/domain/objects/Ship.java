package ru.chousik.domain.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Ship {
    private String name;
    private final List<Cabin> cabins;
}
