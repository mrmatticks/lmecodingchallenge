package co.uk.gerronematticks.lmecodingchallenge.instructions;

import java.util.Collection;

public interface Instruction {

    boolean matchesInstruction(String input);

    String performInstruction(String string);

    Collection<Instruction> allowedNextInstructions();

}
