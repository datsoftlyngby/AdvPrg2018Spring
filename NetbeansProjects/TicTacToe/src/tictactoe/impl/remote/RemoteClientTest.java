/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.impl.remote;

import dk.tobiasgrundtvig.util.socket.SocketConnection;
import dk.tobiasgrundtvig.util.socket.impl.SocketConnectionImpl;
import java.io.IOException;
import tictactoe.TicTacToePlayer;
import tictactoe.impl.RandomTicTacToePlayer;
import tictactoe.remote.TicTacToeConnectionImpl;

/**
 *
 * @author tgrun
 */
public class RemoteClientTest
{

    public static void main(String[] args) throws IOException
    {
        System.out.println("Starting client...");
        SocketConnection conn = new SocketConnectionImpl("localhost", 3456);
        TicTacToePlayer player = new RandomTicTacToePlayer();
        TicTacToePlayerImplementationSide cs = 
                new TicTacToePlayerImplementationSide(new TicTacToeConnectionImpl(conn), player);
        cs.run();
        System.out.println("Goodbye!");
    }
}
