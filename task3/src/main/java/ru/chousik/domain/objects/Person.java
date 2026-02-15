package ru.chousik.domain.objects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.chousik.domain.enums.Race;
import ru.chousik.domain.interfaces.Usable;
import ru.chousik.domain.objects.abstracts.Place;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString
public class Person {
    private String firstName;
    private String lastName;
    private List<Race> races;
    private List<Item> items;
    private Place correctPlace;

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

    public void useItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)){
                if (item instanceof Usable){
                    ((Usable) item).use();
                    System.out.printf("Вы использовали предмет %s", item.getName());
                } else {
                    System.out.println("Вы не можете использовать этот предмет");
                }
                return;
            }
        }
        System.out.println("Предмет с таким название не найден");
    }
}
