package ru.chousik.domain.objects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.chousik.domain.enums.Race;
import ru.chousik.domain.interfaces.Nervous;
import ru.chousik.domain.interfaces.Usable;
import ru.chousik.domain.objects.abstracts.Place;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Person implements Nervous {
    private String firstName;
    private String lastName;
    private List<Race> races;
    private List<Item> items;
    private Place correctPlace;
    private final Set<Race> knownRaces;
    private boolean nervous;

    @Builder
    public Person(String firstName, String lastName,
                  List<Race> races,
                  List<Item> items) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.races = races == null ? new ArrayList<>() : new ArrayList<>(races);
        this.items = items == null ? new ArrayList<>() : new ArrayList<>(items);
        this.knownRaces = EnumSet.noneOf(Race.class);
        validateCompatibility(this.races);
        initializeKnowledge();
    }

    public List<Race> getRaces() {
        return Collections.unmodifiableList(races);
    }

    public Set<Race> knownRacesView() {
        return Collections.unmodifiableSet(knownRaces);
    }

    public void addRace(Race race) {
        if (race != null) {
            ensureCompatible(race);
            this.races.add(race);
            registerKnowledgeFrom(race);
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

    public void useItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                if (item instanceof Usable) {
                    ((Usable) item).use();
                    System.out.printf("Вы использовали предмет %s", item.getName());
                } else {
                    throw new IllegalStateException("Вы не можете использовать этот предмет");
                }
                return;
            }
        }
        throw new IllegalArgumentException("Предмет с таким название не найден");
    }

    private void validateCompatibility(List<Race> races) {
        for (int i = 0; i < races.size(); i++) {
            for (int j = i + 1; j < races.size(); j++) {
                Race first = races.get(i);
                Race second = races.get(j);
                if (!first.isCompatibleWith(second)) {
                    throw new IllegalArgumentException("Несовместимые расы: " + first + " и " + second);
                }
            }
        }
    }

    private void ensureCompatible(Race race) {
        for (Race existing : races) {
            if (!existing.isCompatibleWith(race)) {
                throw new IllegalArgumentException("Несовместимые расы: " + existing + " и " + race);
            }
        }
    }

    private void initializeKnowledge() {
        for (Race race : races) {
            registerKnowledgeFrom(race);
        }
    }

    private void registerKnowledgeFrom(Race race) {
        if (race == null) {
            return;
        }
        knownRaces.add(race);
        knownRaces.addAll(race.knownRacesView());
    }

    @Override
    public boolean isNervous() {
        return nervous;
    }

    @Override
    public void getNervous() {
        this.nervous = true;
        System.out.printf("%s %s нервничает.%n", firstName, lastName);
    }
}
