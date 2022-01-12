# Lme Coding Challenge

## How to run
### With IntelliJ
* Simply run the `LmeCodingChallengeApplication` class
* In the run window, you can directly type the input for each command.

### In Terminal
* Run `mvn clean install` to set up the project
* Run `java -jar  target/lmecodingchallenge-0.0.1-SNAPSHOT.jar` to start the project
* In the run window, you can directly type the input for each command.

## Development Approach
The idea here is each action you can do is based on a `Instruction`.
You can implement `Instruction` to set up new behaviours and auto-wire in what are the allowed next instructions.

`LmeCodingRobotInstructionParser` brings in the first instruction and then parses each input against the allowed list of next allowed instructions and continues running.