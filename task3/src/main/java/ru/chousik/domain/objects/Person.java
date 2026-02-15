package ru.chousik.domain.objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import lombok.*;
import ru.chousik.domain.enums.Race;

@Getter
@Setter
@ToString
public class Person {
    private String firstName;
    private String lastName;
    private List<Race> races;
    private List<Item> items;

    @Builder
    public Person(String firstName, String lastName,
                  List<Race> races,
                  List<Item> items) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.races = races == null ? new ArrayList<>() : races;
        this.items = items == null ? new ArrayList<>() : items;
    }

    public List<Race> racesView() {
        return Collections.unmodifiableList(races);
    }

    public void addRace(Race race) {
        if (race != null) {
            this.races.add(race);
        }
    }

    public List<Item> itemsView() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(Item item) {
        if (item != null) {
            this.items.add(item);
        }
    }
}
