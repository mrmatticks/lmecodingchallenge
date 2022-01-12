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
import java.util.List;
import java.util.Objects;

@Component
@Getter
@EqualsAndHashCode
@ToString
public class WorldState {

    @Nullable
    private Integer xSize;

    @Nullable
    private Integer ySize;

    private final List<Position> lostRobotsLastKnownLocations;

    public WorldState() {
        lostRobotsLastKnownLocations = new ArrayList<>();
    }

    public void initialize(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
    }

    @Nullable
    @Setter
    private Robot currentRobot;

    public void setRobotIsLost(Robot lostRobot) {
        this.lostRobotsLastKnownLocations.add(lostRobot.getPosition());
        this.currentRobot = this.currentRobot.toBuilder().isLost(true).build();
    }

    public String getPrintedState() {

        if (Objects.nonNull(this.currentRobot) && this.currentRobot.isLost()) {
            return String.format("%s %s %s LOST", this.currentRobot.getPosition().getX(),
                    this.currentRobot.getPosition().getY(),
                    this.currentRobot.getOrientation().getOrientationSymbol());
        }

        if (Objects.nonNull(this.currentRobot)) {
            return String.format("%s %s %s", this.currentRobot.getPosition().getX(),
                    this.currentRobot.getPosition().getY(),
                    this.currentRobot.getOrientation().getOrientationSymbol());
        }


        return null;
    }

}
