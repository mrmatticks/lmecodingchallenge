package co.uk.gerronematticks.lmecodingchallenge.instructions;

import co.uk.gerronematticks.lmecodingchallenge.state.WorldState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
@Qualifier("moveRobotInstruction")
public class MoveRobotInstruction implements Instruction {

    private final Collection<Instruction> allowedNextInstruction;
    private final WorldState worldState;

    @Autowired
    public MoveRobotInstruction(@Lazy @Qualifier("blankLineInstruction") Instruction exitApplicationInstruction,
                                WorldState worldState) {
        this.allowedNextInstruction = Collections.singletonList(exitApplicationInstruction);
        this.worldState = worldState;
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
