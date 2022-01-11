package co.uk.gerronematticks.lmecodingchallenge.instructions;

import co.uk.gerronematticks.lmecodingchallenge.state.WorldState;
import org.junit.Assert;
import org.junit.Test;

public class SetUpWorldInstructionTest {

    @Test
    public void setUpWorld() {
        //Given
        WorldState actual = new WorldState();

        //When
        new SetUpWorldInstruction(null, actual).performInstruction("4 5");

        //Then
        WorldState expected = new WorldState();
        expected.initialize(4, 5);
        Assert.assertEquals(actual, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidSetUpWorldNonNumber() {
        //Given
        WorldState actual = new WorldState();

        //When & Then
        new SetUpWorldInstruction(null, actual).performInstruction("4 X");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidSetUpWorldOver50() {
        //Given
        WorldState actual = new WorldState();

        //When & Then
        new SetUpWorldInstruction(null, actual).performInstruction("4 X");
    }

}