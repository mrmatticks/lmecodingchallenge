package co.uk.gerronematticks.lmecodingchallenge.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder=true)
public class Robot {

    private final Orientation orientation;
    private final Position position;
    private final boolean isLost;

}
