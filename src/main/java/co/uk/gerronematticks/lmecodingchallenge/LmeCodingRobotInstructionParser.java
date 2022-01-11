package co.uk.gerronematticks.lmecodingchallenge;

import co.uk.gerronematticks.lmecodingchallenge.instructions.Instruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class LmeCodingRobotInstructionParser {

    private Collection<Instruction> allowedInstructions;

    @Autowired
    public LmeCodingRobotInstructionParser(@Qualifier("setUpWorldInstruction") Instruction instruction) {
        this.allowedInstructions = Collections.singletonList(instruction);
    }

    public String parseInstruction(String input) {

        Instruction matchingInstruction = getMatchingInstruction(input);

        String output = matchingInstruction.performInstruction(input);

        this.allowedInstructions = matchingInstruction.allowedNextInstructions();

        return output;
    }

    public Collection<Instruction> getAllowedInstructions() {
        return allowedInstructions;
    }

    private Instruction getMatchingInstruction(String input) {
        return this.allowedInstructions.stream()
                .filter(instruction -> instruction.matchesInstruction(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Input:" + input + " is not valid next instruction"));
    }

}
