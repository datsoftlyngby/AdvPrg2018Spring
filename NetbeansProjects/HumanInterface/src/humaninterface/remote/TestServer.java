/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.remote;

import dk.tobiasgrundtvig.util.socket.ConnectionHandler;
import dk.tobiasgrundtvig.util.socket.SocketConnection;
import dk.tobiasgrundtvig.util.socket.impl.Server;
import humaninterface.TextualInterface;

/**
 *
 * @author Tobias
 */
public class TestServer implements ConnectionHandler
{
    
    public static void main(String[] args)
    {
        Server server = new Server(new TestServer(), 5665);
        server.run();
    }
    
    @Override
    public void handleConnection(SocketConnection conn)
    {
        TextualInterface ti = new QuestionSide(new TextualInterfaceConnectionImpl(conn));
        Conversation conversation = new Conversation(ti);
        new Thread(conversation).start();
    }
    
}
