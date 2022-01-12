package co.uk.gerronematticks.lmecodingchallenge.instructions;

import co.uk.gerronematticks.lmecodingchallenge.model.Orientation;
import co.uk.gerronematticks.lmecodingchallenge.model.Position;
import co.uk.gerronematticks.lmecodingchallenge.model.Robot;
import co.uk.gerronematticks.lmecodingchallenge.state.WorldState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Component
@Qualifier("setUpRobotPositionInstruction")
public class SetUpRobotPositionInstruction implements Instruction {

    private final Collection<Instruction> allowedNextInstruction;
    private final WorldState worldState;

    @Autowired
    public SetUpRobotPositionInstruction(@Lazy @Qualifier("moveRobotInstruction") Instruction instruction,
                                         WorldState worldState) {
        this.allowedNextInstruction = Collections.singletonList(instruction);
        this.worldState = worldState;
    }

    @Override
    public boolean matchesInstruction(String input) {
        boolean matches = input.matches("[0-9]+ [0-9]+ ([NSEW])");

        if (!matches) {
            return false;
        }

        return checkIfWithinRange(input);
    }

    @Override
    public Optional<String> performInstruction(String input) {
        String[] split = input.split(" ");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);

        validateRobotPosition(x, y);
        Orientation orientation = Orientation.getOrientation(split[2]);

        this.worldState.setCurrentRobot(new Robot(orientation, new Position(x, y), false));

        return Optional.empty();
    }

    private void validateRobotPosition(int x, int y) {
        if (Objects.isNull(this.worldState.getXSize()) || Objects.isNull(this.worldState.getYSize())) {
            throw new IllegalStateException("World state is not set-up yet.");
        }

        if (x > this.worldState.getXSize()) {
            throw new IllegalArgumentException(String.format("Robot position X:%d is greater than the space (x,y) : (%d,%d)",
                    x, this.worldState.getXSize(), this.worldState.getYSize()));
        }

        if (y > this.worldState.getYSize()) {
            throw new IllegalArgumentException(String.format("Robot position y:%d is greater than the space (x,y) : (%d,%d)",
                    y, this.worldState.getXSize(), this.worldState.getYSize()));
        }
    }

    @Override
    public Collection<Instruction> allowedNextInstructions() {
        return allowedNextInstruction;
    }

    private boolean checkIfWithinRange(String input) {
        String[] split = input.split(" ");
        return Integer.parseInt(split[0]) <= 50 && Integer.parseInt(split[1]) <= 50;
    }

}
