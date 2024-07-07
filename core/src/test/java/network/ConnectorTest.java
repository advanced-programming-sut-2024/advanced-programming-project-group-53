package network;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.*;

public class ConnectorTest {
    private Connector connector = new Connector();
    private static final int port = 8080;
    private ServerSocket serverSocket;
    private Thread serverThread;
    private boolean running;

    @Before
    public void setUp() throws IOException {
        serverSocket = new ServerSocket(port);
        running = true;
        serverThread = new Thread(() -> {
            try {
                while (running) {
                    Socket clientSocket = serverSocket.accept();
                    DataInputStream dis = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                    DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());

                    String received = dis.readUTF();
                    Instruction instruction = Instruction.fromString(received);

                    dos.writeUTF(instruction.toString());//just echoing back the same instruction to be simple

                    dis.close();
                    dos.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                if (running) {
                    e.printStackTrace();
                }
            }
        });
        serverThread.start();
    }

    @After
    public void end() throws IOException {
        running = false;
        serverSocket.close();
    }

    @Test
    public void shouldEstablishConnection() {
        connector.establishConnection("localhost", port);
        // the test fails if an exception is thrown
    }

    @Test
    public void testSendMessage() {
        connector.establishConnection("localhost", port);
        Instruction instruction = new Instruction(Command.LOGIN, "username", "password");
        connector.sendMessage(instruction);
        // the test can fail if an exception is thrown
    }

    @Test
    public void testReceiveMessage() {
        connector.establishConnection("localhost", port);
        Instruction instruction = new Instruction(Command.REGISTER, "user", "pass");
        connector.sendMessage(instruction);
        assertEquals(instruction.toString(), connector.receiveMessage().toString());// checking if the received message is the same
    }

    @Test
    public void shouldEndConnection() {
        connector.establishConnection("localhost", port);
        connector.endConnection();
        // test fails if an exception is thrown
    }

}