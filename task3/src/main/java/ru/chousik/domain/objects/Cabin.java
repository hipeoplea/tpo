package ru.chousik.domain.objects;

import lombok.Getter;
import lombok.ToString;
import ru.chousik.domain.enums.LightingLevel;
import ru.chousik.domain.enums.Size;
import ru.chousik.domain.interfaces.BrightnessAdjustable;
import ru.chousik.domain.objects.abstracts.Place;

@Getter
@ToString(callSuper = true)
public class Cabin extends Place implements BrightnessAdjustable {
    private final Person occupant;
    private LightingLevel lightingLevel;
    private final Size size;

    public Cabin(String name, Person occupant, LightingLevel lightingLevel, Size size) {
        super(name);
        this.occupant = occupant;
        this.lightingLevel = lightingLevel;
        this.size = size;
    }

    @Override
    public LightingLevel getCorrectBrightness() {
        return lightingLevel;
    }

    @Override
    public void adjustBrightness(LightingLevel newLightingLevel) {
        this.lightingLevel = newLightingLevel;
    }
}
