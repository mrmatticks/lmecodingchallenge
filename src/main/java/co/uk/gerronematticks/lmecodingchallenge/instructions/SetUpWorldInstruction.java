package co.uk.gerronematticks.lmecodingchallenge.instructions;

import co.uk.gerronematticks.lmecodingchallenge.state.WorldState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
@Qualifier("setUpWorldInstruction")
public class SetUpWorldInstruction implements Instruction {

    private final Collection<Instruction> allowedNextInstruction;
    private final WorldState worldState;

    @Autowired
    public SetUpWorldInstruction(@Lazy @Qualifier("setUpRobotPositionInstruction") Instruction instruction,
                                 WorldState worldState) {
        this.allowedNextInstruction = Collections.singletonList(instruction);
        this.worldState = worldState;
    }


    @Override
    public boolean matchesInstruction(String input) {
        boolean matches = input.matches("[0-9]+ [0-9]+");
        if (!matches) {
            return false;
        }

        return checkIfWithinRange(input);
    }

    @Override
    public Optional<String> performInstruction(String input) {
        String[] split = input.split(" ");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);

        this.worldState.initialize(x, y);

        return Optional.empty();
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
