package ru.chousik.domain.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.chousik.domain.enums.MatchState;
import ru.chousik.domain.interfaces.Usable;
import ru.chousik.domain.objects.abstracts.Item;

@Getter
@Setter
@ToString
public class Match extends Item implements Usable {
    private MatchState state = MatchState.UNLIT;

    public Match(String name, String description) {
        super(name, description);
    }

    @Override
    public void use() {
        this.state = MatchState.LIT;
        System.out.println("Спичка зажжена.");
    }
}
