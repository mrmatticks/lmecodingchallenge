package co.uk.gerronematticks.lmecodingchallenge.instructions;

import co.uk.gerronematticks.lmecodingchallenge.model.Orientation;
import co.uk.gerronematticks.lmecodingchallenge.model.Position;
import co.uk.gerronematticks.lmecodingchallenge.model.Robot;
import co.uk.gerronematticks.lmecodingchallenge.state.WorldState;
import org.junit.Assert;
import org.junit.Test;

public class SetUpRobotPositionInstructionTest {

    @Test
    public void setUpRobotPosition() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);

        //When
        new SetUpRobotPositionInstruction(null, actual).performInstruction("2 3 E");

        //Then
        WorldState expected = new WorldState();
        expected.initialize(4, 5);
        expected.setCurrentRobot(new Robot(Orientation.EAST, new Position(2, 3)));

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void setUpRobotPositionEdge() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);

        //When
        new SetUpRobotPositionInstruction(null, actual).performInstruction("4 5 E");

        //Then
        WorldState expected = new WorldState();
        expected.initialize(4, 5);
        expected.setCurrentRobot(new Robot(Orientation.EAST, new Position(4, 5)));

        Assert.assertEquals(actual, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setUpRobotPositionOffGrid() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);

        //When & Then
        new SetUpRobotPositionInstruction(null, actual).performInstruction("5 5 E");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setUpRobotPositionInvalidXPosition() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);

        //When & Then
        new SetUpRobotPositionInstruction(null, actual).performInstruction("X 5 E");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setUpRobotPositionInvalidYPosition() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);

        //When & Then
        new SetUpRobotPositionInstruction(null, actual).performInstruction("5 X E");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setUpRobotPositionInvalidOrientation() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);

        //When & Then
        new SetUpRobotPositionInstruction(null, actual).performInstruction("5 5 H");
    }


}