package co.uk.gerronematticks.lmecodingchallenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private LmeCodingRobotInstructionParser lmeCodingRobotInstructionParser;

    @MockBean
    private LmeCodingChallengeApplication lmeCodingChallengeApplication;

    @Test
    public void sampleInput() {
        String input = "5 3\n" +
                "1 1 E\n" +
                "RFRFRFRF\n" +
                "3 2 N\n" +
                "FRRFLLFFRRFLL\n" +
                "0 3 W\n" +
                "LLFFFLFLFL\n";

        String expected = "1 1 E\n" +
                "3 3 N LOST\n" +
                "2 3 S";

        assertEquals(expected, getOutput(input));
    }

    private String getOutput(String input) {
        return input.lines()
                .map(line -> lmeCodingRobotInstructionParser.parseInstruction(line))
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
    }


}
