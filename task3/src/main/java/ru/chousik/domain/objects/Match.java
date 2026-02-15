package ru.chousik.domain.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.chousik.domain.enums.LightingLevel;
import ru.chousik.domain.enums.MatchState;
import ru.chousik.domain.interfaces.BrightnessAdjustable;
import ru.chousik.domain.interfaces.Usable;
import ru.chousik.domain.objects.abstracts.Item;
import ru.chousik.domain.objects.abstracts.Place;

@Getter
@Setter
@ToString
public class Match extends Item implements Usable {
    private MatchState state = MatchState.UNLIT;

    public Match(String name, String description, Person owner) {
        super(name, description, owner);
    }

    @Override
    public void use() {
        this.state = MatchState.LIT;
        System.out.println("Спичка зажжена.");
        if (getOwner().getCorrectPlace() != null){
            Place place = getOwner().getCorrectPlace();
            if (place instanceof BrightnessAdjustable adjustable){
                int lvl = adjustable.getCorrectBrightness().getIntensity();
                lvl++;
                LightingLevel newLevel = LightingLevel.fromIntensity(lvl);
                if (newLevel == null){
                    newLevel = LightingLevel.getMaxLightingLevel();
                }
                adjustable.adjustBrightness(newLevel);
            }
        }
    }
}
