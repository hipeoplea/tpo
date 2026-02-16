package ru.chousik.domain.objects;

import org.junit.jupiter.api.Test;
import ru.chousik.domain.enums.Race;
import ru.chousik.domain.interfaces.Usable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void racesViewIsUnmodifiable() {
        Person person = Person.builder()
                .firstName("Ford")
                .lastName("Prefect")
                .build();

        person.addRace(Race.HUMAN);

        assertThrows(UnsupportedOperationException.class, () ->
                person.getRaces().add(Race.VOGON));
    }

    @Test
    void addItemAndUseUsableItem() {
        Person person = Person.builder()
                .firstName("Ford")
                .lastName("Prefect")
                .build();
        TestUsableItem match = new TestUsableItem("match", "Simple match");

        person.addItem(match);
        person.useItem("match");

        assertTrue(match.used);
    }

    @Test
    void useItemThrowsForNonUsable() {
        Person person = Person.builder()
                .firstName("Ford")
                .lastName("Prefect")
                .build();
        person.addItem(new Item("book", "Guide"));

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> person.useItem("book"));
        assertEquals("Вы не можете использовать этот предмет", exception.getMessage());
    }

    @Test
    void useItemThrowsWhenNotFound() {
        Person person = Person.builder()
                .firstName("Ford")
                .lastName("Prefect")
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> person.useItem("unknown"));
        assertEquals("Предмет с таким название не найден", exception.getMessage());
    }

    @Test
    void addRaceThrowsWhenIncompatible() {
        Person person = Person.builder()
                .firstName("Ford")
                .lastName("Prefect")
                .races(List.of(Race.HUMAN))
                .build();

        assertThrows(IllegalArgumentException.class, () -> person.addRace(Race.VOGON));
    }

    @Test
    void builderThrowsWhenInitialRacesIncompatible() {
        assertThrows(IllegalArgumentException.class, () -> Person.builder()
                .firstName("Ford")
                .lastName("Prefect")
                .races(List.of(Race.HUMAN, Race.VOGON))
                .build());
    }

    @Test
    void knownRacesPopulatedFromRaceKnowledge() {
        Person person = Person.builder()
                .firstName("Ford")
                .lastName("Prefect")
                .races(List.of(Race.HUMAN))
                .build();

        assertTrue(person.getKnownRaces().contains(Race.HUMAN));
        assertTrue(person.getKnownRaces().containsAll(Race.HUMAN.knownRacesView()));
    }

    private static class TestUsableItem extends Item implements Usable {
        private boolean used;

        private TestUsableItem(String name, String description) {
            super(name, description);
        }

        @Override
        public void use() {
            used = true;
        }
    }
}
