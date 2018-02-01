/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.impl.remote;

import tictactoe.impl.remote.*;
import dk.tobiasgrundtvig.util.socket.ConnectionHandler;
import dk.tobiasgrundtvig.util.socket.SocketConnection;
import dk.tobiasgrundtvig.util.socket.impl.Server;
import tictactoe.TicTacToeGameCtrl;
import tictactoe.TicTacToePlayer;
import tictactoe.impl.TicTacToeGameCtrlImpl;
import tictactoe.remote.TicTacToeConnectionImpl;

/**
 *
 * @author tgrun
 */
public class TicTacToeServer implements ConnectionHandler
{
    private TicTacToePlayerCallSide p1;
    
    public static void main(String[] args)
    {
        Server server = new Server(new TicTacToeServer(), 3456);
        server.run();
    }

    public TicTacToeServer()
    {
        p1 = null;
    }

    @Override
    public void handleConnection(SocketConnection conn)
    {
        System.out.println("Handling a new connection");
        if(p1 == null)
        {
            p1 = new TicTacToePlayerCallSide(new TicTacToeConnectionImpl(conn));
            //p1.sendMessage("Connected, waiting for another player to connect...");
        }
        else
        {
            TicTacToePlayerCallSide p2 = new TicTacToePlayerCallSide(new TicTacToeConnectionImpl(conn));
            //p2.sendMessage("Connected, starting game...");
            //p1.sendMessage("Starting game...");
            TicTacToeGameCtrl game = new TicTacToeGameCtrlImpl();
            TicTacToePlayer tmp = p1;
            new Thread(() -> game.playGame(tmp, p2)).start();
            p1 = null;
        }
    }
    
}
