package co.uk.gerronematticks.lmecodingchallenge.state;

import co.uk.gerronematticks.lmecodingchallenge.model.Position;
import co.uk.gerronematticks.lmecodingchallenge.model.Robot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
@Getter
@EqualsAndHashCode
@ToString
public class WorldState {

    @Nullable
    private Integer xSize;

    @Nullable
    private Integer ySize;

    private final Collection<Position> lostRobotPositions;

    public WorldState() {
        lostRobotPositions = new ArrayList<>();
    }

    public void initialize(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
    }

    @Nullable
    @Setter
    private Robot currentRobot;
}
