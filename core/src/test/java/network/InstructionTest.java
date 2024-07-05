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

    @Test
    public void toStringCheck() {
        String[] args = {"argument1" , "argument2"};
        Instruction instruction = new Instruction(Command.LOGIN, args);
        assertEquals("LOGIN argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.REGISTER, args);
        assertEquals("REGISTER argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.REGISTER_MESSAGE, args);
        assertEquals("REGISTER_MESSAGE argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.QUESTION, args);
        assertEquals("QUESTION argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.QUESTION_MESSAGE, args);
        assertEquals("QUESTION_MESSAGE argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.USERNAME_CHECK, args);
        assertEquals("USERNAME_CHECK argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.FORGET_PASSWORD_MESSAGE_USER, args);
        assertEquals("FORGET_PASSWORD_MESSAGE_USER argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.FORGET_PASSWORD, args);
        assertEquals("FORGET_PASSWORD argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.FORGET_PASSWORD_MESSAGE_PASSWORD, args);
        assertEquals("FORGET_PASSWORD_MESSAGE_PASSWORD argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.LOGIN_MESSAGE, args);
        assertEquals("LOGIN_MESSAGE argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.PROFILE_INFORMATION, args);
        assertEquals("PROFILE_INFORMATION argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.PROFILE_MESSAGE, args);
        assertEquals("PROFILE_MESSAGE argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.CHANGE_FIELD_MESSAGE, args);
        assertEquals("CHANGE_FIELD_MESSAGE argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.CHANGE_USERNAME, args);
        assertEquals("CHANGE_USERNAME argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.CHANGE_EMAIL, args);
        assertEquals("CHANGE_EMAIL argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.CHANGE_NICKNAME, args);
        assertEquals("CHANGE_NICKNAME argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.CHANGE_PASSWORD, args);
        assertEquals("CHANGE_PASSWORD argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.HISTORY_INFORMATION, args);
        assertEquals("HISTORY_INFORMATION argument1 argument2", instruction.toString());
        instruction = new Instruction(Command.HISTORY_MESSAGE, args);
        assertEquals("HISTORY_MESSAGE argument1 argument2", instruction.toString());
    }
}