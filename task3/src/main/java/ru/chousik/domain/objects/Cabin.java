package ru.chousik.domain.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.chousik.domain.enums.LightingLevel;
import ru.chousik.domain.enums.Size;

@Setter
@Getter
@AllArgsConstructor
public class Cabin {
    private String identifier;
    private Person occupant;
    private LightingLevel lightingLevel;
    private Size size;
}
