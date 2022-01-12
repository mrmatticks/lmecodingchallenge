package co.uk.gerronematticks.lmecodingchallenge;

import co.uk.gerronematticks.lmecodingchallenge.instructions.*;
import co.uk.gerronematticks.lmecodingchallenge.state.WorldState;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.assertEquals;


public class LmeCodingRobotInstructionParserTest {

    private LmeCodingRobotInstructionParser lmeCodingRobotInstructionParser;

    @Before
    public void setUp() {
        WorldState worldState = new WorldState();
        Instruction printStatementInstruction = new BlankLineInstruction(null);
        Instruction moveRobotInstruction = new MoveRobotInstruction(printStatementInstruction, null, worldState);
        Instruction setUpRobotInstruction = new SetUpRobotPositionInstruction(moveRobotInstruction, worldState);
        Instruction setUpWorldInstruction = new SetUpWorldInstruction(setUpRobotInstruction, worldState);

        lmeCodingRobotInstructionParser = new LmeCodingRobotInstructionParser(setUpWorldInstruction);
    }

    @Test
    public void testValidString() {
        String input = "5 3\n" +
                "1 1 E\n" +
                "RFRFRFRF\n";

        input.lines()
                .forEach(line -> lmeCodingRobotInstructionParser.parseInstruction(line));
    }


    @Test
    public void testValidString2() {
        String input = "5 7\n" +
                "1 1 W\n" +
                "RFRFRFRFFLLLL\n";

        input.lines()
                .forEach(line -> lmeCodingRobotInstructionParser.parseInstruction(line));
    }

    @Test
    public void testPrintedOutput() {
        String input = "5 3\n" +
                "1 1 E\n" +
                "RFRFRFRF\n" +
                "\n";

        String expected = "1 1 E";

        String actual = getOutput(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testPrintedOutputLostRobot() {
        String input = "5 3\n" +
                "3 2 N\n" +
                "FRRFLLFFRRFLL\n" +
                "\n";

        String expected = "3 3 N LOST";

        String actual = getOutput(input);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSetupWorld() {
        String input = "5 3 5\n" +
                "1 1 E\n" +
                "RFRFRFRF\n";

        input.lines()
                .forEach(line -> lmeCodingRobotInstructionParser.parseInstruction(line));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSetupWorldCoordinateOver50() {
        String input = "5 51\n" +
                "1 1 E\n" +
                "RFRFRFRF\n";

        input.lines()
                .forEach(line -> lmeCodingRobotInstructionParser.parseInstruction(line));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSetRobotPosition() {
        String input = "5 3\n" +
                "1 1\n" +
                "RFRFRFRF\n";

        input.lines()
                .forEach(line -> lmeCodingRobotInstructionParser.parseInstruction(line));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSetRobotPositionOver50() {
        String input = "5 7\n" +
                "51 1 W\n" +
                "RFRFRFRFFLLLL\n";

        input.lines()
                .forEach(line -> lmeCodingRobotInstructionParser.parseInstruction(line));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStringMoveRobot() {
        String input = "5 3\n" +
                "1 1 E\n" +
                "RFRFRFRFXE\n";

        input.lines()
                .forEach(line -> lmeCodingRobotInstructionParser.parseInstruction(line));
    }

    private String getOutput(String input) {
        return input.lines()
                .map(line -> lmeCodingRobotInstructionParser.parseInstruction(line))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny()
                .orElse(null);
    }

}
