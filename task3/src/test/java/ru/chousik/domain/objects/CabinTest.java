package ru.chousik.domain.objects;

import org.junit.jupiter.api.Test;
import ru.chousik.domain.enums.LightingLevel;
import ru.chousik.domain.enums.Size;

import static org.junit.jupiter.api.Assertions.*;

class CabinTest {

    @Test
    void adjustBrightnessUpdatesState() {
        Person occupant = Person.builder().firstName("Ford").lastName("Prefect").build();
        Cabin cabin = new Cabin("Cabin", occupant, LightingLevel.DARK, Size.SMALL);

        assertEquals(LightingLevel.DARK, cabin.getCorrectBrightness());
        assertEquals("Cabin", cabin.getName());

        cabin.adjustBrightness(LightingLevel.BRIGHT);

        assertEquals(LightingLevel.BRIGHT, cabin.getLightingLevel());
    }
}
