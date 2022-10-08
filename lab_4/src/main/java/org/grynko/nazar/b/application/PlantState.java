package org.grynko.nazar.b.application;

public enum PlantState {

    WATERED,
    DRY;

    public String toString() {
        return this == WATERED ? "W" : "D";
    }

}
