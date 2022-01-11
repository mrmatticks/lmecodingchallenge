package co.uk.gerronematticks.lmecodingchallenge;

import co.uk.gerronematticks.lmecodingchallenge.instructions.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;


public class LmeCodingRobotInstructionParserTest {

    private LmeCodingRobotInstructionParser lmeCodingRobotInstructionParser;

    @Before
    public void setUp() {
        Instruction blankLineInstruction = new BlankLineInstruction(null);
        Instruction moveRobotInstruction = new MoveRobotInstruction(blankLineInstruction);
        Instruction setUpRobotInstruction = new SetUpRobotPositionInstruction(moveRobotInstruction);
        Instruction setUpWorldInstruction = new SetUpWorldInstruction(setUpRobotInstruction);

        lmeCodingRobotInstructionParser = new LmeCodingRobotInstructionParser(setUpWorldInstruction);
    }

    @Test
    public void testValidString() {
        String input = "5 3\n" +
                "1 1 E\n" +
                "RFRFRFRF\n";

        Arrays.stream(input.split("\n"))
                .forEach(line -> lmeCodingRobotInstructionParser.parseInstruction(line));
    }

    @Test
    public void testValidString2() {
        String input = "5 7\n" +
                "1 1 W\n" +
                "RFRFRFRFFLLLL\n";

        Arrays.stream(input.split("\n"))
                .forEach(line -> lmeCodingRobotInstructionParser.parseInstruction(line));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSetupWorld() {
        String input = "5 3 5\n" +
                "1 1 E\n" +
                "RFRFRFRF\n";

        Arrays.stream(input.split("\n"))
                .forEach(line -> lmeCodingRobotInstructionParser.parseInstruction(line));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSetupWorldCoordinateOver50() {
        String input = "5 51\n" +
                "1 1 E\n" +
                "RFRFRFRF\n";

        Arrays.stream(input.split("\n"))
                .forEach(line -> lmeCodingRobotInstructionParser.parseInstruction(line));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSetRobotPosition() {
        String input = "5 3\n" +
                "1 1\n" +
                "RFRFRFRF\n";

        Arrays.stream(input.split("\n"))
                .forEach(line -> lmeCodingRobotInstructionParser.parseInstruction(line));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSetRobotPositionOver50() {
        String input = "5 7\n" +
                "51 1 W\n" +
                "RFRFRFRFFLLLL\n";

        Arrays.stream(input.split("\n"))
                .forEach(line -> lmeCodingRobotInstructionParser.parseInstruction(line));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStringMoveRobot() {
        String input = "5 3\n" +
                "1 1 E\n" +
                "RFRFRFRFXE\n";

        Arrays.stream(input.split("\n"))
                .forEach(line -> lmeCodingRobotInstructionParser.parseInstruction(line));
    }

}
