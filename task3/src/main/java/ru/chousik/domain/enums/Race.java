package ru.chousik.domain.enums;

import lombok.Getter;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@Getter
public enum Race {
    HUMAN(false),
    VOGON(true),
    TRAALIAN(true),
    GOLGAFRINCHAMITE(false),
    MAGRATHEAN(false),
    ANDROID(false),
    UNKNOWN(false);

    private final Set<Race> incompatibleRaces;
    private final Set<Race> knownRaces;
    private final boolean dangerous;

    Race(boolean dangerous) {
        this.incompatibleRaces = new HashSet<>();
        this.knownRaces = new HashSet<>();
        this.dangerous = dangerous;
    }

    static {
        HUMAN.incompatibleRaces.add(VOGON);
        VOGON.incompatibleRaces.add(HUMAN);
        MAGRATHEAN.incompatibleRaces.add(ANDROID);
        ANDROID.incompatibleRaces.add(MAGRATHEAN);

        HUMAN.knownRaces.addAll(EnumSet.of(VOGON, TRAALIAN));
        VOGON.knownRaces.addAll(EnumSet.of(HUMAN, TRAALIAN));
        TRAALIAN.knownRaces.addAll(EnumSet.of(HUMAN, GOLGAFRINCHAMITE));
        GOLGAFRINCHAMITE.knownRaces.addAll(EnumSet.of(HUMAN));
        MAGRATHEAN.knownRaces.addAll(EnumSet.of(HUMAN, ANDROID));
        ANDROID.knownRaces.addAll(EnumSet.of(HUMAN, MAGRATHEAN));
        UNKNOWN.knownRaces.addAll(EnumSet.of(HUMAN, VOGON, TRAALIAN, GOLGAFRINCHAMITE, MAGRATHEAN, ANDROID));
    }

    public Set<Race> knownRacesView() {
        return Collections.unmodifiableSet(knownRaces);
    }

    public boolean isCompatibleWith(Race other) {
        if (other == null) {
            return true;
        }
        return !incompatibleRaces.contains(other) && !other.incompatibleRaces.contains(this);
    }
}
