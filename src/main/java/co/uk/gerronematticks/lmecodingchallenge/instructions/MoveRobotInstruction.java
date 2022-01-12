package co.uk.gerronematticks.lmecodingchallenge.instructions;

import co.uk.gerronematticks.lmecodingchallenge.model.Position;
import co.uk.gerronematticks.lmecodingchallenge.model.Robot;
import co.uk.gerronematticks.lmecodingchallenge.state.WorldState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Component
@Qualifier("moveRobotInstruction")
public class MoveRobotInstruction implements Instruction {

    private final Collection<Instruction> allowedNextInstruction;
    private final WorldState worldState;

    @Autowired
    public MoveRobotInstruction(@Lazy @Qualifier("blankLineInstruction") Instruction blankLineInstruction,
                                @Lazy @Qualifier("setUpRobotPositionInstruction") Instruction setUpRobotPositionInstruction,
                                WorldState worldState) {
        this.allowedNextInstruction = Arrays.asList(blankLineInstruction, setUpRobotPositionInstruction);
        this.worldState = worldState;
    }

    @Override
    public boolean matchesInstruction(String input) {
        return input.matches("([LRF])+");
    }

    @Override
    public String performInstruction(String input) {
        if (Objects.isNull(this.worldState.getXSize()) || Objects.isNull(this.worldState.getYSize())) {
            throw new IllegalStateException("World state is not set-up yet.");
        }

        if (this.worldState.getCurrentRobot() == null) {
            throw new IllegalStateException("Robot position is not set-up yet.");
        }

        for (char action : input.toCharArray()) {
            Robot robotBeforeAction = this.worldState.getCurrentRobot();

            handleAction(action);

            if (isOutOfBounds(this.worldState.getCurrentRobot().getPosition())) {
                this.worldState.setRobotIsLost(robotBeforeAction);
            }
        }

        return this.worldState.getPrintedState();
    }

    @Override
    public Collection<Instruction> allowedNextInstructions() {
        return this.allowedNextInstruction;
    }

    private boolean isOutOfBounds(Position position) {
        return (position.getX() > this.worldState.getXSize()
                || position.getY() > this.worldState.getYSize()
                || position.getX() < 0
                || position.getY() < 0);
    }

    private boolean isCurrentPositionKnownLostLocation(Position position) {
        return this.worldState.getLostRobotsLastKnownLocations().contains(position);
    }


    private void handleAction(char action) {
        Robot currentRobot = this.worldState.getCurrentRobot();
        Position currentRobotPosition = currentRobot.getPosition();

        switch (action) {
            case 'L':
                this.worldState.setCurrentRobot(currentRobot.toBuilder()
                        .orientation(currentRobot.getOrientation().getLeft())
                        .build());
                break;
            case 'R':
                this.worldState.setCurrentRobot(currentRobot.toBuilder()
                        .orientation(currentRobot.getOrientation().getRight())
                        .build());
                break;
            case 'F':
                Position plannedPosition = currentRobot.getOrientation().getForwardPosition(currentRobotPosition);
                if (isCurrentPositionKnownLostLocation(currentRobotPosition) && isOutOfBounds(plannedPosition)){
                    break;
                }

                this.worldState.setCurrentRobot(currentRobot.toBuilder()
                        .position(plannedPosition)
                        .build());
                break;
            default:
                throw new IllegalArgumentException("invalid action: " + action);
        }
    }
}
