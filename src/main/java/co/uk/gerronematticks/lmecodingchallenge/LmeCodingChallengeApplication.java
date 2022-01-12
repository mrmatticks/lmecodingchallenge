package co.uk.gerronematticks.lmecodingchallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = {"co.uk.gerronematticks.lmecodingchallenge"})
public class LmeCodingChallengeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LmeCodingChallengeApplication.class, args);
    }

    private final LmeCodingRobotInstructionParser lmeCodingRobotInstructionParser;

    @Autowired
    public LmeCodingChallengeApplication(LmeCodingRobotInstructionParser lmeCodingRobotInstructionParser) {
        this.lmeCodingRobotInstructionParser = lmeCodingRobotInstructionParser;
    }

    @Override
    public void run(String... args) {
        handleInput();
    }

    private void handleInput() {
        System.out.println("------------------ STARTING ROBOT APPLICATION ------------------------------");
        System.out.println("------------------ PLEASE ENTER YOUR FIRST INPUT: ------------------------------");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            handleInput(input);

            if (lmeCodingRobotInstructionParser.getAllowedInstructions().isEmpty()) {
                return;
            }
        }
    }

    private void handleInput(String input) {
        if (input.length() > 100) {
            System.err.println("Instruction cannot be greater than 100 characters");
            return;
        }

        try {
            lmeCodingRobotInstructionParser.parseInstruction(input).ifPresent(System.out::println);
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
