/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.remote;

import dk.tobiasgrundtvig.util.socket.SocketConnection;
import dk.tobiasgrundtvig.util.socket.impl.SocketConnectionImpl;
import humaninterface.impl.ConsoleTextualInterface;
import java.io.IOException;

/**
 *
 * @author Tobias
 */
public class TestClient
{
    public static void main(String[] args) throws IOException
    {
        SocketConnection socketConn = new SocketConnectionImpl("localhost", 5665);
        TextualInterfaceConnection tic = new TextualInterfaceConnectionImpl(socketConn);
        AnswerSide as = new AnswerSide(tic, new ConsoleTextualInterface());
        
        as.run();
        
    }
}
