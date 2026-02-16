package ru.chousik.domain.objects.abstracts;

import lombok.Getter;
import ru.chousik.domain.objects.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public abstract class Place {
    private final String name;
    private final List<Person> persons;

    public Place(String name) {
        this.name = name;
        this.persons = new ArrayList<>();
    }

    public List<Person> personsView() {
        return Collections.unmodifiableList(persons);
    }

    public void enter(Person person) {
        if (person != null && !persons.contains(person)) {
            persons.add(person);
        }
    }

    public void leave(Person person) {
        persons.remove(person);
    }
}
