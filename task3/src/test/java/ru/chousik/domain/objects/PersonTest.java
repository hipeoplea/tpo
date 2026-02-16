package ru.chousik.domain.objects;

import org.junit.jupiter.api.Test;
import ru.chousik.domain.enums.Race;
import ru.chousik.domain.interfaces.Usable;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    void useItemPrintsMessageForNonUsable() {
        Person person = Person.builder()
                .firstName("Ford")
                .lastName("Prefect")
                .build();
        person.addItem(new Item("book", "Guide"));

        String output = captureStdOut(() -> person.useItem("book"));

        assertTrue(output.contains("Вы не можете использовать этот предмет"));
    }

    @Test
    void useItemPrintsMessageWhenNotFound() {
        Person person = Person.builder()
                .firstName("Ford")
                .lastName("Prefect")
                .build();

        String output = captureStdOut(() -> person.useItem("unknown"));

        assertTrue(output.contains("Предмет с таким название не найден"));
    }

    private String captureStdOut(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            action.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString();
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
