package co.uk.gerronematticks.lmecodingchallenge.instructions;

import co.uk.gerronematticks.lmecodingchallenge.state.WorldState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
@Qualifier("blankLineInstruction")
public class BlankLineInstruction implements Instruction {

    private final Collection<Instruction> allowedNextInstruction;
    private final WorldState worldState;

    @Autowired
    public BlankLineInstruction(@Lazy @Qualifier("setUpRobotPositionInstruction") Instruction instruction,
                                WorldState worldState) {
        this.allowedNextInstruction = Arrays.asList(this, instruction);
        this.worldState = worldState;
    }

    @Override
    public boolean matchesInstruction(String input) {
        return "".matches(input);
    }

    @Override
    public String performInstruction(String string) {
        return null;
    }

    @Override
    public Collection<Instruction> allowedNextInstructions() {
        return allowedNextInstruction;
    }
}
