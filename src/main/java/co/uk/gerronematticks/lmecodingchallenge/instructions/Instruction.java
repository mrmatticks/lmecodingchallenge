package co.uk.gerronematticks.lmecodingchallenge.instructions;

import java.util.Collection;
import java.util.Optional;

public interface Instruction {

    /**
     * Detects if this instruction is applicable to this input
     * @return it if matches
     */
    boolean matchesInstruction(String input);

    /**
     * Performs the instruction which alters the world state
     * @return optional string output
     */
    Optional<String> performInstruction(String input);

    /**
     * @return The allowed next instructions that can occur after this instruction
     */
    Collection<Instruction> allowedNextInstructions();

}
