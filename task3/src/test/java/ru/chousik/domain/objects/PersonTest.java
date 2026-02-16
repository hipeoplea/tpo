package ru.chousik.domain.objects;

import org.junit.jupiter.api.Test;
import ru.chousik.domain.enums.LightingLevel;
import ru.chousik.domain.enums.Race;
import ru.chousik.domain.enums.Size;
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
                person.racesView().add(Race.VOGON));
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

        assertTrue(person.knownRacesView().contains(Race.HUMAN));
        assertTrue(person.knownRacesView().containsAll(Race.HUMAN.knownRacesView()));
    }

    @Test
    void useItemMakesPersonNervousWhenDangerousRaceNearby() {
        Person ford = Person.builder()
                .firstName("Ford")
                .lastName("Prefect")
                .races(List.of(Race.HUMAN))
                .build();
        Person vogon = Person.builder()
                .firstName("Prostetnic")
                .lastName("Jeltz")
                .races(List.of(Race.VOGON))
                .build();
        Cabin cabin = new Cabin("Cabin", ford, LightingLevel.DARK, Size.SMALL);
        ford.setCorrectPlace(cabin);
        vogon.setCorrectPlace(cabin);
        TestUsableItem match = new TestUsableItem("match", "Simple match");
        ford.addItem(match);

        ford.useItem("match");

        assertTrue(ford.isNervous());
    }

    @Test
    void useItemMakesPersonNervousWhenRaceUnknown() {
        Person ford = Person.builder()
                .firstName("Ford")
                .lastName("Prefect")
                .races(List.of(Race.HUMAN))
                .build();
        Person magrathean = Person.builder()
                .firstName("Slartibartfast")
                .lastName("Magrathean")
                .races(List.of(Race.MAGRATHEAN))
                .build();
        Cabin cabin = new Cabin("Cabin", ford, LightingLevel.DARK, Size.SMALL);
        ford.setCorrectPlace(cabin);
        magrathean.setCorrectPlace(cabin);
        TestUsableItem match = new TestUsableItem("match", "Simple match");
        ford.addItem(match);

        ford.useItem("match");

        assertTrue(ford.isNervous());
    }

    @Test
    void useItemKeepsCalmWithKnownSafeRaceNearby() {
        Person ford = Person.builder()
                .firstName("Ford")
                .lastName("Prefect")
                .races(List.of(Race.HUMAN))
                .build();
        Person human = Person.builder()
                .firstName("Arthur")
                .lastName("Dent")
                .races(List.of(Race.HUMAN))
                .build();
        Cabin cabin = new Cabin("Cabin", ford, LightingLevel.DARK, Size.SMALL);
        ford.setCorrectPlace(cabin);
        human.setCorrectPlace(cabin);
        TestUsableItem match = new TestUsableItem("match", "Simple match");
        ford.addItem(match);

        ford.useItem("match");

        assertFalse(ford.isNervous());
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
