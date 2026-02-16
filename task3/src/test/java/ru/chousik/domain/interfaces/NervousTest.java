package ru.chousik.domain.interfaces;

import org.junit.jupiter.api.Test;
import ru.chousik.domain.objects.Person;

import static org.junit.jupiter.api.Assertions.*;

class NervousTest {

    @Test
    void personBecomesNervous() {
        Person person = Person.builder()
                .firstName("Ford")
                .lastName("Prefect")
                .build();

        assertFalse(person.isNervous());
        person.getNervous();
        assertTrue(person.isNervous());
    }
}
