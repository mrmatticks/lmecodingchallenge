package co.uk.gerronematticks.lmecodingchallenge.model;

import java.util.Arrays;

import static java.util.Arrays.stream;

public enum Orientation {
    NORTH("N"), SOUTH("S"), EAST("E"), WEST("W");

    private final String orientationSymbol;

    Orientation(String orientationSymbol) {
        this.orientationSymbol = orientationSymbol;
    }

    public static Orientation getOrientation(String orientationSymbol) {
        return stream(Orientation.values()).filter(it -> orientationSymbol.equals(it.orientationSymbol))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No orientation matching: " + orientationSymbol));
    }

}
