package co.uk.gerronematticks.lmecodingchallenge.instructions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
@Qualifier("setUpRobotPositionInstruction")
public class SetUpRobotPositionInstruction implements Instruction {

    private final Collection<Instruction> allowedNextInstruction;

    @Autowired
    public SetUpRobotPositionInstruction(@Lazy @Qualifier("moveRobotInstruction") Instruction instruction) {
        this.allowedNextInstruction = Collections.singletonList(instruction);
    }

    @Override
    public boolean matchesInstruction(String input) {
        boolean matches = input.matches("[0-9]+ [0-9]+ ([NSEW])");

        if (!matches) {
            return false;
        }

        return checkIfWithinRange(input);
    }

    @Override
    public String performInstruction(String string) {
        return null;
    }

    @Override
    public Collection<Instruction> allowedNextInstructions() {
        return allowedNextInstruction;
    }

    private boolean checkIfWithinRange(String input) {
        String[] split = input.split(" ");
        return Integer.parseInt(split[0]) <= 50 && Integer.parseInt(split[1]) <= 50;
    }

}
