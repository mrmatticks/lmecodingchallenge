package co.uk.gerronematticks.lmecodingchallenge.instructions;

import co.uk.gerronematticks.lmecodingchallenge.state.WorldState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
@Qualifier("blankLineInstruction")
public class BlankLineInstruction implements Instruction {

    private final Collection<Instruction> allowedNextInstruction;

    @Autowired
    public BlankLineInstruction(@Lazy @Qualifier("setUpRobotPositionInstruction") Instruction instruction) {
        this.allowedNextInstruction = Arrays.asList(this, instruction);
    }

    @Override
    public boolean matchesInstruction(String input) {
        return "".matches(input);
    }

    @Override
    public Optional<String> performInstruction(String input) {
        return Optional.empty();
    }

    @Override
    public Collection<Instruction> allowedNextInstructions() {
        return allowedNextInstruction;
    }
}
