package network;

import org.junit.Test;

import static org.junit.Assert.*;

public class InstructionTest {

    @Test
    public void commandsPassingCheck() {
        String[] args = {"argument1","argument2"};
        Instruction instruction = new Instruction(Command.LOGIN,args);

        assertEquals(Command.LOGIN, instruction.command());
        assertArrayEquals(args, instruction.arguments());
        assertEquals(2, instruction.argumentCount());
    }


}