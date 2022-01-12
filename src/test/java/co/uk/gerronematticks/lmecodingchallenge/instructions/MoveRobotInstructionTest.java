package co.uk.gerronematticks.lmecodingchallenge.instructions;

import co.uk.gerronematticks.lmecodingchallenge.model.Orientation;
import co.uk.gerronematticks.lmecodingchallenge.model.Position;
import co.uk.gerronematticks.lmecodingchallenge.model.Robot;
import co.uk.gerronematticks.lmecodingchallenge.state.WorldState;
import org.junit.Assert;
import org.junit.Test;

public class MoveRobotInstructionTest {

    @Test
    public void moveForwardFacingNorth() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);
        actual.setCurrentRobot(new Robot(Orientation.NORTH, new Position(2, 3), false));

        //When
        new MoveRobotInstruction(null,null, actual).performInstruction("F");

        //Then
        WorldState expected = new WorldState();
        expected.initialize(4, 5);
        expected.setCurrentRobot(new Robot(Orientation.NORTH, new Position(2, 4), false));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void moveForwardFacingEast() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);
        actual.setCurrentRobot(new Robot(Orientation.EAST, new Position(2, 3), false));

        //When
        new MoveRobotInstruction(null,null, actual).performInstruction("F");

        //Then
        WorldState expected = new WorldState();
        expected.initialize(4, 5);
        expected.setCurrentRobot(new Robot(Orientation.EAST, new Position(3, 3), false));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void moveForwardFacingWest() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);
        actual.setCurrentRobot(new Robot(Orientation.WEST, new Position(2, 3), false));

        //When
        new MoveRobotInstruction(null,null, actual).performInstruction("F");

        //Then
        WorldState expected = new WorldState();
        expected.initialize(4, 5);
        expected.setCurrentRobot(new Robot(Orientation.WEST, new Position(1, 3), false));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void moveForwardFacingSouth() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);
        actual.setCurrentRobot(new Robot(Orientation.SOUTH, new Position(2, 3), false));

        //When
        new MoveRobotInstruction(null,null, actual).performInstruction("F");

        //Then
        WorldState expected = new WorldState();
        expected.initialize(4, 5);
        expected.setCurrentRobot(new Robot(Orientation.SOUTH, new Position(2, 2), false));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void moveForwardTwice() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);
        actual.setCurrentRobot(new Robot(Orientation.NORTH, new Position(2, 3), false));

        //When
        new MoveRobotInstruction(null,null, actual).performInstruction("FF");

        //Then
        WorldState expected = new WorldState();
        expected.initialize(4, 5);
        expected.setCurrentRobot(new Robot(Orientation.NORTH, new Position(2, 5), false));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void moveForwardThrice() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);
        actual.setCurrentRobot(new Robot(Orientation.NORTH, new Position(2, 3), false));

        //When
        new MoveRobotInstruction(null,null, actual).performInstruction("FFF");

        //Then
        WorldState expected = new WorldState();
        expected.initialize(4, 5);
        expected.setCurrentRobot(new Robot(Orientation.NORTH, new Position(2, 6), true));
        expected.getLostRobotsLastKnownLocations().add(new Position(2, 5));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void rotateLeft() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);
        actual.setCurrentRobot(new Robot(Orientation.NORTH, new Position(2, 3), false));

        //When
        new MoveRobotInstruction(null,null, actual).performInstruction("L");

        //Then
        WorldState expected = new WorldState();
        expected.initialize(4, 5);
        expected.setCurrentRobot(new Robot(Orientation.WEST, new Position(2, 3), false));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void rotateLeftTwice() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);
        actual.setCurrentRobot(new Robot(Orientation.NORTH, new Position(2, 3), false));

        //When
        new MoveRobotInstruction(null,null, actual).performInstruction("LL");

        //Then
        WorldState expected = new WorldState();
        expected.initialize(4, 5);
        expected.setCurrentRobot(new Robot(Orientation.SOUTH, new Position(2, 3), false));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void rotateRight() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);
        actual.setCurrentRobot(new Robot(Orientation.NORTH, new Position(2, 3), false));

        //When
        new MoveRobotInstruction(null,null, actual).performInstruction("R");

        //Then
        WorldState expected = new WorldState();
        expected.initialize(4, 5);
        expected.setCurrentRobot(new Robot(Orientation.EAST, new Position(2, 3), false));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void rotateRightMoveForward() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);
        actual.setCurrentRobot(new Robot(Orientation.NORTH, new Position(2, 3), false));

        //When
        new MoveRobotInstruction(null,null, actual).performInstruction("RF");

        //Then
        WorldState expected = new WorldState();
        expected.initialize(4, 5);
        expected.setCurrentRobot(new Robot(Orientation.EAST, new Position(3, 3), false));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void rotateRightThrice() {
        //Given
        WorldState actual = new WorldState();
        actual.initialize(4, 5);
        actual.setCurrentRobot(new Robot(Orientation.NORTH, new Position(2, 3), false));

        //When
        new MoveRobotInstruction(null,null, actual).performInstruction("RRR");

        //Then
        WorldState expected = new WorldState();
        expected.initialize(4, 5);
        expected.setCurrentRobot(new Robot(Orientation.WEST, new Position(2, 3), false));
        Assert.assertEquals(actual, expected);
    }

}