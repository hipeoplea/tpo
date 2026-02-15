package ru.chousik.domain.interfaces;

import ru.chousik.domain.enums.LightingLevel;

public interface BrightnessAdjustable {
    LightingLevel getCorrectBrightness();
    void adjustBrightness(LightingLevel newLightingLevel);
}
