package ru.chousik.domain.objects;

import org.junit.jupiter.api.Test;
import ru.chousik.domain.enums.Race;
import ru.chousik.domain.interfaces.Usable;

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
    }

    @Test
    void useItemThrowsWhenNotFound() {
        Person person = Person.builder()
                .firstName("Ford")
                .lastName("Prefect")
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> person.useItem("unknown"));
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
