package mars.mips.instructions.customlangs;
import mars.mips.hardware.*;
import mars.*;
import mars.util.*;
import mars.mips.instructions.*;

    public class ExplorePlanets extends CustomAssembly{
        public static boolean sunDown = false;
        public static boolean mercDown = false;
        public static boolean venDown = false;
        public static boolean earDown = false;
        public static boolean marDown = false;
        public static boolean jupDown = false;
        public static boolean satDown = false;
        public static boolean uraDown = false;
        public static boolean nepDown = false;
        @Override
        public String getName(){
            return "ExplorePlanets";
        }
        @Override
        public String getDescription() {
            return "Explore the planets in Mars!";
        }
        // need to use fffff, sssss, tttt and ssssssssssssssss to specify registers and immediate
        @Override
        // java -jar BuildCustomLang.jar ExplorePlanets
        protected void populate(){

            instructionList.add(
                    new BasicInstruction("Pow $t1, $t2, $t3",
                            "Stores the result of $t2 to the power of $t3 into register $t1",
                            BasicInstructionFormat.R_FORMAT,

                            "000000 fffff sssss ttttt 00000 100001",
                            new SimulationCode()
                            {
                                public void simulate(ProgramStatement statement) throws ProcessingException
                                {
                                    int[] operands = statement.getOperands();
                                    int R2 = RegisterFile.getValue(operands[1]);
                                    int R3 = RegisterFile.getValue(operands[2]);
                                    int result = (int)Math.pow(R2, R3);
                                    RegisterFile.updateRegister(operands[0], result);
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("Travel $t1, $t2, $t3",
                            "Travel between planets. adds the result of r2 and r3 and stores into R1",
                            BasicInstructionFormat.R_FORMAT,
                            "001000 fffff sssss ttttt 00000 100000",
                            new SimulationCode()
                            {
                                public void simulate(ProgramStatement statement) throws ProcessingException
                                {
                                    int[] operands = statement.getOperands();
                                    int R2 = RegisterFile.getValue(operands[1]);
                                    int R3 = RegisterFile.getValue(operands[2]);
                                    int result = R2 + R3;
                                    RegisterFile.updateRegister(operands[0], result);
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("Erode $t1, $t2, $t3",
                            "Erode the difference in size. Adds the result of r2 minus r3 and stores into R1",
                            BasicInstructionFormat.R_FORMAT,
                            "001000 fffff sssss ttttt 00000 100010",
                            new SimulationCode()
                            {
                                public void simulate(ProgramStatement statement) throws ProcessingException
                                {
                                    int[] operands = statement.getOperands();
                                    int R2 = RegisterFile.getValue(operands[1]);
                                    int R3 = RegisterFile.getValue(operands[2]);
                                    int result = R2 - R3;
                                    RegisterFile.updateRegister(operands[0], result);
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("Teleport $t1, $t2, 500",
                            "Teleport values between two points. Adds the values inside $t2 and an immediate," +
                                    "storing inside $t1",
                            BasicInstructionFormat.I_FORMAT,
                            "001000 fffff ttttt ssssssssssssssss",
                            new SimulationCode() {
                                public void simulate(ProgramStatement statement) throws ProcessingException {
                                    int[] operands = statement.getOperands();
                                    int R2 = RegisterFile.getValue(operands[1]);
                                    int immediate = operands[2] << 16 >> 16;
                                    int result = R2 + immediate;
                                    RegisterFile.updateRegister(operands[0], result);
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("Establish $t1,-100",
                            "Establish a register in your name! Loads immediate value into $t1",
                            BasicInstructionFormat.I_FORMAT,
                            "001000 fffff 00000 ssssssssssssssss",
                            new SimulationCode() {
                                public void simulate(ProgramStatement statement) throws ProcessingException {
                                    int[] operands = statement.getOperands();
                                    int immediate = operands[1] << 16 >> 16;

                                    RegisterFile.updateRegister(operands[0], immediate);
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("SUN $t1",
                            "Travel to the Sun. Sets register $t1 to 0 and prints a special message",
                            BasicInstructionFormat.I_FORMAT,
                            "100000 fffff 0000000000000000000000 ",
                            new SimulationCode() {
                                public void simulate(ProgramStatement statement) throws ProcessingException {
                                    if(!sunDown) {
                                        int[] operands = statement.getOperands();
                                        RegisterFile.updateRegister(operands[0], 0);
                                        SystemIO.printString("Welcome to the Sun! Please avoid burning yourself\n");
                                    }
                                    else{
                                        SystemIO.printString("Sun has already been destroyed\n");
                                    }
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("MERCURY $t1",
                            "Travel to Mercury. Sets register $t1 to 1 and prints a special message",
                            BasicInstructionFormat.I_FORMAT,
                            "100001 fffff 0000000000000000000000 ",
                            new SimulationCode() {
                                public void simulate(ProgramStatement statement) throws ProcessingException {
                                    if(!mercDown) {
                                        int[] operands = statement.getOperands();
                                        RegisterFile.updateRegister(operands[0], 1);
                                        SystemIO.printString("Welcome to Mercury! Please enjoy your stay\n");
                                    }
                                    else{
                                        SystemIO.printString("Mercury? Must've exploded\n");
                                    }
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("VENUS $t1",
                            "Travel to the Venus. Sets register $t1 to 2 and prints a special message",
                            BasicInstructionFormat.I_FORMAT,
                            "100010 fffff 0000000000000000000000 ",
                            new SimulationCode() {
                                public void simulate(ProgramStatement statement) throws ProcessingException {
                                    if(!venDown) {
                                        int[] operands = statement.getOperands();
                                        RegisterFile.updateRegister(operands[0], 2);
                                        SystemIO.printString("Welcome to Venus! Calling us flytraps is very taboo\n");
                                    }
                                    else{
                                        SystemIO.printString("Venus no longer exists in this galaxy \n");
                                    }
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("EARTH $t1",
                            "Travel to Earth. Sets register $t1 to 3 and prints a special message",
                            BasicInstructionFormat.I_FORMAT,
                            "100011 fffff 0000000000000000000000 ",
                            new SimulationCode() {
                                public void simulate(ProgramStatement statement) throws ProcessingException {
                                    if(!earDown) {
                                        int[] operands = statement.getOperands();
                                        RegisterFile.updateRegister(operands[0], 3);
                                        SystemIO.printString("Welcome to Earth! Or should I say, welcome back\n");
                                    }
                                    else{
                                        SystemIO.printString("Earth has already self imploded\n");
                                    }
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("MARS $t1",
                            "Travel to Mars. Sets register $t1 to 4 and prints a special message",
                            BasicInstructionFormat.I_FORMAT,
                            "100100 fffff 0000000000000000000000 ",
                            new SimulationCode() {
                                public void simulate(ProgramStatement statement) throws ProcessingException {
                                    if(!marDown) {
                                        int[] operands = statement.getOperands();
                                        RegisterFile.updateRegister(operands[0], 4);
                                        SystemIO.printString("Welcome to Mars! One of our inhabitants is called a rover\n");
                                    }
                                    else{
                                        SystemIO.printString("Marvin the Martian accidentally destroyed Mars\n");
                                    }
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("JUPITER $t1",
                            "Travel to Jupiter. Sets register $t1 to 5 and prints a special message",
                            BasicInstructionFormat.I_FORMAT,
                            "100101 fffff 0000000000000000000000 ",
                            new SimulationCode() {
                                public void simulate(ProgramStatement statement) throws ProcessingException {
                                    if(!jupDown) {
                                        int[] operands = statement.getOperands();
                                        RegisterFile.updateRegister(operands[0], 5);
                                        SystemIO.printString("Welcome to Jupiter! \n");
                                    }
                                    else{
                                        SystemIO.printString("Kratos returned\n");
                                    }
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("SATURN $t1",
                            "Travel to Saturn. Sets register $t1 to 6 and prints a special message",
                            BasicInstructionFormat.I_FORMAT,
                            "100110 fffff 0000000000000000000000",
                            new SimulationCode() {
                                public void simulate(ProgramStatement statement) throws ProcessingException {
                                    if(!satDown) {
                                        int[] operands = statement.getOperands();
                                        RegisterFile.updateRegister(operands[0], 6);
                                        SystemIO.printString("Welcome to Saturn! Has nice a ring to it \n");
                                    }
                                    else{
                                        SystemIO.printString("Saturn exploded with the wind\n");
                                    }
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("URANUS $t1",
                            "Travel to Uranus. Sets register $t1 to 7 and prints a special message",
                            BasicInstructionFormat.I_FORMAT,
                            "100111 fffff 0000000000000000000000 ",
                            new SimulationCode() {
                                public void simulate(ProgramStatement statement) throws ProcessingException {
                                    if(!uraDown) {
                                        int[] operands = statement.getOperands();
                                        RegisterFile.updateRegister(operands[0], 7);
                                        SystemIO.printString("Welcome to Uranus! Please enjoy your stay \n");
                                    }
                                    else{
                                        SystemIO.printString("Uranus has long since vanished in a dazzling supernova\n");
                                    }
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("NEPTUNE $t1",
                            "Travel to Neptune. Sets register $t1 to 8 and prints a special message",
                            BasicInstructionFormat.I_FORMAT,
                            "101000 fffff 0000000000000000000000 ",
                            new SimulationCode() {
                                public void simulate(ProgramStatement statement) throws ProcessingException {
                                    if(!nepDown) {
                                        int[] operands = statement.getOperands();
                                        RegisterFile.updateRegister(operands[0], 8);
                                        SystemIO.printString("Welcome to Neptune! Please enjoy your stay \n");
                                    }
                                    else{
                                        SystemIO.printString("Neptune drowned in a supernova\n");
                                    }
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("NOVA $t1",
                            "Destroys current planet, preventing use of special instruction until reformed (valid" +
                                    "for numbers 0-8)",
                            BasicInstructionFormat.I_FORMAT,
                            "100000 fffff 0000000000000000000000 ",
                            new SimulationCode() {
                                public void simulate(ProgramStatement statement) throws ProcessingException {
                                    int[] operands = statement.getOperands();
                                    switch(RegisterFile.getValue(operands[0])){
                                        case 0:
                                            sunDown = true;
                                            SystemIO.printString("Sun has been destroyed! Unable to use SUN\n");
                                            break;
                                        case 1:
                                            mercDown = true;
                                            SystemIO.printString("Mercury has been destroyed! Unable to use MERCURY\n");
                                            break;
                                        case 2:
                                            venDown = true;
                                            SystemIO.printString("Venus has been destroyed! Unable to use VENUS\n");
                                            break;
                                        case 3:
                                            earDown = true;
                                            SystemIO.printString("Earth has been destroyed! Unable to use EARTH\n");
                                            break;
                                        case 4:
                                            marDown = true;
                                            SystemIO.printString("Mars has been destroyed! Unable to use MARS\n");
                                            break;
                                        case 5:
                                            jupDown = true;
                                            SystemIO.printString("Jupiter has been destroyed! Unable to use JUPITER\n");
                                            break;
                                        case 6:
                                            satDown = true;
                                            SystemIO.printString("Saturn has been destroyed! Unable to use SATURN\n");
                                            break;
                                        case 7:
                                            uraDown = true;
                                            SystemIO.printString("Uranus has been destroyed! Unable to use URANUS\n");
                                            break;
                                        case 8:
                                            nepDown = true;
                                            SystemIO.printString("Neptune has been destroyed! Unable to use NEPTUNE\n");
                                            break;

                                    }

                                }
                            }));
            instructionList.add(
                    new BasicInstruction("REBI $t1",
                            "Rebirth planet using register value, allowing for use of " +
                                    "its special instruction again (valid for numbers 0-8)",
                            BasicInstructionFormat.I_FORMAT,
                            "110000 fffff 0000000000000000000000 ",
                            new SimulationCode() {
                                public void simulate(ProgramStatement statement) throws ProcessingException {
                                    int[] operands = statement.getOperands();
                                    switch(RegisterFile.getValue(operands[0])){
                                        case 0:
                                            sunDown = false;
                                            SystemIO.printString("Sun has been restored! Able to use SUN again\n");
                                            break;
                                        case 1:
                                            mercDown = false;
                                            SystemIO.printString("Mercury has been restored! Able to use MERCURY again\n");
                                            break;
                                        case 2:
                                            venDown = false;
                                            SystemIO.printString("Venus has been restored! Able to use VENUS again\n");
                                            break;
                                        case 3:
                                            earDown = false;
                                            SystemIO.printString("Earth has been restored! Able to use EARTH again\n");
                                            break;
                                        case 4:
                                            marDown = false;
                                            SystemIO.printString("Mars has been restored! Able to use MARS again\n");
                                            break;
                                        case 5:
                                            jupDown = false;
                                            SystemIO.printString("Jupiter has been restored! Able to use JUPITER again\n");
                                            break;
                                        case 6:
                                            satDown = false;
                                            SystemIO.printString("Saturn has been restored! Able to use SATURN again\n");
                                            break;
                                        case 7:
                                            uraDown = false;
                                            SystemIO.printString("Uranus has been restored! Able to use URANUS again\n");
                                            break;
                                        case 8:
                                            nepDown = false;
                                            SystemIO.printString("Neptune has been restored! Able to use NEPTUNE again\n");
                                            break;
                                        default:
                                            SystemIO.printString("Not a planet, please set register to number 0-8 to use\n");

                                    }

                                }
                            }));
            // error
            instructionList.add(
                    new BasicInstruction("Inject $t1,0($t2)",
                            "Inject data into a register. Set $t1 to contents of the memory address stored in $t2",
                            BasicInstructionFormat.I_FORMAT,
                            "100011 ttttt fffff ssssssssssssssss",
                            new SimulationCode()
                            {
                                public void simulate(ProgramStatement statement) throws ProcessingException
                                {
                                    int[] operands = statement.getOperands();
                                    try
                                    {
                                        RegisterFile.updateRegister(operands[0],
                                                Globals.memory.getWord(
                                                        RegisterFile.getValue(operands[2]) + operands[1]));
                                    }
                                    catch (AddressErrorException e)
                                    {
                                        throw new ProcessingException(statement, e);
                                    }
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("Inject $t1,Label($t2)",
                            "Inject data into a register. Set $t1 to contents of the memory address stored in $t2",
                            BasicInstructionFormat.I_FORMAT,
                            "100011 ttttt fffff ssssssssssssssss",
                            new SimulationCode()
                            {
                                public void simulate(ProgramStatement statement) throws ProcessingException
                                {
                                    int[] operands = statement.getOperands();
                                    try
                                    {
                                        RegisterFile.updateRegister(operands[0],
                                                Globals.memory.getWord(
                                                        RegisterFile.getValue(operands[2]) + operands[1]));
                                    }
                                    catch (AddressErrorException e)
                                    {
                                        throw new ProcessingException(statement, e);
                                    }
                                }
                            }));
            // not tested
            instructionList.add(
                    new BasicInstruction("Record $t1,0($t2)",
                            "Save some data during your travel. Stores the data in $t1 to memory register in $t2\n",
                            BasicInstructionFormat.I_FORMAT,
                            "101011 ttttt fffff ssssssssssssssss",
                            new SimulationCode()
                            {
                                public void simulate(ProgramStatement statement) throws ProcessingException
                                {
                                    int[] operands = statement.getOperands();
                                    try
                                    {
                                        Globals.memory.setWord(
                                                RegisterFile.getValue(operands[2]) + operands[1],
                                                RegisterFile.getValue(operands[0]));
                                    }
                                    catch (AddressErrorException e)
                                    {
                                        throw new ProcessingException(statement, e);
                                    }
                                }
                            }));
            instructionList.add(
                    new BasicInstruction("Record $t1, label($t2)",
                            "Save some data during your travel. Stores the data in $t1 to memory register in $t2\n",
                            BasicInstructionFormat.I_FORMAT,
                            "101011 ttttt fffff ssssssssssssssss",
                            new SimulationCode()
                            {
                                public void simulate(ProgramStatement statement) throws ProcessingException
                                {
                                    int[] operands = statement.getOperands();
                                    try
                                    {
                                        Globals.memory.setWord(
                                                RegisterFile.getValue(operands[2]) + operands[1],
                                                RegisterFile.getValue(operands[0]));
                                    }
                                    catch (AddressErrorException e)
                                    {
                                        throw new ProcessingException(statement, e);
                                    }
                                }
                            }));
            // tested
            instructionList.add(
                    new BasicInstruction("Splice $t0, $t1, $t2",
                            "Splice to registers. Value in $t2 is divided by value in $t3 and is stored into $t1\n",
                            BasicInstructionFormat.I_FORMAT,
                            "101011 ttttt fffff ssssssssssssssss",
                            new SimulationCode()
                            {
                                public void simulate(ProgramStatement statement) throws ProcessingException
                                {
                                    int[] operands = statement.getOperands();
                                    int R2 = RegisterFile.getValue(operands[1]);
                                    int R3 = RegisterFile.getValue(operands[2]);
                                    int result = R2 / R3;
                                    RegisterFile.updateRegister(operands[0], result);

                                }
                            }));
            //tested, works
            instructionList.add(
                    new BasicInstruction("Propagate $t0, $t1, $t2",
                            "Propagate a register using two others. multiplies value in $t1 by $t2 and stores " +
                                    "in register $t0\n",
                            BasicInstructionFormat.I_FORMAT,
                            "101011 ttttt fffff ssssssssssssssss",
                            new SimulationCode()
                            {
                                public void simulate(ProgramStatement statement) throws ProcessingException
                                {
                                    int[] operands = statement.getOperands();
                                    int R2 = RegisterFile.getValue(operands[1]);
                                    int R3 = RegisterFile.getValue(operands[2]);
                                    int result = R2 * R3;
                                    RegisterFile.updateRegister(operands[0], result);

                                }
                            }));
            // tested, works
            instructionList.add(
                    new BasicInstruction("Warp LABEL",
                            "Warp to a label! Unconditional jump to label",
                            BasicInstructionFormat.J_FORMAT,
                            "000010 ffffffffffffffffffffffffff",
                            new SimulationCode()
                            {
                                public void simulate(ProgramStatement statement) throws ProcessingException
                                {
                                    int[] operands = statement.getOperands();
                                    Globals.instructionSet.processJump(
                                            ((RegisterFile.getProgramCounter() & 0xF0000000)
                                                    | (operands[0] << 2)));
                                }
                            }));
            //tested, works
            instructionList.add(
                    new BasicInstruction("WarpCheck $t1, $t2, label",
                            "Warp calibration check. Jumps to label if $t1 is not equal to $t2",
                            BasicInstructionFormat.I_BRANCH_FORMAT,
                            "000101 fffff sssss tttttttttttttttt",
                            new SimulationCode()
                            {
                                public void simulate(ProgramStatement statement) throws ProcessingException
                                {
                                    int[] operands = statement.getOperands();
                                    if (RegisterFile.getValue(operands[0])
                                            != RegisterFile.getValue(operands[1]))
                                    {
                                        Globals.instructionSet.processBranch(operands[2]);
                                    }
                                }
                            }));
            //tested, works
            instructionList.add(
                    new BasicInstruction("Away $t1",
                            "Check how far you are from Earth. Returns absolute value of register value - 3 ",
                            BasicInstructionFormat.R_FORMAT,
                            "000101 fffff sssss tttttttttttttttt",
                            new SimulationCode()
                            {
                                public void simulate(ProgramStatement statement) throws ProcessingException
                                {
                                    int[] operands = statement.getOperands();
                                    int result =  Math.abs(RegisterFile.getValue(operands[0]) - 3);
                                    SystemIO.printString("You are " + result + " planets away from Earth \n");

                                }
                            }));





        }
}
