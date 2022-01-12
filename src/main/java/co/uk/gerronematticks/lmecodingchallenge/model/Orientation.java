package co.uk.gerronematticks.lmecodingchallenge.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.Arrays.stream;

public enum Orientation {
    WEST("W", pos -> new Position(pos.getX() - 1, pos.getY())),
    NORTH("N", pos -> new Position(pos.getX(), pos.getY() + 1)),
    EAST("E", pos -> new Position(pos.getX() + 1, pos.getY())),
    SOUTH("S", pos -> new Position(pos.getX(), pos.getY() - 1));

    @Getter
    private final String orientationSymbol;
    private final Function<Position, Position> forwardFunction;

    Orientation(String orientationSymbol,
                Function<Position, Position> forwardFunction) {

        this.orientationSymbol = orientationSymbol;
        this.forwardFunction = forwardFunction;
    }

    public static Orientation getOrientation(String orientationSymbol) {
        return stream(Orientation.values()).filter(it -> orientationSymbol.equals(it.orientationSymbol))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No orientation matching: " + orientationSymbol));
    }

    public Position getForwardPosition(Position position) {
        return this.forwardFunction.apply(position);
    }

    public Orientation getLeft() {
        return get(-1);
    }

    public Orientation getRight() {
        return get(1);
    }

    public Orientation get(int indexChange) {
        List<Orientation> orientations = Arrays.asList(Orientation.values());
        int newIndex = orientations.indexOf(this) + indexChange;

        if (newIndex < 0) {
            return orientations.get(newIndex + orientations.size());
        }

        return orientations.get(newIndex % orientations.size());
    }

}
