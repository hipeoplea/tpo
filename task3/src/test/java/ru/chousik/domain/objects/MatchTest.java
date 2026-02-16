package ru.chousik.domain.objects;

import org.junit.jupiter.api.Test;
import ru.chousik.domain.enums.LightingLevel;
import ru.chousik.domain.enums.MatchState;
import ru.chousik.domain.enums.Size;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    void useLightsMatchAndIncreasesBrightness() {
        Person owner = Person.builder().firstName("Ford").lastName("Prefect").build();
        Cabin cabin = new Cabin("Cabin", owner, LightingLevel.DARK, Size.SMALL);
        owner.setCorrectPlace(cabin);
        Match match = new Match("match", "Simple match", owner);

        match.use();

        assertEquals(MatchState.LIT, match.getState());
        assertEquals(LightingLevel.DIM, cabin.getLightingLevel());
    }

    @Test
    void useKeepsBrightnessAtMaxLevel() {
        Person owner = Person.builder().firstName("Ford").lastName("Prefect").build();
        Cabin cabin = new Cabin("Cabin", owner, LightingLevel.BRIGHT, Size.SMALL);
        owner.setCorrectPlace(cabin);
        Match match = new Match("match", "Simple match", owner);

        match.use();

        assertEquals(LightingLevel.BRIGHT, cabin.getLightingLevel());
    }

    @Test
    void useWithoutAssignedPlaceOnlyChangesState() {
        Person owner = Person.builder().firstName("Ford").lastName("Prefect").build();
        Match match = new Match("match", "Simple match", owner);

        match.use();

        assertEquals(MatchState.LIT, match.getState());
        assertNull(owner.getCorrectPlace());
    }
}
