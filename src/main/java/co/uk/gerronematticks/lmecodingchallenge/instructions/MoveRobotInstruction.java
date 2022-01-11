package co.uk.gerronematticks.lmecodingchallenge.instructions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Component
@Qualifier("moveRobotInstruction")
public class MoveRobotInstruction implements Instruction {

    private final Collection<Instruction> allowedNextInstruction;

    @Autowired
    public MoveRobotInstruction(@Lazy @Qualifier("blankLineInstruction") Instruction exitApplicationInstruction) {
        this.allowedNextInstruction = Collections.singletonList(exitApplicationInstruction);
    }

    @Override
    public boolean matchesInstruction(String input) {
        return input.matches("([LRF])+");
    }

    @Override
    public String performInstruction(String string) {
        return null;
    }

    @Override
    public Collection<Instruction> allowedNextInstructions() {
        return this.allowedNextInstruction;
    }
}
