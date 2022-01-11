package co.uk.gerronematticks.lmecodingchallenge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LmeCodingChallengeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LmeCodingChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String next = scanner.nextLine();
            System.out.println("Input:" + next);
        }
    }
}
